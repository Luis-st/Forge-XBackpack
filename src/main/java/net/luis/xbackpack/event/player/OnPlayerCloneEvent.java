package net.luis.xbackpack.event.player;

import net.luis.xbackpack.XBackpack;
import net.luis.xbackpack.world.capability.XBackpackCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 
 * @author Luis-st
 *
 */

@Mod.EventBusSubscriber(modid = XBackpack.MOD_ID)
public class OnPlayerCloneEvent {
	
	@SubscribeEvent
	public static void playerClone(PlayerEvent.Clone event) {
		Player original = event.getOriginal();
		Player player = event.getEntity();
		original.reviveCaps();
		original.getCapability(XBackpackCapabilities.BACKPACK, null).ifPresent(oldBackpack -> {
			player.getCapability(XBackpackCapabilities.BACKPACK, null).ifPresent(newBackpack -> {
				newBackpack.deserialize(oldBackpack.serialize());
			});
		});
		original.invalidateCaps();
	}

}
