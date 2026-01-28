/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.item.MagiccrystalItem;
import shop.notropicalfish.itemsmp.item.LuckyPickItem;
import shop.notropicalfish.itemsmp.item.AetherBladeItem;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;

public class ItemsmpModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ItemsmpMod.MODID);
	public static final DeferredItem<Item> MAGICCRYSTAL;
	public static final DeferredItem<Item> LUCKY_PICK;
	public static final DeferredItem<Item> AETHER_BLADE;
	static {
		MAGICCRYSTAL = REGISTRY.register("magiccrystal", MagiccrystalItem::new);
		LUCKY_PICK = REGISTRY.register("lucky_pick", LuckyPickItem::new);
		AETHER_BLADE = REGISTRY.register("aether_blade", AetherBladeItem::new);
	}
	// Start of user code block custom items
	// End of user code block custom items
}