package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class BlizzardSnow extends ParticleBase {
    public BlizzardSnow(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 20;
        alpha = 0;
        angular_velocity = 32;
        fade_alpha(1, 10);
        setSprite(spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        velocityZ = -0.75;

        if (age == 10) {
            fade_alpha(0, 10);
        }
        if (in_block()) {
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
            BlizzardSnow particle = new BlizzardSnow(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }

}
