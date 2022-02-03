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
public class SandMote extends AnimatedParticle {
    private final float field_3809;

    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }
    public static int groundtimer;

    public SandMote(ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = (Math.random() * 0.1D) + 0.05D;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;

        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.maxAge = (int) (Math.random() * 20) + 50;
        if (Math.random() > 0.5) {
            this.angle += (int) (Math.random() * 20 + 10) * this.age;
        } else {
            this.angle += (int) (Math.random() * -20 - 10) * this.age;
        }
        if (this.age - 50 >= 0) {
            this.alpha = this.age + (this.age * -1);
        }
        setSprite(sprites.getSprite(world.random));
    }

    public int age1;

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        this.scale = 0.15F;
        ++this.age;

        if (this.age >= 90) {
            this.markDead();
        } else {
            if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
                this.velocityY = Sinefunc();
                this.velocityX = 0.2;
                this.velocityZ = 0.2;
                this.velocityY = (this.velocityY - 0.0025) + Sinefunc();
                this.angle += 3.1415927F * this.field_3809 * 0.5F;
                ++groundtimer;
            } else if (this.onGround) {
                this.markDead();
            } else {
                this.velocityX = 0.6;
                this.velocityZ = 0.6;
                this.angle += 3.1415927F * this.field_3809 * 2.0F;
                this.velocityY = (this.velocityY - 0.0025) + Sinefunc();
            }
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class SandMoteFactory implements ParticleFactory<DefaultParticleType> {

        private final FabricSpriteProvider sprites;

        public SandMoteFactory(FabricSpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new SandMote(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
