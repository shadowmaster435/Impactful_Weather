package shadowmaster435.impactfulweather.client.init;

import shadowmaster435.impactfulweather.client.core.ClientCoreServices;
import shadowmaster435.impactfulweather.client.particle.*;
import shadowmaster435.impactfulweather.init.ModRegistry;

public class ModParticleEngine {
    
    public static void registerProviders() {
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.SANDMOTE.get(), SandMote.SandMoteFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.REDSANDMOTE.get(), RedSandMote.RedSandMoteFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.RAIN.get(), Rain.RainFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.HEAVYRAIN.get(), HeavyRain.HeavyRainFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.HEAVYRAINEXT.get(), HeavyRainExt.HeavyRainExtFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.RAINSPLASH.get(), RainSplash.RainSplashFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.TUMBLEBUSH.get(), TumbleBush.TumbleBushFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.SNOW.get(), Snow.SnowFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.BLIZZARDSNOW.get(), BlizzardSnow.BlizzardSnowFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.BLIZZARDWIND.get(), BlizzardWind.BlizzardWindFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.GUST.get(), Gust.GustFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WARPEDSPORE.get(), WarpedSpore.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WEEPINGTEAR.get(), WeepingTear.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WEEPINGTEARSPLASH.get(), WeepingTearSplash.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.UPDRAFT.get(), Updraft.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.STORMSOUL.get(), StormSoul.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.STORMSOULIMPACT.get(), StormSoulImpact.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.FIREFLY.get(), FireFly.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.FOG.get(), Fog.Factory::new);
    }
}
