package shadowmaster435.impactfulweather.core.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * handles registering to forge registries
 * this is a mod specific instance now for Fabric compatibility, Forge would support retrieving current namespace from mod loading context
 * originally heavily inspired by RegistryHelper found in Vazkii's AutoRegLib mod
 */
public class FabricRegistryManager implements RegistryManager {
    /**
     * namespace for this instance
     */
    private final String namespace;

    /**
     * private constructor
     * @param namespace namespace for this instance
     */
    private FabricRegistryManager(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String namespace() {
        return this.namespace;
    }

    @Override
    public <T> RegistryReference<T> register(final ResourceKey<? extends Registry<? super T>> registryKey, String path, Supplier<T> supplier) {
        T value = supplier.get();
        Registry<? super T> registry = (Registry<? super T>) Registry.REGISTRY.get(registryKey.location());
        Objects.requireNonNull(value, "Can't register null value");
        Objects.requireNonNull(registry, String.format("Registry %s not found", registryKey));
        ResourceLocation resourceLocation = this.locate(path);
        Registry.register(registry, resourceLocation, value);
        return new FabricRegistryReference<>(value, resourceLocation, registry);
    }

    /**
     * creates a new registry manager for <code>namespace</code> or returns an existing one
     * @param namespace namespace used for registration
     * @return new mod specific registry manager
     */
    public static RegistryManager of(String namespace) {
        return MOD_TO_REGISTRY.computeIfAbsent(namespace, FabricRegistryManager::new);
    }
}
