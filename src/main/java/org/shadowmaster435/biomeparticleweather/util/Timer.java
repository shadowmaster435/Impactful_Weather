package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.client.MinecraftClient;

import java.lang.reflect.Method;
import java.sql.Time;

public class Timer {

    private long start_tick = 0;
    private int ticks_left = 0;

    private int wait_ticks;
    private boolean finished = false;

    private boolean repeating = false;

    public Timer() {
    }

    public Timer(int ticks) {
        wait_ticks = ticks;
    }
    public Timer(int ticks, boolean repeat) {
        wait_ticks = ticks;
        repeating = repeat;
    }

    public boolean timeout() {
        return ticks_left <= 0;
    }

    public void update() {
        if (timeout()) {
            if (repeating) {
                start();
            } else {
                finished = true;
            }
        } else {
            ticks_left -= 1;
        }
    }

    public float delta() {
        if (wait_ticks > 0 && ticks_left > 0) {
            return 1.0f - (((float) ticks_left) / ((float) wait_ticks));
        } else {
            return 1.0f;
        }
    }

    public boolean is_finished() {
        return finished;
    }

    public void start(int ticks) {
        if (ParticleUtil.get_world() != null) {
            start_tick = ParticleUtil.get_world().getTime();
            ticks_left = ticks;
            wait_ticks = ticks;
        }
    }
    public void start() {
        if (ParticleUtil.get_world() != null) {
            start_tick = ParticleUtil.get_world().getTime();
            ticks_left = wait_ticks;
            wait_ticks = ticks_left;
        }
    }

}
