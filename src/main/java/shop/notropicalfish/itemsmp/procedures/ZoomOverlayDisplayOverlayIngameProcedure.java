package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class ZoomOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping;
	}
}