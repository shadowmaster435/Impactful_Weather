package shadowmaster435.impactfulweather.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shadowmaster435.impactfulweather.particles.*;

public class IWParticles {
    public static final DefaultParticleType SANDMOTE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType REDSANDMOTE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType RAIN = FabricParticleTypes.simple(true);
    public static final DefaultParticleType HEAVYRAIN = FabricParticleTypes.simple(true);
    public static final DefaultParticleType HEAVYRAINEXT = FabricParticleTypes.simple(true);
    public static final DefaultParticleType RAINSPLASH = FabricParticleTypes.simple(true);
    public static final DefaultParticleType TUMBLEBUSH = FabricParticleTypes.simple(true);
    public static final DefaultParticleType SNOW = FabricParticleTypes.simple(true);
    public static final DefaultParticleType BLIZZARDSNOW = FabricParticleTypes.simple(true);
    public static final DefaultParticleType BLIZZARDWIND = FabricParticleTypes.simple(true);
    public static final DefaultParticleType GUST = FabricParticleTypes.simple(true);
    public static final DefaultParticleType WEEPINGTEAR = FabricParticleTypes.simple(true);
    public static final DefaultParticleType WEEPINGTEARSPLASH = FabricParticleTypes.simple(true);
    public static final DefaultParticleType WARPEDSPORE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType UPDRAFT = FabricParticleTypes.simple(true);
    public static final DefaultParticleType STORMSOUL = FabricParticleTypes.simple(true);
    public static final DefaultParticleType STORMSOULIMPACT = FabricParticleTypes.simple(true);

    public static final DefaultParticleType FIREFLY = FabricParticleTypes.simple(true);

    public static final DefaultParticleType FOG = FabricParticleTypes.simple(true);


    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ParticleFactoryRegistry.getInstance().register(SANDMOTE, SandMote.SandMoteFactory::new);
        ParticleFactoryRegistry.getInstance().register(REDSANDMOTE, RedSandMote.RedSandMoteFactory::new);
        ParticleFactoryRegistry.getInstance().register(RAIN, Rain.RainFactory::new);
        ParticleFactoryRegistry.getInstance().register(HEAVYRAIN, HeavyRain.HeavyRainFactory::new);
        ParticleFactoryRegistry.getInstance().register(HEAVYRAINEXT, HeavyRainExt.HeavyRainExtFactory::new);
        ParticleFactoryRegistry.getInstance().register(RAINSPLASH, RainSplash.RainSplashFactory::new);
        ParticleFactoryRegistry.getInstance().register(TUMBLEBUSH, TumbleBush.TumbleBushFactory::new);
        ParticleFactoryRegistry.getInstance().register(SNOW, Snow.SnowFactory::new);
        ParticleFactoryRegistry.getInstance().register(BLIZZARDSNOW, BlizzardSnow.BlizzardSnowFactory::new);
        ParticleFactoryRegistry.getInstance().register(BLIZZARDWIND, BlizzardWind.BlizzardWindFactory::new);
        ParticleFactoryRegistry.getInstance().register(GUST, Gust.GustFactory::new);
        ParticleFactoryRegistry.getInstance().register(WARPEDSPORE, WarpedSpore.Factory::new);
        ParticleFactoryRegistry.getInstance().register(WEEPINGTEAR, WeepingTear.Factory::new);
        ParticleFactoryRegistry.getInstance().register(WEEPINGTEARSPLASH, WeepingTearSplash.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UPDRAFT, Updraft.Factory::new);
        ParticleFactoryRegistry.getInstance().register(STORMSOUL, StormSoul.Factory::new);
        ParticleFactoryRegistry.getInstance().register(STORMSOULIMPACT, StormSoulImpact.Factory::new);
        ParticleFactoryRegistry.getInstance().register(FIREFLY, FireFly.Factory::new);
        ParticleFactoryRegistry.getInstance().register(FOG, Fog.Factory::new);

        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "sandmote"), SANDMOTE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "redsandmote"), REDSANDMOTE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "rain"), RAIN);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "heavyrain"), HEAVYRAIN);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "heavyrainext"), HEAVYRAINEXT);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "rainsplash"), RAINSPLASH);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "tumblebush"), TUMBLEBUSH);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "snow"), SNOW);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "blizzardsnow"), BLIZZARDSNOW);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "blizzardwind"), BLIZZARDWIND);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "gust"), GUST);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "weepingtear"), WEEPINGTEAR);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "weepingtearsplash"), WEEPINGTEARSPLASH);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "warpedspore"), WARPEDSPORE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "updraft"), UPDRAFT);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "stormsoul"), STORMSOUL);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "stormsoulimpact"), STORMSOULIMPACT);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "firefly"), FIREFLY);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "fog"), FOG);

    }
}
