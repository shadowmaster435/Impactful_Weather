package org.shadowmaster435.biomeparticleweather.util.particle_model;

import net.minecraft.util.math.Direction;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

import java.util.HashMap;

public record ParticleVoxel(HashMap<Direction, VoxelFace> faces, ParticleVoxelParams params) {
    
    
    public static ParticleVoxel create(Vector3 position, Vector3 size, VoxelUVMap uv_map) {
        HashMap<Direction, VoxelFace> faces = new HashMap<>();
        for (Direction dir : Direction.values()) {
            faces.put(dir, new VoxelFace(VoxelFaceVerticies.create(dir, position, size), uv_map.get_value(dir)));
        }
        return new ParticleVoxel(faces, new ParticleVoxelParams(size, Vector3.ZERO, VoxelTransform.def()));
    }
    
    
    
}
