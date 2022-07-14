package shadowmaster435.impactfulweather;

import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shadowmaster435.impactfulweather.config.ClientConfig;
import shadowmaster435.impactfulweather.core.CoreServices;
import shadowmaster435.impactfulweather.init.ModRegistry;

public class BiomeParticleWeather {
    public static final String MOD_ID = "impactfulweather";
    public static final String MOD_NAME = "Biome Particle Weather";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void onConstructMod() {
        // this should be done in common as particle types technically are a common registry object (although this mod only uses them client-side)
        ModRegistry.touch();
        CoreServices.ABSTRACTIONS.registerConfig(MOD_ID, ModConfig.Type.CLIENT, ClientConfig.INSTANCE.getSpec());
    }
}
