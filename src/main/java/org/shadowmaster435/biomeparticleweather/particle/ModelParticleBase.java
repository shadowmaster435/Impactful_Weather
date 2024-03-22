package org.shadowmaster435.biomeparticleweather.particle;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import org.shadowmaster435.biomeparticleweather.util.ModResourceLoader;
import org.shadowmaster435.biomeparticleweather.util.Vector3;
import org.shadowmaster435.biomeparticleweather.util.particle_model.ParticleModelPart;
import org.shadowmaster435.biomeparticleweather.util.particle_model.VoxelTransform;

import java.util.HashMap;

public class ModelParticleBase extends ParticleBase {


    private HashMap<String, ParticleModelPart> model = new HashMap<>();
    public VoxelTransform transform = VoxelTransform.def();

    public ModelParticleBase(ClientWorld world, Vector3 pos, FabricSpriteProvider spriteProvider) {
        super(world, pos, spriteProvider);
    }

    public void load_texture_model(String name) {
        model.put("model", ModResourceLoader.models.get(name));
    }

    public void set_model(HashMap<String, ParticleModelPart> model) {
        this.model = model;
    }

    public void set_model(String[] keys, ParticleModelPart[] parts) {

        for (int i = 0; i < keys.length; ++i) {
            this.model.put(keys[i], parts[i]);
        }
    }

    public void rotate(Vector3 amount_degrees) {
        transform = new VoxelTransform(transform.position(), transform.scale(), new Vector3(transform.rotation().add(amount_degrees.deg_to_rad())), transform.origin());
    }
    public void set_rotation(Vector3 amount_degrees) {
        transform = new VoxelTransform(transform.position(), transform.scale(), amount_degrees, transform.origin());

    }
    public void rescale(Vector3 amount) {
        transform = new VoxelTransform(transform.position(), amount, transform.rotation(), transform.origin());
    }
    public void offset(Vector3 amount) {
        transform = new VoxelTransform(amount, transform.scale(), transform.rotation(), transform.origin());
    }

    public HashMap<String, ParticleModelPart> get_model() {
        return this.model;
    }

    public VoxelTransform get_transform() {
        return this.transform;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        for (ParticleModelPart part : model.values()) {
            part.render(vertexConsumer, camera, tickDelta, this, transform);
        }
    }
}
