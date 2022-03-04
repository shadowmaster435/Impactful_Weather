package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import shadowmaster435.impactfulweather.init.IWParticles;


@Environment(EnvType.CLIENT)
public class HeavyRain extends AnimatedParticle {
    public static int rainamount = 1;
    public static ClientWorld cworld;
    public static int heavyrainvel = 1;
    public float light;

    public HeavyRain(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.125f;
        cworld = world;
        this.angle = -45;

        this.setSprite(sprites.getSprite(world.random));
        this.setBoundingBoxSpacing(0.05F, 0.5F);
        this.light = world.getBrightness(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        rainamount = 2;
    }
    public void tick() {
        this.light = world.getBrightness(new BlockPos(this.x, this.y, this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.scale = 0.125f;
        heavyrainvel = 2;

        if (!this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().isReplaceable() || this.onGround || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            if (!this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) && !this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
                world.addParticle(IWParticles.RAINSPLASH, this.x, this.y + 0.1, this.z, 0, 0, 0);

            }
            this.markDead();
        } else {
            if (Math.random() > 0.9975) {
                world.addParticle(IWParticles.HEAVYRAINEXT, MathHelper.lerp(Math.random(), this.x - 0.5, this.x + 0.5), MathHelper.lerp(Math.random(), this.y - 0.5, this.y + 0.5), MathHelper.lerp(Math.random(), this.z - 0.5, this.z + 0.5), 0, 0, 0);
            }
            this.velocityX = -2;
            this.velocityZ = 0;
            this.velocityY = -3;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class HeavyRainFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public HeavyRainFactory(FabricSpriteProvider sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            HeavyRain rain = new HeavyRain(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
