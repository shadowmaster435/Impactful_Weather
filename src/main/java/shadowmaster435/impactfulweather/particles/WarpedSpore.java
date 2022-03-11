package shadowmaster435.impactfulweather.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import shadowmaster435.impactfulweather.init.IWParticles;
import shadowmaster435.impactfulweather.util.MiscUtil;


@Environment(EnvType.CLIENT)
public class WarpedSpore extends AnimatedParticle {
    public static int rainamount = 1;
    public static ClientWorld cworld;
    public float light;


    public WarpedSpore(ClientWorld world, double x, double y, double z, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = -3;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.125f;
        this.maxAge = 50;
        this.setSprite(sprites.getSprite(world.random));
        this.setBoundingBoxSpacing(0.02F, 0.02F);
    }
    public void tick() {
        this.scale = 0.125f;
        ++this.age;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.maxAge - 10)) {
            this.alpha = 1f - ((this.age - (this.maxAge - 10)) / 10f);
        } else {
            this.alpha = 1f;
        }
        float offset = (float) (Math.sin((age + MinecraftClient.getInstance().getTickDelta()) / 8.0) / 16.0);

        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age >= this.maxAge || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement() || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
            this.markDead();
        } else {
            if (MiscUtil.ifchance(85)) {
                world.addParticle(ParticleTypes.PORTAL, this.x,this.y ,this.z,this.velocityX,this.velocityY,this.velocityZ);
            }
            this.velocityX = 0 - offset;
            this.velocityZ = 0 + offset;
            this.velocityY = 0.075;
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
            WarpedSpore rain = new WarpedSpore(world, x, y, z, spriteProvider);
            rain.setSprite(this.spriteProvider);
            return rain;
        }
    }
}
