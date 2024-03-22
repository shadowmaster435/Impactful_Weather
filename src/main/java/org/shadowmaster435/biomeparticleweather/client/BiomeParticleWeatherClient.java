package org.shadowmaster435.biomeparticleweather.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.particle.*;

public class BiomeParticleWeatherClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.RAIN, Rain.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.RAIN_SPLASH, RainSplash.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.RAIN_TRAIL, RainTrail.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.TUMBLE_BUSH, TumbleBush.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.LIGHTNING, Lightning.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.LIGHTNING_NODE, LightningNode.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.RAIN_RIPPLE, RainRipple.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.FOG, Fog.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.FIREFLY, Firefly.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.WIND, Wind.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.WIND_TRAIL, WindTrail.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.RED_SAND_MOTE, RedSandMote.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.SAND_MOTE, SandMote.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.BLIZZARD_WIND, BlizzardWind.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.BLIZZARD_SNOW, BlizzardSnow.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BiomeParticleWeather.SNOW, Snow.Factory::new);


    }
}
