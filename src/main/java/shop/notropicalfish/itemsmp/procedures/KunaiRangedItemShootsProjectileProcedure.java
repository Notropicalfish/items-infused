package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.entity.ThrowingKnifeEntity;

import org.checkerframework.checker.units.qual.radians;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;

public class KunaiRangedItemShootsProjectileProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		Level world = entity.level();
		RandomSource random = entity.getRandom();
		boolean creative = entity instanceof Player _plr && _plr.getAbilities().instabuild;
		if (entity instanceof Player player && player.isShiftKeyDown()) {
			if (!creative)
				itemstack.shrink(2);
			Vec3 look = entity.getLookAngle();
			float angle = 10;
			double radians = Math.toRadians(angle);
			Vec3 leftDir = new Vec3(look.x * Math.cos(radians) - look.z * Math.sin(radians), look.y, look.x * Math.sin(radians) + look.z * Math.cos(radians));
			Vec3 rightDir = new Vec3(look.x * Math.cos(-radians) - look.z * Math.sin(-radians), look.y, look.x * Math.sin(-radians) + look.z * Math.cos(-radians));
			ThrowingKnifeEntity leftKunai = ThrowingKnifeEntity.shoot(player.level(), player, player.getRandom());
			leftKunai.shoot(leftDir.x, leftDir.y, leftDir.z, 2.0f, 0);
			player.level().addFreshEntity(leftKunai);
			ThrowingKnifeEntity rightKunai = ThrowingKnifeEntity.shoot(player.level(), player, player.getRandom());
			rightKunai.shoot(rightDir.x, rightDir.y, rightDir.z, 2.0f, 0);
			player.level().addFreshEntity(rightKunai);
		}
	}
}