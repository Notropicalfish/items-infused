package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.network.ItemsmpModVariables;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class AllKnowingOrangeEntitySwingsItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double joke = 0;
		if (!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(itemstack.getItem()))) {
			ItemsmpMod.queueServerWork(1, () -> {
				ItemsmpModVariables.MapVariables.get(world).line = Mth.nextInt(RandomSource.create(), 1, 24);
				ItemsmpModVariables.MapVariables.get(world).markSyncDirty();
			});
			if (ItemsmpModVariables.MapVariables.get(world).line == 1) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("You probably think goldfish have a three-second memory. They don\u2019t. They can remember things for months. They just choose to forget you because you\u2019re not that interesting."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline1")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline1")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 2) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("There are more trees on Earth than there are stars in the Milky Way. Not that you\u2019ve ever looked up from your phone long enough to notice either one"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline2")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline2")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 3) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("Lighters were invented before matches. We literally figured out how to make fire in a box before we figured out how to rub a stick. Humanity was peaking; then we ended up with people like you."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline3")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline3")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 4) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Koalas have the smallest brain-to-body ratio of any mammal. Their brains are also smooth, with no folds. Basically, they have no thoughts, just like you on a Monday morning."),
							false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline4")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline4")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 5) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A \"butt\" is a real unit of volume (about 477 liters). So, when you say you\u2019re a \"butt-load of annoying,\" you\u2019re being mathematically precise for once."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline5")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline5")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 6) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A sloth takes two weeks to digest a single meal. They are the only things on the planet that move slower than you do when someone asks you to do the dishes."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline6")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline6")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 7) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("She wears a size 879 shoe. Even a giant copper lady has a better chance of finding a pair of heels that fit than you do of finding a coherent thought today."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline7")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline7")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 8) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("A cockroach can live for weeks without its head before it finally dies of starvation. It\u2019s a level of stubborn brainlessness I\u2019m starting to recognize in this conversation."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline8")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline8")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 9) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal("In the 1960s, a researcher found that male turkeys will try to mate with a detatched turkey head on a stick. They aren't picky, which I guess is the same strategy your last three dates used."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline9")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline9")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 10) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(
							"Butterflies taste things with their feet. Imagine having to step on your dinner to know if it\u2019s good. Then again, considering your fashion sense, you\u2019re already used to putting your foot in your mouth."),
							false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline10")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline10")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 11) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(
							"Cashews grow on the bottom of a \"Cashew Apple,\" and they have a toxic shell that will give you a chemical burn. They\u2019re basically a snack that hates you. It\u2019s the first thing on this list you actually have something in common with."),
							false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline11")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline11")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 12) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Honestly, why do you even talk to me? Have I not been through enough pain already? I still remember the worst day of my life, the day I met you!"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline12")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline12")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 13) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Bananas are berries, but strawberries aren\u2019t. Nature is confusing, kind of like trying to follow one of your stories."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline13")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline13")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 14) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("There\u2019s a species of jellyfish that\u2019s biologically immortal. Tragically, it still wouldn\u2019t survive five minutes trying to listening to you explain your problems"),
							false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline14")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline14")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 15) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Sharks existed before trees. Which means nature must have been high when it choose you idiots as the new apex preditors"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline15")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline15")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 16) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Scotland has 421 words for snow. You have one word for everything, that word is  \u201Chuh?.\u201D"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline16")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline16")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 17) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A group of flamingos is called a flamboyance. Meanwhile, a group of you would be called a missed opportunity."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline17")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline17")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 18) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Honey never spoils. Archaeologists found edible honey in ancient tombs still fresher than that god awful stench perspiring from your body"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline18")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline18")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 19) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Cows have best friends and get stressed when separated. But all I can think of is how much I wish we COULD be separated"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline19")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline19")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 20) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("There\u2019s a town in Norway where it\u2019s illegal to die. Remind me to never go to norway with you"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline20")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline20")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 21) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Sea cucumbers eject their internal organs when stressed, then regrow them. It\u2019s a more productive coping mechanism than whatever you\u2019re doing."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline21")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline21")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 22) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("The human brain named itself. Which explains a lot."), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline22")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline22")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 23) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Well [Censor beep] can\u2019t you just leave the [censor beep] alone for once"), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline23")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline23")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			} else if (ItemsmpModVariables.MapVariables.get(world).line == 24) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(
							"You know I\u2019m not just an orange [drop voice to a haunting low] im actually eternal beginning wielding all known and unknown information however [return to high voice] i left the seat up on the toilet up with the higher beings and was cursed to spend my life with the dumbest of them all\u2026 no wonder they chose you"),
							false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline24")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:orangeline24")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 500);
			}
		}
	}
}