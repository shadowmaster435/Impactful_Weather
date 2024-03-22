package org.shadowmaster435.biomeparticleweather.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.shadowmaster435.biomeparticleweather.particle.ParticleBase;

import java.util.Arrays;

public class RenderHelper {
    
    public static void render_particle_quad(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ParticleBase particle) {
        Vec3d vec3d = camera.getPos();
        float drawx = (float)(MathHelper.lerp(tickDelta, particle.prevPosX, particle.x) - vec3d.getX());
        float drawy = (float)(MathHelper.lerp(tickDelta, particle.prevPosY, particle.y) - vec3d.getY());
        float drawz = (float)(MathHelper.lerp(tickDelta, particle.prevPosZ, particle.z) - vec3d.getZ());
        float scale = particle.getSize(tickDelta);
        Vector3f[] corners = new Vector3f[]{new Vector3f(-1.0F, 0F, -1.0F), new Vector3f(-1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, -1.0F)};
        for(int j = 0; j < 4; ++j) {
            Vector3f corner = corners[j];
            corner.mul(scale);
            corner.add(drawx, drawy, drawz);
        }

        float k = particle.getMinU();
        float l = particle.getMaxU();
        float m = particle.getMinV();
        float n = particle.getMaxV();
        int o = particle.getBrightness(tickDelta);
        vertexConsumer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).texture(l, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).texture(l, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).texture(k, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).texture(k, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();

    }

    public static void render_particle_quad(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ParticleBase particle, RenderParams params) {
        Vec3d vec3d = camera.getPos();
        float drawx = (float)(MathHelper.lerp(tickDelta, particle.prevPosX, particle.x) - vec3d.getX());
        float drawy = (float)(MathHelper.lerp(tickDelta, particle.prevPosY, particle.y) - vec3d.getY());
        float drawz = (float)(MathHelper.lerp(tickDelta, particle.prevPosZ, particle.z) - vec3d.getZ());
        float scale = particle.getSize(tickDelta);
        Vector3f[] corners = new Vector3f[]{new Vector3f(-1.0F, 0F, -1.0F), new Vector3f(-1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, -1.0F)};
        for(int j = 0; j < 4; ++j) {
            Vector3f corner = corners[j];
            corner.mul(scale);
            corner.rotateY((float) ((particle.angle) + Math.toRadians(params.rotation_offset())));
            corner.add(drawx + params.offset().x, drawy + params.offset().y, drawz + params.offset().z);
        }
        float k = particle.getMinU();
        float l = particle.getMaxU();
        float m = particle.getMinV();
        float n = particle.getMaxV();
        int o = particle.getBrightness(tickDelta);
        vertexConsumer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).texture(l, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).texture(l, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).texture(k, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).texture(k, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();

    }
    public static void render_particle_quad(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ParticleBase particle, RenderParams params, Vector3 rot) {
        RenderSystem.disableCull();
        Vec3d vec3d = camera.getPos();
        float drawx = (float)(MathHelper.lerp(tickDelta, particle.prevPosX, particle.x) - vec3d.getX());
        float drawy = (float)(MathHelper.lerp(tickDelta, particle.prevPosY, particle.y) - vec3d.getY());
        float drawz = (float)(MathHelper.lerp(tickDelta, particle.prevPosZ, particle.z) - vec3d.getZ());
        float scale = particle.getSize(tickDelta);
        Vector3f[] corners = new Vector3f[]{new Vector3f(-1.0F, 0F, -1.0F), new Vector3f(-1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, 1.0F), new Vector3f(1.0F, 0.0F, -1.0F)};
        for(int j = 0; j < 4; ++j) {
            Vector3f corner = corners[j];
            corner.mul(scale);
            corner.rotateX((float) ((particle.angle) + Math.toRadians(rot.x)));
            corner.rotateY((float) ((particle.angle) + Math.toRadians(rot.y)));
            corner.rotateZ((float) ((particle.angle) + Math.toRadians(rot.z)));
            corner.add(drawx + params.offset().x, drawy + params.offset().y, drawz + params.offset().z);
        }
        float k = particle.getMinU();
        float l = particle.getMaxU();
        float m = particle.getMinV();
        float n = particle.getMaxV();
        int o = particle.getBrightness(tickDelta);
        vertexConsumer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).texture(l, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).texture(l, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).texture(k, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).texture(k, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
    }



    public static void render_particle_billboard(VertexConsumer vertexConsumer, Camera camera, float tickDelta, ParticleBase particle) {
        Vec3d vec3d = camera.getPos();
        float drawx = (float)(MathHelper.lerp(tickDelta, particle.prevPosX, particle.x) - vec3d.getX());
        float drawy = (float)(MathHelper.lerp(tickDelta, particle.prevPosY, particle.y) - vec3d.getY());
        float drawz = (float)(MathHelper.lerp(tickDelta, particle.prevPosZ, particle.z) - vec3d.getZ());
        particle.getRotator().setRotation(particle.rotation, camera, tickDelta);
        if (particle.angle != 0.0F) {
            particle.rotation.rotateZ(MathHelper.lerp(tickDelta, particle.prevAngle, particle.angle));
        }

        Vector3f[] corners = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float scale = particle.getSize(tickDelta);

        for(int j = 0; j < 4; ++j) {
            Vector3f corner = corners[j];
            corner.rotate(particle.rotation);
            corner.mul(scale);
            corner.add(drawx, drawy, drawz);
        }

        float k = particle.getMinU();
        float l = particle.getMaxU();
        float m = particle.getMinV();
        float n = particle.getMaxV();
        int o = particle.getBrightness(tickDelta);
        vertexConsumer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).texture(l, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).texture(l, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).texture(k, m).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();
        vertexConsumer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).texture(k, n).color(particle.red, particle.green, particle.blue, particle.alpha).light(o).next();

    }
    
}
