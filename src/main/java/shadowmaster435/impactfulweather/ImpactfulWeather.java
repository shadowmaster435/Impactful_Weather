package shadowmaster435.impactfulweather;

import net.fabricmc.api.ModInitializer;
import shadowmaster435.impactfulweather.init.IWParticles;

public class ImpactfulWeather implements ModInitializer {

    @Override
    public void onInitialize() {
        IWParticles.init();
    }
}
