package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;


public class FireFly extends SimpleAnimatedParticle {
    public static float upv;
    public float light;
    public double expVelX = 0;
    public double expVelY = 0;
    public double expVelZ = 0;

    public FireFly(ClientLevel world, double x, double y, double z, SpriteSet sprites, float up) {
        super(world, x, y, z, sprites, up);
        this.xd = Math.random() * 0.065 - 0.0375;
        this.yd = 0.065;
        this.zd = Math.random() * 0.065 - 0.0375;
        this.lifetime = 5;
        this.gravity = 0f;
        this.alpha = 0.0f;
        this.quadSize = 0.0f;
        this.age = 0;

        this.lifetime = 400;
        this.setSize(0.02F, 0.1f);
    }

    public double sine = Math.sin(age) * 0.025d;

    public void tick() {
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.lifetime - 10)) {
            this.alpha = 1f - ((this.age - (this.lifetime - 10)) / 10f);
        } else {
            this.alpha = 1f;
        }

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.quadSize = 0.2f;
        if (this.speedUpWhenYMotionIsBlocked || this.onGround || !(this.level.getDayTime() < 22000 && this.level.getDayTime() > 14000) ||
                this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) ||
                this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            if (this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
                this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.x, this.y, this.z, 0,0.5,0);
                this.remove();
            } else {

                this.remove();
            }
        }
        if (this.age > this.lifetime) {
            this.remove();
        } else {
            if (!(((this.age) % 6) < 0 && !(((this.age) % 6)  > 5))) {
                this.setSprite(this.sprites.get(((this.age) % 6), 6));
            }
            ++this.age;
        }

        if (this.age % 100 == 0 && Math.random() < 0.75) {
            this.xd = random.nextGaussian() * 0.05;
            this.yd = random.nextGaussian() * 0.05;
            this.zd = random.nextGaussian() * 0.05;
        }

        if (this.age % 50 == 0 && Math.random() < 0.75) {
            this.xd = this.xd + random.nextGaussian() * 0.05;
            this.yd = this.yd + random.nextGaussian() * 0.05;
            this.zd = this.zd + random.nextGaussian() * 0.05;
        }
        if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).isRedstoneConductor(this.level, new BlockPos(this.x, this.y, this.z))) {
            this.remove();
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
            if (!level.getBlockState(new BlockPos(this.x, this.y, this.z).relative(dir)).isAir()) {
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
        if (this.expVelX != this.xd) {
            this.expVelX = this.expVelX + this.xd * 0.05;
        }
        if (this.expVelY != this.yd) {
            this.expVelY = this.expVelY + this.yd * 0.05;
        }
        if (this.expVelZ != this.zd) {
            this.expVelZ = this.expVelZ + this.zd * 0.05;
        }
        if (this.xd > 0.075) {
            this.xd = 0.075;
        }
        if (this.yd > 0.075) {
            this.yd = 0.075;
        }
        if (this.zd > 0.075) {
            this.zd = 0.075;
        }

        if (this.xd < -0.075) {
            this.xd = -0.075;
        }
        if (this.yd < -0.075) {
            this.yd = -0.075;
        }
        if (this.zd < -0.075) {
            this.zd = -0.075;
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
            FireFly rain = new FireFly(world, x, y, z, spriteProvider, upv);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
