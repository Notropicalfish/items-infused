package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.network.ItemsmpModVariables;

import net.minecraft.world.entity.Entity;

public class ZoomOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping;
	}
}