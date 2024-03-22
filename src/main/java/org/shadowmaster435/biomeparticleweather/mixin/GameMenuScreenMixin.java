package org.shadowmaster435.biomeparticleweather.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.entity.LightningEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.shadowmaster435.biomeparticleweather.gui.ConfigScreen;
import org.shadowmaster435.biomeparticleweather.util.StaticMethods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Inject(at = @At("TAIL"), method = "initWidgets")
    public void initWigits(CallbackInfo ci) {
        StaticMethods.init_config((GameMenuScreen)(Object)this);
    }

}
