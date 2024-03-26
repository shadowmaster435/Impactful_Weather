package org.shadowmaster435.biomeparticleweather.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.texture.atlas.Sprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.resource.Resource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.shadowmaster435.biomeparticleweather.BiomeParticleWeather;
import org.shadowmaster435.biomeparticleweather.gui.ConfigButton;
import org.shadowmaster435.biomeparticleweather.gui.ConfigScreen;

import java.util.function.Supplier;

public class StaticMethods {


    public static boolean make_single_bolt_bool = true;
    public static void spawn_particles(Entity lightningEntity) {

        if (!MinecraftClient.getInstance().isPaused() && lightningEntity.age == 1) {
            if (make_single_bolt_bool) {
                ParticleEngine.spawn_particle(BiomeParticleWeather.LIGHTNING_NODE, new Vector3(lightningEntity.getPos()));
                for (int i = 0; i < 32; ++i) {
                    var bpos = lightningEntity.getBlockPos().add(new Vec3i(0, 0, 0));
                    var effect = new BlockStateParticleEffect(ParticleTypes.BLOCK, lightningEntity.getWorld().getBlockState(bpos));
                    var pos = lightningEntity.getPos();
                    var max_vel = 1000f;
                    var vx = MathHelper.nextBetween(Random.createLocal(), -max_vel, max_vel);
                    var vy = MathHelper.nextBetween(Random.createLocal(), max_vel / 2f, max_vel);
                    var vz = MathHelper.nextBetween(Random.createLocal(), -max_vel, max_vel * 2);
                    lightningEntity.getWorld().addParticle(effect, pos.x, pos.y + 1, pos.z, vx, vy, vz);
                    make_single_bolt_bool = false;
                }
            } else {
                make_single_bolt_bool = true;
            }
        }

    }


    public static double angle_to_2d(Vector2f vector1, Vector2f vector2) {

        return Math.acos(vector2.normalize().dot(vector1.normalize()));

    }
    public float angle_to_with_amplitude(Vector2f first, Vector2f second) {
        return (float) Math.acos(first.dot(second));
    }

    public static final ButtonTextures button_textures = new ButtonTextures(new Identifier("biomeparticleweather", "textures/misc/button"), new Identifier("biomeparticleweather", "textures/misc/button"));
    public static void init_config(GameMenuScreen screen) {
        GridWidget gridWidget2 = new GridWidget();
        GridWidget.Adder adder2 = gridWidget2.createAdder(2);
        gridWidget2.setPosition(0, 0);
        Supplier<Screen> supplier = () -> new ConfigScreen(screen);
        var button = new ConfigButton(0, 0, 16, 16, Text.empty(), (a) -> {MinecraftClient.getInstance().setScreen(supplier.get());}, null);
        adder2.add(button);
        gridWidget2.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget2, 0, 0, 44, 44, 0.5F, 0.25F);
        gridWidget2.forEachChild(screen::addDrawableChild);

    }

}
