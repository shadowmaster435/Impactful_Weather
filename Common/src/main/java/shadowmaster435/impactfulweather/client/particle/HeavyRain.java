package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import shadowmaster435.impactfulweather.init.ModRegistry;


public class HeavyRain extends SimpleAnimatedParticle {
    public static int rainamount = 1;
    public static ClientLevel cworld;
    public static int heavyrainvel = 1;
    public float light;

    public HeavyRain(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
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

        rainamount = 2;
    }
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        heavyrainvel = 2;
        ++this.age;


        if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion() || this.onGround ) {
            if (!this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) && !this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
                this.level.addParticle(ModRegistry.RAINSPLASH.get(), this.x, this.y + 0.1, this.z, 0, 0, 0);

            }
            this.remove();
        } else {
            this.xd = -1;
            this.zd = 0;
            this.yd = -3;
        }

        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class HeavyRainFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public HeavyRainFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            HeavyRain rain = new HeavyRain(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
