package org.shadowmaster435.biomeparticleweather;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.shadowmaster435.biomeparticleweather.util.ConfigFile;
import org.shadowmaster435.biomeparticleweather.util.ModResourceLoader;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleModelPart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class BiomeParticleWeather implements ModInitializer {




    public static final DefaultParticleType RAIN = FabricParticleTypes.simple();
    public static final DefaultParticleType RAIN_SPLASH = FabricParticleTypes.simple();
    public static final DefaultParticleType RAIN_TRAIL = FabricParticleTypes.simple();
    public static final DefaultParticleType RAIN_RIPPLE = FabricParticleTypes.simple();
    public static final DefaultParticleType TUMBLE_BUSH = FabricParticleTypes.simple();
    public static final DefaultParticleType LIGHTNING = FabricParticleTypes.simple(true);
    public static final DefaultParticleType LIGHTNING_NODE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType FOG = FabricParticleTypes.simple();
    public static final DefaultParticleType FIREFLY = FabricParticleTypes.simple();

    public static final DefaultParticleType WIND = FabricParticleTypes.simple();

    public static final DefaultParticleType WIND_TRAIL = FabricParticleTypes.simple();
    public static final DefaultParticleType SAND_MOTE = FabricParticleTypes.simple();
    public static final DefaultParticleType RED_SAND_MOTE = FabricParticleTypes.simple();
    public static final DefaultParticleType SNOW = FabricParticleTypes.simple();
    public static final DefaultParticleType BLIZZARD_WIND = FabricParticleTypes.simple();
    public static final DefaultParticleType BLIZZARD_SNOW = FabricParticleTypes.simple();


    @Override
    public void onInitialize() {
        ConfigFile.ensure_existance();
        ConfigFile.load_config();

        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "rain"), RAIN);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "rain_splash"), RAIN_SPLASH);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "rain_trail"), RAIN_TRAIL);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "tumble_bush"), TUMBLE_BUSH);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "lightning"), LIGHTNING);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "lightning_node"), LIGHTNING_NODE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "rain_ripple"), RAIN_RIPPLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "fog"), FOG);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "firefly"), FIREFLY);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "wind"), WIND);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "wind_trail"), WIND_TRAIL);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "sand_mote"), SAND_MOTE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "red_sand_mote"), RED_SAND_MOTE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "blizzard_wind"), BLIZZARD_WIND);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "blizzard_snow"), BLIZZARD_SNOW);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "snow"), SNOW);
        ModResourceLoader.register();
    }


}
