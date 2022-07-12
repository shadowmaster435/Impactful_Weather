package shadowmaster435.impactfulweather.client.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import shadowmaster435.impactfulweather.BiomeParticleWeather;
import shadowmaster435.impactfulweather.client.core.init.builder.ModSpriteParticleRegistration;
import shadowmaster435.impactfulweather.core.init.RegistryReference;

import java.util.List;
import java.util.Set;

/**
 * Forge implementation of {@link ClientRegistration}
 * content is collected first and then registered when the appropriate event is fired on the mod event bus
 * registration of this class to the mod event bus is done automatically whenever it is used by a mod
 */
public class ForgeClientRegistration implements ClientRegistration {
    /**
     * all the mod event buses this instance has been registered to,
     * it is important to not register more than once as the events will also run every time, resulting in duplicate content
     */
    private final Set<IEventBus> modEventBuses = Sets.newIdentityHashSet();
    /**
     * particle types registered via particle providers
     */
    private final List<Pair<RegistryReference<? extends ParticleType<? extends ParticleOptions>>, ParticleProvider<?>>> particleProviders = Lists.newArrayList();
    /**
     * particle types registered via sprite factories
     */
    private final List<Pair<RegistryReference<? extends ParticleType<? extends ParticleOptions>>, ModSpriteParticleRegistration<?>>> spriteParticleFactories = Lists.newArrayList();

    @Override
    public <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ParticleProvider<T> provider) {
        this.registerModEventBus();
        this.particleProviders.add(Pair.of(type, provider));
    }

    @Override
    public <T extends ParticleOptions> void registerParticleProvider(RegistryReference<? extends ParticleType<T>> type, ModSpriteParticleRegistration<T> factory) {
        this.registerModEventBus();
        this.spriteParticleFactories.add(Pair.of(type, factory));
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public void onRegisterParticleProviders(final RegisterParticleProvidersEvent evt) {
        this.particleProviders.forEach(pair -> evt.register((ParticleType<ParticleOptions>) pair.left().get(), (ParticleProvider<ParticleOptions>) pair.right()));
        this.spriteParticleFactories.forEach(pair -> evt.register((ParticleType<ParticleOptions>) pair.left().get(), spriteSet -> (ParticleProvider<ParticleOptions>) pair.right().create(spriteSet)));
    }

    /**
     * register this singleton instance to the provided mod event bus in case we haven't done so yet
     * call this in every base method inherited from {@link ClientRegistration}
     */
    private void registerModEventBus() {
        if (this.modEventBuses.add(FMLJavaModLoadingContext.get().getModEventBus())) {
            FMLJavaModLoadingContext.get().getModEventBus().register(this);
            BiomeParticleWeather.LOGGER.info("Added listener to client registration of mod {}", ModLoadingContext.get().getActiveNamespace());
        }
    }
}
