package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;


public class RainSplash extends SimpleAnimatedParticle {
    public static float upv;
    public float light;

  
    public RainSplash(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = 0.0D;
        this.yd = 0;
        this.zd = 0.0D;
        this.lifetime = 5;
        this.gravity = 0f;
        this.alpha = 0.0f;
        this.quadSize = 0.0f;
        this.age = 0;
        this.quadSize = (float) (Math.random() / 2) + 0.2f;

        this.setSpriteFromAge(this.sprites);

        this.setSize(0.02F, 0.1f);
        this.light = world.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
    }
    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.alpha = 1;
        this.quadSize = 0.2f;
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

    public static class RainSplashFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public RainSplashFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            RainSplash rain = new RainSplash(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
