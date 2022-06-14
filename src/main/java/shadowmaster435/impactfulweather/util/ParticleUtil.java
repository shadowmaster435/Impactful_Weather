package shadowmaster435.impactfulweather.util;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionTypes;
import shadowmaster435.impactfulweather.client.BPWModConfig;
import shadowmaster435.impactfulweather.init.IWParticles;
import shadowmaster435.impactfulweather.particles.HeavyRain;

import java.util.ArrayList;
import java.util.List;

public class ParticleUtil {

    public static List<BlockPos> fungusposlist = new ArrayList<>();
    public static List<BlockPos> fungusblockposlist = new ArrayList<>();
    public static List<BlockPos> soulsandposlist = new ArrayList<>();
    public static int netherweathertimer = 0;
    public static boolean weathertoggle = true;
    public static final BPWModConfig config = AutoConfig.getConfigHolder(BPWModConfig.class).getConfig();

    public static int timerval = 0;

    public static void netherweatherlogic() {
        if (!MinecraftClient.getInstance().isPaused()) {
            if (netherweathertimer == 0) {
                weathertoggle = !weathertoggle;
                netherweathertimer = (config.misc.nweatherbasedelay * 20) + (int) (Math.random() * config.misc.nweatherrandomdelay * 20);
            }
            if (netherweathertimer > 0) {
                --netherweathertimer;
            }
        }
    }

