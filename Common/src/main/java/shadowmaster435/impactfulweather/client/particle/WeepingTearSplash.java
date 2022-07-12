package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;


public class WeepingTearSplash extends SimpleAnimatedParticle {
    public static float upv;
    public float light;


    public WeepingTearSplash(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = 0.0D;
        this.yd = 0;
        this.zd = 0.0D;
        this.lifetime = 5;
        this.gravity = 0f;
        this.alpha = 1f;
        this.quadSize = 0.125f;
        this.age = 0;

        this.setSpriteFromAge(this.sprites);

        this.setSize(0.02F, 0.1f);
    }
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.alpha = 1;
        this.quadSize = 0.125f;
        if (this.age > this.lifetime) {
            this.remove();
        } else {
            ++this.age;
            this.setSpriteFromAge(this.sprites);
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            WeepingTearSplash rain = new WeepingTearSplash(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
