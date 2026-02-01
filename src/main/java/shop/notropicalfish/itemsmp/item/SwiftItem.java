package shop.notropicalfish.itemsmp.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class SwiftItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;
	private static final String SPRINT_TICKS_TAG = "swiftSprinting";
	private static final ResourceLocation BASE_SPEED_ID = ResourceLocation.fromNamespaceAndPath("itemsmp", "swiftness_base");
	private static final ResourceLocation SPRINT_SPEED_ID = ResourceLocation.fromNamespaceAndPath("itemsmp", "swiftness_sprint");
	private static final int MAX_TICKS = 80;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.LEGGINGS, 0);
				map.put(ArmorItem.Type.CHESTPLATE, 0);
				map.put(ArmorItem.Type.HELMET, 0);
				map.put(ArmorItem.Type.BODY, 0);
			}), 15, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("item.armor.equip_leather")), () -> Ingredient.of(new ItemStack(Items.LAPIS_LAZULI)),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("itemsmp:swift_boots"))), 0f, 0f);
			registerHelper.register(ResourceLocation.parse("itemsmp:swift"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public SwiftItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Boots extends SwiftItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)));
		}

		@Override
		public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
			ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
			super.getDefaultAttributeModifiers(stack).forEach(EquipmentSlotGroup.ARMOR, (attribute, modifier) -> builder.add(attribute, modifier, EquipmentSlotGroup.ARMOR));
			builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_SPEED_ID, 0.10D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.FEET);
			return builder.build();
		}

		@Override
		public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
			if (level.isClientSide)
				return;
			if (!(entity instanceof Player player))
				return;
			if (player.getItemBySlot(EquipmentSlot.FEET) != stack)
				return;
			if (!player.isSprinting()) {
				player.getPersistentData().remove(SPRINT_TICKS_TAG);
				var attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
				if (attr != null)
					attr.removeModifier(SPRINT_SPEED_ID);
				player.setNoGravity(false);
				return;
			}
			CompoundTag data = player.getPersistentData();
			int ticks = Math.min(MAX_TICKS, data.getInt(SPRINT_TICKS_TAG) + 1);
			data.putInt(SPRINT_TICKS_TAG, ticks);
			double progress = ticks / (double) MAX_TICKS;
			double sprintBonus = 0.10D * progress * 4;
			var attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
			if (attr != null) {
				attr.removeModifier(SPRINT_SPEED_ID);
				attr.addTransientModifier(new AttributeModifier(SPRINT_SPEED_ID, sprintBonus, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
			}
			if (ticks >= MAX_TICKS) {
				player.setNoGravity(true);
				player.fallDistance = 0;
			}
		}
	}
}