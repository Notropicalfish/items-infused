package shop.notropicalfish.itemsmp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

public class AllKnowingOrangeEntitySwingsItemProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double joke = 0;
		if (!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(itemstack.getItem()))) {
			joke = Mth.nextInt(RandomSource.create(), 1, 11);
			if (joke == 1) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("You probably think goldfish have a three-second memory. They don\u2019t. They can remember things for months. They just choose to forget you because you\u2019re not that interesting."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 2) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("There are more trees on Earth than there are stars in the Milky Way. Not that you\u2019ve ever looked up from your phone long enough to notice either one"), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 3) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("Lighters were invented before matches. We literally figured out how to make fire in a box before we figured out how to rub a stick. Humanity was peaking; then we ended up with people like you."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 4) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Koalas have the smallest brain-to-body ratio of any mammal. Their brains are also smooth, with no folds. Basically, they have no thoughts, just like you on a Monday morning."),
							false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 5) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A \"butt\" is a real unit of volume (about 477 liters). So, when you say you\u2019re a \"butt-load of annoying,\" you\u2019re being mathematically precise for once."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 6) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A sloth takes two weeks to digest a single meal. They are the only things on the planet that move slower than you do when someone asks you to do the dishes."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 7) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("She wears a size 879 shoe. Even a giant copper lady has a better chance of finding a pair of heels that fit than you do of finding a coherent thought today."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 8) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("A cockroach can live for weeks without its head before it finally dies of starvation. It\u2019s a level of stubborn brainlessness I\u2019m starting to recognize in this conversation."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 9) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("In the 1960s, a researcher found that male turkeys will try to mate with a detatched turkey head on a stick. They aren't picky, which I guess is the same strategy your last three dates used."), false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 10) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(
							"Butterflies taste things with their feet. Imagine having to step on your dinner to know if it\u2019s good. Then again, considering your fashion sense, you\u2019re already used to putting your foot in your mouth."),
							false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (joke == 11) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(
							"Cashews grow on the bottom of a \"Cashew Apple,\" and they have a toxic shell that will give you a chemical burn. They\u2019re basically a snack that hates you. It\u2019s the first thing on this list you actually have something in common with."),
							false);
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			}
		}
	}
}