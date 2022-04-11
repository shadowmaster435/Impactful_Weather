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
        public int particleamount = 32;
        public int particleDensity = 1;
        public int sandmotemodifier = 1;
        public int snowmodifier = 1;
        public int blizzardsnowmodifier = 1;
        public int blizzardwindmodifier = 1;
        public int updraftmodifier = 1;
        public int windmodifier = 1;
        public int sporemodifier = 1;
        public int soulmodifier = 1;
        public int tearmodifier = 1;
        public int rainmodifier = 1;
        public int heavyrainmodifier = 1;
        public int tumblebushmodifier = 1;
    }


    public static class Miscconf {
        public int nweatherbasedelay = 120;
        public int nweatherrandomdelay = 240;
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
        public boolean dodenserain = true;

        public boolean sandstorms = true;
        public boolean redsandstorms = true;
        public boolean blizzards = true;
        public boolean heavyrain = true;
        public boolean wind = true;



    }
}