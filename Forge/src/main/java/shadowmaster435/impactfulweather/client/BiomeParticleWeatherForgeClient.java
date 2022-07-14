package shadowmaster435.impactfulweather.client;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import shadowmaster435.impactfulweather.BiomeParticleWeather;

@Mod.EventBusSubscriber(modid = BiomeParticleWeather.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BiomeParticleWeatherForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        BiomeParticleWeatherClient.onConstructMod();
        BiomeParticleWeather.LOGGER.info("client setup");
    }

    @SubscribeEvent
    public <T extends ParticleOptions> void onRegisterParticleProviders(final RegisterParticleProvidersEvent evt) {
        BiomeParticleWeather.LOGGER.info("factory setup");
    }
}
