package shadowmaster435.impactfulweather.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import shadowmaster435.impactfulweather.init.IWParticles;

@Environment(EnvType.CLIENT)
public class ImpactfulWeatherClient implements ClientModInitializer {

    public static BPWModConfig config = null;
    @Override
    public void onInitializeClient() {

        AutoConfig.register(BPWModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BPWModConfig.class).getConfig();

        IWParticles.initClient();
    }
}
