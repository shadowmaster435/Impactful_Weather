package org.shadowmaster435.biomeparticleweather.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.shadowmaster435.biomeparticleweather.util.ConfigFile;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {


    private final Screen parent;


    @Override
    public void close() {
        client.setScreen(parent);
        ConfigFile.save_config();
    }

    public ConfigScreen(Screen parent) {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("My tutorial screen"));
        this.parent = parent;
    }


    public ButtonWidget button1;
    public ButtonWidget button2;

    public TextFieldWidget text;

    public String tst = "";

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Amount Config"), button -> MinecraftClient.getInstance().setScreen(new AmountSettingScreen(this)))
                .dimensions(width / 2 - 205, 20, 200, 20)
                .build();
        button2 = ButtonWidget.builder(Text.literal("Toggles"), button -> MinecraftClient.getInstance().setScreen(new TogglesSettingScreen(this)))
                .dimensions(width / 2 + 5, 20, 200, 20)
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }

}