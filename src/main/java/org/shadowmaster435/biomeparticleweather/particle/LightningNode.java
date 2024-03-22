package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class LightningNode extends ParticleBase {
    public LightningNode(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 5;
        alpha = 0.0f;
        var lightning_range = 4;
        var rand_pos = Vector3.rand_between(new Vector3(pos.x -lightning_range,0,pos.z-lightning_range), new Vector3(pos.x + lightning_range, 0, pos.z +lightning_range));
        rand_pos = new Vector3(rand_pos.x, pos.y + 12, rand_pos.z);
        if (Math.random() < 0.75 && pos.y > 150) {
            var branch_multiplier = 4;
            var rand_pos2 = Vector3.rand_between(new Vector3(pos.x -lightning_range * branch_multiplier, pos.y - 4,pos.z -lightning_range * branch_multiplier), new Vector3(pos.x + lightning_range * branch_multiplier, pos.y + 4, pos.z +lightning_range * branch_multiplier));
            for (int i = 0; i < ParticleSettings.get_int("lightning_particle_density"); ++i) {
                var delta = (i + 0.001) / 32;
                var lerped = rand_pos.lerp(rand_pos2, delta);
                ParticleEngine.spawn_particle(BiomeParticleWeather.LIGHTNING, new Vector3(lerped));
            }

        }
        for (int i = 0; i < 16; ++i) {
            var delta = (i + 0.001) / 16;
            var lerped = rand_pos.lerp(pos, delta);
            ParticleEngine.spawn_particle(BiomeParticleWeather.LIGHTNING, new Vector3(lerped));

        }
        if (rand_pos.y < 192) {
            ParticleEngine.spawn_particle(BiomeParticleWeather.LIGHTNING_NODE, rand_pos);
        }
    }

    public void tick() {


        super.tick();
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final FabricSpriteProvider spriteProvider;
        public Factory(FabricSpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            LightningNode particle = new LightningNode(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
