package shop.notropicalfish.itemsmp.item;

import shop.notropicalfish.itemsmp.procedures.MagiccrystalItemInHandTickProcedure;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

public class MagiccrystalItem extends Item {
	public MagiccrystalItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			MagiccrystalItemInHandTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
	}
}