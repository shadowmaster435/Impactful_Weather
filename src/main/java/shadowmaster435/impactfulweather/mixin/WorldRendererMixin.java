package shadowmaster435.impactfulweather.mixin;


import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
    @Inject(at = @At("TAIL"), method = "renderWeather")
    private void render(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo ci) {
        MinecraftClient instance2 = MinecraftClient.getInstance();
        PlayerEntity player2 = instance2.player;
        World world2 = instance2.world;
        assert world2 != null;
        assert player2 != null;
        BlockPos pos = player2.getBlockPos();
        Random random = instance2.world.random;
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        double x = (double)i + random.nextDouble();
        double y = (double)j + random.nextDouble();
        double z = (double)k + random.nextDouble();
        if ((world2.isRaining() || world2.isThundering()) && !client.isPaused()) {
            for (int l = 0; l < 8; ++l) {
                if (world2.getBiome(new BlockPos(x + 32, MathHelper.lerp(world2.random.nextDouble(), 63, 100), z + 32)).getCategory() == Biome.Category.DESERT || world2.getBiome(new BlockPos(x + 32, MathHelper.lerp(world2.random.nextDouble(), 63, 100), z + 32)).getCategory() == Biome.Category.MESA) {
                    if (world2.getBiome(new BlockPos(x + 32, MathHelper.lerp(world2.random.nextDouble(), 63, 100), z + 32)).getCategory() == Biome.Category.MESA) {
                        world2.addParticle(IWParticles.REDSANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 32), MathHelper.lerp(world2.random.nextDouble(), 63, 100), z - 24, 0f, 0f, 0f);
                    } else {
                        world2.addParticle(IWParticles.SANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 32), MathHelper.lerp(world2.random.nextDouble(), 63, 100), z - 24, 0f, 0f, 0f);
                    }
                    if (Math.random() < 0.0075) {
                        world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 32), MathHelper.lerp(world2.random.nextDouble(), 64, 76), z - 24, 0f, 0f, 0f);
                    }
                }
                if (world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).getCategory() == Biome.Category.SAVANNA) {
                    world2.addParticle(IWParticles.GUST, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), y - 32, y + 32), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                }
                if (
                        world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY(), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).doesNotSnow(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY(), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))) && world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY(), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).getPrecipitation().equals(Biome.Precipitation.RAIN)
                ) {
                    for (int am = 0; am < Rain.rainamount; ++am) {
                        if (world2.isThundering() || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).getCategory() == Biome.Category.JUNGLE) {
                            world2.addParticle(IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32) + 32 * HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                        } else {
                            world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                        }
                    }
                }
                if (
                        world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY(), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).getPrecipitation() == Biome.Precipitation.SNOW) {
                    if (world2.isThundering() || world2.getBiomeKey(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).isPresent() && world2.getBiomeKey(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32))).get().equals(BiomeKeys.ICE_SPIKES)) {
                        if (Math.random() < 0.5) {
                            world2.addParticle(IWParticles.BLIZZARDSNOW, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32) - 32, MathHelper.lerp(world2.random.nextDouble(), y - 32, y + 32), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                            world2.addParticle(IWParticles.BLIZZARDWIND, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), MathHelper.lerp(world2.random.nextDouble(), y - 32, y + 32), MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                        }
                    } else {
                        if (Math.random() < 0.4) {
                            world2.addParticle(IWParticles.SNOW, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 32), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 32, z + 32), 0f, 0f, 0f);
                        }
                    }
                }
            }
        }
    }

    /**
     * @author shadowmaster435
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
            Biome biome = worldView.getBiome(blockPos3);
            if (blockPos3.getY() <= worldView.getBottomY() || blockPos3.getY() > blockPos.getY() + 10 || blockPos3.getY() < blockPos.getY() - 10 || biome.getPrecipitation() != Biome.Precipitation.RAIN || !biome.doesNotSnow(blockPos3)) continue;
            blockPos2 = blockPos3.down();
            if (instance.options.particles == ParticlesMode.MINIMAL) break;
        }
        if (blockPos2 != null && random.nextInt(3) < this.field_20793++) {
            this.field_20793 = 0;
            if (instance.world.getBiome(blockPos).getPrecipitation() == Biome.Precipitation.RAIN) {

                if (blockPos2.getY() > blockPos.getY() + 1 && worldView.getTopPosition(Heightmap.Type.MOTION_BLOCKING, blockPos).getY() > MathHelper.floor(blockPos.getY())) {
                    instance.world.playSound(playerpos, SoundEvents.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.1f, 0.5f, false);
                } else {
                    instance.world.playSound(playerpos, SoundEvents.WEATHER_RAIN, SoundCategory.WEATHER, 0.2f, 1.0f, false);
                }
            }
        }
    }
}
