package shadowmaster435.impactfulweather.client.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.biome.Biomes;

public class FogUtil {

    public static void applyFog(GameRenderer gameRenderer, ClientLevel world, Camera camera) {
        float g = gameRenderer.getRenderDistance();
        ProfilerFiller profiler = world.getProfiler();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        assert Minecraft.getInstance().player != null;
        BlockPos pos = Minecraft.getInstance().player.blockPosition();
        if (world.getBiome(pos).unwrapKey().isPresent() && world.getBiome(pos).unwrapKey().get().equals(Biomes.DESERT) && world.isRaining() && world.canSeeSky(pos)) {
            FogRenderer.levelFogColor();

            RenderSystem.setShaderFogColor(200, 190, 120);
            profiler.popPush("fog");
//            BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_TERRAIN, Math.max(g, 32.0F), false);
            profiler.popPush("sky");
            RenderSystem.setShaderFogColor(170, 160, 110);

        }
    }

}
