package org.shadowmaster435.biomeparticleweather.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.util.ParticleUtil;
import org.shadowmaster435.biomeparticleweather.util.RenderHelper;
import org.shadowmaster435.biomeparticleweather.util.RenderParams;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class Fog extends ParticleBase{
    public Fog(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 40;
        alpha = 0.0f;
        scale = MathHelper.nextBetween(Random.createLocal(), 0.6f, 1.0f);
        fade_alpha(0.45f, 10);
        angular_velocity = (float) Math.random();
    }

    public void tick() {
        var distance = MinecraftClient.getInstance().player.getPos().distanceTo(get_pos());
        var fade_delta = 1.0f - (distance / 16.0);
        if (age == 10) {
            fade_alpha(0.45f, 20);
        }
        if (age == 30) {
            fade_alpha(.0f, 10);
        }
        if (distance > 16 || ParticleUtil.get_world().getBlockState(BlockPos.ofFloored(get_pos())).isSolid()) {
            alpha = 0;
            markDead();
        } else {

            /*alpha = (float) fade_delta / 4.0f;
            var max_distance = 3;
            if (distance < max_distance) {
                alpha -= (float) ((max_distance - distance) / (4 * max_distance));
            }*/

        }
        super.tick();
    }



    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        for (int i = 1; i < 2; ++i) {
            var delta = (((float) i) / 4) * Math.PI;
            var life_delta = (((((float) age) + 0.001) / maxAge)) * Math.PI;
            var y_offset = (float) Math.sin(((delta + life_delta) % (Math.PI * 2)) - Math.PI) / 8;
            var params = new RenderParams(new Vector3f(0, 0, 0), 25 * i, scale / i);

            RenderHelper.render_particle_quad(vertexConsumer, camera, tickDelta, this, params);

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
            Fog particle = new Fog(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
