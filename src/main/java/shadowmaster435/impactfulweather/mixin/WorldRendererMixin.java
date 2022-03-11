package shadowmaster435.impactfulweather.mixin;


import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.impactfulweather.util.ParticleUtil;

import java.util.Random;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private int ticks;

    @Shadow
    private int field_20793;

    /**
     * @author shadowmaster435
     */
    @Inject(at = @At("HEAD"), method = "renderWeather")
    private void renderweather(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo ci) {
        ParticleUtil.spawnweatherparticles();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        ParticleUtil.netherweatherlogic();
    }
    /**
     * @author shadowmaster435
     * @reason needed to disable block surface rain particles
     */

    @Overwrite
    public void tickRainSplashing(Camera camera) {
        MinecraftClient instance = MinecraftClient.getInstance();

        assert instance.world != null;
        float f = instance.world.getRainGradient(1.0f) / (MinecraftClient.isFancyGraphicsOrBetter() ? 1.0f : 2.0f);
        if (f <= 0.0f) {
            return;
        }

        Random random = new Random((long)this.ticks * 312987231L);
        ClientWorld worldView = instance.world;
        BlockPos blockPos = new BlockPos(camera.getPos());
        BlockPos blockPos2 = null;
        assert instance.player != null;
        BlockPos playerpos = instance.player.getBlockPos();
        int i = (int)(100.0f * f * f) / (instance.options.particles == ParticlesMode.DECREASED ? 2 : 1);
        for (int j = 0; j < i; ++j) {
            int k = random.nextInt(21) - 10;
            int l = random.nextInt(21) - 10;
            BlockPos blockPos3 = worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos.add(k, 0, l));
            Biome biome = worldView.getBiome(blockPos3).value();
            if (blockPos3.getY() <= worldView.getBottomY() || blockPos3.getY() > blockPos.getY() + 10 || blockPos3.getY() < blockPos.getY() - 10 || biome.getPrecipitation() != Biome.Precipitation.RAIN || !biome.doesNotSnow(blockPos3)) continue;
            blockPos2 = blockPos3.down();
            if (instance.options.particles == ParticlesMode.MINIMAL) break;
        }
        if (blockPos2 != null && random.nextInt(3) < this.field_20793++) {
            this.field_20793 = 0;
            if (instance.world.getBiome(blockPos).value().getPrecipitation() == Biome.Precipitation.RAIN) {

                if (blockPos2.getY() > blockPos.getY() + 1 && worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos).getY() > MathHelper.floor(blockPos.getY())) {
                    instance.world.playSound(playerpos, SoundEvents.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.1f, 0.5f, false);
                } else {
                    instance.world.playSound(playerpos, SoundEvents.WEATHER_RAIN, SoundCategory.WEATHER, 0.2f, 1.0f, false);
                }
            }
        }
    }
}
