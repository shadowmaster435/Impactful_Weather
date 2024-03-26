package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class Rain extends ParticleBase {

    public Rain(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        assert MinecraftClient.getInstance().world != null;
        maxAge = 200;
        alpha = 0;

        fade_alpha(0.8f, 2);
        collidesWithWorld = true;
    }

    public void tick() {
        super.tick();
        scale = 0.125f;

        velocityY = -1;
        var trails = ParticleSettings.get_bool("rain_trails");
        var splash = ParticleSettings.get_bool("rain_splash");
        var ripples = ParticleSettings.get_bool("rain_surface_ripples");

        setSprite(provider.getSprite(Random.create(random_seed)));
        if (alpha >= 0.8f && (get_pos().y > MinecraftClient.getInstance().player.getPos().y - 8) && trails) {
            ParticleEngine.spawn_particle(BiomeParticleWeather.RAIN_TRAIL, new Vector3(get_pos().add(new Vector3(0, 0.01, 0))));
        }
        BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
        FluidState fluidState = this.world.getFluidState(blockPos);

        if (!fluidState.isEmpty()) {
            var height = fluidState.getHeight();
            if (splash) {
                ParticleEngine.spawn_particle(BiomeParticleWeather.RAIN_SPLASH, new Vector3(x, blockPos.getY() + height, z));

            }
            if (ripples) {
                ParticleEngine.spawn_particle(BiomeParticleWeather.RAIN_RIPPLE, new Vector3(x, blockPos.getY() + height, z));
            }
            markDead();
        } else {
            if (onGround && get_collision_direction().name().equals("UP")) {
                if (splash) {
                    ParticleEngine.spawn_particle(BiomeParticleWeather.RAIN_SPLASH, new Vector3(get_hit_pos().add(new Vec3d(0, 1.0625, 0))));
                }
                markDead();
            }
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
            Rain particle = new Rain(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
