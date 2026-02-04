/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package shop.notropicalfish.itemsmp.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.GameRules;

@EventBusSubscriber
public class ItemsmpModGameRules {
	public static GameRules.Key<GameRules.BooleanValue> BOUND_ITEMS;

	@SubscribeEvent
	public static void registerGameRules(FMLCommonSetupEvent event) {
		BOUND_ITEMS = GameRules.register("boundItems", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
	}
}