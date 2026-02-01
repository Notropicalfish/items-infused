package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class WingedMaceToolInInventoryTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround() || entity.isInWater()) {
			{
				ItemsmpModVariables.PlayerVariables _vars = entity.getData(ItemsmpModVariables.PLAYER_VARIABLES);
				_vars.is_leaping = false;
				_vars.markSyncDirty();
			}
		}
		if (entity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping == true) {
			entity.fallDistance = 0;
		}
	}
}