    public float windmod() {
        float shortener = config.particleamountconfig.windmodifier;
        float remainder = (float) (shortener % Math.ceil(shortener));
        int decimalplace = Integer.parseInt("1" + ("0".repeat(String.valueOf(shortener).length() - 2)));
        if (shortener > 0 && !(shortener <= 0)) {
            if (remainder > 0 && remainder < 1) {
                return remainder * decimalplace;
            } else  {
                return shortener;
            }
        } else {
            return 1;
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

        for (double hj = 0; hj < Math.ceil((double) config.particleamountconfig.particleamount * ((config.particleamountconfig.particleDensity * 0.03125))); hj++) {
            if ((world2.isRaining() || world2.isThundering()) && !instance2.isPaused()) {
                for (int l = 0; l < 8; ++l) {
                    for (int i1 = 1; i1 <= config.particleamountconfig.sandmotemodifier; ++i1) {
                        if (MiscUtil.forloopdecimalizer(config.particleamountconfig.sandmotemodifier, i1)) {

                            if (world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.WOODED_BADLANDS)
                                    || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.ERODED_BADLANDS)
                                    || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.BADLANDS)
                                    || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.DESERT)) {
                                if (config.toggles.redsandstorms && world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.WOODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.ERODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.BADLANDS)) {
                                    if (config.particletoggles.redsandmote) {
                                        world2.addParticle(IWParticles.REDSANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                    }
                                }
                                if (world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.DESERT)) {
                                    if (config.toggles.sandstorms) {
                                        if (config.particletoggles.sandmote) {
                                            world2.addParticle(IWParticles.SANDMOTE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 63, 90), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                        }
                                    }
                                }

                            }
                        }
                    }
                        for (int i1 = 1; i1 <= config.particleamountconfig.tumblebushmodifier; ++i1) {

                                if (config.particletoggles.tumblebush && Math.random() < 0.0075 && world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.WOODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.ERODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.BADLANDS)
                                        || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.DESERT))
                                {

                                            if (Math.random() < 0.0075 && config.toggles.sandstorms && world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.DESERT)) {
                                                world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 110), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                            if (Math.random() < 0.0075 &&config.toggles.redsandstorms && world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.WOODED_BADLANDS)
                                                    || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.ERODED_BADLANDS)
                                                    || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.BADLANDS)
                                            ) {

                                                world2.addParticle(IWParticles.TUMBLEBUSH, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 110), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                }
                    }

                    for (int i1 = 1; i1 <= config.particleamountconfig.windmodifier; ++i1) {
                        if (MiscUtil.forloopdecimalizer(config.particleamountconfig.windmodifier, i1)) {
                            if (config.toggles.wind &&  world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.SAVANNA)
                            ||  world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.SAVANNA_PLATEAU) ||
                                    world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.WINDSWEPT_SAVANNA)) {
                                world2.addParticle(IWParticles.GUST, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        }
                    }
                    if (world2.getBiome(pos).value().getPrecipitation().equals(Biome.Precipitation.RAIN)) {

                        if (config.toggles.heavyrain && (world2.isThundering() || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).matchesId(BiomeKeys.BAMBOO_JUNGLE.getValue()) || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).matchesId(BiomeKeys.SPARSE_JUNGLE.getValue()) || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).matchesId(BiomeKeys.JUNGLE.getValue()) || world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).value().hasHighHumidity())) {
                            for (int i1 = 1; i1 <= config.particleamountconfig.heavyrainmodifier; ++i1) {
                                if (MiscUtil.forloopdecimalizer(config.particleamountconfig.heavyrainmodifier, i1)) {
                                    if (config.particletoggles.heavyrain) {

                                        world2.addParticle(IWParticles.HEAVYRAIN, MathHelper.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                }
                            }
                        } else {
                            for (int i1 = 1; i1 <= config.particleamountconfig.rainmodifier; ++i1) {
                                if (MiscUtil.forloopdecimalizer(config.particleamountconfig.rainmodifier, i1)) {

                                    if (config.particletoggles.rain) {
                                        world2.addParticle(IWParticles.RAIN, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 100, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                }
                            }
                        }
                    }
                    if (
                            world2.getBiome(player2.getBlockPos()).value().getPrecipitation() == Biome.Precipitation.SNOW) {
                        if (world2.isThundering() && config.toggles.blizzards) {
                            if (Math.random() < 0.75) {
                                    if (config.particletoggles.blizzardsnow) {
                                        for (int i1 = 1; i1 <= config.particleamountconfig.blizzardsnowmodifier; ++i1) {
                                            if (MiscUtil.forloopdecimalizer(config.particleamountconfig.blizzardsnowmodifier, i1)) {

                                                world2.addParticle(IWParticles.BLIZZARDSNOW, MathHelper.lerp(world2.random.nextDouble(), x - 96, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                            }
                                    }
                                for (int i1 = 1; i1 <= config.particleamountconfig.blizzardwindmodifier; ++i1) {
                                    if (config.particletoggles.blizzardwind) {
                                        if (MiscUtil.forloopdecimalizer(config.particleamountconfig.blizzardwindmodifier, i1)) {

                                            world2.addParticle(IWParticles.BLIZZARDWIND, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), y - 64, y + 64), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (int i1 = 1; i1 <= config.particleamountconfig.snowmodifier; ++i1) {
                                if (MiscUtil.forloopdecimalizer(config.particleamountconfig.snowmodifier, i1)) {

                                    if (config.particletoggles.snow) {
                                        world2.addParticle(IWParticles.SNOW, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.getBlockPos().getY() + 50, MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                }
                            }
                        }

                    }

                }

            } else {
                    for (int l = 0; l < 8; ++l) {
                        if (world2.getBiome(new BlockPos(MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 256), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64))).matchesKey(BiomeKeys.MANGROVE_SWAMP) &&  !instance2.isPaused() && config.particletoggles.fireflies && (world2.getTimeOfDay() < 22000 && world2.getTimeOfDay() > 14000)) {
                            for (int i1 = 1; i1 <= config.particleamountconfig.fireflymodifier; ++i1) {
                                if (Math.random() > 0.95) {

                                    world2.addParticle(IWParticles.FIREFLY, MathHelper.lerp(world2.random.nextDouble(), x - 64, x + 64), MathHelper.lerp(world2.random.nextDouble(), 64, 76), MathHelper.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                                if (Math.random() > 0.8) {

                                    world2.addParticle(IWParticles.FIREFLY, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), 64, 72), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                }
            //nether particles
            if (player2.getEntityWorld().getDimensionKey().equals(DimensionTypes.THE_NETHER) && (weathertoggle || config.toggles.endlessnweather) && !MinecraftClient.getInstance().isPaused()) {
                if (world2.getBiome(player2.getBlockPos()).getKey().isPresent()) {
                    if (config.toggles.updrafts && world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.NETHER_WASTES) || world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.BASALT_DELTAS)) {
                        for (int l = 0; l < 8; ++l) {
                            for (int i1 = 1; i1 <= config.particleamountconfig.updraftmodifier; ++i1) {
                                if (MiscUtil.forloopdecimalizer(config.particleamountconfig.updraftmodifier, i1)) {

                                    world2.addParticle(IWParticles.UPDRAFT, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 48, y + 48), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                    if (config.toggles.sporestorm && world2.getBiome(player2.getBlockPos()).getKey().get().equals(BiomeKeys.WARPED_FOREST)) {
                        for (int l = 0; l < 12; ++l) {
                            for (int i1 = 0; i1 <= config.particleamountconfig.sporemodifier; ++i1) {
                                if (MiscUtil.forloopdecimalizer(config.particleamountconfig.sporemodifier, i1)) {

                                    world2.addParticle(IWParticles.WARPEDSPORE, MathHelper.lerp(world2.random.nextDouble(), x - 48, x + 48), MathHelper.lerp(world2.random.nextDouble(), y - 48, y + 48), MathHelper.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                        for (int x2 = 1; x2 < config.misc.searchDist.x; ++x2) {
                            for (int y2 = 1; y2 < config.misc.searchDist.y; ++y2) {
                                for (int z2 = 1; z2 < config.misc.searchDist.z; ++z2) {
                                    int xps = (pos.getX() - config.misc.searchDist.x / 2) + x2;
                                    int yps = (pos.getY() - config.misc.searchDist.y / 2) + y2;
                                    int zps = (pos.getZ() - config.misc.searchDist.z / 2) + z2;
                                    if (config.toggles.soulstorms && world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SAND) || world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SOIL)) {
                                        if (world2.isAir(new BlockPos(xps, yps + 1, zps)) && world2.getBiome(new BlockPos(xps, yps, zps)).getKey().get().equals(BiomeKeys.SOUL_SAND_VALLEY)) {
                                            soulsandposlist.add(new BlockPos(xps, yps, zps));
                                        }
                                    }
                                    int xps1 = (pos.getX() - config.misc.searchDist.x / 2) + x2;
                                    int yps1 = (pos.getY() - config.misc.searchDist.y / 2) + y2;
                                    int zps1 = (pos.getZ() - config.misc.searchDist.z / 2) + z2;
                                    if (config.toggles.weepingrain) {

                                        if (world2.getBiome(new BlockPos(xps1, yps1, zps1)).getKey().get().equals(BiomeKeys.CRIMSON_FOREST)) {
                                        if (world2.getBlockState(new BlockPos(xps1, yps1, zps1)).getBlock().equals(Blocks.CRIMSON_FUNGUS)) {
                                            fungusposlist.add(new BlockPos(xps1, yps1, zps1));
                                        }
                                        if (world2.getBlockState(new BlockPos(xps1, yps1, zps1)).getBlock().equals(Blocks.NETHER_WART_BLOCK)) {
                                            if (world2.isAir(new BlockPos(xps1, yps1 - 1, zps1))) {
                                                fungusblockposlist.add(new BlockPos(xps1, yps1, zps1));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (config.toggles.weepingrain) {
                        for (int index = 1; index < fungusblockposlist.size(); ++index) {
                            int xf = fungusblockposlist.get(index).getX();
                            int yf = fungusblockposlist.get(index).getY();
                            int zf = fungusblockposlist.get(index).getZ();
                                if (Math.random() > 0.985) {
                                    for (int i1 = 1; i1 <= config.particleamountconfig.tearmodifier; ++i1) {
                                        if (MiscUtil.forloopdecimalizer(config.particleamountconfig.tearmodifier, i1)) {

                                            world2.addParticle(IWParticles.WEEPINGTEAR, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), MathHelper.lerp(world2.random.nextDouble(), yf, yf + 1), MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                        }
                                    }
                            }
                        }

                        for (int index = 1; index < fungusposlist.size(); ++index) {
                            int xf = fungusposlist.get(index).getX();
                            int yf = fungusposlist.get(index).getY();
                            int zf = fungusposlist.get(index).getZ();
                            for (int i1 = 1; i1 <= config.particleamountconfig.tearmodifier; ++i1) {
                                while (timerval >= 1) {

                                    if (Math.random() > 0.97) {
                                    world2.addParticle(IWParticles.WEEPINGTEAR, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), MathHelper.lerp(world2.random.nextDouble(), yf, yf + 1), MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                    }
                                }
                            }
                        }
                        ++timerval;
                    }
                    if (config.toggles.soulstorms) {
                        int timerval;
                        for (int index = 1; index < soulsandposlist.size(); ++index) {

                            int xf = soulsandposlist.get(index).getX();
                            int yf = soulsandposlist.get(index).getY();
                            int zf = soulsandposlist.get(index).getZ();
                            for (int i1 = 0; i1 <= config.particleamountconfig.soulmodifier; ++i1) {
                                if (config.particleamountconfig.soulmodifier < 1) {
                                    if (Math.random() > 0.9975) {
                                        world2.addParticle(IWParticles.STORMSOUL, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), yf + 2, MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                    }
                                } else {
                                    if (Math.random() > 0.9975) {
                                        world2.addParticle(IWParticles.STORMSOUL, MathHelper.lerp(world2.random.nextDouble(), xf, xf + 1), yf + 2, MathHelper.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                    }
                                }
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
