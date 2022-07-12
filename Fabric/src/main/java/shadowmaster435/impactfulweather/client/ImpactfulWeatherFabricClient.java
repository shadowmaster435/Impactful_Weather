package shadowmaster435.impactfulweather.client;

import net.fabricmc.api.ClientModInitializer;

public class ImpactfulWeatherFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ImpactfulWeatherClient.onConstructMod();
    }
}
