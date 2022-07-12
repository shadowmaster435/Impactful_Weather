package shadowmaster435.impactfulweather.mixin.client;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.impactfulweather.ImpactfulWeather;
import shadowmaster435.impactfulweather.client.util.ParticleUtil;
import shadowmaster435.impactfulweather.config.ClientConfig;

import java.util.Random;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Unique
    private static final ResourceLocation BLANK_WEATHER_LOCATION = new ResourceLocation(ImpactfulWeather.MOD_ID, "textures/misc/blank.png");

    @Shadow
    private int ticks;
    @Shadow
    private int rainSoundTime;

    @Inject(at = @At("HEAD"), method = "renderLevel")
    private void impactfulweather$renderLevel(PoseStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
       // RenderUtil.RenderLightningBurnMark(matrices, tickDelta);
    }

    @Inject(at = @At("HEAD"), method = "renderSnowAndRain")
    private void impactfulweather$renderSnowAndRain(LightTexture manager, float f, double d, double e, double g, CallbackInfo ci) {
        ParticleUtil.spawnweatherparticles();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void impactfulweather$tick(CallbackInfo ci) {
        ParticleUtil.netherweatherlogic();
    }

    @Inject(method = "renderSnowAndRain", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", shift = At.Shift.AFTER))
    private void impactfulweather$renderSnowAndRain(CallbackInfo ci) {
        if (!ClientConfig.INSTANCE.misc.renderedweather.get()) {
            RenderSystem.setShaderTexture(0, BLANK_WEATHER_LOCATION);
        }
    }

    /**
     * needed to disable block surface rain particles
     *
     * DO NOT use {@link Overwrite}, it makes for very poor mod compatibility, any other mod trying to mixin to the same method will be incompatible with this
     * using {@link Inject} instead has the exact same result, but is perfectly compatible with other mods (injections from other mods will most likely do nothing than, but they won't crash)
     */
    @Inject(method = "tickRain", at = @At("HEAD"), cancellable = true)
    public void impactfulweather$tickRain(Camera camera, CallbackInfo callback) {
        // cancelling callback info at head level makes this the same as Overwrite, shouldn't really matter where this line is place in this method
        callback.cancel();
        Minecraft client = Minecraft.getInstance();
        float f = client.level.getRainLevel(1.0F) / (Minecraft.useFancyGraphics() ? 1.0F : 2.0F);
        if (!(f <= 0.0F)) {
            Random random = new Random((long)this.ticks * 312987231L);
            LevelReader worldView = client.level;
            BlockPos blockPos = new BlockPos(camera.getPosition());
            BlockPos blockPos2 = null;
            int i = (int)(100.0F * f * f) / (client.options.particles().get() == ParticleStatus.DECREASED ? 2 : 1);

            for(int j = 0; j < i; ++j) {
                int k = random.nextInt(21) - 10;
                int l = random.nextInt(21) - 10;
                BlockPos blockPos3 = worldView.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, blockPos.offset(k, 0, l));
                Biome biome = (Biome)worldView.getBiome(blockPos3).value();
                if (blockPos3.getY() > worldView.getMinBuildHeight() && blockPos3.getY() <= blockPos.getY() + 10 && blockPos3.getY() >= blockPos.getY() - 10 && biome.getPrecipitation() == Biome.Precipitation.RAIN && biome.warmEnoughToRain(blockPos3)) {
                    blockPos2 = blockPos3.below();
                    if (client.options.particles().get() == ParticleStatus.MINIMAL) {
                        break;
                    }

                    double d = random.nextDouble();
                    double e = random.nextDouble();
                    BlockState blockState = worldView.getBlockState(blockPos2);
                    FluidState fluidState = worldView.getFluidState(blockPos2);
                    VoxelShape voxelShape = blockState.getCollisionShape(worldView, blockPos2);
                    double g = voxelShape.max(Direction.Axis.Y, d, e);
                    double h = (double)fluidState.getHeight(worldView, blockPos2);
                    double m = Math.max(g, h);
                    ParticleOptions particleEffect = !fluidState.is(FluidTags.LAVA) && !blockState.is(Blocks.MAGMA_BLOCK) && !CampfireBlock.isLitCampfire(blockState) ? ParticleTypes.RAIN : ParticleTypes.SMOKE;
                    if (ClientConfig.INSTANCE.misc.renderedweather.get()) {
                        client.level.addParticle(particleEffect, (double) blockPos2.getX() + d, (double) blockPos2.getY() + m, (double) blockPos2.getZ() + e, 0.0, 0.0, 0.0);
                    }
                }
            }

            if (blockPos2 != null && random.nextInt(3) < this.rainSoundTime++) {
                this.rainSoundTime = 0;
                if (blockPos2.getY() > blockPos.getY() + 1 && worldView.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, blockPos).getY() > Mth.floor((float)blockPos.getY())) {
                    client.level.playLocalSound(blockPos2, SoundEvents.WEATHER_RAIN_ABOVE, SoundSource.WEATHER, 0.1F, 0.5F, false);
                } else {
                    client.level.playLocalSound(blockPos2, SoundEvents.WEATHER_RAIN, SoundSource.WEATHER, 0.2F, 1.0F, false);
                }
            }
        }
    }
}
