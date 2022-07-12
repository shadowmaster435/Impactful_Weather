package shadowmaster435.impactfulweather.core;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import shadowmaster435.impactfulweather.core.init.RegistryManager;

public interface CommonAbstractions {

    /**
     * creates a new registry manager for <code>namespace</code> or returns an existing one
     * @param namespace namespace used for registration
     * @return new mod specific registry manager
     */
    RegistryManager createRegistryManager(String namespace);

    SimpleParticleType createSimpleParticleType(boolean alwaysActive);

    void registerConfig(String modId, ModConfig.Type type, IConfigSpec<?> spec);
}
