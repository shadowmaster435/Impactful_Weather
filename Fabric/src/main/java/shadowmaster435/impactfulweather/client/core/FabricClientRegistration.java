package shadowmaster435.impactfulweather.client.core;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import shadowmaster435.impactfulweather.client.core.init.builder.ModSpriteParticleRegistration;
import shadowmaster435.impactfulweather.core.init.RegistryReference;

/**
 * Fabric implementation of {@link ClientRegistration}
 * everything is handled directly, as Fabric has no loading stages
 */
public class FabricClientRegistration implements ClientRegistration {

    @Override
    public <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(type.get(), provider);
    }

    @Override
    public <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ModSpriteParticleRegistration<T> factory) {
        ParticleFactoryRegistry.getInstance().register(type.get(), factory::create);
    }
}
