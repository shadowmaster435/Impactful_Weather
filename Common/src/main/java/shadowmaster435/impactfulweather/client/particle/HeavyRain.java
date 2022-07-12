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
        this.quadSize = 0.125f;
        cworld = world;
        this.roll = -45;

        this.setSprite(sprites.get(world.random));
        this.setSize(0.05F, 0.5F);
        this.light = world.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        rainamount = 2;
    }
    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.quadSize = 0.125f;
        heavyrainvel = 2;

        if (!this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().isReplaceable() || this.onGround || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            if (!this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) && !this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
                level.addParticle(ModRegistry.RAINSPLASH, this.x, this.y + 0.1, this.z, 0, 0, 0);

            }
            this.remove();
        } else {
            if (Math.random() > 0.9975) {
                level.addParticle(ModRegistry.HEAVYRAINEXT, Mth.lerp(Math.random(), this.x - 0.5, this.x + 0.5), Mth.lerp(Math.random(), this.y - 0.5, this.y + 0.5), Mth.lerp(Math.random(), this.z - 0.5, this.z + 0.5), 0, 0, 0);
            }
            this.xd = -2;
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
