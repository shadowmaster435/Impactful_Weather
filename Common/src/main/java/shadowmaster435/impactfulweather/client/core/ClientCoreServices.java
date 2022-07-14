package shadowmaster435.impactfulweather.client.core;

import shadowmaster435.impactfulweather.core.CoreServices;

/**
 * services which may only be loaded on the client side
 */
public class ClientCoreServices extends CoreServices {
    /**
     * a collection of utility methods for registering client side content
     */
    public static final ClientRegistration CLIENT_REGISTRATION = load(ClientRegistration.class);
}
