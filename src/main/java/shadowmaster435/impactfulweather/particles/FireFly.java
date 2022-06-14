package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import shadowmaster435.impactfulweather.util.ParticleUtil;


@Environment(EnvType.CLIENT)
public class FireFly extends AnimatedParticle {
    public static float upv;
    public float light;
    public double expVelX = 0;
    public double expVelY = 0;
    public double expVelZ = 0;

    public FireFly(ClientWorld world, double x, double y, double z, SpriteProvider sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.velocityX = Math.random() * 0.065 - 0.0375;
        this.velocityY = 0.065;
        this.velocityZ = Math.random() * 0.065 - 0.0375;
        this.maxAge = 5;
        this.gravityStrength = 0f;
        this.alpha = 0.0f;
        this.scale = 0.0f;
        this.age = 0;

        this.maxAge = 400;
        this.setBoundingBoxSpacing(0.02F, 0.1f);
    }

    public double sine = Math.sin(age) * 0.025d;

    public void tick() {
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.maxAge - 10)) {
            this.alpha = 1f - ((this.age - (this.maxAge - 10)) / 10f);
        } else {
            this.alpha = 1f;
        }

        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.scale = 0.2f;
        if (this.field_28787 || this.onGround || !(this.world.getTimeOfDay() < 22000 && this.world.getTimeOfDay() > 14000) ||
                this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) ||
                this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
                this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.x, this.y, this.z, 0,0.5,0);
                this.markDead();
            } else {

                this.markDead();
            }
        }
        if (this.age > this.maxAge) {
            this.markDead();
        } else {
            if (!(((this.age) % 6) < 0 && !(((this.age) % 6)  > 5))) {
                this.setSprite(this.spriteProvider.getSprite(((this.age) % 6), 6));
            }
            ++this.age;
        }

        if (this.age % 100 == 0 && Math.random() < 0.75) {
            this.velocityX = random.nextGaussian() * 0.05;
            this.velocityY = random.nextGaussian() * 0.05;
            this.velocityZ = random.nextGaussian() * 0.05;
        }

        if (this.age % 50 == 0 && Math.random() < 0.75) {
            this.velocityX = this.velocityX + random.nextGaussian() * 0.05;
            this.velocityY = this.velocityY + random.nextGaussian() * 0.05;
            this.velocityZ = this.velocityZ + random.nextGaussian() * 0.05;
        }
        if (this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).isSolidBlock(this.world, new BlockPos(this.x, this.y, this.z))) {
            this.markDead();
        }

        if (this.age % 10 == 0 && Math.random() < 0.85) {
            if (Math.random() > 0.5) {
                this.x = this.x + this.sine * 0.1;

            }
            if (Math.random() > 0.5) {
                this.y = this.y + this.sine * 0.1;

            }
            if (Math.random() > 0.5) {
                this.z = this.z + this.sine * 0.1;

            }
        }


        for (Direction dir : Direction.values()) {
            if (!world.getBlockState(new BlockPos(this.x, this.y, this.z).offset(dir)).isAir()) {
                if (dir.getAxis().equals(Direction.Axis.X)) {
                    this.expVelX = this.expVelX * -1;

                }
                if (dir.getAxis().equals(Direction.Axis.Y)) {
                    this.expVelY = this.expVelY * -1;

                }
                if (dir.getAxis().equals(Direction.Axis.Z)) {
                    this.expVelZ = this.expVelZ * -1;
                }
            }
        }
        if (this.expVelX != this.velocityX) {
            this.expVelX = this.expVelX + this.velocityX * 0.05;
        }
        if (this.expVelY != this.velocityY) {
            this.expVelY = this.expVelY + this.velocityY * 0.05;
        }
        if (this.expVelZ != this.velocityZ) {
            this.expVelZ = this.expVelZ + this.velocityZ * 0.05;
        }
        if (this.velocityX > 0.075) {
            this.velocityX = 0.075;
        }
        if (this.velocityY > 0.075) {
            this.velocityY = 0.075;
        }
        if (this.velocityZ > 0.075) {
            this.velocityZ = 0.075;
        }

        if (this.velocityX < -0.075) {
            this.velocityX = -0.075;
        }
        if (this.velocityY < -0.075) {
            this.velocityY = -0.075;
        }
        if (this.velocityZ < -0.075) {
            this.velocityZ = -0.075;
        }

        if (this.expVelX > 0.075) {
            this.expVelX = 0.075;
        }
        if (this.expVelY > 0.075) {
            this.expVelY = 0.075;
        }
        if (this.expVelZ > 0.075) {
            this.expVelZ = 0.075;
        }

        if (this.expVelX < -0.075) {
            this.expVelX = -0.075;
        }
        if (this.expVelY < -0.075) {
            this.expVelY = -0.075;
        }
        if (this.expVelZ < -0.075) {
            this.expVelZ = -0.075;
        }
        this.move(this.expVelX,this.expVelY,this.expVelZ);
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
            FireFly rain = new FireFly(world, x, y, z, spriteProvider, upv);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
