package org.shadowmaster435.biomeparticleweather.util.particle_model;

import net.minecraft.util.math.Direction;
import org.joml.Vector2i;

public record VoxelUVMap(UVFace u, UVFace d, UVFace n, UVFace s, UVFace e, UVFace w) {


    public UVFace get_value(Direction direction) {
        UVFace result = UVFace.single_pixel(new Vector2i(0));
        switch (direction) {
            case UP -> result = u;
            case DOWN -> result = d;
            case NORTH -> result = n;
            case SOUTH -> result = s;
            case EAST -> result = e;
            case WEST -> result = w;

        }
        return result;
    }

    public static VoxelUVMap array(Vector2i[][] input) {
        var u = UVFace.rectangle(input[0][0], input[0][1]);
        var d = UVFace.rectangle(input[1][0], input[1][1]);
        var n = UVFace.rectangle(input[2][0], input[2][1]);
        var s = UVFace.rectangle(input[3][0], input[3][1]);
        var e = UVFace.rectangle(input[4][0], input[4][1]);
        var w = UVFace.rectangle(input[5][0], input[5][1]);
        return new VoxelUVMap(u, d, n, s, e, w);
    }

    public static VoxelUVMap square_column(UVFace ends, UVFace sides) {
        return new VoxelUVMap(ends, ends, sides, sides, sides, sides);
    }

    public static VoxelUVMap three_axis(UVFace x, UVFace y, UVFace z) {
        return new VoxelUVMap(y, y, z, z, x, x);
    }

    public static VoxelUVMap rectangle(Vector2i pixel_coord, Vector2i size) {
        var a = UVFace.rectangle(pixel_coord, size);
        return new VoxelUVMap(a, a, a, a, a, a);
    }

    public static VoxelUVMap square(Vector2i pixel_coord, int size) {
        var a = UVFace.square(pixel_coord, size);
        return new VoxelUVMap(a, a, a, a, a, a);
    }

    public static VoxelUVMap single_pixel(Vector2i pixel_coord) {
        var a = UVFace.single_pixel(pixel_coord);
        return new VoxelUVMap(a, a, a, a, a, a);
    }
}
