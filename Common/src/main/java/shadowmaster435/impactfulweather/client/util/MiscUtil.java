package shadowmaster435.impactfulweather.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MiscUtil {

    public static BlockHitResult getBlockHitResult(Level world, LivingEntity placer) {
        return world.clip(new ClipContext(placer.getEyePosition(), placer.pick(4, Minecraft.getInstance().getFrameTime(), false).getLocation(), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, placer));
    }
    public static boolean forloopdecimalizer(double modifier, float delta) {
        float remainder = (float) Math.abs(modifier - Math.ceil(modifier));
        int decimalplace = Integer.parseInt("1" + ("0".repeat(String.valueOf(modifier).length() - 2)));
        double floatresult = modifier * (remainder + 1);

        if (delta - floatresult >= 0) {
                        return true;
                    } else {
                        return false;
                    }

    }

    public static boolean IsBlockAtPos(Block block, BlockPos pos, Level world) {
        return world.getBlockState(pos).getBlock() == block;
    }

    public static ClipContext getRaycastContext(Level world, LivingEntity placer) {
        return new ClipContext(placer.getEyePosition(), placer.pick(4, Minecraft.getInstance().getFrameTime(), false).getLocation(), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, placer);
    }


    public static List<BlockPos> getSurfacePosArray(BlockPos pos, Level world, BlockState[] blockStates, int poslimit) {
        List<BlockPos> poslist = new ArrayList<>();
        List<BlockState> templist = Arrays.stream(blockStates).toList();
        for (int x2 = 1; x2 < 32; ++x2) {
            for (int y2 = 1; y2 < 32; ++y2) {
                for (int z2 = 1; z2 < 32; ++z2) {
                    int xps = (pos.getX() - 16) + x2;
                    int yps = (pos.getY() - 16) + y2;
                    int zps = (pos.getZ() - 16) + z2;

                    if (templist.contains(world.getBlockState(new BlockPos(xps, yps, zps)))) {
                        if (world.isEmptyBlock(new BlockPos(xps, yps + 1, zps)) && world.canSeeSky(new BlockPos(xps, yps, zps))) {
                            poslist.add(new BlockPos(xps, yps, zps));
                        }
                    }
                }
            }
        }

        return poslist;
    }

    public static int RandomIntseed(Random random, int mult) {
        return random.nextInt() * mult;
    }

    public static Vec3 RayLerpPos(double delta, Level world, Vec3 start, Vec3 end) {
        double x = Mth.lerp(delta, start.x, end.x);
        double y = Mth.lerp(delta, start.y, end.y);
        double z = Mth.lerp(delta, start.z, end.z);
        return new Vec3(x, y, z);
    }

    public static float makeUnInt(float maxval, float val) {
        return val > 0 ? maxval / val : 0;
    }



 /*   public static TagKey<BiomeTags> getBiomeCatagory(RegistryKey<Biome> biomeRegistryKey) {

        RegistryKey<Biome> registryKey = biomeRegistryKey;
        return TagKey.of(Registry.BIOME_KEY, biomeRegistryKey);
    }
    public static boolean isBiomeAtPos(Regis.istryKey<Biome> current, RegistryKey<Biome> compare, BlockPos pos) {
        return compare.getValue() == current.getValue();
    }*/



    public static float getRealLightLevel(ClientLevel world, BlockPos pos) {
        int skylight;
        // int blocklight =  world.getLightLevel(LightType.BLOCK, pos) > 3 ? world.getLightLevel(LightType.BLOCK, pos) : 1;
        int blocklight = world.getBrightness(LightLayer.BLOCK, pos);
        int light;
        int timelight = 0;

        if (world.isRaining()) {
            skylight = 2;
        } else if (world.isThundering()) {
            skylight = 4;
        } else {
            skylight = 0;
        }
        if ((world.getGameTime() > 1000 && world.getGameTime() < 12000)) {
            timelight = 15;
        } else if ((world.getGameTime() > 13000 && world.getGameTime() < 23000)) {
            timelight = 6;
        } else if ((world.getGameTime() < 1000 && world.getGameTime() > 0)|| (world.getGameTime() < 13000 && world.getGameTime() > 12000)) {
            timelight = 12;
        }
        int gammaval = (int) Math.floor(Minecraft.getInstance().options.gamma().get() * 3d);
        if (blocklight < 3) {
            light = Math.abs(timelight - skylight + gammaval);
        } else {
            light = Math.abs((Math.max((timelight - skylight), blocklight))) + gammaval;
        }
        return Math.max(light, 0);
    }

    public static Vec3 AreaLerpPosRand(Vec3 vec3d, double xsize, double ysize, double zsize) {
        double x = xsize - (xsize * 0.5d);
        double y = xsize - (ysize * 0.5d);
        double z = xsize - (zsize * 0.5d);
        double xneg = xsize - (xsize * 0.5d) * -1;
        double yneg = xsize - (ysize * 0.5d) * -1;
        double zneg = xsize - (zsize * 0.5d) * -1;
        return new Vec3(Mth.lerp(RandomDouble(1d), x, xneg), Mth.lerp(RandomDouble(1d), y, yneg), Mth.lerp(RandomDouble(1d), z, zneg));
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
