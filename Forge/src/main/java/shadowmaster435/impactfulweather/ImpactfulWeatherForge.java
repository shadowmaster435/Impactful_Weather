package shadowmaster435.impactfulweather;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(ImpactfulWeather.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ImpactfulWeatherForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ImpactfulWeather.onConstructMod();
    }
}
