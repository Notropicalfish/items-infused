package shop.notropicalfish.itemsmp.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import java.util.WeakHashMap;
import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class SpyThighHighsItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 1);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 6);
			}), 5, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(ItemTags.create(ResourceLocation.parse("minecraft:wool"))), List.of(new ArmorMaterial.Layer(ResourceLocation.parse("itemsmp:empty"))), 0f, 0f);
			registerHelper.register(ResourceLocation.parse("itemsmp:spy_thigh_highs"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	private static final WeakHashMap<Player, Boolean> sneakingInvisible = new WeakHashMap<>();
	private static final int INVISIBILITY_DURATION = 18 * 20;

	@SubscribeEvent
	public static void onLivingTick(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player player))
			return;
		ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
		if (!(leggings.getItem() instanceof SpyThighHighsItem.Leggings)) {
			sneakingInvisible.remove(player);
			return;
		}
		boolean isSneaking = player.isCrouching();
		if (isSneaking) {
			player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 0, false, false, true));
			sneakingInvisible.put(player, true);
		} else {
			sneakingInvisible.put(player, false);
		}
	}

	public SpyThighHighsItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Leggings extends SpyThighHighsItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(10)));
		}
	}
}