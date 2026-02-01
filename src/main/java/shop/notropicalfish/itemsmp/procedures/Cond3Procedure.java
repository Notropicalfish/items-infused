package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class Cond3Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("combo") >= 3) {
			return true;
		}
		return false;
	}
}