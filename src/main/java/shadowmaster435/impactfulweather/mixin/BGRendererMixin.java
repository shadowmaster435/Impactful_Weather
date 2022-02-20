package shadowmaster435.impactfulweather.mixin;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.impactfulweather.util.FogUtil;

@Mixin(BackgroundRenderer.class)
public abstract class BGRendererMixin {
    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;

    @Shadow
    public static void clearFog() {
    }

   /* @Inject(at = @At("TAIL"), method = "applyFog")
    private static void render(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        ClientWorld world = MinecraftClient.getInstance().world;

        assert world != null;
        FogUtil.fog(world, camera);
        if (world.isRaining() && world.getBiome(camera.getBlockPos()).getCategory() == Biome.Category.DESERT) {

            Vec3d vec3d = world.getSkyColor(camera.getPos(), MinecraftClient.getInstance().getTickDelta());

            float u = (float)vec3d.x;
            float v;
            if (red != 0.0F && green != 0.0F && blue != 0.0F) {
                v = Math.min(1.0F / red, Math.min(1.0F / green, 1.0F / blue));
                red = FogUtil.r * (1.0F - u) +  v * u;
                green = FogUtil.g * (1.0F - u) +  v * u;
                blue = FogUtil.b * (1.0F - u) +  v * u;
            }

        }

    }*/
}
