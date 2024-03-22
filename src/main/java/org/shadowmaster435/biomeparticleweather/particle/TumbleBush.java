package org.shadowmaster435.biomeparticleweather.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;

import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.shadowmaster435.biomeparticleweather.util.ParticleUtil;
import org.shadowmaster435.biomeparticleweather.util.Vector3;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleModelPart;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleVoxel;
import org.shadowmaster435.biomeparticleweather.util.particle_model.VoxelTransform;
import org.shadowmaster435.biomeparticleweather.util.particle_model.VoxelUVMap;

public class TumbleBush extends ModelParticleBase {

    private int current_sprite = 0;

    public float current_rot = 0;
    public TumbleBush(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 600;
        scale = 0.5f;
        current_sprite = (int) Math.round(Math.random() * 3);
        collidesWithWorld = true;
        setBoundingBoxSpacing(0.005f, 0.005f);
        make_model();
    }

    public void make_model() {
        var uv = VoxelUVMap.rectangle(new Vector2i(0, 0), new Vector2i(8,8));
        var outer_voxel = ParticleVoxel.create(new Vector3(-0.5, -0.5, -0.5),  new Vector3(new Vector3(4, 4, 4).multiply(0.25f)), uv);
        var outer = new ParticleModelPart(new ParticleVoxel[]{outer_voxel}, outer_voxel.params());
        var inner_voxel = ParticleVoxel.create(new Vector3(-0.25, -0.25, -0.25),  new Vector3(new Vector3(2, 2, 2).multiply(0.25f)), uv);
        var inner = new ParticleModelPart(new ParticleVoxel[]{inner_voxel}, inner_voxel.params());
        set_model(new String[]{"outer", "inner"}, new ParticleModelPart[]{outer, inner});

    }

    private boolean hit_wall = false;



    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        super.buildGeometry(vertexConsumer, camera, tickDelta);
        if (!MinecraftClient.getInstance().isPaused()) {
            if (!hit_wall) {
                current_rot += 0.25F * tickDelta;
            } else {
                current_rot -= ((0.125F * tickDelta) * (1 - (float) ((age - 540) / 60)));
            }
            transform = new VoxelTransform(transform.position(), Vector3.ONE, new Vector3(0, 0, -current_rot), Vector3.ZERO);

        }
    }

    public void tick() {
        super.tick();
        velocityY -= 0.05;

        if (!hit_wall) {
            velocityX = 0.25;

        }
        if (is_colliding()) {
             var dir = get_collision_direction();
             bounce();
             if (hit_wall) {
                 velocityY *= 0.55f;
             }
             if (dir.getAxis().isHorizontal()) {
                 hit_wall = true;
                 age = 540;
                 fade_alpha(0, 40);
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
            TumbleBush particle = new TumbleBush(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
