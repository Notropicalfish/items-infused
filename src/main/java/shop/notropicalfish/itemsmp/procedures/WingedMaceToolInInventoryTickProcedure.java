package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.network.ItemsmpModVariables;

import net.minecraft.world.entity.Entity;

public class WingedMaceToolInInventoryTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround() && entity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping) {
			{
				ItemsmpModVariables.PlayerVariables _vars = entity.getData(ItemsmpModVariables.PLAYER_VARIABLES);
				_vars.is_leaping = false;
				_vars.markSyncDirty();
			}
			entity.fallDistance = 0;
		}
	}
}