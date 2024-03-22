package org.shadowmaster435.biomeparticleweather.util.particle_model.temp;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;
import org.shadowmaster435.biomeparticleweather.util.particle_model.temp.ModAnimationHelper;

import java.util.Optional;
import java.util.function.Function;

@Environment(value= EnvType.CLIENT)
public abstract class SinglePartModel extends Model {
    private static final Vector3f TEMP = new Vector3f();

    public SinglePartModel() {
        this(RenderLayer::getEntityCutoutNoCull);
    }

    public SinglePartModel(Function<Identifier, RenderLayer> function) {
        super(function);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    public abstract ModelPart getPart();

    public Optional<ModelPart> getChild(String name) {
        if (name.equals("root")) {
            return Optional.of(this.getPart());
        }
        return this.getPart().traverse().filter(part -> part.hasChild(name)).findFirst().map(part -> part.getChild(name));
    }

    protected void updateAnimation(AnimationState animationState, Animation animation, float animationProgress) {
        this.updateAnimation(animationState, animation, animationProgress, 1.0f);
    }

    protected void animateMovement(Animation animation, float limbAngle, float limbDistance, float limbAngleScale, float limbDistanceScale) {
        long l = (long)(limbAngle * 50.0f * limbAngleScale);
        float f = Math.min(limbDistance * limbDistanceScale, 1.0f);
        ModAnimationHelper.animate(this, animation, l, f, TEMP);
    }

    protected void updateAnimation(AnimationState animationState, Animation animation, float animationProgress, float speedMultiplier) {
        animationState.update(animationProgress, speedMultiplier);
        animationState.run(state -> ModAnimationHelper.animate(this, animation, state.getTimeRunning(), 1.0f, TEMP));
    }

    protected void animate(Animation animation) {
        ModAnimationHelper.animate(this, animation, 0L, 1.0f, TEMP);
    }
}

