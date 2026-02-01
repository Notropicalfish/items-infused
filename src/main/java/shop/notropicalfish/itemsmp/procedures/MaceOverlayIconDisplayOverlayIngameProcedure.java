package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class MaceOverlayIconDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemsmpModItems.DELETED_MOD_ELEMENT.get()) {
			return true;
		}
		return false;
	}
}