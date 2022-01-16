package shadowmaster435.impactfulweather.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BGRendererMixin {
    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;


    @Inject(at = @At("HEAD"), method = "render")
    private static void render(Camera camera, float tickDelta, ClientWorld world, int i2, float f, CallbackInfo info) {
        if (world.isRaining() && world.getBiome(camera.getBlockPos()).getCategory() == Biome.Category.DESERT) {
            if (red != 0.0f && green != 0.0f && blue != 0.0f) {
                float k4 = Math.min(1.0f / red, Math.min(1.0f / green, 1.0f / blue));
                red = (84 / 255f) * k4;
                green = (87 / 255f) * k4;
                blue = (44f / 255f) * k4;
            }
        }
    }
}
