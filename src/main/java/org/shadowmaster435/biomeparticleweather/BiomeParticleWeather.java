package org.shadowmaster435.biomeparticleweather;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
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
    public static final DefaultParticleType WEEPING_TEAR = FabricParticleTypes.simple();
    public static final DefaultParticleType WEEPING_TEAR_SPLASH = FabricParticleTypes.simple();
    public static final DefaultParticleType UPDRAFT = FabricParticleTypes.simple();
    public static final DefaultParticleType STORM_SOUL = FabricParticleTypes.simple();
    public static final DefaultParticleType STORM_SOUL_IMPACT = FabricParticleTypes.simple();
    public static final DefaultParticleType WARPED_SPORE = FabricParticleTypes.simple();

    public static final ParticleTextureSheet NO_CULL_PARTICLE_SHEET = new ParticleTextureSheet() {
        @Override
        public void begin(BufferBuilder builder, TextureManager textureManager) {
            RenderSystem.depthMask(true);
            RenderSystem.setShaderTexture(0, SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE);
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.defaultBlendFunc();
            builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);

        }

        @Override
        public void draw(Tessellator tessellator) {
            tessellator.draw();
        }

        public String toString() {
            return "NO_CULL_PARTICLE_SHEET";
        }
    };


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
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "snow"), SNOW);

        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "blizzard_wind"), BLIZZARD_WIND);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "blizzard_snow"), BLIZZARD_SNOW);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "warped_spore"), WARPED_SPORE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "storm_soul"), STORM_SOUL);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "storm_soul_impact"), STORM_SOUL_IMPACT);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "weeping_tear"), WEEPING_TEAR);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "weeping_tear_splash"), WEEPING_TEAR_SPLASH);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("biomeparticleweather", "updraft"), UPDRAFT);

        ModResourceLoader.register();
    }


}
