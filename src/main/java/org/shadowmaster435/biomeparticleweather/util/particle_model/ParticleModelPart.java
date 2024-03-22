package org.shadowmaster435.biomeparticleweather.util.particle_model;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.model.SpriteAtlasManager;
import net.minecraft.client.render.model.json.ItemModelGenerator;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.SpriteAtlasHolder;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.client.texture.atlas.AtlasSourceManager;
import net.minecraft.client.util.RawTextureDataLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.particle.ModelParticleBase;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public record ParticleModelPart(ParticleVoxel[] voxels, ParticleVoxelParams params) {

    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ModelParticleBase particle, VoxelTransform transform) {
        RenderSystem.disableCull();
        for (ParticleVoxel val : voxels) {
            voxel(vertexConsumer, camera, tickDelta, particle, ParticleVoxelParams.transform_by(params, transform), val);
        }

    }

    private void voxel(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ModelParticleBase particle, ParticleVoxelParams params, ParticleVoxel voxel) {
        for (Direction dir : Direction.values()) {
            quad(vertexConsumer, camera, tickDelta, particle, params, voxel.faces().get(dir));
        }
    }






    private void quad(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ModelParticleBase particle, ParticleVoxelParams params, VoxelFace face) {
        Vec3d vec3d = camera.getPos();
        var tf = params.transform();

        float drawx = (float) ((float) (MathHelper.lerp(tickDelta, particle.prevPosX, particle.x) - vec3d.getX()));
        float drawy = (float) ((float) (MathHelper.lerp(tickDelta, particle.prevPosY, particle.y) - vec3d.getY()));
        float drawz = (float) ((float) (MathHelper.lerp(tickDelta, particle.prevPosZ, particle.z) - vec3d.getZ()));
        float scale = particle.getSize(tickDelta);
        Vector3f[] corners = face.verticies().create_array(tf);
        for (int j = 0; j < 4; ++j) {
            Vector3f corner = corners[j];
            corner.rotateX((float) tf.rotation().x);
            corner.rotateY((float) tf.rotation().y);
            corner.rotateZ((float) tf.rotation().z);
            corner.mul(scale);

            corner.add(drawx, drawy, drawz).add(tf.origin().to_3f());


        }
      //  System.out.println(Arrays.toString(Arrays.stream(corners).toArray()));
/*

        float k = face.uv().start().x / 32f;
        float l = face.uv().end().x / 32f;
        float m = face.uv().start().y / 32f;
        float n = face.uv().end().y / 32f;  - (face.uv().start().x / 32f)*/
        float k = (float) (face.uv().start().x + particle.sprite.getX()) / particle.provider.getAtlas().width;
        float l = (float) (face.uv().end().x + particle.sprite.getX()) / particle.provider.getAtlas().width;
        float m = (float) (face.uv().start().y + particle.sprite.getY()) / particle.provider.getAtlas().height;
        float n = (float) (face.uv().end().y + particle.sprite.getY()) / particle.provider.getAtlas().height;
        int o = particle.getBrightness(tickDelta);
        var consumer = particle.sprite.getTextureSpecificVertexConsumer(vertexConsumer);
        vertexConsumer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).texture(l, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).texture(l, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).texture(k, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).texture(k, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
    }

}
