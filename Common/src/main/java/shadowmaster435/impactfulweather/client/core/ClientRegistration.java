package shadowmaster435.impactfulweather.client.core;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import shadowmaster435.impactfulweather.client.core.init.builder.ModSpriteParticleRegistration;
import shadowmaster435.impactfulweather.core.init.RegistryReference;

/**
 * a collection of utility methods for registering client side content
 */
public interface ClientRegistration {

    /**
     * registers a factory for a particle type client side
     * @param type     particle type (registered separately)
     * @param provider particle factory
     * @param <T>      type of particle
     */
    <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ParticleProvider<T> provider);

    /**
     * registers a factory for a particle type client side
     * @param type     particle type (registered separately)
     * @param factory particle factory
     * @param <T>      type of particle
     */
    <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ModSpriteParticleRegistration<T> factory);
}
