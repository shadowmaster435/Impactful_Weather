package org.shadowmaster435.biomeparticleweather.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.text.Text;
import org.shadowmaster435.biomeparticleweather.util.ConfigParameters;
import org.shadowmaster435.biomeparticleweather.util.ParticleSettings;

import java.util.function.Consumer;

public class TypedToggleButtonWidget extends CheckboxWidget {


    public Consumer<TrackedListener> listener;
    public boolean toggled;
    public String id = "";
    public String type = "";
    public TypedToggleButtonWidget(int x, int y, boolean toggled, ConfigParameters parameters) {
        super(x, y, Text.empty(), MinecraftClient.getInstance().textRenderer, toggled, (checkbox, checked) -> ((TypedToggleButtonWidget) checkbox).update(checked));
        this.id = parameters.name();
        this.type = parameters.type();
        setToggled(toggled);
    }

    public record TrackedListener(boolean value, String id, String type) {}
    @Override
    public void onPress() {
        super.onPress();

    }


    public void update(boolean checked) {
        var l = new TrackedListener(checked, id, type);
        listener.accept(l);

    }
    public void setToggled(boolean value) {
        checked = value;
    }


    public void setTrackedChangedListener(Consumer<TrackedListener> changedListener) {
        listener = changedListener;
    }
}
