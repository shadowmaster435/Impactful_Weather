package shadowmaster435.impactfulweather.core;

import java.util.ServiceLoader;

public class CoreServices {
    /**
     * register for furnace fuel items, might be included in another service in the future
     */
    public static final CommonAbstractions ABSTRACTIONS = load(CommonAbstractions.class);

    /**
     * loads a service yay
     * @param clazz service class to load
     * @param <T>   service type
     * @return      loaded service
     */
    protected static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
