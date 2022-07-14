package shadowmaster435.impactfulweather;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(BiomeParticleWeather.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeParticleWeatherForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        BiomeParticleWeather.onConstructMod();
    }
}
