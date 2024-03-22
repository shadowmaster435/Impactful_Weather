package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.block.AbstractBlock;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockStateRaycastContext;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2d;
import org.shadowmaster435.biomeparticleweather.util.ParticleUtil;
import org.shadowmaster435.biomeparticleweather.util.Timer;
import org.shadowmaster435.biomeparticleweather.util.Vector3;
import org.shadowmaster435.biomeparticleweather.util.dynamic_class.DynamicClass;

import java.util.HashMap;
import java.util.function.Predicate;

public class ParticleBase extends SpriteBillboardParticle {
    private HashMap<String, Timer> animations = new HashMap<>();
    private float previous_alpha = 1.0f;
    private Vector3 previous_rgb = Vector3.ONE;
    private float previous_scale = 1;
    private float target_alpha_fade = 1.0f;
    private Vector3 target_rgb_fade = Vector3.ONE;
    private float target_scale_fade = 1;
    private boolean fading_alpha = false;
    private boolean fading_rgb = false;
    private boolean fading_scale = false;

    public long random_seed = 0;
    public float angular_velocity = 0;

    public float orbit_velocity = 0;

    public FabricSpriteProvider provider;
    public ParticleBase(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos.x, pos.y, pos.z);
        random_seed = Random.createLocal().nextInt();
        provider = spriteProvider;
        create_animation_timers();
        collidesWithWorld = false;
    }

    private void create_animation_timers() {
        var fade_alpha = new Timer();
        var fade_scale = new Timer();
        var fade_rgb = new Timer();
        animations.put("fade_alpha", fade_alpha);
        animations.put("fade_scale", fade_scale);
        animations.put("fade_rgb", fade_rgb);
    }

    public void fade_alpha(float to, int ticks) {
        previous_alpha = alpha;
        target_alpha_fade = to;
        animations.get("fade_alpha").start(ticks);
        fading_alpha = true;
    }

    public void fade_rgb(Vector3 to, int ticks) {
        previous_rgb = get_color();
        target_rgb_fade = to;
        animations.get("fade_rgb").start(ticks);
        fading_rgb = true;
    }

    public void fade_scale(float to, int ticks) {
        previous_scale = scale;
        animations.get("fade_scale").start(ticks);
        target_scale_fade = to;
        fading_scale = true;
    }
    public void process_animations() {

        if (fading_alpha) {
            var timer = animations.get("fade_alpha");
            alpha = MathHelper.lerp(timer.delta(), previous_alpha, target_alpha_fade);
            if (timer.is_finished()) {
                fading_alpha = false;
            }
            timer.update();

        }
        if (fading_rgb) {
            var timer = animations.get("fade_rgb");
            set_color(new Vector3(previous_rgb.lerp(target_rgb_fade, timer.delta())));
            if (timer.is_finished()) {
                fading_rgb = false;
            }
            timer.update();
        }

        if (fading_scale) {
            var timer = animations.get("fade_scale");
            scale = MathHelper.lerp(timer.delta(), previous_scale, target_scale_fade);
            if (timer.is_finished()) {
                fading_scale = false;
            }
            timer.update();
        }
    }

    public void tick() {
        super.tick();


        process_animations();
        this.prevAngle = angle;
        this.angle += (float) Math.toRadians(angular_velocity);

    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public Vector3 get_color() {
        return new Vector3(red, green, blue);
    }
    public void set_color(Vector3 vec) {
        setColor((float) vec.x, (float) vec.y, (float) vec.z);
    }
    public void bounce() {
        if (is_colliding()) {
            set_velocity(get_velocity().bounce(get_collision_direction()));
        }
    }

    public boolean in_block() {
        return world.getBlockState(get_pos().to_blockpos()).isSolidBlock(world, get_pos().to_blockpos());
    }

    public Vector3 get_orbit_tangent(Vector3 point, float gravity_scale) {
        return get_pos().angle_to(point, Math.max(orbit_velocity, 0.01) / Math.max(get_pos().distanceTo(point), 0.01));
    }

    public Vector3 get_pull_tangent(Vector3 point, float gravity_scale) {
        var v = get_orbit_tangent(point);
        return v.lerp(point, gravity_scale);
    }
    public Vector3 get_orbit_tangent(Vector3 point) {
        return get_pos().angle_to(point, Math.max(orbit_velocity, 0.01) / Math.max(get_pos().distanceTo(point), 0.01));
    }

    public void div_vel(Vector3 scalar) {
        set_velocity(get_velocity().div(scalar));

    }

    public void mul_vel(Vector3 scalar) {
        set_velocity(get_velocity().mul(scalar));
    }
    public void set_position(Vector3 pos) {
        x = pos.x;
        y = pos.y;
        z = pos.z;
    }

    public void add_position(Vector3 pos) {

        x += pos.x;
        y += pos.y;
        z += pos.z;
    }

    public void orbit(Vector3 pos) {
        var tangent = get_orbit_tangent(pos);
        add_position(new Vector3(0, tangent.y, tangent.z).mul(orbit_velocity));
    }

    public void orbit(Vector3 pos, float distance) {




    }


    public void bounce(Direction direction) {
        set_velocity(get_velocity().bounce(direction));
    }
    public void bounce(Vector3 offset) {
        if (is_colliding(offset)) {
            set_velocity(get_velocity().bounce(get_collision_direction()));
        }
    }
    public void set_velocity(Vector3 vec) {
        velocityX = vec.x;
        velocityY = vec.y;
        velocityZ = vec.z;
    }
    public Direction get_collision_direction() {
        return get_hit_result().getSide();
    }

    public Direction get_collision_direction(Vector3 offset) {
        return get_hit_result(offset).getSide();
    }

    public boolean is_colliding() {
        return get_hit_result().getType() != HitResult.Type.MISS;
    }

    public boolean is_colliding(Vector3 offset) {
        return get_hit_result(offset).isInsideBlock();
    }

    public boolean is_raycast_colliding(Vector3 pos) {
        return raycast_to(pos).getType() != HitResult.Type.MISS;
    }
    public BlockHitResult get_fluid_hit_result() {
        var ctx = new BlockStateRaycastContext(get_pos(), get_pos(), (AbstractBlock.AbstractBlockState::isLiquid));
        return ParticleUtil.get_world().raycast(ctx);
    }
    public BlockHitResult get_fluid_hit_result(Vector3 offset) {
        var ctx = new BlockStateRaycastContext(get_pos(), get_pos().add(offset), (AbstractBlock.AbstractBlockState::isLiquid));
        return ParticleUtil.get_world().raycast(ctx);
    }

    public BlockHitResult raycast_to(Vector3 pos) {
        var ctx = new BlockStateRaycastContext(get_pos(), pos, (Predicate.not(AbstractBlock.AbstractBlockState::isAir)));
        return ParticleUtil.get_world().raycast(ctx);
    }
    public BlockHitResult get_hit_result(Vector3 offset) {
        var ctx = new BlockStateRaycastContext(get_previous_pos(), get_pos().add(offset), (AbstractBlock.AbstractBlockState::blocksMovement));
        return ParticleUtil.get_world().raycast(ctx);
    }
    public BlockHitResult get_hit_result() {
        var ctx = new BlockStateRaycastContext(get_pos(), get_pos().add(get_velocity_length_vector()), (AbstractBlock.AbstractBlockState::isSolid));
        return ParticleUtil.get_world().raycast(ctx);
    }

    public Vector3 get_velocity_length_vector() {
        var hlength = get_velocity().length();
        var vlength = new Vector3(get_velocity().x, 0, get_velocity().z).length();
        return new Vector3(get_velocity());
    }
    public Vector3 get_velocity() {
        return new Vector3(velocityX, velocityY, velocityZ);
    }
    public Vector3 get_pos() {
        return new Vector3(x, y, z);
    }
    public Vector3 get_previous_pos() {
        return new Vector3(prevPosX, prevPosY, prevPosZ);
    }
    public Vector3 get_hit_pos(Vector3 offset) {
        return new Vector3(get_hit_result(offset).getPos());
    }
    public Vector3 get_hit_pos() {
        return new Vector3(get_hit_result().getPos());
    }
    public Vector3 get_hit_fluid_surface() {
        var hit = get_fluid_hit_result();
        return new Vector3(hit.getPos());
    }
    public boolean will_collide_with_fluid() {
        var hit = get_fluid_hit_result();
        return hit.isInsideBlock();
    }

    @Override
    public int getBrightness(float tint) {
        return super.getBrightness(tint);
    }
}

