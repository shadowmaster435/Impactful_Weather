package org.shadowmaster435.biomeparticleweather.util.behavior;

public record BehaviorCondition(String first, String type, String second) {




    public boolean get_value(ModularParticle particle) {
        var result = false;
        switch (type) {
            case "!=" -> result = not_equal(particle);
            case "==" -> result = equal(particle);
            case ">" -> result = greater_than(particle);
            case "<" -> result = less_than(particle);
            case ">=" -> result = greater_than_eq(particle);
            case "<=" -> result = less_than_eq(particle);
        }
        return result;
    }

    public boolean not_equal(ModularParticle particle) {
        return particle.get(first) != particle.get(second);
    }

    public boolean equal(ModularParticle particle) {
        return particle.get(first) == particle.get(second);
    }

    public boolean greater_than(ModularParticle particle) {
        return (float) particle.get(first) > (float) particle.get(second);
    }

    public boolean less_than(ModularParticle particle) {
        return (float) particle.get(first) < (float) particle.get(second);
    }

    public boolean greater_than_eq(ModularParticle particle) {
        return (float) particle.get(first) >= (float) particle.get(second);
    }

    public boolean less_than_eq(ModularParticle particle) {
        return (float) particle.get(first) <= (float) particle.get(second);
    }




}
