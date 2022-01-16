package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;


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

        this.setBoundingBoxSpacing(1F, 1F);
        this.maxAge = (int) 100;
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
    public int groundtimer;

    public static float rotvel = 0;
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        this.angle += 3.1415927F * this.field_3809 * (rotvel + 1);
        this.scale = 0.4F;
        ++age1;
        if (this.onGround) {
            rotvel = 2;
            ++groundtimer;
            if (this.velocityY < -0.8) {
                this.velocityY = 0.6;
            } else {
                this.velocityY = this.velocityY * -1;
            }
        } else {
            groundtimer = 0;
            if (rotvel <= 0) {
                rotvel = 0;
            } else {
                rotvel = rotvel - 0.2f;
            }
        }
        if (this.age1 >= 150 || groundtimer > 5) {
            this.markDead();
        }
        this.velocityX = 0.4;
        this.velocityZ = 0.4;
        this.velocityY = (this.velocityY - 0.05);
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
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
