package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;


@Environment(EnvType.CLIENT)
public class Rain extends SpriteBillboardParticle {

    public static ClientWorld cworld;
    public Rain(ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.125f;
        cworld = world;
        this.setBoundingBoxSpacing(0.02F, 0.02F);
    }

    public void tick() {
        this.scale = 0.125f;

        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.onGround) {
            for (int i = 0; i < 1; ++i) {
                world.addParticle(ParticleTypes.RAIN, prevPosX, prevPosY, prevPosZ, 0, 0, 0);
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
            Rain rain = new Rain(world, x, y, z, Xv, Yv, Zv);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
