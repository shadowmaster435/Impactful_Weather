package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class RedSandMote extends SimpleAnimatedParticle {
    private final float field_3809;

    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }
    public float light;

    public RedSandMote(ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.0D;
        this.yd = (Math.random() * 0.1D) + 0.05D;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.15F;
        this.alpha = 0;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.setSize(0.01F, 0.01F);
        this.lifetime = 40;
        this.setSprite(sprites.get(world.random));
    }

    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.oRoll = this.roll;
        this.quadSize = 0.15F;

        ++this.age;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.lifetime - 10)) {
            this.alpha = 1f - ((this.age - 50) / 10f);
        } else {
            this.alpha = 1f;
        }
        if (this.age >= this.lifetime) {
            this.remove();
        } else {
            if (this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
                this.yd = Sinefunc();
                this.xd = 0.2;
                this.zd = 0.2;
                this.yd = (this.yd - 0.0025) + Sinefunc();
                this.roll += 3.1415927F * this.field_3809 * 0.5F;
              //  ++groundtimer;
            } else if (this.onGround || this.y < 63 || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion()) {
                this.remove();
            } else {
                this.xd = 0.6;
                this.zd = 0.6;
                this.roll += 3.1415927F * this.field_3809 * 2.0F;
                this.yd = (this.yd - 0.0025) + Sinefunc();
            }
        }
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class RedSandMoteFactory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public RedSandMoteFactory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new RedSandMote(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
