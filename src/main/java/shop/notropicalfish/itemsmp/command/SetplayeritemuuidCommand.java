package shop.notropicalfish.itemsmp.command;

import shop.notropicalfish.itemsmp.init.ItemsmpModGameRules;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

import java.util.Iterator;

@EventBusSubscriber
public class SetplayeritemuuidCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("setplayeritemuuid").requires(source -> source.hasPermission(2))
				.then(Commands.argument("item", ItemArgument.item(event.getBuildContext())).then(Commands.argument("player", EntityArgument.player()).executes(context -> {
					Item item = ItemArgument.getItem(context, "item").getItem();
					ServerPlayer player = EntityArgument.getPlayer(context, "player");
					if (!player.level().getGameRules().getBoolean(ItemsmpModGameRules.BOUND_ITEMS)) {
						context.getSource().sendFailure(Component.literal("The gamerule 'boundItems' is disabled."));
						return 0;
					}
					ProtectedItemData.setProtectedItem(player, item);
					context.getSource().sendSuccess(() -> Component.literal("Protected item " + item.getDescriptionId() + " for player " + player.getName().getString()), true);
					return 1;
				}))));
	}

	public static class ProtectedItemData {
		private static final String NBT_KEY = "ProtectedItem";

		public static void setProtectedItem(ServerPlayer player, Item item) {
			CompoundTag persistentData = player.getPersistentData();
			ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
			persistentData.putString(NBT_KEY, itemId.toString());
		}

		public static Item getProtectedItem(ServerPlayer player) {
			CompoundTag persistentData = player.getPersistentData();
			if (!persistentData.contains(NBT_KEY))
				return null;
			String itemIdString = persistentData.getString(NBT_KEY);
			ResourceLocation itemId = ResourceLocation.tryParse(itemIdString);
			if (itemId == null)
				return null;
			return BuiltInRegistries.ITEM.get(itemId);
		}
	}

	@SubscribeEvent
	public static void onPlayerDrops(LivingDropsEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player))
			return;
		Item protectedItem = ProtectedItemData.getProtectedItem(player);
		if (protectedItem == null)
			return;
		if (!player.level().getGameRules().getBoolean(ItemsmpModGameRules.BOUND_ITEMS)) {
			return;
		}
		CompoundTag data = player.getPersistentData();
		data.remove("ProtectedStacks");
		int index = 0;
		Iterator<ItemEntity> iterator = event.getDrops().iterator();
		while (iterator.hasNext()) {
			ItemEntity drop = iterator.next();
			ItemStack stack = drop.getItem();
			if (stack.getItem() == protectedItem) {
				CompoundTag stackTag = new CompoundTag();
				stack.save(player.server.registryAccess(), stackTag);
				data.put("ProtectedStack_" + index, stackTag);
				index++;
				iterator.remove();
			}
		}
		data.putInt("ProtectedStackCount", index);
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath())
			return;
		ServerPlayer original = (ServerPlayer) event.getOriginal();
		ServerPlayer clone = (ServerPlayer) event.getEntity();
		CompoundTag originalData = original.getPersistentData();
		CompoundTag cloneData = clone.getPersistentData();
		if (!original.level().getGameRules().getBoolean(ItemsmpModGameRules.BOUND_ITEMS)) {
			return;
		}
		if (originalData.contains("ProtectedItem")) {
			cloneData.putString("ProtectedItem", originalData.getString("ProtectedItem"));
		}
		int count = originalData.getInt("ProtectedStackCount");
		for (int i = 0; i < count; i++) {
			CompoundTag stackTag = originalData.getCompound("ProtectedStack_" + i);
			ItemStack stack = ItemStack.parseOptional(clone.server.registryAccess(), stackTag);
			if (!stack.isEmpty()) {
				clone.getInventory().add(stack);
			}
		}
		originalData.remove("ProtectedStackCount");
	}
}