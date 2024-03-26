package org.shadowmaster435.biomeparticleweather.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.shadowmaster435.biomeparticleweather.util.ConfigFile;
import org.shadowmaster435.biomeparticleweather.util.ConfigParameters;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class TogglesSettingScreen extends Screen {


    private final Screen parent;


    @Override
    public void close() {
        client.setScreen(parent);
        ConfigFile.save_config();
    }

    public TogglesSettingScreen(Screen parent) {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("My tutorial screen"));
        this.parent = parent;
    }

    public HashMap<ConfigParameters, TypedToggleButtonWidget> options = new HashMap<>();
    public TextFieldWidget text;

    public String tst = "";

    @Override
    protected void init() {
        try {
            add_setting(new ConfigParameters("vanilla_lightning", "bool"));
            add_setting(new ConfigParameters("rain_trails", "bool"));
            add_setting(new ConfigParameters("rain_splash", "bool"));
            add_setting(new ConfigParameters("rain_surface_ripples", "bool"));
            add_setting(new ConfigParameters("vanilla_rain_rendering", "bool"));
            add_setting(new ConfigParameters("vanilla_rain_splash", "bool"));

            for (ConfigParameters parameters : options.keySet()) {
                set_value_of(parameters, ParticleSettings.get_bool(parameters.name()));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void add_setting(ConfigParameters parameters) {
        var text_display = new TextWidget(4,4 + (20 * options.size()),512,16,Text.translatable("biomeparticleweather." + parameters.name()),MinecraftClient.getInstance().textRenderer);
        var button = new TypedToggleButtonWidget(width - 68,4 + (20 * options.size()), ParticleSettings.get_bool(parameters.name()), parameters);
        text_display.alignLeft();
        text_display.setTooltip(Tooltip.of(Text.translatable("biomeparticleweather." + parameters.name() + ".tooltip") ));
        options.put(parameters, button);
        button.setTrackedChangedListener((a) -> this.changed(new TypedToggleButtonWidget.TrackedListener(a.value(), a.id(), a.type())));
        addDrawableChild(text_display);
        addDrawableChild(button);
    }

    public void changed(TypedToggleButtonWidget.TrackedListener listener) {
        set_setting(new ConfigParameters(listener.id(), listener.type()), listener.value());
    }

    public void set_value_of(ConfigParameters parameters, boolean value) {
        for (ConfigParameters param : options.keySet()) {
            if (param.match(parameters)) {
                options.get(param).setToggled(value);
                return;
            }
        }
    }

    public void set_setting(ConfigParameters parameters, boolean value) {
        try {
            ParticleSettings.set_bool(parameters.name(), value);
        } catch (Exception ignored) {
            System.out.println("bool '" + parameters.name() + "' doesn't exist.");

        }
    }


}