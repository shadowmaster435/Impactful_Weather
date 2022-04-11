package shadowmaster435.impactfulweather.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import shadowmaster435.impactfulweather.client.BPWModConfig;

public class MiscInit {
    public static final BPWModConfig config = AutoConfig.getConfigHolder(BPWModConfig.class).getConfig();


    public static GuiRegistry registry = AutoConfig.getGuiRegistry(BPWModConfig.class);



}
