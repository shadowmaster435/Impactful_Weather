package org.shadowmaster435.biomeparticleweather.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.shadowmaster435.biomeparticleweather.util.ConfigParameters;

import java.util.function.Consumer;

public class TypedTextFieldWidget extends TextFieldWidget {

    public Consumer<TrackedListener> listener;

    public String id = "";
    public String type = "";


    public TypedTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text, ConfigParameters parameters) {
        super(textRenderer, x, y, width, height, text);
        this.id = parameters.name();
        this.type = parameters.type();
    }
    public static record TrackedListener(String text, String id, String type) {

    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {


        return super.clicked(mouseX, mouseY);
    }

    @Override
    public void setFocused(boolean focused) {
        if (focused) {
            setCursor(0, true);
            setSelectionEnd(getText().length());
        } else {
            setSelectionStart(0);
            setSelectionEnd(0);
        }
        super.setFocused(focused);
    }

    public void setTrackedChangedListener(Consumer<TrackedListener> changedListener) {
        listener = changedListener;
    }
    @Override
    public void write(String text) {
        super.write(text);
        listener.accept(new TrackedListener(this.getText(), id, type));
    }

    @Override
    public void setCursor(int cursor, boolean shiftKeyPressed) {
        super.setCursor(cursor, shiftKeyPressed);
        listener.accept(new TrackedListener(this.getText(), id, type));

    }
    @Override
    public void setMaxLength(int maxLength) {
        super.setMaxLength(maxLength);
        listener.accept(new TrackedListener(this.getText(), id, type));
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        listener.accept(new TrackedListener(this.getText(), id, type));

    }
}
