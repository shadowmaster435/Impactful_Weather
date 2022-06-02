package shadowmaster435.impactfulweather.util;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LightType;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MiscUtil {

    public static BlockHitResult getBlockHitResult(World world, LivingEntity placer) {
        return world.raycast(new RaycastContext(placer.getEyePos(), placer.raycast(4, MinecraftClient.getInstance().getTickDelta(), false).getPos(), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, placer));
    }
    public static boolean forloopdecimalizer(float modifier, float delta) {
        float remainder = (float) Math.abs(modifier - Math.ceil(modifier));
        int decimalplace = Integer.parseInt("1" + ("0".repeat(String.valueOf(modifier).length() - 2)));
        float floatresult;
                    floatresult = modifier * (remainder + 1);

        if (delta - floatresult >= 0) {
                        return true;
                    } else {
                        return false;
                    }

    }

    public static boolean IsBlockAtPos(Block block, BlockPos pos, World world) {
        return world.getBlockState(pos).getBlock() == block;
    }

    public static RaycastContext getRaycastContext(World world, LivingEntity placer) {
        return new RaycastContext(placer.getEyePos(), placer.raycast(4, MinecraftClient.getInstance().getTickDelta(), false).getPos(), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, placer);
    }


    public static List<BlockPos> getSurfacePosArray(BlockPos pos, World world, BlockState[] blockStates, int poslimit) {
        List<BlockPos> poslist = new ArrayList<>();
        List<BlockState> templist = Arrays.stream(blockStates).toList();
        for (int x2 = 1; x2 < 32; ++x2) {
            for (int y2 = 1; y2 < 32; ++y2) {
                for (int z2 = 1; z2 < 32; ++z2) {
                    int xps = (pos.getX() - 16) + x2;
                    int yps = (pos.getY() - 16) + y2;
                    int zps = (pos.getZ() - 16) + z2;

                    if (templist.contains(world.getBlockState(new BlockPos(xps, yps, zps)))) {
                        if (world.isAir(new BlockPos(xps, yps + 1, zps)) && world.isSkyVisible(new BlockPos(xps, yps, zps))) {
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

    public static Vec3d RayLerpPos(double delta, World world, Vec3d start, Vec3d end) {
        double x = MathHelper.lerp(delta, start.x, end.x);
        double y = MathHelper.lerp(delta, start.y, end.y);
        double z = MathHelper.lerp(delta, start.z, end.z);
        return new Vec3d(x, y, z);
    }

    public static float makeUnInt(float maxval, float val) {
        return val > 0 ? maxval / val : 0;
    }

    public static BiomeKeys getBiomeRegistry(RegistryEntry<Biome> keys) {

        return RegistryKey.of(Registry.BIOME_KEY, new Identifier());
    }

 /*   public static TagKey<BiomeTags> getBiomeCatagory(RegistryKey<Biome> biomeRegistryKey) {

        RegistryKey<Biome> registryKey = biomeRegistryKey;
        return TagKey.of(Registry.BIOME_KEY, biomeRegistryKey);
    }
    public static boolean isBiomeAtPos(Regis.istryKey<Biome> current, RegistryKey<Biome> compare, BlockPos pos) {
        return compare.getValue() == current.getValue();
    }*/



    public static float getRealLightLevel(ClientWorld world, BlockPos pos) {
        int skylight;
        // int blocklight =  world.getLightLevel(LightType.BLOCK, pos) > 3 ? world.getLightLevel(LightType.BLOCK, pos) : 1;
        int blocklight = world.getLightLevel(LightType.BLOCK, pos);
        int light;
        int timelight = 0;

        if (world.isRaining()) {
            skylight = 2;
        } else if (world.isThundering()) {
            skylight = 4;
        } else {
            skylight = 0;
        }
        if ((world.getTime() > 1000 && world.getTime() < 12000)) {
            timelight = 15;
        } else if ((world.getTime() > 13000 && world.getTime() < 23000)) {
            timelight = 6;
        } else if ((world.getTime() < 1000 && world.getTime() > 0)|| (world.getTime() < 13000 && world.getTime() > 12000)) {
            timelight = 12;
        }
        int gammaval = (int) Math.floor(MinecraftClient.getInstance().options.getGamma().getValue() * 3d);
        if (blocklight < 3) {
            light = Math.abs(timelight - skylight + gammaval);
        } else {
            light = Math.abs((Math.max((timelight - skylight), blocklight))) + gammaval;
        }
        return Math.max(light, 0);
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
