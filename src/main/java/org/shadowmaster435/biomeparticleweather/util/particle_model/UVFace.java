package org.shadowmaster435.biomeparticleweather.util.particle_model;

import org.joml.Vector2i;

public record UVFace(Vector2i start, Vector2i end) {

    public static UVFace single_pixel(Vector2i pixel_position) {
        return new UVFace(pixel_position, new Vector2i(pixel_position).add(1, 1));
    }

    public static UVFace square(Vector2i pixel_position, int size) {
        return new UVFace(pixel_position, new Vector2i(pixel_position).add(new Vector2i(size)));
    }

    public static UVFace rectangle(Vector2i pixel_position, Vector2i size) {
        return new UVFace(pixel_position, new Vector2i(pixel_position).add(size));
    }

}
