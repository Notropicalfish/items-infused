package shop.notropicalfish.itemsmp.entity;

import shop.notropicalfish.itemsmp.init.ItemsmpModItems;
import shop.notropicalfish.itemsmp.init.ItemsmpModEntities;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class ThrowingKnifeEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(ItemsmpModItems.KUNAI.get());
	private int knockback = 0;

	public ThrowingKnifeEntity(EntityType<? extends ThrowingKnifeEntity> type, Level world) {
		super(type, world);
	}

	public ThrowingKnifeEntity(EntityType<? extends ThrowingKnifeEntity> type, double x, double y, double z, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, x, y, z, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
	}

	public ThrowingKnifeEntity(EntityType<? extends ThrowingKnifeEntity> type, LivingEntity entity, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, entity, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
	}

	@Override
	public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.put("item", this.getPickupItemStackOrigin().save(this.registryAccess())); // use getter
		tag.putByte("pickup", (byte) this.pickup.ordinal());
	}

	@Override
	public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("item", 10)) {
			this.setPickupItemStack(ItemStack.parse(this.registryAccess(), tag.getCompound("item")).orElse(this.getDefaultPickupItem())); // use setter
		} else {
			this.setPickupItemStack(this.getDefaultPickupItem());
		}
		this.pickup = Pickup.byOrdinal(tag.getByte("pickup"));
	}

	@Override
	public void setOwner(@Nullable Entity entity) {
		super.setOwner(entity);
		this.pickup = switch (entity) {
			case Player player when this.pickup == Pickup.DISALLOWED -> Pickup.ALLOWED;
			case null, default -> this.pickup;
		};
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(ItemsmpModItems.KUNAI.get());
	}

	public void setKnockback(int knockback) {
		this.knockback = knockback;
	}

	@Override
	protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
		if (knockback > 0.0) {
			double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
			if (vec3.lengthSqr() > 0.0) {
				livingEntity.push(vec3.x, 0.1, vec3.z);
			}
		} else {
			super.doKnockback(livingEntity, damageSource);
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		Entity target = entityHitResult.getEntity();
		Entity owner = this.getOwner();
		DamageSource source = this.damageSources().arrow(this, owner != null ? owner : this);
		double damage = this.getBaseDamage();
		if (this.getWeaponItem() != null && this.level() instanceof ServerLevel serverLevel) {
			damage = EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), target, source, (float) damage);
		}
		target.hurt(source, (float) damage);
		if (target instanceof LivingEntity living) {
			this.doKnockback(living, source);
			if (this.level() instanceof ServerLevel serverLevel) {
				EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, source, this.getWeaponItem());
			}
			living.invulnerableTime = 0;
		}
		this.doPostHurtEffects(target instanceof LivingEntity living ? living : null);
		if (!this.level().isClientSide) {
			this.discard();
		}
	}

	public static ThrowingKnifeEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 0.5f, 4, 0);
	}

	public static ThrowingKnifeEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 0.5f, 4, 0);
	}

	public static ThrowingKnifeEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		ThrowingKnifeEntity entityarrow = new ThrowingKnifeEntity(ItemsmpModEntities.THROWING_KNIFE.get(), entity, world, null);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 4, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(2.0);
		entityarrow.setKnockback(0);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.egg.throw")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ThrowingKnifeEntity shoot(LivingEntity entity, LivingEntity target) {
		ThrowingKnifeEntity entityarrow = new ThrowingKnifeEntity(ItemsmpModEntities.THROWING_KNIFE.get(), entity, entity.level(), null);
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.5f * 4, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(2.0);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.egg.throw")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}