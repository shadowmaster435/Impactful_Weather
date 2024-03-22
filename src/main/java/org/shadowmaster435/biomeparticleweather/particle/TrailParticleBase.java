package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import org.shadowmaster435.biomeparticleweather.util.ParticleEngine;
import org.shadowmaster435.biomeparticleweather.util.Timer;
import org.shadowmaster435.biomeparticleweather.util.Vector3;

public class TrailParticleBase extends ParticleBase {

    public ParticleEffect effect;
    public Timer interval_timer;

    public int trail_density = 1;
    public int spawn_interval = 1;
    public TrailParticleBase(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
        interval_timer = new Timer();
    }

    @Override
    public void tick() {
        super.tick();
        update_timer();

    }
    public void update_timer() {
        if (effect != null) {
            if (spawn_interval <= 1) {
                ParticleEngine.spawn_particle(effect, get_pos());
                if (trail_density > 0) {
                    spawn_trail_detail();
                }
            } else {
                if (interval_timer.is_finished()) {
                    ParticleEngine.spawn_particle(effect, get_pos());
                    if (trail_density > 0) {
                        spawn_trail_detail();
                    }
                    interval_timer.start(spawn_interval);
                }
                interval_timer.update();
            }
        }
    }

    public void spawn_trail_detail() {
        for (int i = 0; i < trail_density; ++i) {
            var delta = (float) (i + 1) / (float) (trail_density + 1);
            ParticleEngine.spawn_particle(effect, get_pos().lerp(get_previous_pos(), 1.0 - delta));
        }
    }

}
