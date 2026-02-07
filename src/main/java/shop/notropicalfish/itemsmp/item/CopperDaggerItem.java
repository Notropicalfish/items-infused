package shop.notropicalfish.itemsmp.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;

import java.util.WeakHashMap;

public class CopperDaggerItem extends SwordItem {
	private static final WeakHashMap<LivingEntity, Integer> hitCounter = new WeakHashMap<>();
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 312;
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
			return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
		}

		@Override
		public int getEnchantmentValue() {
			return 2;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(Items.COPPER_INGOT));
		}
	};

	public CopperDaggerItem() {
		super(TOOL_TIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOL_TIER, 6f, -2.1f)));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide) {
			// Increase hit count for this mob
			int hits = hitCounter.getOrDefault(target, 0) + 1;
			hitCounter.put(target, hits);
			if (hits >= 3) {
				Level level = attacker.level();
				// Spawn lightning at mob
				LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
				if (lightning != null) {
					lightning.moveTo(target.getX(), target.getY(), target.getZ());
					level.addFreshEntity(lightning);
				}
				// Damage the dagger
				stack.hurtAndBreak(8, attacker, EquipmentSlot.MAINHAND);
				// Reset counter
				hitCounter.put(target, 0);
			}
		}
		return super.hurtEnemy(stack, target, attacker);
	}
}