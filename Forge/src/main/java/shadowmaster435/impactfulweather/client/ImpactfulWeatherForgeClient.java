package shadowmaster435.impactfulweather.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import shadowmaster435.impactfulweather.ImpactfulWeather;
import shadowmaster435.impactfulweather.client.ImpactfulWeatherClient;

@Mod.EventBusSubscriber(modid = ImpactfulWeather.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ImpactfulWeatherForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ImpactfulWeatherClient.onConstructMod();
    }
}
