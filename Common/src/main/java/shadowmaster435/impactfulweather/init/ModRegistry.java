package shadowmaster435.impactfulweather.init;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import shadowmaster435.impactfulweather.BiomeParticleWeather;
import shadowmaster435.impactfulweather.core.CoreServices;
import shadowmaster435.impactfulweather.core.init.RegistryManager;
import shadowmaster435.impactfulweather.core.init.RegistryReference;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CoreServices.ABSTRACTIONS.createRegistryManager(BiomeParticleWeather.MOD_ID);

    public static final RegistryReference<SimpleParticleType> SANDMOTE = registerParticleType("sandmote", true);
    public static final RegistryReference<SimpleParticleType> REDSANDMOTE = registerParticleType("redsandmote", true);
    public static final RegistryReference<SimpleParticleType> RAIN = registerParticleType("rain", true);
    public static final RegistryReference<SimpleParticleType> HEAVYRAIN = registerParticleType("heavyrain", true);
    public static final RegistryReference<SimpleParticleType> HEAVYRAINEXT = registerParticleType("heavyrainext", true);
    public static final RegistryReference<SimpleParticleType> RAINSPLASH = registerParticleType("rainsplash", true);
    public static final RegistryReference<SimpleParticleType> TUMBLEBUSH = registerParticleType("tumblebush", true);
    public static final RegistryReference<SimpleParticleType> SNOW = registerParticleType("snow", true);
    public static final RegistryReference<SimpleParticleType> BLIZZARDSNOW = registerParticleType("blizzardsnow", true);
    public static final RegistryReference<SimpleParticleType> BLIZZARDWIND = registerParticleType("blizzardwind", true);
    public static final RegistryReference<SimpleParticleType> GUST = registerParticleType("gust", true);
    public static final RegistryReference<SimpleParticleType> WEEPINGTEAR = registerParticleType("weepingtear", true);
    public static final RegistryReference<SimpleParticleType> WEEPINGTEARSPLASH = registerParticleType("weepingtearsplash", true);
    public static final RegistryReference<SimpleParticleType> WARPEDSPORE = registerParticleType("warpedspore", true);
    public static final RegistryReference<SimpleParticleType> UPDRAFT = registerParticleType("updraft", true);
    public static final RegistryReference<SimpleParticleType> STORMSOUL = registerParticleType("stormsoul", true);
    public static final RegistryReference<SimpleParticleType> STORMSOULIMPACT = registerParticleType("stormsoulimpact", true);
    public static final RegistryReference<SimpleParticleType> FIREFLY = registerParticleType("firefly", true);

//    public static final RegistryReference<SimpleParticleType> FOG = registerParticleType("fog", true);

    public static void touch() {
        // we need this dummy method to ensure this class is loaded at the correct time (which is during mod construction), otherwise it might be called to late when registries are already frozen
        // this is only really important for Forge (I don't think vanilla registries are ever frozen, which Fabric uses)
    }

    private static RegistryReference<SimpleParticleType> registerParticleType(String particleId, boolean alwaysActive) {
        return REGISTRY.register(Registries.PARTICLE_TYPE, particleId, () -> CoreServices.ABSTRACTIONS.createSimpleParticleType(alwaysActive));
    }
}
