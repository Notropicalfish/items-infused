/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.item.*;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class ItemsmpModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ItemsmpMod.MODID);
	public static final DeferredItem<Item> MAGICCRYSTAL;
	public static final DeferredItem<Item> LUCKY_PICK;
	public static final DeferredItem<Item> AETHER_BLADE;
	public static final DeferredItem<Item> ALL_KNOWING_ORANGE;
	public static final DeferredItem<Item> BLACKHEART;
	public static final DeferredItem<Item> BLACKHORIZERN;
	public static final DeferredItem<Item> SHORKIE;
	static {
		MAGICCRYSTAL = REGISTRY.register("magiccrystal", MagiccrystalItem::new);
		LUCKY_PICK = REGISTRY.register("lucky_pick", LuckyPickItem::new);
		AETHER_BLADE = REGISTRY.register("aether_blade", AetherBladeItem::new);
		ALL_KNOWING_ORANGE = REGISTRY.register("all_knowing_orange", AllKnowingOrangeItem::new);
		BLACKHEART = REGISTRY.register("blackheart", BlackheartItem::new);
		BLACKHORIZERN = REGISTRY.register("blackhorizern", BlackhorizernItem::new);
		SHORKIE = block(ItemsmpModBlocks.SHORKIE, new Item.Properties().stacksTo(1));
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}