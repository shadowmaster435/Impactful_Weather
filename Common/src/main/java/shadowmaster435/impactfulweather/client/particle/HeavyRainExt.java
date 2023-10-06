package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import shadowmaster435.impactfulweather.init.ModRegistry;


public class HeavyRainExt extends SimpleAnimatedParticle {
    public static ClientLevel cworld;
    public float light;

    public HeavyRainExt(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.0D;
        this.yd = -3;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.2f;
        cworld = world;
      //  this.roll = -45;
        this.setSize(0.02F, 0.02F);

        this.setSprite(sprites.get(world.random));
        this.light = level.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
    }
    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        ++this.age;
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.level.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).blocksMotion() || this.onGround ) {
            if (!this.level.getFluidState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).is(FluidTags.WATER) && !this.level.getFluidState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).is(FluidTags.LAVA)) {
                this.level.addParticle(ModRegistry.RAINSPLASH.get(), this.x, this.y + 0.1, this.z, 0, 0, 0);
            }
              this.remove();
        } else {
            this.xd = -1;
            this.zd = 0;
            this.yd = -3;
        }
       /* if (this.age < 5) {
            this.alpha = this.age / 5f;
        } else if (this.age > (this.lifetime - 5)) {
            this.alpha = 1f - ((this.age - (this.lifetime - 10)) / 5f);
        } else {
            this.alpha = 1f;
        }*/

        this.move(this.xd, this.yd, this.zd);

    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class HeavyRainExtFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public HeavyRainExtFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            HeavyRainExt rain = new HeavyRainExt(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
