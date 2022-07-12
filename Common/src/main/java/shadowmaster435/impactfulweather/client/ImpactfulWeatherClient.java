package shadowmaster435.impactfulweather.client;

import shadowmaster435.impactfulweather.client.init.ModParticleEngine;

public class ImpactfulWeatherClient {

    public static void onConstructMod() {
        ModParticleEngine.registerProviders();
    }
}
