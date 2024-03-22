package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;
import org.shadowmaster435.biomeparticleweather.util.RenderHelper;
import org.shadowmaster435.biomeparticleweather.util.RenderParams;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class BlizzardWind extends ParticleBase {
    public BlizzardWind(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);

        maxAge = 16;
        alpha = 0.0f;

        scale = 0.75f;
        setSpriteForAge(provider);

    }

    @Override
    public void tick() {
        super.tick();
        alpha = 0.25f;

        setSpriteForAge(provider);
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        RenderHelper.render_particle_quad(vertexConsumer, camera, tickDelta, this, new RenderParams(Vector3.ZERO.to_3f(), 0, 1), new Vector3(90,90,180));

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
            BlizzardWind particle = new BlizzardWind(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
