package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class BlizzardSnow extends SimpleAnimatedParticle {
    public static float upv;
    public static ClientLevel cworld;
    public float light;

  
    public BlizzardSnow(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = 0.0D;
        this.yd = -3;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.125f;
        this.alpha = 0;
        this.lifetime = 40;
        upv = up;
        cworld = world;
        this.setSize(0.01F, 0.01F);
        this.setSprite(sprites.get(world.random));
        this.light = world.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
    }

    public float groundtimer = 5;

    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.onGround || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion() || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            this.groundtimer = this.groundtimer - 1;
            this.yd = 0;
            this.quadSize = this.quadSize - 0.035f;
            if (this.groundtimer <= 0) {
                this.remove();
            }
        } else {
            ++this.age;

            if (this.age >= this.lifetime) {
                this.remove();
            }

            this.groundtimer = 5;
            this.quadSize = 0.175f;
            this.xd = 2;
            this.zd = 0;
            this.yd = 0;
        }
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.lifetime - 10)) {
            this.alpha = 1f - ((this.age - (this.lifetime - 10)) / 10f);
        } else {
            this.alpha = 1f;
        }
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class BlizzardSnowFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public BlizzardSnowFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            BlizzardSnow rain = new BlizzardSnow(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
