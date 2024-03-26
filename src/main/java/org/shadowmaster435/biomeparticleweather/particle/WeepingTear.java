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
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class WeepingTear extends ParticleBase {
    public WeepingTear(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 100;
        setSprite(spriteProvider);
        collidesWithWorld = false;
    }

    @Override
    public void tick() {
        super.tick();
        if (age < 10) {
            velocityY = -0.01;
        } else {
            velocityY = -1;
            collidesWithWorld = true;
        }
        if (onGround) {
            ParticleEngine.spawn_particle(BiomeParticleWeather.WEEPING_TEAR_SPLASH, get_pos());
            markDead();
        }

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
            WeepingTear particle = new WeepingTear(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }

}
