package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class TestideaProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.MACE) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) (6.5 + (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) * 0.4));
			sourceentity.fallDistance = 0;
		}
	}
}