package shadowmaster435.impactfulweather.core;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import shadowmaster435.impactfulweather.core.init.FabricRegistryManager;
import shadowmaster435.impactfulweather.core.init.RegistryManager;

public class FabricAbstractions implements CommonAbstractions {

    @Override
    public RegistryManager createRegistryManager(String namespace) {
        return FabricRegistryManager.of(namespace);
    }

    @Override
    public SimpleParticleType createSimpleParticleType(boolean alwaysActive) {
        return FabricParticleTypes.simple(alwaysActive);
    }

    @Override
    public void registerConfig(String modId, ModConfig.Type type, IConfigSpec<?> spec) {
        ModLoadingContext.registerConfig(modId, type, spec);
    }
}
