package shadowmaster435.impactfulweather.core.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * handles registering to forge registries
 * this is a mod specific instance now for Fabric compatibility, Forge would support retrieving current namespace from mod loading context
 * originally heavily inspired by RegistryHelper found in Vazkii's AutoRegLib mod
 */
public class ForgeRegistryManager implements RegistryManager {
    /**
     * storage for {@link DeferredRegister} required for registering data on Forge
     */
    private static final Map<ResourceKey<? extends Registry<?>>, DeferredRegister<?>> DEFERRED_REGISTERS = Maps.newConcurrentMap();

    /**
     * namespace for this instance
     */
    private final String namespace;
    /**
     * the mod event bus required for registering {@link DeferredRegister}
     */
    private final IEventBus modEventBus;

    /**
     * private constructor
     * @param namespace namespace for this instance
     */
    private ForgeRegistryManager(String namespace) {
        this.namespace = namespace;
        this.modEventBus = ModList.get().getModContainerById(namespace)
                .map(container -> (FMLModContainer) container)
                .map(FMLModContainer::getEventBus)
                .orElseThrow();
    }

    @Override
    public String namespace() {
        return this.namespace;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> RegistryReference<T> register(final ResourceKey<? extends Registry<? super T>> registryKey, String path, Supplier<T> supplier) {
        DeferredRegister<?> register = DEFERRED_REGISTERS.computeIfAbsent(registryKey, key -> {
            DeferredRegister<T> deferredRegister = DeferredRegister.create((ResourceKey<? extends Registry<T>>) registryKey, this.namespace);
            deferredRegister.register(this.modEventBus);
            return deferredRegister;
        });
        if (StringUtils.isEmpty(path)) throw new IllegalArgumentException("Can't register object without name");
        RegistryObject<T> registryObject = ((DeferredRegister<T>) register).register(path, supplier);
        return new ForgeRegistryReference<>(registryObject, (ResourceKey<? extends Registry<T>>) registryKey);
    }

    /**
     * creates a new registry manager for <code>namespace</code> or returns an existing one
     * @param namespace namespace used for registration
     * @return new mod specific registry manager
     */
    public static RegistryManager of(String namespace) {
        return MOD_TO_REGISTRY.computeIfAbsent(namespace, ForgeRegistryManager::new);
    }
}
