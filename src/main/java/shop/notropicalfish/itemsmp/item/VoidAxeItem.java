package shop.notropicalfish.itemsmp.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class VoidAxeItem extends AxeItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1443;
		}

		@Override
		public float getSpeed() {
			return 8f;
		}

		@Override
		public float getAttackDamageBonus() {
			return 0;
		}

		@Override
		public TagKey<Block> getIncorrectBlocksForDrops() {
			return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
		}

		@Override
		public int getEnchantmentValue() {
			return 2;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(Blocks.OBSIDIAN));
		}
	};

	public VoidAxeItem() {
		super(TOOL_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(TOOL_TIER, 6f, -3f)).fireResistant());
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		boolean result = super.mineBlock(stack, level, state, pos, entity);
		if (level.isClientSide)
			return result;
		if (!(entity instanceof Player player))
			return result;
		if (!player.isShiftKeyDown())
			return result;
		if (!state.is(BlockTags.LOGS))
			return result;
		breakConnectedLogs(level, pos, player, stack);
		return result;
	}

	private void breakConnectedLogs(Level level, BlockPos start, Player player, ItemStack tool) {
		HashSet<BlockPos> visited = new HashSet<>();
		Queue<BlockPos> queue = new LinkedList<>();
		queue.add(start);
		visited.add(start);
		while (!queue.isEmpty()) {
			BlockPos current = queue.poll();
			BlockState state = level.getBlockState(current);
			if (!state.is(BlockTags.LOGS))
				continue;
			level.destroyBlock(current, true, player);
			tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			if (tool.isEmpty())
				return;
			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					for (int dz = -1; dz <= 1; dz++) {
						BlockPos next = current.offset(dx, dy, dz);
						if (!visited.contains(next) && level.getBlockState(next).is(BlockTags.LOGS)) {
							visited.add(next);
							queue.add(next);
						}
					}
				}
			}
		}
	}
}