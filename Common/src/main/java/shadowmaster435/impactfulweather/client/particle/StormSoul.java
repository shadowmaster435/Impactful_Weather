package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Blocks;
import shadowmaster435.impactfulweather.init.ModRegistry;


public class StormSoul extends SimpleAnimatedParticle {


    public StormSoul(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        if (Math.random() > 0.8) {
            this.yd = -0.15;
            this.setPos(this.x, this.y + 2, this.z);
        } else if (Math.random() > 0.5) {
            this.yd = -0.1;
            this.setPos(this.x, this.y + 1, this.z);
        } else {
            this.yd = -0.05;
        }
        this.gravity = 0f;
        this.quadSize = 0.3f;
        this.alpha = 0.0f;
        this.lifetime = 20;
        this.setSpriteFromAge(this.sprites);
        this.setSize(0.02F, 0.02F);

    }
    public void tick() {
        this.quadSize = 0.3f;
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age < 20) {
            this.alpha = this.age / 20f;
        } else {
            this.alpha = 1f;
        }
        if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getBlock() == Blocks.SOUL_SAND || this.age > this.lifetime || this.onGround || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion() || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getBlock() == Blocks.SOUL_SAND) {
                this.level.addParticle(ModRegistry.STORMSOULIMPACT, this.x, this.y + 0.2 + 0.075, this.z, 0, 0, 0);

            }
            if (this.onGround) {
                    this.level.addParticle(ModRegistry.STORMSOULIMPACT, this.x, this.y + 0.2, this.z, 0, 0, 0);
                }
            this.remove();
        } else {
            ++this.age;

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
            StormSoul rain = new StormSoul(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
