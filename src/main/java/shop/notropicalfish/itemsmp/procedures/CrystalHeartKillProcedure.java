package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.init.ItemsmpModItems;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ItemParticleOption;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalHeartKillProcedure {
	private static int shakeTicks = 0;
	private static double shakeIntensity = 0;
	private static boolean fogActive = false;
	private static float fogStartDistance = 100.0f;
	private static float fogEndDistance = 200.0f;
	private static float fogTargetDistance = 5.0f;
	private static float fogSpeed = 5.0f;

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof Player player) {
			if (hasEntityInInventory(player, new ItemStack(ItemsmpModItems.BLACKHEART.get()))) {
				if (entity instanceof Animal) {
					double kills = player.getPersistentData().getDouble("heartKills") + 1;
					player.getPersistentData().putDouble("heartKills", kills);
					if (!player.level().isClientSide && player.level() instanceof ServerLevel serverLevel) {
						serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(ItemsmpModItems.BLACKHEART.get())), player.getX(), player.getY() + 1.0, player.getZ(), 10, 0.3, 0.5, 0.3, 0.1);
					}
					if (!player.level().isClientSide) {
						player.displayClientMessage(Component.translatable("translation.itemsmp.heart_kill").withStyle(style -> style.withColor(0xFF0000)), true);
					}
					if (kills >= 3 && !player.getPersistentData().getBoolean("heartTitle")) {
						if (player instanceof ServerPlayer serverPlayer) {
							Component titleText = Component.translatable("translation.itemsmp.heart_title").withStyle(style -> style.withColor(TextColor.fromRgb(0xFF0000)));
							serverPlayer.connection.send(new ClientboundSetTitleTextPacket(titleText));
						}
						player.getPersistentData().putBoolean("heartTitle", true);
					}
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}