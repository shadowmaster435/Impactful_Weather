package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class Gust extends SimpleAnimatedParticle {
    public static float upv;
    public float light;

   
    public Gust(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = 0.0D;
        this.yd = 0;
        this.zd = 0.0D;
        this.lifetime = (int) (15 + Math.floor(Math.random() * 5));
        this.gravity = 0f;
        this.alpha = 0.0f;
        this.quadSize = 0.0f;
        this.age = 0;
        this.quadSize = (float) (Math.random() / 2) + 0.2f;
        this.setSpriteFromAge(this.sprites);
        this.light = world.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));

    }
    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.alpha = 1;
        if (this.age > this.lifetime || !this.level.isEmptyBlock(new BlockPos(this.x, this.y, this.z)) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {

            this.remove();
            this.age = 0;
        } else {
            ++this.age;
            this.setSpriteFromAge(this.sprites);
            if (this.age < 5) {
                this.alpha = this.alpha + 0.04f;
            }
            else {
                this.alpha = 0.2f;
            }
            if (this.age >= 10) {
                this.alpha = this.alpha - 0.04f;
            } else {
                this.alpha = 0.2f;
            }
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class GustFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public GustFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            Gust rain = new Gust(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
