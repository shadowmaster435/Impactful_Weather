package shadowmaster435.impactfulweather.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shadowmaster435.impactfulweather.particles.Rain;
import shadowmaster435.impactfulweather.particles.SandMote;
import shadowmaster435.impactfulweather.particles.Snow;
import shadowmaster435.impactfulweather.particles.TumbleBush;

public class IWParticles {
    public static final DefaultParticleType SANDMOTE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType RAIN = FabricParticleTypes.simple(true);
    public static final DefaultParticleType TUMBLEBUSH = FabricParticleTypes.simple(true);
    public static final DefaultParticleType SNOW = FabricParticleTypes.simple(true);


    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ParticleFactoryRegistry.getInstance().register(SANDMOTE, SandMote.SandMoteFactory::new);
        ParticleFactoryRegistry.getInstance().register(RAIN, Rain.RainFactory::new);
        ParticleFactoryRegistry.getInstance().register(TUMBLEBUSH, TumbleBush.TumbleBushFactory::new);
        ParticleFactoryRegistry.getInstance().register(SNOW, Snow.SnowFactory::new);
    }

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "sandmote"), SANDMOTE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "rain"), RAIN);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "tumblebush"), TUMBLEBUSH);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("impactfulweather", "snow"), SNOW);

    }
}
