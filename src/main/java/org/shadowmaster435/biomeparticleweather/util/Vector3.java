package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import org.joml.*;

import java.lang.Math;
import java.nio.FloatBuffer;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.*;

public class Vector3 extends Vec3d {
    public static final Vector3 ZERO = new Vector3(0.0,0.0,0.0);

    public static final Vector3 ONE = new Vector3(1.0,1.0,1.0);

    public Vector3(float all) {
        super(all, all, all);
    }

    public Vector3(double all) {
        super(all, all, all);
    }

    public Vector3(Vec3d vec) {
        super(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3(Vec3i vec) {
        super(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vector3 negate() {
        return new Vector3(this.multiply(-1.0));
    }

    public Vector3(Vector3f vec) {
        super(vec);
    }

    public Vector3(float x, float y, float z) {
        super(new Vector3f(x, y ,z));
    }

    public Vector3(float h, float v) {
        super(new Vector3f(h, v, h));
    }

    public Vector3 mul(Vector3 other) {
        return new Vector3(multiply(other));
    }
    public Vector3 mul(float other) {
        return new Vector3(multiply(other));
    }
    public Vector3 mul(double other) {
        return new Vector3(multiply(other));
    }
    public Vector3 mul(int other) {
        return new Vector3(multiply(other));
    }

    public Vector3 div(Vector3 other) {
        var x = max(this.x, 0.001) / max(other.x, 0.001);
        var y = max(this.y, 0.001) / max(other.y, 0.001);
        var z = max(this.z, 0.001) / max(other.z, 0.001);
        return new Vector3(x, y, z);
    }

    public BlockPos to_blockpos() {
        return new BlockPos((int) x,(int) y,(int) z);
    }


    public Vector3 div(float other) {
        return div(new Vector3(other));
    }
    public Vector3 div(double other) {
        return div(new Vector3(other));
    }
    public Vector3 div(int other) {
        return div(new Vector3(other));
    }


    public Vector3(Vector3d vec) {
        super(vec.x, vec.y, vec.z);
    }
    public Vector3(double x, double y, double z) {
        super(x, y, z);
    }
    private Vector3d to_joml() {
        return new Vector3d(x, y, z);
    }

    public Vector3f to_3f() {
        return new Vector3f((float) x, (float) y, (float) z);
    }

    public Vector3 deg_to_rad() {
        return new Vector3(Math.toRadians(x),Math.toRadians(y),Math.toRadians(z));
    }

    
    public Vector3 with_x(double x) {
        return new Vector3(x, getY(), getZ());
    }
    public Vector3 with_y(double y) {
        return new Vector3(getX(), y, getZ());
    }
    public Vector3 with_z(double z) {
        return new Vector3(getX(), getY(), z);
    }


    public Vector3 add_x(double x) {
        return new Vector3(getX() + x, getY(), getZ());
    }
    public Vector3 add_y(double y) {
        return new Vector3(getX(), getY() + y, getZ());
    }
    public Vector3 add_z(double z) {
        return new Vector3(getX(), getY(), getZ() + z);
    }

    public Vector3 with_axis(Direction.Axis axis, double h, double v) {
        var result = this;
        switch (axis) {
            case X -> result = new Vector3(x, v, h);
            case Y -> result = new Vector3(h, y, v);
            case Z -> result = new Vector3(h, v, z);
        }
        return result;
    }


    public Vector3 add_axis(Direction.Axis axis, double h, double v) {
        var result = this;
        switch (axis) {
            case X -> result = new Vector3(x, y + v, z + h);
            case Y -> result = new Vector3(x + h, y, z + v);
            case Z -> result = new Vector3(x + h, y + v, z);
        }
        return result;
    }

    public Vector3 rotate_around(Vector3 rotation, Vector3 origin) {

        return new Vector3(0);
    }
    public Vector3 angle_to(Vector3 other, double amplitude) {
        var quat = new Quaternionf();
        var thisf = this.to_3f();
        var otherf = other.to_3f().normalize().rotationTo(thisf.normalize(), quat);

        return new Vector3(otherf.x, otherf.y, otherf.z).mul(amplitude);

    }

    public Vector3 with_random_offset(float max_ofs) {
        var min = new Vector3(-max_ofs);
        var max = new Vector3(max_ofs);
        var rand = MathHelper.nextBetween(Random.createLocal(), 0f, 1f);

        return new Vector3(this.add(min.lerp(max, rand)));
    }
    public Vector3 with_random_offset(Vector3 max_ofs) {

        var x = MathHelper.nextBetween(Random.createLocal(), 0f, 1f);
        var y = MathHelper.nextBetween(Random.createLocal(), 0f, 1f);
        var z = MathHelper.nextBetween(Random.createLocal(), 0f, 1f);

        return new Vector3(this.add(max_ofs.negate())).lerp(new Vector3(this.add(max_ofs)), new Vector3(x, y, z));
    }

    public Vector3 lerp(Vector3 to, double delta) {
        return new Vector3(super.lerp(to, delta));
    }
    public Vector3 lerp(Vector3 to, Vector3 delta) {
        var x = MathHelper.lerp(delta.x, this.x, to.x);
        var y = MathHelper.lerp(delta.y, this.y, to.y);
        var z = MathHelper.lerp(delta.z, this.z, to.z);

        return new Vector3(x, y, z);
    }

    public Vector3 bounce(Direction direction) {
        var axis = direction.getAxis();
        double x = this.x;
        double y = this.y;
        double z = this.z;
        if (Objects.equals(axis.toString(), "x")) {
            x = this.x * -1;
        }
        if (Objects.equals(axis.toString(), "y")) {
            y = this.y * -1;
        }
        if (Objects.equals(axis.toString(), "z")) {
            x = this.z * -1;
        }
        return new Vector3(x, y, z);
    }

    public static Vector3 rand_between(Vector3 min, Vector3 max) {
        return new Vector3(MathHelper.nextBetween(Random.createLocal(), (float) min.x, (float) max.x),MathHelper.nextBetween(Random.createLocal(), (float) min.y, (float) max.y),MathHelper.nextBetween(Random.createLocal(), (float) min.z, (float) max.z));
    }

}
