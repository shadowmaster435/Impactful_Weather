package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.HashMap;

public class ParticleSettings {
    public static HashMap<String, Boolean> bools = new HashMap<>();
    public static HashMap<String, Integer> ints = new HashMap<>();
    public static HashMap<String, Float> floats = new HashMap<>();
    public static HashMap<String, String> strings = new HashMap<>();

    public static void load_defaults() {
        // ints
        ints.put("rain_amount", 16);
        ints.put("heavy_rain_amount", 24);
        ints.put("snow_amount", 16);
        ints.put("blizzard_snow_amount", 16);
        ints.put("blizzard_wind_amount", 16);
        ints.put("sand_mote_amount", 16);
        ints.put("wind_amount", 16);
        ints.put("red_sand_mote_amount", 16);
        ints.put("tumblebush_amount", 2);
        ints.put("lightning_particle_density", 32);
        ints.put("updraft_amount", 16);
        ints.put("soul_amount", 16);
        ints.put("weeping_tear_amount", 16);
        ints.put("spore_amount", 16);
        ints.put("fog_amount", 24);
        ints.put("spawn_radius", 24);
        ints.put("global_multiplier", 1);
        // bools
        bools.put("vanilla_lightning", false);
        bools.put("rain_trails", true);
        bools.put("rain_splash", true);
        bools.put("rain_surface_ripples", true);
        bools.put("vanilla_rain_rendering", false);
        bools.put("vanilla_rain_splash", false);

    }
    public static boolean get_bool(String key) {
        try { // fixes nullptr exception in world rendering mixin
            return bools.get(key);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static int get_int(String key) {
        return ints.get(key);
    }

    public static float get_float(String key) {
        return floats.get(key);
    }

    public static String get_string(String key) {
        return strings.get(key);
    }

    public static void set_bool(String key, boolean value) {
        bools.replace(key, value);
    }

    public static void set_int(String key, int value) {
        ints.replace(key, value);
    }

    public static void set_float(String key, float value) {
        floats.replace(key, value);
    }

    public static void set_string(String key, String value) {
        strings.replace(key, value);
    }

    public static void load_bool(String[] entry) {
        var value_name = entry[0];
        var value = entry[1];
        bools.put(value_name, Boolean.parseBoolean(value));
    }
    public static void load_int(String[] entry) {
        var value_name = entry[0];
        var value = entry[1];
        ints.put(value_name, Integer.parseInt(value));

    }
    public static void load_float(String[] entry) {
        var value_name = entry[0];
        var value = entry[1];
        floats.put(value_name, Float.parseFloat(value));

    }

    public static void load_string(String[] entry) {
        var value_name = entry[0];
        var value = entry[1];
        strings.put(value_name, value);

    }
    public static String get_config_string() {
        var builder = new StringBuilder();
        for (int i = 0; i < ints.size(); ++i) {
            var key = ints.keySet().stream().toList().get(i);
            var value = ints.get(key);
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append("\n");
        }
        builder.append("float\n");
        for (int i = 0; i < floats.size(); ++i) {
            var key = floats.keySet().stream().toList().get(i);
            var value = floats.get(key);
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append("\n");
        }
        builder.append("bool\n");
        for (int i = 0; i < bools.size(); ++i) {
            var key = bools.keySet().stream().toList().get(i);
            var value = bools.get(key);
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append("\n");
        }
        builder.append("string\n");
        for (int i = 0; i < strings.size(); ++i) {
            var key = strings.keySet().stream().toList().get(i);
            var value = strings.get(key);
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append("\n");
        }
        return builder.toString();
    }

}
