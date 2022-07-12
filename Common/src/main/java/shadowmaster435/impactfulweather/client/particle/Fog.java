package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;


public class Fog extends TextureSheetParticle {
    private final float field_3809;
    public float light;

    public Fog(ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteSet sprites) {
        super(world, x, y, z, Xv, Yv, Zv);
        this.xd = Math.random() * 0.01D;
        this.yd = 0.0D;
        this.zd = Math.random() * 0.01D;
        this.gravity = 0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.alpha = 0;
        this.setSize(1F, 1F);
        this.lifetime = 100;
        this.setSprite(sprites.get(world.random));
    }
    public int age1;
    public int groundtimer;

    public static float rotvel = 0;
    public void tick() {
        if (this.age < 50) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.lifetime - 15)) {
            this.alpha = 1f - ((this.age - (this.lifetime - 15)) / 15f);
        } else {
            this.alpha = 1f;
        }
        if (this.age >= this.lifetime) {
            this.remove();
        }
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new Fog(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
