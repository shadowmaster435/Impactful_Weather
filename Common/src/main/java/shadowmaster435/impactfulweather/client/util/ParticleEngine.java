package shadowmaster435.impactfulweather.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import shadowmaster435.impactfulweather.config.ClientConfig;
import shadowmaster435.impactfulweather.core.init.RegistryReference;
import shadowmaster435.impactfulweather.init.ModRegistry;

import java.util.ArrayList;
public class ParticleEngine {
    public static int netherweathertimer = 0;
    public static boolean netherexperiencingweather = true;
    public static void netherweatherlogic() {

        if (!Minecraft.getInstance().isPaused()) {
            if (netherweathertimer == 0) {
                netherexperiencingweather = !netherexperiencingweather;
                netherweathertimer = (ClientConfig.INSTANCE.misc.nweatherbasedelay.get() * 20) + (int) (Math.abs(Math.random()) * ClientConfig.INSTANCE.misc.nweatherrandomdelay.get() * 20);
            }
            if (netherweathertimer > 0) {
                --netherweathertimer;
            }
        }
    }
    public static void tick() {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            if (level.dimension() == Level.OVERWORLD) {
                if (level.isRaining() || level.isThundering()) {
                    rain();
                    snow();
                    desert();
                    wind();
                }
                if (level.getDayTime() > 13000 && level.getDayTime() < 23000) {
                    fireflies();
                }
            }
            if (level.dimension() == Level.NETHER) {
                nether();
            }
        }
    }
    public static void rain() {
        var rain_type = ModRegistry.RAIN;
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        if (player != null && level != null) {
            var biome = get_biome_at_player(player);
            var pos = player.position();
            if (biome.hasPrecipitation() && biome.warmEnoughToRain(player.blockPosition())) {
                if (biome.getBaseTemperature() > 0.9 || level.isThundering()) {
                    rain_type = ModRegistry.HEAVYRAIN;
                    spawn_around_player(32, pos.y + 100, -32, 96, pos.y + 100, 32, 10, rain_type);
                } else {
                    spawn_around_player(-32, pos.y + 100, -32, 32, pos.y + 100, 32, 10, rain_type);
                }

            }
        }
    }
    public static void snow() {
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        if (player != null && level != null) {
            var biome = get_biome_at_player(player);
            var pos = player.position();
            if (biome.hasPrecipitation() && biome.coldEnoughToSnow(player.blockPosition())) {
                if (level.isThundering()) {
                    spawn_around_player_in_height_range(-64, 32, -32, 0, 32, 32, 64, 128, 10, ModRegistry.BLIZZARDSNOW);
                    spawn_around_player_in_height_range(-32, 32, -32, 32, 32, 32, 64, 256, 5, ModRegistry.BLIZZARDWIND);
                } else {
                    spawn_around_player(-32, pos.y + 100, -32, 32, pos.y + 100, 32, 10, ModRegistry.SNOW);
                }
            }
        }
    }

    public static void desert() {
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        var sand_type = ModRegistry.SANDMOTE;
        if (player != null && level != null) {
            var biome = get_biome_entry_at_player(player);
            if (biome.is(Biomes.BADLANDS) || biome.is(Biomes.ERODED_BADLANDS) || biome.is(Biomes.WOODED_BADLANDS) || biome.is(Biomes.DESERT)) {
                if (!biome.is(Biomes.DESERT)) {
                    sand_type = ModRegistry.REDSANDMOTE;
                }
                if (Math.random() < 0.125) {
                    spawn_around_player_heightmap(-64, 1, -64, 1, 3, 0,  1, ModRegistry.TUMBLEBUSH);
                }
                spawn_around_player_in_height_range(-32, -32, -32, 32, 32, 32, 64, 96, 24, sand_type);
            }
        }
    }

    public static void wind() {
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        if (player != null && level != null) {
            if (!level.getBiome(player.blockPosition()).value().hasPrecipitation()) {
                spawn_around_player_in_height_range(-32, -32, -32, 32, 32, 32, 64, 192, 20, ModRegistry.GUST);
            }
        }
    }

    public static void fireflies() {
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        if (player != null && level != null) {
            if (get_biome_entry_at_player(player).is(Biomes.MANGROVE_SWAMP)) {
                spawn_around_player_in_height_range(-32, -32, -32, 32, 32, 32, 64, 96, 1, ModRegistry.FIREFLY);
            }
        }
    }

    public static void nether() {
        var player = Minecraft.getInstance().player;
        var level = Minecraft.getInstance().level;
        if (player != null && level != null) {
            var biome = level.getBiome(player.blockPosition());
            if (biome.is(Biomes.CRIMSON_FOREST)) {
                var pos_arr = get_random_matching_block_array_around_player(-16, -8, -16, 16, 32, 16, Blocks.NETHER_WART_BLOCK, 256, player);
                for (BlockPos pos : pos_arr) {
                    if (!level.getBlockState(pos.below()).blocksMotion()) {
                        spawn_in_area(pos.getX(), pos.getY() , pos.getZ(), pos.getX() + 1, pos.getY() , pos.getZ() + 1, -1, ModRegistry.WEEPINGTEAR);
                    }
                }
            } else if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
                var sand_pos_arr = get_random_matching_block_array_around_player(-32, -12, -32, 32, 12, 32, Blocks.SOUL_SAND, 32, player);
                var pos_arr = get_random_matching_block_array_around_player(-32, -12, -32, 32, 12, 32, Blocks.SOUL_SOIL, 256, player);
                pos_arr.addAll(sand_pos_arr);
                for (BlockPos pos : pos_arr) {
                    if (!level.getBlockState(pos.above()).blocksMotion()) {
                        spawn_in_area(pos.getX(), pos.getY() - 0.05, pos.getZ(), pos.getX() + 8, pos.getY() - 0.05, pos.getZ() + 8, -1, ModRegistry.STORMSOUL);
                    }
                }
            } else if (biome.is(Biomes.WARPED_FOREST)) {
                spawn_around_player_with_air(-32, -32, -32, 32, 32, 32, 32, ModRegistry.WARPEDSPORE);
            } else {
                spawn_around_player_with_air(-32, -32, -32, 32, 32, 32, 32, ModRegistry.UPDRAFT);
            }
        }
    }

    public static double get_modifier(RegistryReference<SimpleParticleType> particle) {
        var toggle = ClientConfig.INSTANCE.particleToggles;
        var amount = ClientConfig.INSTANCE.particleAmounts;
        var val = particle.get().writeToString().replace("impactfulweather:", "");
        double result = 0;
        switch (val) {
            case "sandmote":
                if (toggle.sandmote.get()) {
                    result = amount.sandmotemodifier.get();
                }
            case "redsandmote":
                if (toggle.redsandmote.get()) {
                    result = amount.sandmotemodifier.get();
                }
            case "rain":
                if (toggle.rain.get()) {
                    result = amount.rainmodifier.get();
                }
            case "heavyrain":
                if (toggle.heavyrain.get()) {
                    result = amount.heavyrainmodifier.get();
                }
            case "tumblebush":
                if (toggle.tumblebush.get()) {
                    result = amount.tumblebushmodifier.get();
                }
            case "snow":
                if (toggle.snow.get()) {
                    result = amount.snowmodifier.get();
                }
            case "blizzardsnow":
                if (toggle.blizzardsnow.get()) {
                    result = amount.blizzardsnowmodifier.get();
                }
            case "blizzardwind":
                if (toggle.blizzardwind.get()) {
                    result = amount.blizzardwindmodifier.get();
                }
            case "gust":
                if (toggle.wind.get()) {
                    result = amount.windmodifier.get();
                }
            case "firefly":
                if (toggle.fireflies.get()) {
                    result = amount.fireflymodifier.get();
                }
            case "weepingtear":
                if (toggle.weepingrain.get()) {
                    result = amount.tearmodifier.get();
                }
            case "warpedspore":
                if (toggle.sporestorm.get()) {
                    result = amount.sporemodifier.get();
                }
            case "updraft":
                if (toggle.updrafts.get()) {
                    result = amount.updraftmodifier.get();
                }
            case "stormsoul":
                if (toggle.soulstorms.get()) {
                    result = amount.soulmodifier.get();
                }
        }
        return result;
    }

    public static Biome get_biome_at(BlockPos pos) {
        var world = Minecraft.getInstance().level;
        assert world != null;
        return world.getBiome(pos).value();
    }
    public static Biome get_biome_at_player(Player player) {
        var world = Minecraft.getInstance().level;
        assert world != null;
        return world.getBiome(player.blockPosition()).value();
    }
    public static Holder<Biome> get_biome_entry_at_player(Player player) {
        var world = Minecraft.getInstance().level;
        assert world != null;
        return world.getBiome(player.blockPosition());
    }
    public static ArrayList<BlockPos> get_random_matching_block_array_around_player(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, Block block, int tries, Player player) {
        var result = new ArrayList<BlockPos>();
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < tries; ++i) {
                var randx = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_x, max_x)) + player.blockPosition().getX();
                var randy = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_y, max_y)) + player.blockPosition().getY();
                var randz = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_z, max_z)) + player.blockPosition().getZ();
                var rand_pos = new BlockPos(randx, randy, randz);
                if (level.getBlockState(rand_pos).is(block)) {
                    result.add(rand_pos);
                }
            }
        }
        return result;
    }
    public static ArrayList<BlockPos> get_random_matching_block_array(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, Block block, int tries) {
        var result = new ArrayList<BlockPos>();
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < tries; ++i) {
                var randx = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_x, max_x));
                var randy = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_y, max_y));
                var randz = (int) Math.round(Mth.lerp(Math.abs(Math.random()), min_z, max_z));
                var rand_pos = new BlockPos(randx, randy, randz);
                if (level.getBlockState(rand_pos).is(block)) {
                    result.add(rand_pos);
                }
            }
        }
        return result;
    }
    
    public static int modified_try_count(double initial, RegistryReference<SimpleParticleType> particle) {
        var particle_modifier = get_modifier(particle);
        if (particle_modifier > 0) {
            return (int) Math.round(initial + (ClientConfig.INSTANCE.particleAmounts.particleDensity.get() + get_modifier(particle)));
        } else {
            return 0;
        }
    }
    public static void spawn_in_area_heightmap(double min_x, double min_y_offset, double min_z, double max_x, double max_y_offset, double max_z, int amount, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < modified_try_count(amount, particle); ++i) {
                var randx = Mth.lerp(Math.abs(Math.random()), min_x, max_x);
                var randz = Mth.lerp(Math.abs(Math.random()), min_z, max_z);
                var height = (double) level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) Math.round(randx), (int) Math.round(randz));
                var randy = Mth.lerp(Math.abs(Math.random()), min_y_offset, max_y_offset) + height;
                var pos = new BlockPos((int) randx, (int) randy, (int) randz);
                level.addParticle(particle.get(), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
            }
        }
    }
    public static void spawn_at_provided(ArrayList<BlockPos> positions, int amount, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < modified_try_count(amount, particle); ++i) {
                var pos = positions.get((int) Math.round((Math.abs(Math.random()) * positions.size())));
                level.addParticle(particle.get(), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
            }
        }
    }

    public static void spawn_at(BlockPos pos, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            level.addParticle(particle.get(), pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
        }
    }
    public static void spawn_around_player_in_height_range(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int min_height, int max_height, int amount, RegistryReference<SimpleParticleType> particle) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            var min = player.position().add(min_x, 0, min_z);
            var max = player.position().add(max_x, 0, max_z);
            spawn_in_area_in_height_range(min.x, min_height, min.z, max.x, max_height, max.z, min_height, max_height, modified_try_count(amount, particle), particle);
        }
    }
    public static void spawn_around_player_with_air(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int amount, RegistryReference<SimpleParticleType> particle) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            var min = player.position().add(min_x, min_y, min_z);
            var max = player.position().add(max_x, max_y, max_z);
            spawn_in_area_with_air(min.x, min.y, min.z, max.x, max.y, max.z, modified_try_count(amount, particle), particle);
        }
    }
    public static void spawn_around_player_heightmap(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, double amount, RegistryReference<SimpleParticleType> particle) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            var min = player.position().add(min_x, 0, min_z);
            var max = player.position().add(max_x, 0, max_z);

            spawn_in_area_heightmap(min.x, min_y, min.z, max.x, max_y, max.z, modified_try_count(amount, particle), particle);
        }
    }
    public static void spawn_around_player(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int amount, RegistryReference<SimpleParticleType> particle) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            var min = player.position().add(min_x, min_y, min_z);
            var max = player.position().add(max_x, max_y, max_z);
            spawn_in_area(min.x, min.y, min.z, max.x, max.y, max.z, modified_try_count(amount, particle), particle);
        }
    }

    public static void spawn_in_area_in_height_range(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int min_height, int max_height, int amount, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;

        if (level != null) {
            for (int i = 0; i < modified_try_count(amount, particle); ++i) {
                var randx = Mth.lerp(Math.abs(Math.random()), min_x, max_x);
                var randy = Mth.lerp(Math.abs(Math.random()), min_y, max_y);
                var randz = Mth.lerp(Math.abs(Math.random()), min_z, max_z);
                var is_air = !level.getBlockState(new BlockPos(new Vec3i((int) Math.round(randx), (int) Math.round(randy), (int) Math.round(randz)))).blocksMotion();
                var in_height_range = (randy >= min_height && randy <= max_height);
                if (is_air && in_height_range) {
                    level.addParticle(particle.get(), randx, randy, randz, 0, 0, 0);
                }
            }
        }
    }
    public static void spawn_in_area_with_air(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int amount, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < modified_try_count(amount, particle); ++i) {
                var randx = Mth.lerp(Math.abs(Math.random()), min_x, max_x);
                var randy = Mth.lerp(Math.abs(Math.random()), min_y, max_y);
                var randz = Mth.lerp(Math.abs(Math.random()), min_z, max_z);
                if (!level.getBlockState(new BlockPos(new Vec3i((int) Math.round(randx), (int) Math.round(randy), (int) Math.round(randz)))).blocksMotion() && level.getFluidState(new BlockPos(new Vec3i((int) Math.round(randx), (int) Math.round(randy), (int) Math.round(randz)))).isEmpty()) {
                    level.addParticle(particle.get(), randx, randy, randz, 0, 0, 0);
                }
            }
        }
    }
    public static void spawn_in_area(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z, int amount, RegistryReference<SimpleParticleType> particle) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            for (int i = 0; i < modified_try_count(amount, particle); ++i) {
                var randx = Mth.lerp(Math.abs(Math.random()), min_x, max_x);
                var randy = Mth.lerp(Math.abs(Math.random()), min_y, max_y);
                var randz = Mth.lerp(Math.abs(Math.random()), min_z, max_z);
                level.addParticle(particle.get(), randx, randy, randz, 0, 0, 0);
            }
        }
    }
}
