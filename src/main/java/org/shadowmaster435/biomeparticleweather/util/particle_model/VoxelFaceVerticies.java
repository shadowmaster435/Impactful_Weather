package org.shadowmaster435.biomeparticleweather.util.particle_model;

import net.minecraft.util.math.Direction;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public record VoxelFaceVerticies(Vector3 bl, Vector3 tl, Vector3 tr, Vector3 br) {


    public Vector3f[] create_array(VoxelTransform transform) {
        var rot = transform.rotation().deg_to_rad();
        var bl = this.bl.add(transform.position()).multiply(transform.scale()).rotateX((float) rot.x).rotateY((float) rot.y).rotateZ((float) rot.z).toVector3f();
        var tl = this.tl.add(transform.position()).multiply(transform.scale()).rotateX((float) rot.x).rotateY((float) rot.y).rotateZ((float) rot.z).toVector3f();
        var tr = this.tr.add(transform.position()).multiply(transform.scale()).rotateX((float) rot.x).rotateY((float) rot.y).rotateZ((float) rot.z).toVector3f();
        var br = this.br.add(transform.position()).multiply(transform.scale()).rotateX((float) rot.x).rotateY((float) rot.y).rotateZ((float) rot.z).toVector3f();

        return new Vector3f[]{bl, tl, tr, br};

    }

    public static VoxelFaceVerticies create(Direction dir, Vector3 position, Vector3 size) {
        var bl = position;
        var tl = position;
        var tr = position;
        var br = position;
        var negative_axis = false;
        var negative_axis_z = false;

        switch (dir.getAxis()) {
            case X -> {
                bl = (dir == Direction.WEST) ?  bl.add_z(size.z) : bl.add_z(size.z).add_x(size.x);
                tl = (dir == Direction.WEST) ?  tl.add_z(size.z).add_y(size.y) : tl.add_z(size.z).add_y(size.y).add_x(size.x);
                tr = (dir == Direction.WEST) ?  tr.add_y(size.y) : tr.add_y(size.y).add_x(size.x);
                br = (dir == Direction.WEST) ?  br : br.add_x(size.x);
                if (dir.getOffsetX() < 0) {
                    negative_axis = true;
                }
            }
            case Y -> {
                bl = (dir == Direction.DOWN) ?  bl : bl.add_y(size.y);
                tl = (dir == Direction.DOWN) ?  tl.add_z(size.z) : tl.add_z(size.z).add_y(size.y);
                tr = (dir == Direction.DOWN) ?  tr.add_x(size.x).add_z(size.z) : tr.add_y(size.y).add_z(size.z).add_x(size.x);
                br = (dir == Direction.DOWN) ?  br.add_x(size.x) : br.add_x(size.x).add_y(size.y);
                if (dir.getOffsetY() > 0) {
                    negative_axis = true;
                }
            }
            case Z -> {
                bl = (dir == Direction.NORTH) ?  bl : bl.add_z(size.z);
                tl = (dir == Direction.NORTH) ?  tl.add_y(size.y) : tl.add_z(size.z).add_y(size.y);
                tr = (dir == Direction.NORTH) ?  tr.add_y(size.y).add_x(size.x) : tr.add_z(size.z).add_y(size.y).add_x(size.x);
                br = (dir == Direction.NORTH) ?  br.add_x(size.x) : br.add_x(size.x).add_z(size.z);
                if (dir.getOffsetZ() < 0) {
                    negative_axis_z = true;
                }
            }
        }

      //  return (!negative_axis) ?  new VoxelFaceVerticies(bl, tl, tr, br) : new VoxelFaceVerticies(br, tr, tl, bl);
        return new VoxelFaceVerticies(br, tr, tl, bl);
        //return new VoxelFaceVerticies(new Vector3(-1,0,-1),new Vector3(-1,0,1),new Vector3(1,0,1),new Vector3(-1,0,1));
    }

}
