package org.shadowmaster435.biomeparticleweather.mixin;

import net.minecraft.client.MinecraftClient;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {



    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo ci) {
        ParticleEngine.try_spawn_particles();
    }
}
