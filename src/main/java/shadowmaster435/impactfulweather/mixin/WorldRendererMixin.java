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
import shadowmaster435.impactfulweather.client.BPWModConfig;
import shadowmaster435.impactfulweather.init.IWParticles;
import shadowmaster435.impactfulweather.particles.HeavyRain;
import shadowmaster435.impactfulweather.particles.Rain;

import java.util.Random;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private int ticks;

    @Shadow
    private int field_20793;


    @Shadow @Final private  MinecraftClient client;

    @Shadow @Nullable private ClientWorld world;

    /**
     * @author shadowmaster435
     */
    @Inject(at = @At("HEAD"), method = "renderWeather")
    private void renderweather(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo ci) {
        MinecraftClient instance2 = MinecraftClient.getInstance();
        PlayerEntity player2 = instance2.player;
        World world2 = instance2.world;
        assert world2 != null;
        assert player2 != null;
        BlockPos pos = player2.getBlockPos();
        BPWModConfig config = AutoConfig.getConfigHolder(BPWModConfig.class).getConfig();

        Random random = instance2.world.random;
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        double x = (double)i + random.nextDouble();
        double y = (double)j + random.nextDouble();
        double z = (double)k + random.nextDouble();

        for (double hj = 0; hj < Math.ceil((double) config.particleamount * ((config.particleDensity * 0.03125))); hj++) {
            if ((world2.isRaining() || world2.isThundering()) && !client.isPaused()) {
                for (int l = 0; l < 8; ++l) {
                    if (Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.MESA) || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.DESERT)) {
                        if (Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.MESA)) {
                            world2.addParticle(IWParticles.REDSANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                        } else {
                            world2.addParticle(IWParticles.SANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                        }
                        if (Math.random() < 0.0075) {
                            if (Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.DESERT)) {
                                world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 110), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            } else {
                                world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 76), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        }
                    }
                    if (Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.SAVANNA)) {
                        world2.addParticle(IWParticles.GUST, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                    }
                    if (world2.getBiome(player2.getBlockPos()).value().getPrecipitation() == Biome.Precipitation.RAIN) {
                        if (config.dodenserain) {
                            for (int am = 0; am < Rain.rainamount; ++am) {
                                if (world2.isThundering() || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.JUNGLE)) {
                                    world2.addParticle(IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                } else {
                                    world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                            }
                        } else {
                            if (world2.isThundering() || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.JUNGLE)) {
                                world2.addParticle(IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            } else {
                                world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        }
                    }
                    if (
                            world2.getBiome(player2.getBlockPos()).value().getPrecipitation() == Biome.Precipitation.SNOW) {
                        if (world2.isThundering()) {
                            if (Math.random() < 0.75) {
                                if (config.dodenserain) {
                                    for (int ikl = 0; ikl < 2; ++ikl) {
                                        world2.addParticle(IWParticles.BLIZZARDSNOW, MathHelper.lerp(world2.random.nextDouble(), x - 96, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                } else {
                                    world2.addParticle(IWParticles.BLIZZARDSNOW, MathHelper.lerp(world2.random.nextDouble(), x - 96, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                                world2.addParticle(IWParticles.BLIZZARDWIND, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        } else {
                            world2.addParticle(IWParticles.SNOW, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 50, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                        }
                    }
                }
                    }
                }
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
