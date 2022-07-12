package shadowmaster435.impactfulweather;

import net.fabricmc.api.ModInitializer;

public class BiomeParticleWeatherFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BiomeParticleWeather.onConstructMod();
    }
}
