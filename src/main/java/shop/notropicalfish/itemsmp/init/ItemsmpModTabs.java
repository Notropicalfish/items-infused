/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class ItemsmpModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItemsmpMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEM_SMP = REGISTRY.register("item_smp",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.itemsmp.item_smp")).icon(() -> new ItemStack(Items.AMETHYST_SHARD)).displayItems((parameters, tabData) -> {
				tabData.accept(ItemsmpModItems.MAGICCRYSTAL.get());
				tabData.accept(ItemsmpModItems.LUCKY_PICK.get());
			}).build());
}