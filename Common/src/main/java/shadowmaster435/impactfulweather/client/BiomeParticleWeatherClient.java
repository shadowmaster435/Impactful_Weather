package shadowmaster435.impactfulweather.client;

import shadowmaster435.impactfulweather.client.init.ModParticleEngine;

public class BiomeParticleWeatherClient {

    public static void onConstructMod() {
        ModParticleEngine.registerProviders();
    }
}
