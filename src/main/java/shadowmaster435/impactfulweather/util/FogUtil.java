package shadowmaster435.impactfulweather.util;

import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.biome.Biome;

public class FogUtil {

    public static float vdist;
    public static float sdist;
    public static float edist;
    public static float r;
    public static float g;
    public static float b;
    public static int tickamount = 1;


    public static void fog(ClientWorld world, Camera camera) {
        // Put here to make testing easier
        tickamount++;
        if (world.isRaining() && world.getBiome(camera.getBlockPos()).getCategory() == Biome.Category.DESERT) {
            vdist = 150f;
            edist = 140f;
            sdist = -100f;
        }
    }
}
