package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import shadowmaster435.impactfulweather.init.IWParticles;


@Environment(EnvType.CLIENT)
public class Rain extends AnimatedParticle {
    public static int rainamount = 1;
    public static ClientWorld cworld;
    public Rain(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.125f;
        cworld = world;
        this.setSprite(sprites.getSprite(world.random));
        this.setBoundingBoxSpacing(0.02F, 0.02F);
        rainamount = 2;
    }
    public void tick() {
        this.scale = 0.125f;

        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.onGround || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement() || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
                this.markDead();
            } else {
                world.addParticle(IWParticles.RAINSPLASH, prevPosX, prevPosY + 0.1, prevPosZ, 0, 0, 0);
            }
            this.markDead();
        } else {
            this.velocityX = 0;
            this.velocityZ = 0;
            this.velocityY = -3;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class RainFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public RainFactory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            Rain rain = new Rain(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
