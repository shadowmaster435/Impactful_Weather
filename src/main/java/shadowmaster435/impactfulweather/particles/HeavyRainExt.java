package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;


@Environment(EnvType.CLIENT)
public class HeavyRainExt extends AnimatedParticle {
    public static ClientWorld cworld;
    public HeavyRainExt(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.maxAge = 15;
        this.scale = 0.125f;
        cworld = world;
        this.setSprite(sprites.getSprite(world.random));
        this.setBoundingBoxSpacing(0.02F, 0.02F);
    }
    public void tick() {
        this.scale = 0.125f;
        ++this.age;
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
            this.markDead();
        } else if (this.age >= this.maxAge || this.onGround || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement() || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            this.markDead();
        } else {
            this.velocityX = -HeavyRain.heavyrainvel;
            this.velocityZ = 0;
            this.velocityY = -3;
        }
        if (this.age < 5) {
            this.alpha = this.age / 5f;
        } else if (this.age > (this.maxAge - 5)) {
            this.alpha = 1f - ((this.age - (this.maxAge - 10)) / 5f);
        } else {
            this.alpha = 1f;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class HeavyRainExtFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public HeavyRainExtFactory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            HeavyRainExt rain = new HeavyRainExt(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
