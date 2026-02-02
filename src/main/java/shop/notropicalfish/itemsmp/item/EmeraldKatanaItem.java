package shop.notropicalfish.itemsmp.item;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;

public class EmeraldKatanaItem extends SwordItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1029;
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
			return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
		}

		@Override
		public int getEnchantmentValue() {
			return 2;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(Items.EMERALD));
		}
	};

	public EmeraldKatanaItem() {
		super(TOOL_TIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOL_TIER, 6f, -2.2f)));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!player.getCooldowns().isOnCooldown(this)) {
			player.startUsingItem(hand);
			return InteractionResultHolder.success(stack);
		}
		return InteractionResultHolder.fail(stack);
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int count) {
		if (!(entity instanceof Player player))
			return;
		if (count == this.getUseDuration(stack, entity) - 1) {
			double dashStrength = 1;
			double dx = player.getLookAngle().x * dashStrength;
			double dy = player.getLookAngle().y * dashStrength;
			double dz = player.getLookAngle().z * dashStrength;
			player.setDeltaMovement(dx, dy, dz);
			player.hurtMarked = true;
			player.getCooldowns().addCooldown(this, 100);
			AABB dashBox = player.getBoundingBox().inflate(1.5, 1, 1.5);
			world.getEntitiesOfClass(LivingEntity.class, dashBox, e -> e != player).forEach(target -> {
				target.hurt(new DamageSource(world.holderOrThrow(DamageTypes.PLAYER_ATTACK), player), 6);
			});
			if (!world.isClientSide()) {
				world.levelEvent(2001, player.blockPosition(), 0);
			}
			player.stopUsingItem();
		}
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 20;
	}
}