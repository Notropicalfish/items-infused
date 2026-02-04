package shop.notropicalfish.itemsmp.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class VoidPickaxeItem extends PickaxeItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1735;
		}

		@Override
		public float getSpeed() {
			return 4f;
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
			return 12;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(Blocks.OBSIDIAN));
		}
	};

	public VoidPickaxeItem() {
		super(TOOL_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(TOOL_TIER, 3f, -2.8f)).fireResistant());
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	private static final TagKey<Block> VEINMINE_ORES = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		boolean result = super.mineBlock(stack, level, state, pos, entity);
		if (level.isClientSide)
			return result;
		if (!(entity instanceof Player player))
			return result;
		if (!player.isShiftKeyDown())
			return result;
		if (!state.is(VEINMINE_ORES))
			return result;
		breakConnectedOres(level, pos, player, stack);
		return result;
	}

	private void breakConnectedOres(Level level, BlockPos start, Player player, ItemStack tool) {
		HashSet<BlockPos> visited = new HashSet<>();
		Queue<BlockPos> queue = new LinkedList<>();
		visited.add(start);
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				for (int dz = -1; dz <= 1; dz++) {
					BlockPos next = start.offset(dx, dy, dz);
					if (!visited.contains(next) && level.getBlockState(next).is(VEINMINE_ORES)) {
						visited.add(next);
						queue.add(next);
					}
				}
			}
		}
		while (!queue.isEmpty()) {
			BlockPos current = queue.poll();
			BlockState state = level.getBlockState(current);
			if (!state.is(VEINMINE_ORES))
				continue;
			level.setBlock(current, Blocks.AIR.defaultBlockState(), 3);
			state.getBlock().playerDestroy(level, player, current, state, null, tool);
			tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			if (tool.isEmpty())
				return;
			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					for (int dz = -1; dz <= 1; dz++) {
						BlockPos next = current.offset(dx, dy, dz);
						if (!visited.contains(next) && level.getBlockState(next).is(VEINMINE_ORES)) {
							visited.add(next);
							queue.add(next);
						}
					}
				}
			}
		}
	}
}