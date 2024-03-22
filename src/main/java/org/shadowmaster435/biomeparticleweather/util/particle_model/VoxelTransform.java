package org.shadowmaster435.biomeparticleweather.util.particle_model;

import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public record VoxelTransform(Vector3 position, Vector3 scale, Vector3 rotation, Vector3 origin) {


    public static VoxelTransform def() {
        return new VoxelTransform(Vector3.ZERO, Vector3.ONE, Vector3.ZERO, Vector3.ZERO);
    }

    public static VoxelTransform transform_by(VoxelTransform transformed, VoxelTransform transformer) {
        var scale = transformed.scale.multiply(transformer.scale);
        var rot = transformed.rotation.add(transformer.rotation);
        var pos = transformed.position.add(transformer.position).multiply(scale);
        return new VoxelTransform(new Vector3(pos), new Vector3(scale), new Vector3(rot), transformer.origin);
    }

}
