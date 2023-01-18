package shadowmaster435.impactfulweather.client.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import shadowmaster435.impactfulweather.client.core.ClientCoreServices;
import shadowmaster435.impactfulweather.client.particle.*;
import shadowmaster435.impactfulweather.init.ModRegistry;

public class ModParticleEngine {

    public static SoundEvent raindrop = SoundEvent.createFixedRangeEvent(new ResourceLocation("impactfulweather:sounds/raindrop"), 1);
    public static SoundEvent raindropceil = SoundEvent.createFixedRangeEvent(new ResourceLocation("impactfulweather:sounds/raindrop"), 1);

    public static void registerProviders() {
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.SANDMOTE, SandMote.SandMoteFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.REDSANDMOTE, RedSandMote.RedSandMoteFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.RAIN, Rain.RainFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.HEAVYRAIN, HeavyRain.HeavyRainFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.HEAVYRAINEXT, HeavyRainExt.HeavyRainExtFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.RAINSPLASH, RainSplash.RainSplashFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.TUMBLEBUSH, TumbleBush.TumbleBushFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.SNOW, Snow.SnowFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.BLIZZARDSNOW, BlizzardSnow.BlizzardSnowFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.BLIZZARDWIND, BlizzardWind.BlizzardWindFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.GUST, Gust.GustFactory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WARPEDSPORE, WarpedSpore.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WEEPINGTEAR, WeepingTear.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.WEEPINGTEARSPLASH, WeepingTearSplash.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.UPDRAFT, Updraft.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.STORMSOUL, StormSoul.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.STORMSOULIMPACT, StormSoulImpact.Factory::new);
        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.FIREFLY, FireFly.Factory::new);
//        ClientCoreServices.CLIENT_REGISTRATION.registerParticleProvider(ModRegistry.FOG, Fog.Factory::new);
    }
}
