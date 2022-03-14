package shadowmaster435.impactfulweather.util;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import shadowmaster435.impactfulweather.client.BPWModConfig;
import shadowmaster435.impactfulweather.init.IWParticles;
import shadowmaster435.impactfulweather.particles.HeavyRain;
import shadowmaster435.impactfulweather.particles.Rain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleUtil {

    public static List<BlockPos> fungusposlist = new ArrayList<>();
    public static List<BlockPos> fungusblockposlist = new ArrayList<>();
    public static List<BlockPos> soulsandposlist = new ArrayList<>();
    public static int netherweathertimer = 0;
    public static boolean weathertoggle = true;
    public static final BPWModConfig config = AutoConfig.getConfigHolder(BPWModConfig.class).getConfig();

    public static void netherweatherlogic() {
        if (!MinecraftClient.getInstance().isPaused()) {
            if (netherweathertimer == 0) {
                weathertoggle = !weathertoggle;
                netherweathertimer = (config.nweatherbasedelay * 20) + (int) (Math.random() * config.nweatherrandomdelay * 20);
            }
            if (netherweathertimer > 0) {
                --netherweathertimer;
            }
        }
    }

    public static void spawnweatherparticles() {
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

        for (double hj = 0; hj < Math.ceil((double) config.particleamount * ((config.particleDensity * 0.03125))); hj++) {
            if ((world2.isRaining() || world2.isThundering()) && !instance2.isPaused()) {
                for (int l = 0; l < 8; ++l) {
                    if ( Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.MESA) || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.DESERT)) {
                        if (config.redsandstorms ||Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.MESA)) {
                            world2.addParticle(IWParticles.REDSANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                        } else {
                            if (config.sandstorms) {
                                world2.addParticle(IWParticles.SANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                            }
                        }
                        if (Math.random() < 0.0075) {
                            if (Biome.getCategory(world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))).equals(Biome.Category.DESERT) || Biome.getCategory(world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))).equals(Biome.Category.MESA)) {
                                if (config.sandstorms && Biome.getCategory(world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))).equals(Biome.Category.DESERT)) {
                                    world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 110), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                                if (config.redsandstorms && Biome.getCategory(world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))).equals(Biome.Category.MESA)) {
                                    world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 110), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                    if (config.wind || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.SAVANNA)) {
                        world2.addParticle(IWParticles.GUST, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                    }
                    if (world2.getBiome(player2.getBlockPos()).value().getPrecipitation() == Biome.Precipitation.RAIN) {
                        if (config.dodenserain) {
                            for (int am = 0; am < Rain.rainamount; ++am) {
                                if (config.heavyrain && (world2.isThundering() || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.JUNGLE))) {
                                    world2.addParticle(IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                } else {
                                    world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                            }
                        } else {
                            if (config.heavyrain && (world2.isThundering() || Biome.getCategory(world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64)))).equals(Biome.Category.JUNGLE))) {
                                world2.addParticle( IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            } else {
                                world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        }
                    }
                    if (
                            world2.getBiome(player2.getBlockPos()).value().getPrecipitation() == Biome.Precipitation.SNOW) {
                        if (world2.isThundering() && config.blizzards) {
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
            //nether particles
            if ((weathertoggle || config.endlessnweather) && !MinecraftClient.getInstance().isPaused()) {
                if (world2.getBiome(player2.getBlockPos()).getKey().isPresent()) {
                    if (config.updrafts && world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.NETHER_WASTES) || world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.BASALT_DELTAS)) {
                        for (int l = 0; l < 8; ++l) {
                            world2.addParticle(IWParticles.UPDRAFT, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 48, y + 48), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                        }
                    }
                    if (config.sporestorm && world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.WARPED_FOREST)) {
                        for (int l = 0; l < 12; ++l) {

                            world2.addParticle(IWParticles.WARPEDSPORE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 48, y + 48), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);

                        }
                    }
                    if (!config.weepingrain) {
                        continue;
                    } else {
                        for (int x2 = 1; x2 < 32; ++x2) {
                            for (int y2 = 1; y2 < 32; ++y2) {
                                for (int z2 = 1; z2 < 32; ++z2) {
                                    int xps = (pos.getX() - 16) + x2;
                                    int yps = (pos.getY() - 16) + y2;
                                    int zps = (pos.getZ() - 16) + z2;
                                    if (world2.getBiome(new BlockPos(xps, yps, zps)).getKey().get().equals(BiomeKeys.CRIMSON_FOREST)) {
                                        if (world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.CRIMSON_FUNGUS)) {
                                            fungusposlist.add(new BlockPos(xps, yps, zps));
                                        }
                                        if (world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.NETHER_WART_BLOCK)) {
                                            if (world2.isAir(new BlockPos(xps, yps - 1, zps))) {
                                                fungusblockposlist.add(new BlockPos(xps, yps, zps));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!config.soulstorms) {
                        continue;
                    } else {
                        for (int x2 = 1; x2 < 32; ++x2) {
                            for (int y2 = 1; y2 < 32; ++y2) {
                                for (int z2 = 1; z2 < 32; ++z2) {
                                    int xps = (pos.getX() - 16) + x2;
                                    int yps = (pos.getY() - 16) + y2;
                                    int zps = (pos.getZ() - 16) + z2;
                                    if (config.soulstorms && world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SAND) || world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SOIL)) {
                                        if (world2.isAir(new BlockPos(xps, yps + 1, zps)) && world2.getBiome(new BlockPos(xps, yps, zps)).getKey().get().equals(BiomeKeys.SOUL_SAND_VALLEY)) {
                                            soulsandposlist.add(new BlockPos(xps, yps, zps));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (config.weepingrain) {
                        for (int index = 1; index < fungusblockposlist.size(); ++index) {
                            int xf = fungusblockposlist.get(index).getX();
                            int yf = fungusblockposlist.get(index).getY();
                            int zf = fungusblockposlist.get(index).getZ();
                            if (Math.random() > 0.985) {
                                world2.addParticle(IWParticles.WEEPINGTEAR, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), MathHelper.lerp(world2.random.nextDouble(), yf, yf + 1), MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                            }
                        }

                        for (int index = 1; index < fungusposlist.size(); ++index) {
                            int xf = fungusposlist.get(index).getX();
                            int yf = fungusposlist.get(index).getY();
                            int zf = fungusposlist.get(index).getZ();
                            if (Math.random() > 0.97) {
                                world2.addParticle(IWParticles.WEEPINGTEAR, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), MathHelper.lerp(world2.random.nextDouble(), yf, yf + 1), MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                            }
                        }
                    }
                    if (config.soulstorms) {
                        for (int index = 1; index < soulsandposlist.size(); ++index) {
                            int xf = soulsandposlist.get(index).getX();
                            int yf = soulsandposlist.get(index).getY();
                            int zf = soulsandposlist.get(index).getZ();
                            if (Math.random() > 0.9975) {
                                world2.addParticle(IWParticles.STORMSOUL, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), yf + 2, MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                            }
                        }
                    }
                    fungusblockposlist.clear();
                    fungusposlist.clear();
                    soulsandposlist.clear();
                }
            }
        }
    }
}
