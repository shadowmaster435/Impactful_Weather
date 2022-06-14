package shadowmaster435.impactfulweather.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "impactfulweather")
public class BPWModConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Toggles toggles = new Toggles();

    @ConfigEntry.Gui.CollapsibleObject
    public AmountConf particleamountconfig = new AmountConf();

    @ConfigEntry.Gui.CollapsibleObject
    public Miscconf misc = new Miscconf();

    @ConfigEntry.Gui.CollapsibleObject
    public Particletoggles particletoggles = new Particletoggles();


    public static class AmountConf {
        public float particleamount = 32;
        public float particleDensity = 1;
        public float sandmotemodifier = 1;
        public float snowmodifier = 1;
        public float blizzardsnowmodifier = 1;
        public float blizzardwindmodifier = 1;
        public float updraftmodifier = 1;
        public float windmodifier = 1;
        public float sporemodifier = 1;
        public float soulmodifier = 1;
        public float tearmodifier = 1;
        public float rainmodifier = 1;
        public float heavyrainmodifier = 1;
        public float tumblebushmodifier = 1;

        public float fireflymodifier = 1;


    }


    public static class Miscconf {

     //   public float lightningburnmarklifespan = 500;
        public int nweatherbasedelay = 120;
        public int nweatherrandomdelay = 240;

        public boolean renderedweather = false;
        public float snowspeedmodifier = 1;


        @ConfigEntry.Gui.CollapsibleObject
        public BlockSearchDist searchDist = new BlockSearchDist();
        public static class BlockSearchDist {
            public int x = 32;
            public int y = 32;
            public int z = 32;
        }
    }

    public static class Particletoggles {
        public boolean fireflies = true;

        public boolean sandmote = true;
        public boolean redsandmote = true;
        public boolean tumblebush = true;
        public boolean snow = true;
        public boolean blizzardwind = true;
        public boolean blizzardsnow = true;
        public boolean rain = true;
        public boolean heavyrain = true;
        public boolean rainsplash = true;

    }

    public static class Toggles {
        public boolean weepingrain = true;
        public boolean sporestorm = true;
        public boolean updrafts = true;
        public boolean soulstorms = true;
        public boolean endlessnweather = false;


        public boolean sandstorms = true;
        public boolean redsandstorms = true;
        public boolean blizzards = true;
        public boolean heavyrain = true;
        public boolean wind = true;



    }
}