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
import net.minecraft.world.LightType;
import shadowmaster435.impactfulweather.util.MiscUtil;


@Environment(EnvType.CLIENT)
public class RedSandMote extends AnimatedParticle {
    private final float field_3809;

    public float Sinefunc() {
        return (float) ((float) (Math.sin(this.age) / 8.0) / 16.0);
    }
    public float light;

    public RedSandMote(ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv, SpriteProvider sprites) {
        super(world, x, y, z, sprites, 0f);
        this.velocityX = 0.0D;
        this.velocityY = (Math.random() * 0.1D) + 0.05D;
        this.velocityZ = 0.0D;
        this.gravityStrength = 0f;
        this.scale = 0.15F;
        this.alpha = 0;
        this.field_3809 = ((float) Math.random() - 0.5F) * 0.1F;
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.maxAge = 40;
        this.setSprite(sprites.getSprite(world.random));
    }

    public void tick() {
        /*this.light = MiscUtil.makeUnInt(15, MiscUtil.getRealLightLevel(world, new BlockPos(this.prevPosX, this.prevPosY, this.prevPosZ)));
        if (this.light < 0.275f) {
            this.setColor(0.275f, 0.275f, 0.275f);
        } else {
            if (this.world.isThundering() && this.world.isSkyVisible(new BlockPos(this.prevPosX, this.prevPosY, this.prevPosZ))) {
                this.setColor(0.275f, 0.275f, 0.275f);

            }
            if (this.world.isRaining() && this.world.isSkyVisible(new BlockPos(this.prevPosX, this.prevPosY, this.prevPosZ))) {
                this.setColor(this.light + 0.75f, this.light + 0.75f, this.light + 0.75f);

            }
                if (this.world.isSkyVisible(new BlockPos(this.prevPosX, this.prevPosY, this.prevPosZ)) && this.world.getTime() > 1000 && this.world.getTime() < 11000) {
                    this.setColor(255, 255, 255);
                } else {
                    this.setColor(this.light, this.light, this.light);

                }
        }*/
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        this.scale = 0.15F;

        ++this.age;
        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.maxAge - 10)) {
            this.alpha = 1f - ((this.age - 50) / 10f);
        } else {
            this.alpha = 1f;
        }
        if (this.age >= this.maxAge) {
            this.markDead();
        } else {
            if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER) || this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.LAVA)) {
                this.velocityY = Sinefunc();
                this.velocityX = 0.2;
                this.velocityZ = 0.2;
                this.velocityY = (this.velocityY - 0.0025) + Sinefunc();
                this.angle += 3.1415927F * this.field_3809 * 0.5F;
              //  ++groundtimer;
            } else if (this.onGround || this.y < 63 || this.world.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMovement()) {
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
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class RedSandMoteFactory implements ParticleFactory<DefaultParticleType> {

        private final FabricSpriteProvider sprites;

        public RedSandMoteFactory(FabricSpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double Xv, double Yv, double Zv) {
            return new RedSandMote(world, x, y, z, Xv, Yv, Zv, sprites);

        }
    }
}
