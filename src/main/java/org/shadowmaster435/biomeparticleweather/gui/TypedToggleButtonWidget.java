package org.shadowmaster435.biomeparticleweather.gui;

import net.minecraft.client.gui.widget.ToggleButtonWidget;
import org.shadowmaster435.biomeparticleweather.util.ConfigParameters;

import java.util.function.Consumer;

public class TypedToggleButtonWidget extends ToggleButtonWidget {


    public Consumer<TypedTextFieldWidget.TrackedListener> listener;

    public String id = "";
    public String type = "";
    public TypedToggleButtonWidget(int x, int y, int width, int height, boolean toggled, ConfigParameters parameters) {
        super(x, y, width, height, toggled);
    }


    public void setTrackedChangedListener(Consumer<TypedTextFieldWidget.TrackedListener> changedListener) {
        listener = changedListener;
    }
}
