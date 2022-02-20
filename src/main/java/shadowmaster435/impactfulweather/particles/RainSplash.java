package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;


@Environment(EnvType.CLIENT)
public class RainSplash extends AnimatedParticle {
    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }
    public static float upv;
    public static ClientWorld cworld;
    private final float field_3809;

    public RainSplash(ClientWorld world, double x, double y, double z, SpriteProvider sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.velocityX = 0.0D;
        this.velocityY = 0;
        this.velocityZ = 0.0D;
        this.maxAge = 5;
        this.gravityStrength = 0f;
        this.alpha = 0.0f;
        this.scale = 0.0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.age = 0;
        this.scale = (float) (Math.random() / 2) + 0.2f;

        this.setSpriteForAge(this.spriteProvider);

        this.setBoundingBoxSpacing(0.02F, 0.1f);
    }


    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.alpha = 1;
        this.scale = 0.2f;
        if (this.age > this.maxAge) {
            this.markDead();
            this.age = 0;
        } else {
            ++this.age;
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class RainSplashFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public RainSplashFactory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            RainSplash rain = new RainSplash(world, x, y, z, spriteProvider, upv);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
