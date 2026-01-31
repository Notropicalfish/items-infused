/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.block.BlackhorizernPortalBlock;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

public class ItemsmpModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ItemsmpMod.MODID);
	public static final DeferredBlock<Block> BLACKHORIZERN_PORTAL;
	static {
		BLACKHORIZERN_PORTAL = REGISTRY.register("blackhorizern_portal", BlackhorizernPortalBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}