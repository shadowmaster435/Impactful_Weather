package org.shadowmaster435.biomeparticleweather.util.behavior;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.world.ClientWorld;
import org.shadowmaster435.biomeparticleweather.particle.ParticleBase;
import org.shadowmaster435.biomeparticleweather.util.Vector3;
import org.shadowmaster435.biomeparticleweather.util.dynamic_class.DynamicClass;

import java.util.HashMap;

public class ModularParticle extends DynamicClass {

    public HashMap<String, BehaviorValue> values;

    public ModularParticle(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(new ParticleBase(world, pos, spriteProvider));

    }
}
