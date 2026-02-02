package shop.notropicalfish.itemsmp.command;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;

@EventBusSubscriber
public class SetplayeritemuuidCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(
				Commands.literal("setplayeritemuuid").requires(src -> src.hasPermission(2)).then(Commands.argument("player", EntityArgument.player()).then(Commands.argument("item", ItemArgument.item(event.getBuildContext())).executes(ctx -> {
					ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
					var item = ItemArgument.getItem(ctx, "item").getItem();
					var worldData = ProtectedItemWorldData.get(player.getLevel());
					worldData.setProtectedItem(player.getUUID().toString(), item);
					ctx.getSource().sendSuccess(() -> Component.literal("Protected item [" + new net.minecraft.world.item.ItemStack(item).getHoverName().getString() + "] for player " + player.getName().getString()), true);
					return 1;
				}))));
	}

	public static class ProtectedItemWorldData extends SavedData {
		private final Map<String, ResourceLocation> playerToItem = new HashMap<>();

		public ProtectedItemWorldData() {
		}

		public void setProtectedItem(String playerUUID, Item item) {
			playerToItem.put(playerUUID, BuiltInRegistries.ITEM.getKey(item));
			setDirty();
		}

		public Item getProtectedItem(String playerUUID) {
			ResourceLocation id = playerToItem.get(playerUUID);
			if (id == null)
				return null;
			return BuiltInRegistries.ITEM.get(id);
		}

		@Override
		public CompoundTag save(CompoundTag tag, HolderLookup.Provider context) {
			ListTag list = new ListTag();
			for (Map.Entry<String, ResourceLocation> entry : playerToItem.entrySet()) {
				CompoundTag playerTag = new CompoundTag();
				playerTag.putString("PlayerUUID", entry.getKey());
				playerTag.putString("Item", entry.getValue().toString());
				list.add(playerTag);
			}
			tag.put("ProtectedItems", list);
			return tag;
		}

		public static ProtectedItemWorldData load(CompoundTag tag, HolderLookup.Provider context) {
			ProtectedItemWorldData data = new ProtectedItemWorldData();
			ListTag list = tag.getList("ProtectedItems", 10);
			for (int i = 0; i < list.size(); i++) {
				CompoundTag playerTag = list.getCompound(i);
				String uuid = playerTag.getString("PlayerUUID");
				ResourceLocation id = ResourceLocation.tryParse(playerTag.getString("Item"));
				if (id != null)
					data.playerToItem.put(uuid, id);
			}
			return data;
		}

		public static ProtectedItemWorldData get(net.minecraft.server.level.ServerLevel world) {
			return world.getDataStorage().computeIfAbsent((tag, context) -> ProtectedItemWorldData.load(tag, context), ProtectedItemWorldData::new, "protected_items_data");
		}
	}

	// Temporary store removed stacks before player respawn
	private static final String TEMP_STACKS_KEY = "ProtectedStacksTemp";

	@SubscribeEvent
	public static void onPlayerDrops(LivingDropsEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player))
			return;
		var worldData = ProtectedItemWorldData.get(player.getLevel());
		var protectedItem = worldData.getProtectedItem(player.getUUID().toString());
		if (protectedItem == null)
			return;
		List<ItemStack> stacksToRestore = new ArrayList<>();
		Iterator<net.minecraft.world.entity.item.ItemEntity> iterator = event.getDrops().iterator();
		while (iterator.hasNext()) {
			var drop = iterator.next();
			ItemStack stack = drop.getItem();
			if (stack.getItem() == protectedItem) {
				stacksToRestore.add(stack.copy()); // save for later
				iterator.remove(); // prevent it from dropping
			}
		}
		if (!stacksToRestore.isEmpty()) {
			// Save to player NBT temporarily for cloning
			var nbt = player.getPersistentData();
			nbt.put(TEMP_STACKS_KEY, net.minecraft.nbt.ListTag.of(stacksToRestore.stream().map(ItemStack::save).toList()));
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath())
			return;
		ServerPlayer original = (ServerPlayer) event.getOriginal();
		ServerPlayer clone = (ServerPlayer) event.getEntity();
		var originalNBT = original.getPersistentData();
		if (!originalNBT.contains(TEMP_STACKS_KEY))
			return;
		var list = originalNBT.getList(TEMP_STACKS_KEY, 10); // 10 = CompoundTag
		for (int i = 0; i < list.size(); i++) {
			ItemStack stack = ItemStack.of(list.getCompound(i));
			if (!stack.isEmpty()) {
				clone.getInventory().add(stack);
			}
		}
		originalNBT.remove(TEMP_STACKS_KEY); // cleanup
	}
}