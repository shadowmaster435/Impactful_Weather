package org.shadowmaster435.biomeparticleweather.util;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigFile {



    public static void ensure_existance() {
        try {
            if (!get_mod_config_directory().toFile().exists()) {
                get_mod_config_directory().toFile().mkdir();
            }
            if (!Path.of(get_mod_config_directory().toString(), "config.txt").toFile().exists()) {
                Path.of(get_mod_config_directory().toString(), "config.txt").toFile().createNewFile();
                ParticleSettings.load_defaults();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void save_config() {
        ensure_existance();
        var string = ParticleSettings.get_config_string();
        try {
            var file = new FileWriter(get_mod_config_file().toFile());
            file.flush();
            file.write(string);
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void load_config() {
        try {
            var file = new Scanner(get_mod_config_file().toFile());
            var mode = "int";
            while (file.hasNext()) {
                var line = file.nextLine();
                if (line.contains("=")) {
                    var val = get_value(line);
                    if (mode.equals("int")) {
                        ParticleSettings.load_int(val);
                        continue;

                    }
                    if (mode.equals("float")) {
                        ParticleSettings.load_float(val);
                        continue;
                    }
                    if (mode.equals("bool")) {
                        ParticleSettings.load_bool(val);
                        continue;
                    }
                    if (mode.equals("string")) {
                        ParticleSettings.load_string(val);
                    }

                } else {
                    mode = line;
                }

            }
            save_config();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


   public static String[] get_value(String line) {
        return line.split("=");
   }
    public static Path get_mod_config_file() {
        return Path.of(get_mod_config_directory().toString(), "config.txt");
    }
    public static Path get_mod_config_directory() {
        var dir = FabricLoader.getInstance().getConfigDir();

        return Path.of(dir.toFile().getAbsolutePath(), "biomeparticleweather");
    }

}
