package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.particle.ParticleBase;

public class ParticleEngine {
    public static void try_spawn_particles() {
        var client = MinecraftClient.getInstance();
        var player = client.player;
        if (player != null && !MinecraftClient.getInstance().isPaused()) {

            rain();
            fog();
            wind();
            sandstorm();
            snow();
            updraft();
            warped_spore();
            weeping_tears();
            soul_storm();
        }
    }

    public static void rain() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        if (world.isRaining() && ParticleUtil.can_rain(player_pos)) {
            var thunder_amount = ParticleSettings.get_int("heavy_rain_amount");
            var amount = ParticleSettings.get_int("rain_amount");
            if (world.isThundering()) {
                amount = thunder_amount;
            }
            for (int i = 0; i < amount; ++i) {
                var rand = get_rand_radius();
                var pos = new Vector3(player_pos.add(new Vector3(rand.x, 8, rand.z)));
                if (pos.subtract(0, 8, 0).distanceTo(player_pos) > 1) {
                    spawn_particle(BiomeParticleWeather.RAIN, pos);
                }
            }
        }
    }

    public static void sandstorm() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        var a = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.DESERT);
        var b = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.BADLANDS);
        var c = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.ERODED_BADLANDS);
        var d = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.WOODED_BADLANDS);
        var normal_desert = !(b || c || d);
        if (world.isRaining() && (a || b || c || d)) {
            var red_sand_amount = ParticleSettings.get_int("red_sand_mote_amount");
            var sand_amount = ParticleSettings.get_int("sand_mote_amount");
            var tumblebush_amount = ParticleSettings.get_int("tumblebush_amount");

            var rand_offset = player_pos.with_random_offset(16);
            if (world.getBlockState(rand_offset.to_blockpos()).isAir()) {
                if (normal_desert) {
                    try_spawn_particle_in_height_range(BiomeParticleWeather.SAND_MOTE, player_pos, new Vector3(16, 8, 16), sand_amount, 63, 196);
                } else {
                    try_spawn_particle_in_height_range(BiomeParticleWeather.RED_SAND_MOTE, player_pos, new Vector3(16, 8, 16), red_sand_amount, 63, 196);
                }
                for (int i = 0; i < tumblebush_amount; ++i) {
                    spawn_particle_on_surface(BiomeParticleWeather.TUMBLE_BUSH, player_pos.x - 8, player_pos.z, 0, 1.25f, new Vector3(16));
                }
            }

        }
    }

    public static void weeping_tears() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;

        if (world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.CRIMSON_FOREST)) {
            var amount = ParticleSettings.get_int("weeping_tear_amount");
            try_spawn_particle_under_block(BiomeParticleWeather.WEEPING_TEAR, Blocks.NETHER_WART_BLOCK, player_pos, Math.round(amount * 0.75f));
            try_spawn_particle_under_block(BiomeParticleWeather.WEEPING_TEAR, Blocks.CRIMSON_FUNGUS, player_pos, Math.round(amount * 0.25f));

        }

    }

    public static void warped_spore() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;

        if (world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.WARPED_FOREST)) {
            var amount = ParticleSettings.get_int("warped_spore_amount");

            try_spawn_particle(BiomeParticleWeather.WARPED_SPORE, player_pos, 16, amount);
        }

    }

    public static void soul_storm() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        if (world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.SOUL_SAND_VALLEY)) {
            var amount = ParticleSettings.get_int("soul_amount");

            try_spawn_particle_above_block(BiomeParticleWeather.STORM_SOUL, Blocks.SOUL_SAND, player_pos, 16, 4, (int) Math.floor(amount * 0.5f));
            try_spawn_particle_above_block(BiomeParticleWeather.STORM_SOUL, Blocks.SOUL_SOIL, player_pos, 16, 4, (int) Math.ceil(amount * 0.5f));

        }
    }

    public static void snow() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        var rand_ofs = player_pos.with_random_offset(16);
        if (world.getBiome(rand_ofs.to_blockpos()).value().getPrecipitation(rand_ofs.to_blockpos()) == Biome.Precipitation.SNOW) {
            var snow_amount = ParticleSettings.get_int("snow_amount");
            var blizzard_snow_amount = ParticleSettings.get_int("blizzard_snow_amount");
            var blizzard_wind_amount = ParticleSettings.get_int("blizzard_wind_amount");

            if (world.isThundering()) {
                try_spawn_particle(BiomeParticleWeather.BLIZZARD_SNOW, new Vector3(player_pos.add(0,0,8)), new Vector3(16, 8), blizzard_snow_amount);
                try_spawn_particle(BiomeParticleWeather.BLIZZARD_WIND, player_pos, new Vector3(16, 8), blizzard_wind_amount);

            } else {
                try_spawn_particle(BiomeParticleWeather.SNOW, player_pos.with_y(player_pos.y + 8), new Vector3(16, 0), snow_amount);

            }
        }
    }

    public static void updraft() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        var a = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.BASALT_DELTAS);
        var b = world.getBiome(player_pos.to_blockpos()).matchesKey(BiomeKeys.NETHER_WASTES);
        if (a || b) {
            try_spawn_particle(BiomeParticleWeather.UPDRAFT, player_pos, new Vector3(16, 8), ParticleSettings.get_int("updraft_amount"));
        }
    }

    public static void fog() {
        var biome = ParticleUtil.get_biome_at(BlockPos.ofFloored(ParticleUtil.get_player_pos_plus_vel(2)));
        if ((biome.matchesKey(BiomeKeys.SWAMP) || biome.matchesKey(BiomeKeys.MANGROVE_SWAMP)) && ParticleUtil.get_world().isRaining()) {
            var player_pos = ParticleUtil.get_player_pos_plus_vel(2);

            for (int i = 0; i < ParticleSettings.get_int("fog_amount"); ++i) {
                var rand = get_rand_radius();
                var pos = new Vector3(player_pos.add(new Vector3(rand.x, 8, rand.z)));
                spawn_particle_on_surface(BiomeParticleWeather.FOG, pos.x, pos.z, pos.y, 0.25f, new Vector3(0.25));
            }
        }
    }

    public static void wind() {
        var biome = ParticleUtil.get_biome_at(BlockPos.ofFloored(ParticleUtil.get_player_pos_plus_vel(2)));
        var world = MinecraftClient.getInstance().world;
        var a1 = (biome.matchesKey(BiomeKeys.SAVANNA) || biome.matchesKey(BiomeKeys.SAVANNA_PLATEAU));
        var b1 = biome.matchesKey(BiomeKeys.WINDSWEPT_SAVANNA) ;
        if (world.isRaining() && (a1 || b1)) {
            var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
            spawn_particle(BiomeParticleWeather.WIND, player_pos, ParticleSettings.get_int("wind_amount"), new Vector3(16, 8, 16));
        }
    }

    public static void spawn_particle_on_surface(ParticleEffect effect, double x, double z, double ypos, float y_offset, Vector3 rand_ofs) {
        var world = ParticleUtil.get_world();
        var pos = new Vector3(world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, BlockPos.ofFloored(x, ypos, z))).add(Vector3.rand_between(rand_ofs.negate(), rand_ofs).with_y(0));
        spawn_particle(effect, new Vector3(pos.getX(), pos.getY() + y_offset, pos.getZ()));
    }

    public static void try_spawn_particle_in_height_range(ParticleEffect effect, Vector3 pos_origin, Vector3 max_ofs, int amount, float min_y, float max_y) {
        var world = MinecraftClient.getInstance().world;
        for (int i = 0; i < amount; ++i) {
            var pos = pos_origin.with_random_offset(max_ofs);
            var bp = pos.to_blockpos();
            var block_at = world.getBlockState(bp);
            var is_not_solid = !block_at.isSolidBlock(world, bp);
            var in_range = pos.y > min_y && pos.y < max_y;
            if (is_not_solid && in_range) {
                spawn_particle(effect, pos);
            }
        }
    }
    public static void try_spawn_particle(ParticleEffect effect, Vector3 pos_origin, Vector3 max_ofs, int amount) {
        var world = MinecraftClient.getInstance().world;
        for (int i = 0; i < amount; ++i) {
            var pos = pos_origin.with_random_offset(max_ofs);
            var bp = pos.to_blockpos();
            var block_at = world.getBlockState(bp);
            var is_not_solid = !block_at.isSolidBlock(world, bp);
            if (is_not_solid) {
                spawn_particle(effect, pos);
            }
        }
    }

    public static void try_spawn_particle(ParticleEffect effect, Vector3 pos_origin, float max_ofs, int amount) {
        var world = MinecraftClient.getInstance().world;
        for (int i = 0; i < amount; ++i) {
            var pos = pos_origin.with_random_offset(max_ofs);
            var bp = pos.to_blockpos();
            var block_at = world.getBlockState(bp);
            var is_not_solid = !block_at.isSolidBlock(world, bp);
            if (is_not_solid) {
                spawn_particle(effect, pos);
            }
        }
    }
    public static void try_spawn_particle_under_block(ParticleEffect effect, Block block, Vector3 pos, int tries) {
        var world = ParticleUtil.get_world();
        for (int i = 0; i < tries; ++i) {
            var ofs = pos.with_random_offset(new Vector3(16, 16, 16));
            var bs = world.getBlockState(ofs.to_blockpos());
            var bs_under = world.getBlockState(ofs.to_blockpos().offset(Direction.DOWN));
            var under_pos = ofs.with_y(Math.floor(ofs.y) - 0.05f);
            if (bs.isOf(block) && bs_under.isAir()) {

                spawn_particle(effect, under_pos);
            }
        }
    }


    public static void try_spawn_particle_above_block(ParticleEffect effect, Block block, Vector3 pos, int y_ofs, int tries) {
        var world = ParticleUtil.get_world();
        for (int i = 0; i < tries; ++i) {
            var bs = world.getBlockState(pos.to_blockpos());
            var bs_over = world.getBlockState(pos.to_blockpos().offset(Direction.UP, y_ofs));
            var over_pos = pos.with_y(Math.floor(pos.y) + y_ofs);

            if (bs.isOf(block) && bs_over.isAir()) {
                spawn_particle(effect, over_pos);
            }
        }
    }

    public static void try_spawn_particle_above_block(ParticleEffect effect, Block block, Vector3 pos_origin, int max_ofs, int y_ofs, int tries) {
        var world = ParticleUtil.get_world();
        for (int i = 0; i < tries; ++i) {
            var pos = pos_origin.with_random_offset(new Vector3(max_ofs));
            var bs = world.getBlockState(pos.to_blockpos());
            var bs_over = world.getBlockState(pos.to_blockpos().offset(Direction.UP, y_ofs));
            var over_pos = pos.with_y(Math.floor(pos.y) + y_ofs);

            if (bs.isOf(block) && bs_over.isAir()) {
                spawn_particle(effect, over_pos);
            }
        }
    }

    public static void spawn_particle(ParticleEffect effect, Vector3 pos) {
        MinecraftClient.getInstance().world.addParticle(effect, pos.x, pos.y, pos.z, 0, 0, 0);
    }
    public static void spawn_particle(ParticleEffect effect, Vector3 pos_origin, int tries, float max_ofs) {
        for (int i = 0; i < tries; ++i) {
            var pos = pos_origin.with_random_offset(max_ofs);
            MinecraftClient.getInstance().world.addParticle(effect, pos.x, pos.y, pos.z, 0, 0, 0);

        }
    }

    public static void spawn_particle(ParticleEffect effect, Vector3 pos_origin, int tries, Vector3 max_ofs) {
        for (int i = 0; i < tries; ++i) {
            var pos = pos_origin.with_random_offset(max_ofs);
            MinecraftClient.getInstance().world.addParticle(effect, pos.x, pos.y, pos.z, 0, 0, 0);

        }
    }

    public static Vector3 get_rand_radius() {
        return Vector3.rand_between(new Vector3(-ParticleSettings.get_int("spawn_radius")), new Vector3(ParticleSettings.get_int("spawn_radius")));
    }

}
