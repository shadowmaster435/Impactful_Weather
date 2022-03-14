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
public class StormSoulImpact extends AnimatedParticle {


    public StormSoulImpact(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = 0.0D;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.3f;
        this.alpha = 1f;
        this.maxAge = 20;
        this.setSpriteForAge(this.spriteProvider);
        this.setBoundingBoxSpacing(0.02F, 0.02F);

    }
    public void tick() {
        this.scale = 0.3f;
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.alpha = 1f;

        if (this.age > this.maxAge || this.onGround || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement() || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            this.markDead();
        } else {
            ++this.age;
            this.velocityX = 0;
            this.velocityZ = 0;
            this.velocityY = 0;
            this.setSpriteForAge(this.spriteProvider);
        }

        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            StormSoulImpact rain = new StormSoulImpact(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
