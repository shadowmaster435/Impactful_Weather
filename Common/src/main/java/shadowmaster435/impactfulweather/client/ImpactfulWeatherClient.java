package shadowmaster435.impactfulweather.client;

import shadowmaster435.impactfulweather.init.ModRegistry;

public class ImpactfulWeatherClient {

    public static void onConstructMod() {
        ModRegistry.touch();
    }
}
