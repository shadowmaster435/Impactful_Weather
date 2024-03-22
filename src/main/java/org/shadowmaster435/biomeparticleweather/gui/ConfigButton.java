package org.shadowmaster435.biomeparticleweather.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.texture.Scaling;
import net.minecraft.client.texture.Sprite;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ConfigButton extends ButtonWidget {


    public static final Identifier button_texture = new Identifier("biomeparticleweather", "textures/gui/button.png");
    public ConfigButton(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, message, onPress, narrationSupplier);
    }


    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderWidget(context, mouseX, mouseY, delta);
        context.drawTexture(button_texture, this.getX(), this.getY(), 0, 0, 16, 16, 16, 16);
    }


    @Override
    public boolean isNarratable() {
        return false;
    }
}
