package shadowmaster435.impactfulweather.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.biome.BiomeKeys;

public class FogUtil {

    public static void applyFog(GameRenderer gameRenderer, ClientWorld world, Camera camera) {
        float g = gameRenderer.getViewDistance();
        Profiler profiler = world.getProfiler();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        assert MinecraftClient.getInstance().player != null;
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
        if (world.getBiome(pos).getKey().isPresent() && world.getBiome(pos).getKey().get().equals(BiomeKeys.DESERT) && world.isRaining() && world.isSkyVisible(pos)) {
            BackgroundRenderer.setFogBlack();

            RenderSystem.setShaderFogColor(200, 190, 120);
            profiler.swap("fog");
            BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_TERRAIN, Math.max(g, 32.0F), false);
            profiler.swap("sky");
            RenderSystem.setShaderFogColor(170, 160, 110);

        }
    }

}
