package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import shadowmaster435.impactfulweather.config.ClientConfig;


public class Snow extends SimpleAnimatedParticle {
    public static float upv;
    public static ClientLevel cworld;
    public float light;

    public Snow(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = 0.0D;
        this.yd = -3;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.125f;
        this.alpha = 0f;
        upv = up;
        cworld = this.level;
        this.setSize(0.01F, 0.01F);
        this.setSprite(sprites.get(world.random));
     }

    public float groundtimer = 5;

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        ++this.age;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else {
            this.alpha = 1f;
        }
        if (this.onGround || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            this.groundtimer = this.groundtimer - 1;
            this.yd = 0;
            this.quadSize = this.quadSize - 0.035f;
            if (this.groundtimer <= 0 || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion()) {
                this.remove();
            }
        } else {

            this.groundtimer = 5;
            this.quadSize = 0.175f;
            this.xd = 0;
            this.zd = 0;
            this.yd = Math.abs(ClientConfig.INSTANCE.misc.snowspeedmodifier.get()) * -1;
        }
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class SnowFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public SnowFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            Snow rain = new Snow(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
