package shadowmaster435.impactfulweather.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import shadowmaster435.impactfulweather.config.ClientConfig;
import shadowmaster435.impactfulweather.init.ModRegistry;
import shadowmaster435.impactfulweather.client.particle.HeavyRain;

import java.util.ArrayList;
import java.util.List;

public class ParticleUtil {

    public static List<BlockPos> fungusposlist = new ArrayList<>();
    public static List<BlockPos> fungusblockposlist = new ArrayList<>();
    public static List<BlockPos> soulsandposlist = new ArrayList<>();
    public static int netherweathertimer = 0;
    public static boolean weathertoggle = true;

    public static int timerval = 0;

    public static void netherweatherlogic() {
        if (!Minecraft.getInstance().isPaused()) {
            if (netherweathertimer == 0) {
                weathertoggle = !weathertoggle;
                netherweathertimer = (ClientConfig.INSTANCE.misc.nweatherbasedelay.get() * 20) + (int) (Math.random() * ClientConfig.INSTANCE.misc.nweatherrandomdelay.get() * 20);
            }
            if (netherweathertimer > 0) {
                --netherweathertimer;
            }
        }
    }

    public float windmod() {
        float shortener = ClientConfig.INSTANCE.particleAmounts.windmodifier.get().floatValue();
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
        Minecraft instance2 = Minecraft.getInstance();
        Player player2 = instance2.player;
        Level world2 = instance2.level;
        assert world2 != null;
        assert player2 != null;
        BlockPos pos = player2.blockPosition();
        RandomSource random = instance2.level.random;

        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        double x = (double)i + random.nextDouble();
        double y = (double)j + random.nextDouble();
        double z = (double)k + random.nextDouble();

        for (double hj = 0; hj < Math.ceil((double) ClientConfig.INSTANCE.particleAmounts.particleamount.get() * ((ClientConfig.INSTANCE.particleAmounts.particleDensity.get() * 0.03125))); hj++) {
            if ((world2.isRaining() || world2.isThundering()) && !instance2.isPaused()) {
                for (int l = 0; l < 8; ++l) {
                    for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.sandmotemodifier.get(); ++i1) {
                        if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.sandmotemodifier.get(), i1)) {

                            if (world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.WOODED_BADLANDS)
                                    || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.ERODED_BADLANDS)
                                    || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.BADLANDS)
                                    || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.DESERT)) {
                                if (ClientConfig.INSTANCE.particleToggles.redsandstorms.get() && world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.WOODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.ERODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.BADLANDS)) {
                                    if (ClientConfig.INSTANCE.particleToggles.redsandmote.get()) {
                                        world2.addParticle(ModRegistry.REDSANDMOTE.get(), Mth.lerp(world2.random.nextDouble(), x - 48, x + 48), Mth.lerp(world2.random.nextDouble(), 63, 90), Mth.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                    }
                                }
                                if (world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.DESERT)) {
                                    if (ClientConfig.INSTANCE.particleToggles.sandstorms.get()) {
                                        if (ClientConfig.INSTANCE.particleToggles.sandmote.get()) {
                                            world2.addParticle(ModRegistry.SANDMOTE.get(), Mth.lerp(world2.random.nextDouble(), x - 48, x + 48), Mth.lerp(world2.random.nextDouble(), 63, 90), Mth.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                        }
                                    }
                                }

                            }
                        }
                    }
                        for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.tumblebushmodifier.get(); ++i1) {

                                if (ClientConfig.INSTANCE.particleToggles.tumblebush.get() && Math.random() < 0.0075 && world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.WOODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.ERODED_BADLANDS)
                                        || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.BADLANDS)
                                        || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.DESERT))
                                {

                                            if (Math.random() < 0.0075 && ClientConfig.INSTANCE.particleToggles.sandstorms.get() && world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.DESERT)) {
                                                world2.addParticle(ModRegistry.TUMBLEBUSH.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 110), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                            if (Math.random() < 0.0075 &&ClientConfig.INSTANCE.particleToggles.redsandstorms.get() && world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.WOODED_BADLANDS)
                                                    || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.ERODED_BADLANDS)
                                                    || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.BADLANDS)
                                            ) {

                                                world2.addParticle(ModRegistry.TUMBLEBUSH.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 110), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                }
                    }

                    for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.windmodifier.get(); ++i1) {
                        if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.windmodifier.get(), i1)) {
                            if (ClientConfig.INSTANCE.particleToggles.wind.get() &&  world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.SAVANNA)
                            ||  world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.SAVANNA_PLATEAU) ||
                                    world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).is(Biomes.WINDSWEPT_SAVANNA)) {
                                world2.addParticle(ModRegistry.GUST.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), y - 64, y + 64), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                            }
                        }
                    }
                    if (world2.getBiome(pos).value().getPrecipitation().equals(Biome.Precipitation.RAIN)) {

                        if (ClientConfig.INSTANCE.particleToggles.heavyrain.get() && (world2.isThundering() || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).is(Biomes.BAMBOO_JUNGLE.location()) || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).is(Biomes.SPARSE_JUNGLE.location()) || world2.getBiome(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).is(Biomes.JUNGLE.location()) || world2.getBiome(new BlockPos(Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 256), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64))).value().isHumid())) {
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.heavyrainmodifier.get(); ++i1) {
                                if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.heavyrainmodifier.get(), i1)) {
                                    if (ClientConfig.INSTANCE.particleToggles.heavyrain.get()) {

                                        world2.addParticle(ModRegistry.HEAVYRAIN.get(), Mth.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.blockPosition().getY() + 100, Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                        world2.addParticle(ModRegistry.HEAVYRAIN.get(), Mth.lerp(world2.random.nextDouble(), x - 32, x + 96) - HeavyRain.heavyrainvel, player2.blockPosition().getY() + 100, Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);

                                    }
                                }
                            }
                        } else {
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.rainmodifier.get(); ++i1) {
                                if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.rainmodifier.get(), i1)) {

                                    if (ClientConfig.INSTANCE.particleToggles.rain.get()) {
                                        world2.addParticle(ModRegistry.RAIN.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.blockPosition().getY() + 100, Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                }
                            }
                        }
                    }
                    if (
                            world2.getBiome(player2.blockPosition()).value().getPrecipitation() == Biome.Precipitation.SNOW) {
                        if (world2.isThundering() && ClientConfig.INSTANCE.particleToggles.blizzards.get()) {
                            if (Math.random() < 0.75) {
                                    if (ClientConfig.INSTANCE.particleToggles.blizzardsnow.get()) {
                                        for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.blizzardsnowmodifier.get(); ++i1) {
                                            if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.blizzardsnowmodifier.get(), i1)) {

                                                world2.addParticle(ModRegistry.BLIZZARDSNOW.get(), Mth.lerp(world2.random.nextDouble(), x - 96, x + 48), Mth.lerp(world2.random.nextDouble(), y - 64, y + 64), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                            }
                                            }
                                    }
                                for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.blizzardwindmodifier.get(); ++i1) {
                                    if (ClientConfig.INSTANCE.particleToggles.blizzardwind.get()) {
                                        if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.blizzardwindmodifier.get(), i1)) {

                                            world2.addParticle(ModRegistry.BLIZZARDWIND.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), y - 64, y + 64), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.snowmodifier.get(); ++i1) {
                                if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.snowmodifier.get(), i1)) {

                                    if (ClientConfig.INSTANCE.particleToggles.snow.get()) {
                                        world2.addParticle(ModRegistry.SNOW.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), player2.blockPosition().getY() + 50, Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                    }
                                }
                            }
                        }

                    }

                }

            } else {
                    for (int l = 0; l < 2; ++l) {
                        if (world2.getBiomeManager().getBiome(player2.blockPosition()).is(Biomes.MANGROVE_SWAMP) && !instance2.isPaused() && ClientConfig.INSTANCE.particleToggles.fireflies.get() && (world2.getDayTime() < 22000 && world2.getDayTime() > 14000)) {
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.fireflymodifier.get(); ++i1) {
                                if (Math.random() > 0.95) {

                                    world2.addParticle(ModRegistry.FIREFLY.get(), Mth.lerp(world2.random.nextDouble(), x - 64, x + 64), Mth.lerp(world2.random.nextDouble(), 64, 76), Mth.lerp(world2.random.nextDouble(), z - 64, z + 64), 0f, 0f, 0f);
                                }
                                if (Math.random() > 0.8) {

                                    world2.addParticle(ModRegistry.FIREFLY.get(), Mth.lerp(world2.random.nextDouble(), x - 48, x + 48), Mth.lerp(world2.random.nextDouble(), 64, 72), Mth.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                }
            //nether particles
            if (player2.getCommandSenderWorld().dimensionTypeId().equals(BuiltinDimensionTypes.NETHER) && (weathertoggle || !ClientConfig.INSTANCE.particleToggles.endlessnweather.get()) && !Minecraft.getInstance().isPaused()) {
                if (world2.getBiome(player2.blockPosition()).unwrapKey().isPresent()) {
                    if (ClientConfig.INSTANCE.particleToggles.updrafts.get() && world2.getBiome(player2.blockPosition()).unwrapKey().get().equals(Biomes.NETHER_WASTES) || world2.getBiome(player2.blockPosition()).unwrapKey().get().equals(Biomes.BASALT_DELTAS)) {
                        for (int l = 0; l < 8; ++l) {
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.updraftmodifier.get(); ++i1) {
                                if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.updraftmodifier.get(), i1)) {

                                    world2.addParticle(ModRegistry.UPDRAFT.get(), Mth.lerp(world2.random.nextDouble(), x - 48, x + 48), Mth.lerp(world2.random.nextDouble(), y - 48, y + 48), Mth.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                    if (ClientConfig.INSTANCE.particleToggles.sporestorm.get() && world2.getBiome(player2.blockPosition()).unwrapKey().get().equals(Biomes.WARPED_FOREST)) {
                        for (int l = 0; l < 12; ++l) {
                            for (int i1 = 0; i1 <= ClientConfig.INSTANCE.particleAmounts.sporemodifier.get(); ++i1) {
                                if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.sporemodifier.get(), i1)) {

                                    world2.addParticle(ModRegistry.WARPEDSPORE.get(), Mth.lerp(world2.random.nextDouble(), x - 48, x + 48), Mth.lerp(world2.random.nextDouble(), y - 48, y + 48), Mth.lerp(world2.random.nextDouble(), z - 48, z + 48), 0f, 0f, 0f);
                                }
                            }
                        }
                    }
                        for (int x2 = 1; x2 < ClientConfig.INSTANCE.misc.searchDistanceConfig.x.get(); ++x2) {
                            for (int y2 = 1; y2 < ClientConfig.INSTANCE.misc.searchDistanceConfig.y.get(); ++y2) {
                                for (int z2 = 1; z2 < ClientConfig.INSTANCE.misc.searchDistanceConfig.z.get(); ++z2) {
                                    int xps = (pos.getX() - ClientConfig.INSTANCE.misc.searchDistanceConfig.x.get() / 2) + x2;
                                    int yps = (pos.getY() - ClientConfig.INSTANCE.misc.searchDistanceConfig.y.get() / 2) + y2;
                                    int zps = (pos.getZ() - ClientConfig.INSTANCE.misc.searchDistanceConfig.z.get() / 2) + z2;
                                    if (ClientConfig.INSTANCE.particleToggles.soulstorms.get() && world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SAND) || world2.getBlockState(new BlockPos(xps, yps, zps)).getBlock().equals(Blocks.SOUL_SOIL)) {
                                        if (world2.isEmptyBlock(new BlockPos(xps, yps + 1, zps)) && world2.getBiome(new BlockPos(xps, yps, zps)).unwrapKey().get().equals(Biomes.SOUL_SAND_VALLEY)) {
                                            soulsandposlist.add(new BlockPos(xps, yps, zps));
                                        }
                                    }
                                    int xps1 = (pos.getX() - ClientConfig.INSTANCE.misc.searchDistanceConfig.x.get() / 2) + x2;
                                    int yps1 = (pos.getY() - ClientConfig.INSTANCE.misc.searchDistanceConfig.y.get() / 2) + y2;
                                    int zps1 = (pos.getZ() - ClientConfig.INSTANCE.misc.searchDistanceConfig.z.get() / 2) + z2;
                                    if (ClientConfig.INSTANCE.particleToggles.weepingrain.get()) {

                                        if (world2.getBiome(new BlockPos(xps1, yps1, zps1)).unwrapKey().get().equals(Biomes.CRIMSON_FOREST)) {
                                        if (world2.getBlockState(new BlockPos(xps1, yps1, zps1)).getBlock().equals(Blocks.CRIMSON_FUNGUS)) {
                                            fungusposlist.add(new BlockPos(xps1, yps1, zps1));
                                        }
                                        if (world2.getBlockState(new BlockPos(xps1, yps1, zps1)).getBlock().equals(Blocks.NETHER_WART_BLOCK)) {
                                            if (world2.isEmptyBlock(new BlockPos(xps1, yps1 - 1, zps1))) {
                                                fungusblockposlist.add(new BlockPos(xps1, yps1, zps1));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (ClientConfig.INSTANCE.particleToggles.weepingrain.get()) {
                        for (int index = 1; index < fungusblockposlist.size(); ++index) {
                            int xf = fungusblockposlist.get(index).getX();
                            int yf = fungusblockposlist.get(index).getY();
                            int zf = fungusblockposlist.get(index).getZ();
                                if (Math.random() > 0.985) {
                                    for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.tearmodifier.get(); ++i1) {
                                        if (MiscUtil.forloopdecimalizer(ClientConfig.INSTANCE.particleAmounts.tearmodifier.get(), i1)) {

                                            world2.addParticle(ModRegistry.WEEPINGTEAR.get(), Mth.lerp(world2.random.nextDouble(), xf, xf + 1), Mth.lerp(world2.random.nextDouble(), yf, yf + 1), Mth.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                        }
                                    }
                            }
                        }

                        for (int index = 1; index < fungusposlist.size(); ++index) {
                            int xf = fungusposlist.get(index).getX();
                            int yf = fungusposlist.get(index).getY();
                            int zf = fungusposlist.get(index).getZ();
                            for (int i1 = 1; i1 <= ClientConfig.INSTANCE.particleAmounts.tearmodifier.get(); ++i1) {

                                    if (Math.random() > 0.97) {
                                    world2.addParticle(ModRegistry.WEEPINGTEAR.get(), Mth.lerp(world2.random.nextDouble(), xf, xf + 1), Mth.lerp(world2.random.nextDouble(), yf, yf + 1), Mth.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                }
                            }
                        }
                        ++timerval;
                    }
                    if (ClientConfig.INSTANCE.particleToggles.soulstorms.get()) {
                        int timerval;
                        for (int index = 1; index < soulsandposlist.size(); ++index) {

                            int xf = soulsandposlist.get(index).getX();
                            int yf = soulsandposlist.get(index).getY();
                            int zf = soulsandposlist.get(index).getZ();
                            for (int i1 = 0; i1 <= ClientConfig.INSTANCE.particleAmounts.soulmodifier.get(); ++i1) {
                                if (ClientConfig.INSTANCE.particleAmounts.soulmodifier.get() < 1) {
                                    if (Math.random() > 0.9975) {
                                        world2.addParticle(ModRegistry.STORMSOUL.get(), Mth.lerp(world2.random.nextDouble(), xf, xf + 1), yf + 2, Mth.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
                                    }
                                } else {
                                    if (Math.random() > 0.9975) {
                                        world2.addParticle(ModRegistry.STORMSOUL.get(), Mth.lerp(world2.random.nextDouble(), xf, xf + 1), yf + 2, Mth.lerp(world2.random.nextDouble(), zf, zf + 1), 0, 0, 0);
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
