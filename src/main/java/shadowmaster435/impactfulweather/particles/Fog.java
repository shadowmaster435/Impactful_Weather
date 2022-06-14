package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;


@Environment(EnvType.CLIENT)
public class Fog extends SpriteBillboardParticle {
    private final float field_3809;
    public float light;

    public Fog(ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteProvider sprites) {
        super(world, x, y, z, Xv, Yv, Zv);
        this.velocityX = Math.random() * 0.01D;
        this.velocityY = 0.0D;
        this.velocityZ = Math.random() * 0.01D;
        this.gravityStrength = 0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.alpha = 0;
        this.setBoundingBoxSpacing(1F, 1F);
        this.maxAge = 100;
        this.setSprite(sprites.getSprite(world.random));
    }
    public int age1;
    public int groundtimer;

    public static float rotvel = 0;
    public void tick() {
        if (this.age < 50) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.maxAge - 15)) {
            this.alpha = 1f - ((this.age - (this.maxAge - 15)) / 15f);
        } else {
            this.alpha = 1f;
        }
        if (this.age >= this.maxAge) {
            this.markDead();
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {

        private final FabricSpriteProvider sprites;

        public Factory(FabricSpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new Fog(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
