package shadowmaster435.impactfulweather.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import shadowmaster435.impactfulweather.init.IWParticles;

@Environment(EnvType.CLIENT)
public class ImpactfulWeatherClient implements ClientModInitializer {
    @java.lang.Override
    public void onInitializeClient() {
        IWParticles.initClient();
    }
}
