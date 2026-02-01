package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.init.ItemsmpModItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class MaceOverlayIconDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemsmpModItems.WINGED_MACE.get()) {
			return true;
		}
		return false;
	}
}