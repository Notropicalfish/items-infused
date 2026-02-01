package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.init.ItemsmpModItems;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalHeartKillProcedure {
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
					if (!player.level().isClientSide && kills < 3) {
						player.displayClientMessage(Component.translatable("translation.itemsmp.heart_kill").withStyle(style -> style.withColor(0xFF0000)), true);
					}
					if (kills >= 3 && !player.getPersistentData().getBoolean("heartTitle")) {
						if (player instanceof ServerPlayer serverPlayer) {
							Component titleText = Component.translatable("translation.itemsmp.heart_title").withStyle(style -> style.withColor(TextColor.fromRgb(0xFF0000)));
							serverPlayer.connection.send(new ClientboundSetTitleTextPacket(titleText));
							serverPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120, 5, false, true));
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

	@SubscribeEvent
	public static void onItemTick(EntityTickEvent.Post event) {
		if (!(event.getEntity() instanceof ItemEntity itemEntity))
			return;
		Level world = itemEntity.level();
		ItemStack stack = itemEntity.getItem();
		if (stack.is(ItemsmpModItems.CRYSTAL_HEART.get())) {
			BlockPos pos = itemEntity.blockPosition();
			if (world.getBlockState(pos).is(Blocks.FIRE)) {
				if (!world.isClientSide) {
					world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					itemEntity.setItem(new ItemStack(ItemsmpModItems.BLACKHEART.get(), stack.getCount()));
					world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
				}
				if (world.isClientSide) {
					double width = itemEntity.getBbWidth();
					double height = itemEntity.getBbHeight();
					for (int i = 0; i < 3; i++) {
						double offsetX = (Math.random() - 0.5) * width * 1.2d;
						double offsetY = Math.random() * height * 1.2d;
						double offsetZ = (Math.random() - 0.5) * width * 1.2d;
						world.addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, itemEntity.getX() + offsetX, itemEntity.getY() + offsetY, itemEntity.getZ() + offsetZ, 0.0, 0.0, 0.0);
					}
				}
			}
		}
	}
}