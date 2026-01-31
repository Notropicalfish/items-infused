package shop.notropicalfish.itemsmp.command;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

@EventBusSubscriber
public class SetplayeritemuuidCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		if (event.getCommandSelection() == Commands.CommandSelection.DEDICATED)
			event.getDispatcher().register(Commands.literal("setplayeritemuuid").requires(s -> s.hasPermission(2)).then(Commands.argument("name", ItemArgument.item(event.getBuildContext())).then(Commands.argument("name", EntityArgument.player()))));
	}

}