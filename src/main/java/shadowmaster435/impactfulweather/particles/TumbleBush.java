package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;


@Environment(EnvType.CLIENT)
public class TumbleBush extends SpriteBillboardParticle {
    private final float field_3809;

    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }

    public TumbleBush(ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteProvider sprites) {
        super(world, x, y, z, Xv, Yv, Zv);
        this.velocityX = 0.0D;
        this.velocityY = (Math.random() * 0.1D) + 0.05D;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.alpha = 0;
        this.setBoundingBoxSpacing(1F, 1F);
        this.maxAge = 100;
        setSprite(sprites.getSprite(world.random));
    }
    public int age1;
    public int groundtimer;

    public static float rotvel = 0;
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        this.angle += 3.1415927F * this.field_3809 * (rotvel + 1);
        this.scale = 0.5F;
        ++age1;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > 90) {
            this.alpha = 1f - ((this.age - 90) / 10f);
        } else {
            this.alpha = 1f;
        }
        if (this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement()) {
            this.markDead();
        }
        if (this.onGround ) {
            rotvel = 2;
            ++groundtimer;
            if (this.velocityY < -0.8) {
                this.velocityY = (this.velocityY * -1) * 0.3F;
            } else {
                this.velocityY = this.velocityY * -1;
            }
        } else if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
            for (int b = 0; b < 8; ++b) {
                this.world.addParticle(ParticleTypes.SPLASH,this.x, this.y, this.z,1,1.2,1);
            }
            this.markDead();
            ++groundtimer;
        }
        else if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            for (int i = 0; i < 4; ++i) {
                this.world.addParticle(ParticleTypes.LARGE_SMOKE,this.x, this.y, this.z,0.4,0.2,0.4);
                this.world.addParticle(ParticleTypes.FLAME,this.x, this.y, this.z,0.4,0.2,0.4);
                this.world.addParticle(ParticleTypes.LAVA,this.x, this.y, this.z,0.4,0.2,0.4);

            }
            this.markDead();
        } else {
            this.velocityX = 0.4;
            this.velocityZ = 0.4;
            this.velocityY = (this.velocityY - 0.05);
            groundtimer = 0;
            if (rotvel <= 0) {
                rotvel = 0;
            } else {
                rotvel = rotvel - 0.2f;
            }
        }
        if (this.age1 >= 100 || groundtimer > 15) {
            this.markDead();
        } else if (groundtimer > 5) {
            this.scale -= 0.05f;
        }
        ++age;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class TumbleBushFactory implements ParticleFactory<DefaultParticleType> {

        private final FabricSpriteProvider sprites;

        public TumbleBushFactory(FabricSpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new TumbleBush(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
