package shop.notropicalfish.itemsmp.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.phys.Vec3;
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
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class MinecartItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;
	private static final ResourceLocation BASE_SPEED_ID = ResourceLocation.fromNamespaceAndPath("itemsmp", "swiftness_base");

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 3);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 6);
			}), 11, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("item.armor.equip_iron")), () -> Ingredient.of(new ItemStack(Items.IRON_INGOT)),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("itemsmp:minecart_boots"))), 0f, 0f);
			registerHelper.register(ResourceLocation.parse("itemsmp:minecart"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	private static final double SPEED_MULTIPLIER = 0.5;

	@SubscribeEvent
	public static void onLivingTick(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player player))
			return;
		if (!player.isCrouching())
			return;
		ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
		if (!(boots.getItem() instanceof MinecartItem.Boots))
			return;
		Vec3 look = player.getLookAngle();
		Vec3 horizontalLook = new Vec3(look.x, 0, look.z).normalize().scale(SPEED_MULTIPLIER);
		Vec3 currentMotion = player.getDeltaMovement();
		Vec3 newMotion = new Vec3(horizontalLook.x, currentMotion.y, horizontalLook.z);
		player.setDeltaMovement(newMotion);
		player.hurtMarked = true;
	}

	public MinecartItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Boots extends MinecartItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)));
		}

		@Override
		public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
			ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
			super.getDefaultAttributeModifiers(stack).forEach(EquipmentSlotGroup.ARMOR, (attribute, modifier) -> builder.add(attribute, modifier, EquipmentSlotGroup.ARMOR));
			builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_SPEED_ID, 0.25D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.FEET);
			return builder.build();
		}
	}
}