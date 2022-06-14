package shadowmaster435.impactfulweather.mixin;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.impactfulweather.init.IWParticles;
import shadowmaster435.impactfulweather.util.ParticleUtil;
import shadowmaster435.impactfulweather.util.RenderUtil;

import java.util.Random;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow
    private int ticks;

    @Shadow
    private int rainSoundCounter;

    @Shadow @Nullable private ClientWorld world;


    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
       // RenderUtil.RenderLightningBurnMark(matrices, tickDelta);
    }



    @Inject(at = @At("HEAD"), method = "renderWeather")
    private void renderweather(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo ci) {
        ParticleUtil.spawnweatherparticles();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        ParticleUtil.netherweatherlogic();
    }

    @Inject(method = "renderWeather", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V", shift = At.Shift.AFTER))
    private void injected(CallbackInfo ci) {
        if (!ParticleUtil.config.misc.renderedweather) {
            final Identifier BLANKWEATHER = new Identifier("impactfulweather:textures/misc/blank.png");
            RenderSystem.setShaderTexture(0, BLANKWEATHER);
        }
    }


    /**
     * @author shadowmaster435
     * @reason needed to disable block surface rain particles
     */
    @Overwrite
    public void tickRainSplashing(Camera camera) {
        MinecraftClient client = MinecraftClient.getInstance();
        float f = client.world.getRainGradient(1.0F) / (MinecraftClient.isFancyGraphicsOrBetter() ? 1.0F : 2.0F);
        if (!(f <= 0.0F)) {
            Random random = new Random((long)this.ticks * 312987231L);
            WorldView worldView = client.world;
            BlockPos blockPos = new BlockPos(camera.getPos());
            BlockPos blockPos2 = null;
            int i = (int)(100.0F * f * f) / (client.options.getParticles().getValue() == ParticlesMode.DECREASED ? 2 : 1);

            for(int j = 0; j < i; ++j) {
                int k = random.nextInt(21) - 10;
                int l = random.nextInt(21) - 10;
                BlockPos blockPos3 = worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos.add(k, 0, l));
                Biome biome = (Biome)worldView.getBiome(blockPos3).value();
                if (blockPos3.getY() > worldView.getBottomY() && blockPos3.getY() <= blockPos.getY() + 10 && blockPos3.getY() >= blockPos.getY() - 10 && biome.getPrecipitation() == Biome.Precipitation.RAIN && biome.doesNotSnow(blockPos3)) {
                    blockPos2 = blockPos3.down();
                    if (client.options.getParticles().getValue() == ParticlesMode.MINIMAL) {
                        break;
                    }

                    double d = random.nextDouble();
                    double e = random.nextDouble();
                    BlockState blockState = worldView.getBlockState(blockPos2);
                    FluidState fluidState = worldView.getFluidState(blockPos2);
                    VoxelShape voxelShape = blockState.getCollisionShape(worldView, blockPos2);
                    double g = voxelShape.getEndingCoord(Direction.Axis.Y, d, e);
                    double h = (double)fluidState.getHeight(worldView, blockPos2);
                    double m = Math.max(g, h);
                    ParticleEffect particleEffect = !fluidState.isIn(FluidTags.LAVA) && !blockState.isOf(Blocks.MAGMA_BLOCK) && !CampfireBlock.isLitCampfire(blockState) ? ParticleTypes.RAIN : ParticleTypes.SMOKE;
                    if (ParticleUtil.config.misc.renderedweather) {
                        client.world.addParticle(particleEffect, (double) blockPos2.getX() + d, (double) blockPos2.getY() + m, (double) blockPos2.getZ() + e, 0.0, 0.0, 0.0);
                    }
                }
            }

            if (blockPos2 != null && random.nextInt(3) < this.rainSoundCounter++) {
                this.rainSoundCounter = 0;
                if (blockPos2.getY() > blockPos.getY() + 1 && worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos).getY() > MathHelper.floor((float)blockPos.getY())) {
                    client.world.playSound(blockPos2, SoundEvents.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.1F, 0.5F, false);
                } else {
                    client.world.playSound(blockPos2, SoundEvents.WEATHER_RAIN, SoundCategory.WEATHER, 0.2F, 1.0F, false);
                }
            }

        }
    }
}
