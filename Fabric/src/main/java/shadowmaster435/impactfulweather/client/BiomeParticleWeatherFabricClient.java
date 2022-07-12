package shadowmaster435.impactfulweather.client;

import net.fabricmc.api.ClientModInitializer;

public class BiomeParticleWeatherFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BiomeParticleWeatherClient.onConstructMod();
    }
}
