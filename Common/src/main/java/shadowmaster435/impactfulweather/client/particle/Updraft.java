package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class Updraft extends SimpleAnimatedParticle {


    public Updraft(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.0D;
        this.yd = 0.0D;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.3f;
        this.alpha = 0;
        this.lifetime = 30;
        this.setSpriteFromAge(this.sprites);
        this.setSize(0.02F, 0.02F);

    }
    public void tick() {
        this.alpha = 0.5f;

        this.quadSize = 0.3f;
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age > this.lifetime || this.onGround || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion() || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            this.remove();
        } else {
            ++this.age;
            this.xd = 0;
            this.zd = 0;
            this.yd = 0.025;
            this.setSpriteFromAge(this.sprites);
        }

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
            Updraft rain = new Updraft(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
