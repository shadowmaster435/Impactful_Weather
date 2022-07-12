package shadowmaster435.impactfulweather.core.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * handles registering to game registries
 * this is a mod specific instance now for Fabric compatibility, Forge would support retrieving current namespace from mod loading context
 * originally based on RegistryHelper found in Vazkii's AutoRegLib mod
 */
public interface RegistryManager {
    /**
     * registry data is stored for each mod separately so when registry events are fired every mod is responsible for registering their own stuff
     * this is important so that entries are registered for the proper namespace
     */
    Map<String, RegistryManager> MOD_TO_REGISTRY = Maps.newConcurrentMap();

    /**
     * @return namespace for this instance
     */
    String namespace();

    /**
     * creates a placeholder registry reference for this {@link #namespace()}
     * @param registryKey key for registry to register to
     * @param path path for new entry
     * @param <T> registry type
     * @return placeholder registry object for <code>entry</code>
     */
    default <T> RegistryReference<T> placeholder(final ResourceKey<? extends Registry<? super T>> registryKey, String path) {
        return RegistryReference.placeholder(registryKey, this.locate(path));
    }

    /**
     * register any type of registry entry with a path
     * @param registryKey key for registry to register to
     * @param path path for new entry
     * @param supplier supplier for entry to register
     * @return registry object for <code>entry</code>
     * @param <T> registry type
     */
    <T> RegistryReference<T> register(final ResourceKey<? extends Registry<? super T>> registryKey, String path, Supplier<T> supplier);

    /**
     * @param path path for location
     * @return resource location for {@link #namespace}
     */
    default ResourceLocation locate(String path) {
        if (StringUtils.isEmpty(path)) throw new IllegalArgumentException("Can't register object without name");
        return new ResourceLocation(this.namespace(), path);
    }
}
