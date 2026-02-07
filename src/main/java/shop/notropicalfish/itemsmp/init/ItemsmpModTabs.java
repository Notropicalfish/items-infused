/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class ItemsmpModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItemsmpMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEM_SMP = REGISTRY.register("item_smp",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.itemsmp.item_smp")).icon(() -> new ItemStack(ItemsmpModItems.CRYSTAL_HEART.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ItemsmpModItems.MAGICCRYSTAL.get());
				tabData.accept(ItemsmpModItems.LUCKY_PICK.get());
				tabData.accept(ItemsmpModItems.AETHER_BLADE.get());
				tabData.accept(ItemsmpModItems.ALL_KNOWING_ORANGE.get());
				tabData.accept(ItemsmpModItems.BLACKHEART.get());
				tabData.accept(ItemsmpModItems.BLACKHORIZERN.get());
				tabData.accept(ItemsmpModBlocks.SHORKIE.get().asItem());
				tabData.accept(ItemsmpModItems.SWIFT_BOOTS.get());
				tabData.accept(ItemsmpModItems.CRYSTAL_HEART.get());
				tabData.accept(ItemsmpModItems.WINGED_MACE.get());
				tabData.accept(ItemsmpModItems.KUNAI.get());
				tabData.accept(ItemsmpModItems.WITHER_BLADE.get());
				tabData.accept(ItemsmpModItems.EMERALD_KATANA.get());
				tabData.accept(ItemsmpModItems.VOID_AXE.get());
				tabData.accept(ItemsmpModItems.VOID_PICKAXE.get());
				tabData.accept(ItemsmpModItems.COPPER_DAGGER.get());
				tabData.accept(ItemsmpModItems.MINECART_BOOTS.get());
			}).build());
}