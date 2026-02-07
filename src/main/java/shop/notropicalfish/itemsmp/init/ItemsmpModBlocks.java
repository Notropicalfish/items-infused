/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.block.ShorkieBlock;
import shop.notropicalfish.itemsmp.block.CrystalOreBlock;
import shop.notropicalfish.itemsmp.block.BlackhorizernPortalBlock;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

public class ItemsmpModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ItemsmpMod.MODID);
	public static final DeferredBlock<Block> BLACKHORIZERN_PORTAL;
	public static final DeferredBlock<Block> SHORKIE;
	public static final DeferredBlock<Block> CRYSTAL_ORE;
	static {
		BLACKHORIZERN_PORTAL = REGISTRY.register("blackhorizern_portal", BlackhorizernPortalBlock::new);
		SHORKIE = REGISTRY.register("shorkie", ShorkieBlock::new);
		CRYSTAL_ORE = REGISTRY.register("crystal_ore", CrystalOreBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}