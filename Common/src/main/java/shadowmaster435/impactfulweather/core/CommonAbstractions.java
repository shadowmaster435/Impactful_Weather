package shadowmaster435.impactfulweather.core;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public interface CommonAbstractions {

    SimpleParticleType createSimpleParticleType(boolean alwaysActive);

    void registerConfig(String modId, ModConfig.Type type, IConfigSpec<?> spec);
}
