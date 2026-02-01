package shop.notropicalfish.itemsmp.item;

import shop.notropicalfish.itemsmp.procedures.WingedMaceToolInInventoryTickProcedure;
import shop.notropicalfish.itemsmp.procedures.WingedMaceRightclickedProcedure;
import shop.notropicalfish.itemsmp.procedures.WingedMaceLivingEntityIsHitWithToolProcedure;
import shop.notropicalfish.itemsmp.init.ItemsmpModItems;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.network.chat.Component;

import java.util.List;

public class WingedMaceItem extends AxeItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 312;
		}

		@Override
		public float getSpeed() {
			return 6f;
		}

		@Override
		public float getAttackDamageBonus() {
			return 0;
		}

		@Override
		public TagKey<Block> getIncorrectBlocksForDrops() {
			return BlockTags.INCORRECT_FOR_STONE_TOOL;
		}

		@Override
		public int getEnchantmentValue() {
			return 4;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(ItemsmpModItems.MAGICCRYSTAL.get()));
		}
	};

	public WingedMaceItem() {
		super(TOOL_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(TOOL_TIER, 4.5f, -2.8f)));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		WingedMaceLivingEntityIsHitWithToolProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, sourceentity, itemstack);
		return retval;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		WingedMaceRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
		return ar;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.itemsmp.winged_mace.description_0"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_1"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_2"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_3"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_4"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_5"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_6"));
		list.add(Component.translatable("item.itemsmp.winged_mace.description_7"));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		WingedMaceToolInInventoryTickProcedure.execute(entity);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}