package shadowmaster435.impactfulweather.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ClientConfig INSTANCE = new ClientConfig();

    private final ForgeConfigSpec spec;
    public final ParticleTogglesConfig particleToggles;
    public final ParticleAmountsConfig particleAmounts;
    public final ClientConfig.MiscConfig misc;

    private ClientConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        this.particleToggles = new ParticleTogglesConfig(builder);
        this.particleAmounts = new ParticleAmountsConfig(builder);
        this.misc = new MiscConfig(builder);
        this.spec = builder.build();
    }

    public ForgeConfigSpec getSpec() {
        return this.spec;
    }

    public static class ParticleAmountsConfig {
        public final ForgeConfigSpec.DoubleValue particleamount;
        public final ForgeConfigSpec.DoubleValue particleDensity;
        public final ForgeConfigSpec.DoubleValue sandmotemodifier;
        public final ForgeConfigSpec.DoubleValue snowmodifier;
        public final ForgeConfigSpec.DoubleValue blizzardsnowmodifier;
        public final ForgeConfigSpec.DoubleValue blizzardwindmodifier;
        public final ForgeConfigSpec.DoubleValue updraftmodifier;
        public final ForgeConfigSpec.DoubleValue windmodifier;
        public final ForgeConfigSpec.DoubleValue sporemodifier;
        public final ForgeConfigSpec.DoubleValue soulmodifier;
        public final ForgeConfigSpec.DoubleValue tearmodifier;
        public final ForgeConfigSpec.DoubleValue rainmodifier;
        public final ForgeConfigSpec.DoubleValue heavyrainmodifier;
        public final ForgeConfigSpec.DoubleValue tumblebushmodifier;
        public final ForgeConfigSpec.DoubleValue fireflymodifier;

        private ParticleAmountsConfig(ForgeConfigSpec.Builder builder) {
            builder.push("particle_amounts");
            this.particleamount = builder.defineInRange("particleamount", 1.0, 0, Double.MAX_VALUE);
            this.particleDensity = builder.defineInRange("particleDensity", 1.0, 0, Double.MAX_VALUE);
            this.sandmotemodifier = builder.defineInRange("sandmotemodifier", 1.0, 0, Double.MAX_VALUE);
            this.snowmodifier = builder.defineInRange("snowmodifier", 1.0, 0, Double.MAX_VALUE);
            this.blizzardsnowmodifier = builder.defineInRange("blizzardsnowmodifier", 1.0, 0, Double.MAX_VALUE);
            this.blizzardwindmodifier = builder.defineInRange("blizzardwindmodifier", 1.0, 0, Double.MAX_VALUE);
            this.updraftmodifier = builder.defineInRange("updraftmodifier", 1.0, 0, Double.MAX_VALUE);
            this.windmodifier = builder.defineInRange("windmodifier", 1.0, 0, Double.MAX_VALUE);
            this.sporemodifier = builder.defineInRange("sporemodifier", 1.0, 0, Double.MAX_VALUE);
            this.soulmodifier = builder.defineInRange("soulmodifier", 1.0, 0, Double.MAX_VALUE);
            this.tearmodifier = builder.defineInRange("tearmodifier", 1.0, 0, Double.MAX_VALUE);
            this.rainmodifier = builder.defineInRange("rainmodifier", 1.0, 0, Double.MAX_VALUE);
            this.heavyrainmodifier = builder.defineInRange("heavyrainmodifier", 1.0, 0, Double.MAX_VALUE);
            this.tumblebushmodifier = builder.defineInRange("tumblebushmodifier", 1.0, 0, Double.MAX_VALUE);
            this.fireflymodifier = builder.defineInRange("fireflymodifier", 1.0, 0, Double.MAX_VALUE);
            builder.pop();
        }
    }

    public static class MiscConfig {
//        public final ForgeConfigSpec.DoubleValue lightningburnmarklifespan;
        public final ForgeConfigSpec.IntValue nweatherbasedelay;
        public final ForgeConfigSpec.IntValue nweatherrandomdelay;
        public final ForgeConfigSpec.BooleanValue renderedweather;
        public final ForgeConfigSpec.DoubleValue snowspeedmodifier;
        public final SearchDistanceConfig searchDistanceConfig;

        private MiscConfig(ForgeConfigSpec.Builder builder) {
            builder.push("misc");
//            this.lightningburnmarklifespan = builder.defineInRange("lightningburnmarklifespan", 500.0, 0, Double.MAX_VALUE);
            this.nweatherbasedelay = builder.defineInRange("nweatherbasedelay", 120, 0, Integer.MAX_VALUE);
            this.nweatherrandomdelay = builder.defineInRange("nweatherrandomdelay", 240, 0, Integer.MAX_VALUE);
            this.renderedweather = builder.define("renderedweather", false);
            this.snowspeedmodifier = builder.defineInRange("snowspeedmodifier", 1.0, 0, Double.MAX_VALUE);
            this.searchDistanceConfig = new SearchDistanceConfig(builder);
            builder.pop();
        }

        public static class SearchDistanceConfig {
            public final ForgeConfigSpec.IntValue x;
            public final ForgeConfigSpec.IntValue y;
            public final ForgeConfigSpec.IntValue z;

            private SearchDistanceConfig(ForgeConfigSpec.Builder builder) {
                builder.push("block_search_distance");
                this.x = builder.defineInRange("x", 32, 0, Integer.MAX_VALUE);
                this.y = builder.defineInRange("y", 32, 0, Integer.MAX_VALUE);
                this.z = builder.defineInRange("z", 32, 0, Integer.MAX_VALUE);
                builder.pop();
            }
        }
    }

    public static class ParticleTogglesConfig {
        public final ForgeConfigSpec.BooleanValue fireflies;
        public final ForgeConfigSpec.BooleanValue sandmote;
        public final ForgeConfigSpec.BooleanValue redsandmote;
        public final ForgeConfigSpec.BooleanValue tumblebush;
        public final ForgeConfigSpec.BooleanValue snow;
        public final ForgeConfigSpec.BooleanValue blizzardwind;
        public final ForgeConfigSpec.BooleanValue blizzardsnow;
        public final ForgeConfigSpec.BooleanValue rain;
        public final ForgeConfigSpec.BooleanValue heavyrain;
        public final ForgeConfigSpec.BooleanValue rainsplash;
        public final ForgeConfigSpec.BooleanValue weepingrain;
        public final ForgeConfigSpec.BooleanValue sporestorm;
        public final ForgeConfigSpec.BooleanValue updrafts;
        public final ForgeConfigSpec.BooleanValue soulstorms;
        public final ForgeConfigSpec.BooleanValue endlessnweather;
        public final ForgeConfigSpec.BooleanValue sandstorms;
        public final ForgeConfigSpec.BooleanValue redsandstorms;
        public final ForgeConfigSpec.BooleanValue blizzards;
        public final ForgeConfigSpec.BooleanValue wind;
        
        private ParticleTogglesConfig(ForgeConfigSpec.Builder builder) {
            builder.push("particle_toggles");
            this.fireflies = builder.define("fireflies", true);
            this.sandmote = builder.define("sandmote", true);
            this.redsandmote = builder.define("redsandmote", true);
            this.tumblebush = builder.define("tumblebush", true);
            this.snow = builder.define("snow", true);
            this.blizzardwind = builder.define("blizzardwind", true);
            this.blizzardsnow = builder.define("blizzardsnow", true);
            this.rain = builder.define("rain", true);
            this.heavyrain = builder.define("heavyrain", true);
            this.rainsplash = builder.define("rainsplash", true);
            this.weepingrain = builder.define("weepingrain", true);
            this.sporestorm = builder.define("sporestorm", true);
            this.updrafts = builder.define("updrafts", true);
            this.soulstorms = builder.define("soulstorms", true);
            this.endlessnweather = builder.define("endlessnweather", false);
            this.sandstorms = builder.define("sandstorms", true);
            this.redsandstorms = builder.define("redsandstorms", true);
            this.blizzards = builder.define("blizzards", true);
            this.wind = builder.define("wind", true);
            builder.pop();
        }
    }
}