package shadowmaster435.impactfulweather.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shadowmaster435.impactfulweather.particles.*;

public class IWParticles {
    public static final DefaultParticleType SANDMOTE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType REDSANDMOTE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType RAIN = FabricParticleTypes.simple(true);
    public static final DefaultParticleType TUMBLEBUSH = FabricParticleTypes.simple(true);
    public static final DefaultParticleType SNOW = FabricParticleTypes.simple(true);
    public static final DefaultParticleType GUST = FabricParticleTypes.simple(true);


    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ParticleFactoryRegistry.getInstance().register(SANDMOTE, SandMote.SandMoteFactory::new);
        ParticleFactoryRegistry.getInstance().register(REDSANDMOTE, RedSandMote.RedSandMoteFactory::new);
        ParticleFactoryRegistry.getInstance().register(RAIN, Rain.RainFactory::new);
        ParticleFactoryRegistry.getInstance().register(TUMBLEBUSH, TumbleBush.TumbleBushFactory::new);
        ParticleFactoryRegistry.getInstance().register(SNOW, Snow.SnowFactory::new);
        ParticleFactoryRegistry.getInstance().register(GUST, Gust.GustFactory::new);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "sandmote"), SANDMOTE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "redsandmote"), REDSANDMOTE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "rain"), RAIN);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "tumblebush"), TUMBLEBUSH);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "snow"), SNOW);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "gust"), GUST);
    }
}
