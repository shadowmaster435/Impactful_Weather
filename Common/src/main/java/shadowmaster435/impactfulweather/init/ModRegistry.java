package shadowmaster435.impactfulweather.init;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import shadowmaster435.impactfulweather.ImpactfulWeather;
import shadowmaster435.impactfulweather.core.CoreServices;

public class ModRegistry {
    public static final SimpleParticleType SANDMOTE = registerParticleType("sandmote", true);
    public static final SimpleParticleType REDSANDMOTE = registerParticleType("redsandmote", true);
    public static final SimpleParticleType RAIN = registerParticleType("rain", true);
    public static final SimpleParticleType HEAVYRAIN = registerParticleType("heavyrain", true);
    public static final SimpleParticleType HEAVYRAINEXT = registerParticleType("heavyrainext", true);
    public static final SimpleParticleType RAINSPLASH = registerParticleType("rainsplash", true);
    public static final SimpleParticleType TUMBLEBUSH = registerParticleType("tumblebush", true);
    public static final SimpleParticleType SNOW = registerParticleType("snow", true);
    public static final SimpleParticleType BLIZZARDSNOW = registerParticleType("blizzardsnow", true);
    public static final SimpleParticleType BLIZZARDWIND = registerParticleType("blizzardwind", true);
    public static final SimpleParticleType GUST = registerParticleType("gust", true);
    public static final SimpleParticleType WEEPINGTEAR = registerParticleType("weepingtear", true);
    public static final SimpleParticleType WEEPINGTEARSPLASH = registerParticleType("weepingtearsplash", true);
    public static final SimpleParticleType WARPEDSPORE = registerParticleType("warpedspore", true);
    public static final SimpleParticleType UPDRAFT = registerParticleType("updraft", true);
    public static final SimpleParticleType STORMSOUL = registerParticleType("stormsoul", true);
    public static final SimpleParticleType STORMSOULIMPACT = registerParticleType("stormsoulimpact", true);
    public static final SimpleParticleType FIREFLY = registerParticleType("firefly", true);
    public static final SimpleParticleType FOG = registerParticleType("fog", true);

    public static void touch() {
        // we need this dummy method to ensure this class is loaded at the correct time (which is during mod construction), otherwise it might be called to late when registries are already frozen
        // this is only really important for Forge (I don't think vanilla registries are ever frozen, which Fabric uses)
    }

    private static SimpleParticleType registerParticleType(String particleId, boolean alwaysActive) {
        return Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(ImpactfulWeather.MOD_ID, particleId), CoreServices.ABSTRACTIONS.createSimpleParticleType(alwaysActive));
    }
}
