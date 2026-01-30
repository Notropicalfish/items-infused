package shop.notropicalfish.itemsmp.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class AllKnowingOrangeRightclickedOnBlockProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String facts = "";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(facts), false);
	}
}