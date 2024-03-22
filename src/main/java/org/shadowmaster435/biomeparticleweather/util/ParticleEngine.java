package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
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
        }
    }

    public static void rain() {
        var player_pos = ParticleUtil.get_player_pos_plus_vel(2);
        var world = MinecraftClient.getInstance().world;
        if (world.isRaining() && ParticleUtil.can_rain()) {
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
        if (world.isRaining() && (biome.matchesKey(BiomeKeys.SAVANNA) || biome.matchesKey(BiomeKeys.SAVANNA_PLATEAU))) {
            var player_pos = ParticleUtil.get_player_pos_plus_vel(2);

            for (int i = 0; i < ParticleSettings.get_int("wind_amount"); ++i) {
                var rand = get_rand_radius();
                var pos = new Vector3(player_pos.add(new Vector3(rand.x, rand.y / 4, rand.z)));
                var a = ParticleUtil.get_world().isAir(pos.to_blockpos());
                var b = ParticleUtil.get_world().isAir(pos.to_blockpos().offset(Direction.Axis.X, -1));
                var c = ParticleUtil.get_world().isAir(pos.to_blockpos().offset(Direction.Axis.X, -2));
                if (a && b && c) {
                    spawn_particle(BiomeParticleWeather.WIND, pos);
                }
            }
        }
    }

    public static void spawn_particle_on_surface(ParticleEffect effect, double x, double z, double ypos, float y_offset, Vector3 rand_ofs) {
        var world = ParticleUtil.get_world();
        var pos = new Vector3(world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, BlockPos.ofFloored(x, ypos, z))).add(Vector3.rand_between(rand_ofs.negate(), rand_ofs));
        spawn_particle(effect, new Vector3(pos.getX(), pos.getY() + y_offset, pos.getZ()));
    }

    public static void spawn_particle(ParticleEffect effect, Vector3 pos) {
        assert MinecraftClient.getInstance().world != null;
        MinecraftClient.getInstance().world.addParticle(effect, pos.x, pos.y, pos.z, 0, 0, 0);
    }

    public static Vector3 get_rand_radius() {
        return Vector3.rand_between(new Vector3(-ParticleSettings.get_int("spawn_radius")), new Vector3(ParticleSettings.get_int("spawn_radius")));
    }

}
