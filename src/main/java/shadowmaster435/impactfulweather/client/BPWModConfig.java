package shadowmaster435.impactfulweather.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "impactfulweather")
public class BPWModConfig implements ConfigData {
    public int particleamount = 32;
    public boolean dodenserain = true;
    public int particleDensity = 1;
    public boolean sandstorms = true;
    public boolean redsandstorms = true;
    public boolean blizzards = true;
    public boolean heavyrain = true;
    public boolean wind = true;
    public int nweatherbasedelay = 120;
    public int nweatherrandomdelay = 240;
    public boolean weepingrain = true;
    public boolean sporestorm = true;
    public boolean updrafts = true;
    public boolean soulstorms = true;
    public boolean endlessnweather = true;
}