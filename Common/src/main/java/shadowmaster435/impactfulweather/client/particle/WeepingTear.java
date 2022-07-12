package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import shadowmaster435.impactfulweather.init.ModRegistry;


public class WeepingTear extends SimpleAnimatedParticle {
    public static int rainamount = 1;
    public static ClientLevel cworld;
    public float light;


    public WeepingTear(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.0D;
        this.yd = -3;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.125f;
        cworld = world;
        this.setSprite(sprites.get(world.random));
        this.setSize(0.02F, 0.02F);
        rainamount = 2;
        this.lifetime = 100;
    }
    public void tick() {
        this.quadSize = 0.125f;

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age > this.lifetime || this.onGround) {
            if (this.onGround) {
                this.level.addParticle(ModRegistry.WEEPINGTEARSPLASH, this.x, this.y + 0.0625f, this.z, 0, 0, 0);
            }
            this.remove();
        } else {
            if (this.age < 10) {
                this.xd = 0;
                this.zd = 0;
                this.yd = -0.025;
            } else {
                this.xd = 0;
                this.zd = 0;
                this.yd = -0.5;
            }
        }
        ++this.age;
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            WeepingTear rain = new WeepingTear(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
