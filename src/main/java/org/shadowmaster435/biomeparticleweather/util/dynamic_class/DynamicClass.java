package org.shadowmaster435.biomeparticleweather.util.dynamic_class;

import java.sql.Statement;

public class DynamicClass {
    private final Object original_object;

    public DynamicClass() {
        original_object = this;
    }

    public DynamicClass(Object object) {
        original_object = object;
    }

    public Object get(String key) {

        Object result = null;
        try {
            result = original_object.getClass().getField(key).get(original_object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void set(String key, Object value) {
        Object[] tst = {1, "", 0.5f};
        try {
            original_object.getClass().getField(key).set(value, original_object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object call(String key) {
        Object result = null;
        try {
            result = original_object.getClass().getMethod(key).invoke(original_object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Object call(String key, Object[] args) {
        Object result = null;
        try {
            result = original_object.getClass().getMethod(key).invoke(original_object, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
