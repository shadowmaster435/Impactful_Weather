package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class TumbleBush extends TextureSheetParticle {
    private final float field_3809;
    public float light;

    public TumbleBush(ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteSet sprites) {
        super(world, x, y, z, Xv, Yv, Zv);
        this.xd = 0.0D;
        this.yd = (Math.random() * 0.1D) + 0.05D;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.alpha = 0;
        this.setSize(1F, 1F);
        this.lifetime = 100;
        this.setSprite(sprites.get(world.random));
        this.light = world.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
    }
    public int age1;
    public int groundtimer;

    public static float rotvel = 0;
    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.oRoll = this.roll;
        this.roll += 3.1415927F * this.field_3809 * (rotvel + 1);
        this.quadSize = 0.5F;
        ++age1;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > 90) {
            this.alpha = 1f - ((this.age - 90) / 10f);
        } else {
            this.alpha = 1f;
        }
        if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion()) {
            this.remove();
        }
        if (this.onGround ) {
            rotvel = 2;
            ++groundtimer;
            if (this.yd < -0.8) {
                this.yd = (this.yd * -1) * 0.3F;
            } else {
                this.yd = this.yd * -1;
            }
        } else if (this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER)) {
            for (int b = 0; b < 8; ++b) {
                this.level.addParticle(ParticleTypes.SPLASH,this.x, this.y, this.z,1,1.2,1);
            }
            this.remove();
            ++groundtimer;
        }
        else if (this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            for (int i = 0; i < 4; ++i) {
                this.level.addParticle(ParticleTypes.LARGE_SMOKE,this.x, this.y, this.z,0.4,0.2,0.4);
                this.level.addParticle(ParticleTypes.FLAME,this.x, this.y, this.z,0.4,0.2,0.4);
                this.level.addParticle(ParticleTypes.LAVA,this.x, this.y, this.z,0.4,0.2,0.4);

            }
            this.remove();
        } else {
            this.xd = 0.4;
            this.zd = 0.4;
            this.yd = (this.yd - 0.05);
            groundtimer = 0;
            if (rotvel <= 0) {
                rotvel = 0;
            } else {
                rotvel = rotvel - 0.2f;
            }
        }
        if (this.age1 >= 100 || groundtimer > 15) {
            this.remove();
        } else if (groundtimer > 5) {
            this.quadSize -= 0.05f;
        }
        ++age;
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class TumbleBushFactory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public TumbleBushFactory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new TumbleBush(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
