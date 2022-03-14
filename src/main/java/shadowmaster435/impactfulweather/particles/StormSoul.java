package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import shadowmaster435.impactfulweather.init.IWParticles;


@Environment(EnvType.CLIENT)
public class StormSoul extends AnimatedParticle {


    public StormSoul(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        if (Math.random() > 0.8) {
            this.velocityY = -0.15;
            this.setPos(this.x, this.y + 2, this.z);
        } else if (Math.random() > 0.5) {
            this.velocityY = -0.1;
            this.setPos(this.x, this.y + 1, this.z);
        } else {
            this.velocityY = -0.05;
        }
        this.gravityStrength = 0f;
        this.scale = 0.3f;
        this.alpha = 0.0f;
        this.maxAge = 20;
        this.setSpriteForAge(this.spriteProvider);
        this.setBoundingBoxSpacing(0.02F, 0.02F);

    }
    public void tick() {
        this.scale = 0.3f;
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age < 20) {
            this.alpha = this.age / 20f;
        } else {
            this.alpha = 1f;
        }
        if (this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getBlock() == Blocks.SOUL_SAND || this.age > this.maxAge || this.onGround || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement() || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            if (this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getBlock() == Blocks.SOUL_SAND) {
                this.world.addParticle(IWParticles.STORMSOULIMPACT, this.x, this.y + 0.2 + 0.075, this.z, 0, 0, 0);

            }
            if (this.onGround) {
                    this.world.addParticle(IWParticles.STORMSOULIMPACT, this.x, this.y + 0.2, this.z, 0, 0, 0);
                }
            this.markDead();
        } else {
            ++this.age;

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
            StormSoul rain = new StormSoul(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
