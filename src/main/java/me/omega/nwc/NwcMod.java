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

public class NwcMod implements ModInitializer {

	public static final String MOD_ID = "nwc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("No Waterlogged Chests initialized.");

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockState blockState = world.getBlockState(hitResult.getBlockPos());
			if (blockState.getBlock() == Blocks.CHEST && blockState.get(Properties.WATERLOGGED)) {
				player.sendMessage(Text.of("You can't open waterlogged chests, silly! Try breaking it instead."), false);
				return ActionResult.FAIL;
			}
			return ActionResult.PASS;
		});
	}
}
