package shadowmaster435.impactfulweather.util;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Random;

public class MiscUtil {

    public static BlockHitResult getBlockHitResult(World world, LivingEntity placer) {
        return world.raycast(new RaycastContext(placer.getEyePos(), placer.raycast(4, MinecraftClient.getInstance().getTickDelta(), false).getPos(), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, placer));
    }

    public static boolean IsBlockAtPos(Block block, BlockPos pos, World world) {
        return world.getBlockState(pos).getBlock() == block;
    }

    public static RaycastContext getRaycastContext(World world, LivingEntity placer) {
        return new RaycastContext(placer.getEyePos(), placer.raycast(4, MinecraftClient.getInstance().getTickDelta(), false).getPos(), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, placer);
    }

    public static int RandomIntseed(Random random, int mult) {
        return random.nextInt() * mult;
    }

    public static Vec3d RayLerpPos(double delta, World world, Vec3d start, Vec3d end) {
        double x = MathHelper.lerp(delta, start.x, end.x);
        double y = MathHelper.lerp(delta, start.y, end.y);
        double z = MathHelper.lerp(delta, start.z, end.z);
        return new Vec3d(x, y, z);
    }

    public static Vec3d AreaLerpPosRand(Vec3d vec3d, double xsize, double ysize, double zsize) {
        double x = xsize - (xsize * 0.5d);
        double y = xsize - (ysize * 0.5d);
        double z = xsize - (zsize * 0.5d);
        double xneg = xsize - (xsize * 0.5d) * -1;
        double yneg = xsize - (ysize * 0.5d) * -1;
        double zneg = xsize - (zsize * 0.5d) * -1;
        return new Vec3d(MathHelper.lerp(RandomDouble(1d), x, xneg), MathHelper.lerp(RandomDouble(1d), y, yneg), MathHelper.lerp(RandomDouble(1d), z, zneg));
    }

    public static boolean ifchance(int percent) {
        return (Math.random() * 100) > percent;
    }

    public static int RandomInt(int mult) {
        return (int) (Math.random() * mult);
    }

    public static double RandomDoubleSeeded(Random random, double mult) {
        return random.nextDouble() * mult;
    }

    public static double RandomDouble(double mult) {
        return Math.random() * mult;
    }

    public static float RandomFloatSeeded(Random random, float mult) {
        return random.nextFloat() * mult;
    }

    public static float RandomFloat(float mult) {
        return (float) (Math.random()) * mult;
    }


}
