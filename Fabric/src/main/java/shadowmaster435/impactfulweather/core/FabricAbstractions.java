package shadowmaster435.impactfulweather.core;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.impl.game.minecraft.patch.ModClassLoader_125_FML;
import net.minecraft.core.particles.SimpleParticleType;
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
        ForgeConfigRegistry.INSTANCE.register(modId, type, spec);
    }
}
