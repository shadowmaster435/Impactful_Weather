package shadowmaster435.impactfulweather.core;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import shadowmaster435.impactfulweather.core.init.ForgeRegistryManager;
import shadowmaster435.impactfulweather.core.init.RegistryManager;

public class ForgeAbstractions implements CommonAbstractions {

    @Override
    public RegistryManager createRegistryManager(String namespace) {
        return ForgeRegistryManager.of(namespace);
    }

    @Override
    public SimpleParticleType createSimpleParticleType(boolean alwaysActive) {
        return new SimpleParticleType(alwaysActive);
    }

    @Override
    public void registerConfig(String modId, ModConfig.Type type, IConfigSpec<?> spec) {
        ModLoadingContext.get().registerConfig(type, spec);
    }
}
