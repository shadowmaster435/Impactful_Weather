package org.shadowmaster435.biomeparticleweather.util.behavior;

import org.shadowmaster435.biomeparticleweather.particle.ParticleBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ParticleBehaivor {
    public static final String[] statement_strings = {"if", "else", "elif", "and", "or", "xor", "!", "!=", ">", "<", "<=", ">=", "==", "+", "-", "*", "/", "%" };
    public static final String[] setter_strings = {"=", "-=", "+=", "*=", "/=", "%="};
    public final HashMap<String, Object[]> inputs;

    private final BehaviorFunction[] functions = {};
    public ParticleBehaivor(HashMap<String, Object[]> inputs) {
        this.inputs = inputs;
    }



    public void parse_entry(String[] entry) {
        for (String string : entry) {
            if (Arrays.asList(statement_strings).contains(string)) {

            }
        }
    }
}
