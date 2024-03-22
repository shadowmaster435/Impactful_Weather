package org.shadowmaster435.biomeparticleweather.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.data.client.Model;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;
import org.shadowmaster435.biomeparticleweather.util.Vector3;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleModelPart;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleVoxel;
import org.shadowmaster435.biomeparticleweather.util.particle_model.VoxelTransform;
import org.shadowmaster435.biomeparticleweather.util.particle_model.VoxelUVMap;

public class Firefly extends ModelParticleBase {

    public Firefly(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        maxAge = 100;
        scale = 0.25f;
        make_model();
    }

    public void make_model() {
        var body_uv = VoxelUVMap.rectangle(new Vector2i(0, 0), new Vector2i(4,2));
        var body_voxel = ParticleVoxel.create(Vector3.ZERO,  new Vector3(new Vector3(4, 2, 2).multiply(0.25f)), body_uv);
        var body = new ParticleModelPart(new ParticleVoxel[]{body_voxel}, body_voxel.params());
        var lwing_uv = VoxelUVMap.rectangle(new Vector2i(0, 2), new Vector2i(2,2));
        var lwing_voxel = ParticleVoxel.create(new Vector3(0.25, 0.5, 0.5),  new Vector3(new Vector3(2, 0, 2).multiply(0.25f)), lwing_uv);
        var lwing = new ParticleModelPart(new ParticleVoxel[]{lwing_voxel}, lwing_voxel.params());
        var rwing_uv = VoxelUVMap.rectangle(new Vector2i(0, 2), new Vector2i(2,2));
        var rwing_voxel = ParticleVoxel.create(new Vector3(-0.5, 0.0, -0.5),  new Vector3(new Vector3(2, 0, 2).multiply(0.25f)), rwing_uv);
        var rwing = new ParticleModelPart(new ParticleVoxel[]{rwing_voxel}, rwing_voxel.params());
        set_model(new String[]{"body", "rwing", "lwing"}, new ParticleModelPart[]{body, rwing, lwing});
    }

    public void tick() {
        super.tick();
       // rotate(new Vector3(360,415,212));
        velocityX = 0.05;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        RenderSystem.enableCull();
        var rwing = get_model().get("rwing");
        var lwing = get_model().get("lwing");
        var body = get_model().get("body");

        var sine = Math.sin((age + tickDelta)) * 25;new Vector3(0.25, 0.5, -0.5); //Math.toRadians(sine)
        var lwingtf = new VoxelTransform(new Vector3(0.25,0,0), Vector3.ONE, new Vector3(Math.toRadians(sine), 0, 0), new Vector3(0.125, 0.125, 0.0));
        var rwingtf = new VoxelTransform(Vector3.ZERO, Vector3.ONE, new Vector3(-sine, 0, 0), Vector3.ZERO);
        lwing.render(vertexConsumer, camera, tickDelta, this, rwingtf);
      //  lwing.render(vertexConsumer, camera, tickDelta, this, VoxelTransform.transform_by(lwingtf, get_transform()));
        body.render(vertexConsumer, camera, tickDelta, this, get_transform());
        RenderSystem.disableCull();
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
            Firefly particle = new Firefly(world, new Vector3(x, y, z), spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
