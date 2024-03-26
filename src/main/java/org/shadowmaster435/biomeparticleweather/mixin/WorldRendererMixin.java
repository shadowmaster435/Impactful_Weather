package org.shadowmaster435.biomeparticleweather.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.client.BiomeParticleWeatherClient;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    
    @Inject(at = @At("HEAD"), method = "renderWeather", cancellable = true) 
    public void renderWeather(LightmapTextureManager manager, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci){
        if (!ParticleSettings.get_bool("vanilla_rain_rendering")) {
            ci.cancel();
        }
    }
    
    @Final
    @Shadow
    private MinecraftClient client;

    @Shadow
    private int rainSoundCounter;


    @Inject(at = @At("HEAD"), method = "tickRainSplashing", cancellable = true)
    public void tickRainSplashing(Camera camera, CallbackInfo ci){
        if (!ParticleSettings.get_bool("vanilla_rain_rendering")) {
            float f = this.client.world.getRainGradient(1.0F) / (MinecraftClient.isFancyGraphicsOrBetter() ? 1.0F : 2.0F);
            if (!(f <= 0.0F)) {
                Random random = Random.createLocal();
                WorldView worldView = this.client.world;
                BlockPos blockPos = BlockPos.ofFloored(camera.getPos());
                BlockPos blockPos2 = null;
                int i = (int)(100.0F * f * f) / (this.client.options.getParticles().getValue() == ParticlesMode.DECREASED ? 2 : 1);

                for(int j = 0; j < i; ++j) {
                    int k = random.nextInt(21) - 10;
                    int l = random.nextInt(21) - 10;
                    BlockPos blockPos3 = worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos.add(k, 0, l));
                    if (blockPos3.getY() > worldView.getBottomY() && blockPos3.getY() <= blockPos.getY() + 10 && blockPos3.getY() >= blockPos.getY() - 10) {
                        Biome biome = worldView.getBiome(blockPos3).value();
                        if (biome.getPrecipitation(blockPos3) == Biome.Precipitation.RAIN) {
                            blockPos2 = blockPos3.down();
                            if (this.client.options.getParticles().getValue() == ParticlesMode.MINIMAL || ParticleSettings.get_bool("disable_vanilla_rain_splash")) {
                                break;
                            }

                            double d = random.nextDouble();
                            double e = random.nextDouble();
                            BlockState blockState = worldView.getBlockState(blockPos2);
                            FluidState fluidState = worldView.getFluidState(blockPos2);
                            VoxelShape voxelShape = blockState.getCollisionShape(worldView, blockPos2);
                            double g = voxelShape.getEndingCoord(Direction.Axis.Y, d, e);
                            double h = fluidState.getHeight(worldView, blockPos2);
                            double m = Math.max(g, h);
                            ParticleEffect particleEffect = !fluidState.isIn(FluidTags.LAVA) && !blockState.isOf(Blocks.MAGMA_BLOCK) && !CampfireBlock.isLitCampfire(blockState) ? ParticleTypes.RAIN : ParticleTypes.SMOKE;
                            if (ParticleSettings.get_bool("vanilla_rain_splash")) {
                                this.client.world.addParticle(particleEffect, (double)blockPos2.getX() + d, (double)blockPos2.getY() + m, (double)blockPos2.getZ() + e, 0.0, 0.0, 0.0);

                            }
                        }
                    }
                }

                if (blockPos2 != null && random.nextInt(3) < this.rainSoundCounter++) {
                    this.rainSoundCounter = 0;
                    if (blockPos2.getY() > blockPos.getY() + 1 && worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos).getY() > MathHelper.floor((float)blockPos.getY())) {
                        this.client.world.playSoundAtBlockCenter(blockPos2, SoundEvents.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.1F, 0.5F, false);
                    } else {
                        this.client.world.playSoundAtBlockCenter(blockPos2, SoundEvents.WEATHER_RAIN, SoundCategory.WEATHER, 0.2F, 1.0F, false);
                    }
                }

            }
            ci.cancel();
        }

    }

}
