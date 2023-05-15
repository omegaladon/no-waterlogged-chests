package me.omega.nwc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NwcInitializer implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("nwc");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("No Waterlogged Chests initialized.");

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockState blockState = world.getBlockState(hitResult.getBlockPos());
			if (blockState.getBlock() == Blocks.CHEST && blockState.get(Properties.WATERLOGGED)) {
				player.sendMessage(Text.literal("&cYou can't open waterlogged chests, silly! Try breaking it instead."), false);
				return ActionResult.FAIL;
			}
			return ActionResult.PASS;
		});
	}
}
