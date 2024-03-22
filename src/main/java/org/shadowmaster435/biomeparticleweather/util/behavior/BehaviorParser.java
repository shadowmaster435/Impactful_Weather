package org.shadowmaster435.biomeparticleweather.util.behavior;

public record BehaviorParser(BehaviorValue[] values) {


    public static BehaviorParser parse(String[] strings) {
        var result = new BehaviorParser(new BehaviorValue[]{});
        for (String string : strings) {

        }
        return result;
    }



}
