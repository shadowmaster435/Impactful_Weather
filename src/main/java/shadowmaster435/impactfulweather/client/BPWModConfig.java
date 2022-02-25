package shadowmaster435.impactfulweather.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "impactfulweather")
public class BPWModConfig implements ConfigData {
    public int particleamount = 32;
    public boolean dodenserain = true;
    public int particleDensity = 1;
}