package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2f;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class WarpedSpore extends ParticleBase {

    public final Vector2f vel_vec;
    public WarpedSpore(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 100;
        alpha = 0;
        vel_vec = new Vector2f(MathHelper.nextBetween(Random.createLocal(), 0f, 1f), MathHelper.nextBetween(Random.createLocal(), 0f, 1f)).normalize();
        fade_alpha(1, 10);
        setSprite(spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        velocityY = -0.05;
        var sine = Math.sin(age / 4.0f);
        velocityX = sine * vel_vec.x;
        velocityZ = sine * vel_vec.y;
        if (Math.random() > 0.8) {
            ParticleEngine.spawn_particle(ParticleTypes.PORTAL, get_pos());
        }
        angular_velocity = (float) sine;
        if (onGround && age < 90) {
            age = 90;
        }
        if (age == 90) {
            fade_scale(0, 10);
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
            WarpedSpore particle = new WarpedSpore(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }

}
