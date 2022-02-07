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
public class Snow extends AnimatedParticle {
    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }
    public static float upv;
    public static ClientWorld cworld;

    public Snow(ClientWorld world, double x, double y, double z, SpriteProvider sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.125f;
        upv = up;
        cworld = world;
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setSprite(sprites.getSprite(world.random));
    }

    public float groundtimer = 5;

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.onGround || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            this.groundtimer = this.groundtimer - 1;
            this.velocityY = 0;
            this.scale = this.scale - 0.035f;
            if (this.groundtimer <= 0 || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement()) {
                this.markDead();
            }
        } else {

            this.groundtimer = 5;
            this.scale = 0.175f;
            this.velocityX = 0;
            this.velocityZ = 0;
            this.velocityY = -0.75;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class SnowFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public SnowFactory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            Snow rain = new Snow(world, x, y, z, spriteProvider, upv);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
