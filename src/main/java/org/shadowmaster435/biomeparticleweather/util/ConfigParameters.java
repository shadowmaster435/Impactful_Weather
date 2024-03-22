package org.shadowmaster435.biomeparticleweather.util;

import java.util.Objects;

public record ConfigParameters(String name, String type) {
    public boolean match(ConfigParameters parameters) {
        return Objects.equals(parameters.name, name()) && Objects.equals(parameters.type, type());
    }
}
