package org.shadowmaster435.biomeparticleweather.util;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.joml.Vector2i;
import org.shadowmaster435.biomeparticleweather.util.particle_model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ModResourceLoader {

    public static HashMap<String, ParticleModelPart> models = new HashMap<>();

    public static void register() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("biomeparticleweather", "particle_model");
            }

            @Override
            public void reload(ResourceManager manager) {
                models.clear();

                for(Identifier id : manager.findResources("textures/particle_model", path -> path.getPath().endsWith(".png")).keySet().stream().toList()) {
                    try(InputStream stream = manager.getResource(id).orElseThrow().getInputStream()) {
                        build_model(id, stream);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

    }
    public static int counter = 0;
    public static void build_model(Identifier id, InputStream stream) {
        var result = new ArrayList<ParticleVoxel>();
        BufferedImage image = null;
        try {
            image = ImageIO.read(stream);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int height = image.getHeight(), width = image.getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int RGBA = image.getRGB(x, y);
                int alpha = (RGBA >> 24) & 255;

                if (alpha != 0) {
                    var uv = VoxelUVMap.single_pixel(new Vector2i(x, y));
                    var voxel = ParticleVoxel.create(new Vector3(x, y, 0), new Vector3(1), uv);
                    result.add(voxel);
                }
            }
        }
        ParticleVoxel[] voxels = result.toArray(new ParticleVoxel[]{});
        models.put(id.toString().replace("biomeparticleweather:textures/particle_model/", "").replace(".png", ""), new ParticleModelPart(voxels, new ParticleVoxelParams(Vector3.ONE, Vector3.ZERO, VoxelTransform.def())));
        System.out.println(models);
    }
}
