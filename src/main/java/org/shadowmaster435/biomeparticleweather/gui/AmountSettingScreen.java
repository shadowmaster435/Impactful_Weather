package org.shadowmaster435.biomeparticleweather.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.shadowmaster435.biomeparticleweather.util.ConfigFile;
import org.shadowmaster435.biomeparticleweather.util.ConfigParameters;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AmountSettingScreen extends Screen {


    private final Screen parent;


    @Override
    public void close() {
        client.setScreen(parent);
        ConfigFile.save_config();
    }

    public AmountSettingScreen(Screen parent) {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("My tutorial screen"));
        this.parent = parent;
    }

    public HashMap<ConfigParameters, TextFieldWidget> options = new HashMap<>();
    public TextFieldWidget text;

    public String tst = "";

    @Override
    protected void init() {
        try {
            add_setting(new ConfigParameters("rain_amount", "int"));
            add_setting(new ConfigParameters("heavy_rain_amount", "int"));
            add_setting(new ConfigParameters("fog_amount", "int"));
            add_setting(new ConfigParameters("wind_amount", "int"));

            for (ConfigParameters parameters : options.keySet()) {
                if (Objects.equals(parameters.type(), "float")) {
                    set_text_of(parameters, String.valueOf(ParticleSettings.get_float(parameters.name())));
                }
                if (Objects.equals(parameters.type(), "int")) {
                    set_text_of(parameters, String.valueOf(ParticleSettings.get_int(parameters.name())));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void add_setting(ConfigParameters parameters) {
        var text_display = new TextWidget(4,4 + (20 * options.size()),512,16,Text.translatable("biomeparticleweather." + parameters.name()),MinecraftClient.getInstance().textRenderer);
        var text_input = new TypedTextFieldWidget(MinecraftClient.getInstance().textRenderer, width - 68,4 + (20 * options.size()),64,16,Text.of(""), parameters);
        text_display.alignLeft();
        options.put(parameters, text_input);
        text_input.setTrackedChangedListener((a) -> this.changed(new TypedTextFieldWidget.TrackedListener(a.text(), a.id(), a.type())));
        addDrawableChild(text_display);
        addDrawableChild(text_input);
    }

    public void set_float_setting(String key, float value) {
        ParticleSettings.set_float(key, value);
    }
    public void set_int_setting(String key, int value) {
        ParticleSettings.set_int(key, value);
    }
    public final String[] valid_chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};

    public void changed(TypedTextFieldWidget.TrackedListener listener) {
        set_setting(new ConfigParameters(listener.id(), listener.type()), listener.text());
    }

    public void set_text_of(ConfigParameters parameters, String text) {
        for (ConfigParameters param : options.keySet()) {
            if (param.match(parameters)) {

                options.get(param).setText(text);
                return;
            }
        }
    }

    public void set_setting(ConfigParameters parameters, String text) {
        if (Objects.equals(parameters.type(), "float")) {
            try {
                set_float_setting(parameters.name(), Float.parseFloat(text));
            } catch (Exception ignored) {
                set_text_of(new ConfigParameters(parameters.name(), parameters.type()), String.valueOf(ParticleSettings.get_float(parameters.name())));
                System.out.println("float parse failed");

            }
        }
        if (Objects.equals(parameters.type(), "int")) {
            try {
                set_int_setting(parameters.name(), Integer.parseInt(text));
            } catch (Exception ignored) {
                set_text_of(new ConfigParameters(parameters.name(), parameters.type()), String.valueOf(ParticleSettings.get_int(parameters.name())));
                System.out.println("int parse failed");
            }
        }
    }
    public void test(String string) {
        System.out.println(string);
    }

}