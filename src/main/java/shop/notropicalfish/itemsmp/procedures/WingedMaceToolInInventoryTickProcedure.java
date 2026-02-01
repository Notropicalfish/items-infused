package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class WingedMaceToolInInventoryTickProcedure {
public static void execute(
Entity entity ) {
if (
entity == null ) return ;
if (entity.onGround()&&) {entity.fallDistance = 0;}
}
}