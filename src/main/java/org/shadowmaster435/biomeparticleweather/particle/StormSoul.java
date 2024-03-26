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

public class StormSoul extends ParticleBase {
    public StormSoul(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 100;
        alpha = 0;
        collidesWithWorld = true;
        scale = 0.35f;
        fade_alpha(1, 10);
        setBoundingBoxSpacing(0.05f, 0.25f);
        setSpriteForAge(spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        velocityY = -0.1;
        if (onGround && age < 90) {
            ParticleEngine.spawn_particle(BiomeParticleWeather.STORM_SOUL_IMPACT, get_pos().with_y(get_pos().y + .35f));
            markDead();
        }
        setSprite(provider.getSprite(((age * 3) + ((int) world.getTime() % 2000)) % maxAge, maxAge));


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
            StormSoul particle = new StormSoul(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }

}
