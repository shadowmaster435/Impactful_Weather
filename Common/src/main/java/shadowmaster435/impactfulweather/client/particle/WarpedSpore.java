package shadowmaster435.impactfulweather.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import shadowmaster435.impactfulweather.client.util.MiscUtil;


public class WarpedSpore extends SimpleAnimatedParticle {
    public static int rainamount = 1;
    public static ClientLevel cworld;
    public float light;


    public WarpedSpore(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.25D;
        this.yd = 0.075;
        this.zd = -0.25D;
        this.gravity = 0f;
        this.quadSize = 0.2f;
        this.alpha = 0;
        this.lifetime = 50;
        this.setSprite(sprites.get(world.random));
        this.setSize(0.02F, 0.02F);
    }
    public void tick() {
        this.quadSize = 0.2f;

        ++this.age;

        if (this.age < 10) {
            this.alpha = this.age / 10f;
        } else if (this.age > (this.lifetime - 10)) {
            this.alpha = 1f - ((this.age - (this.lifetime - 10)) / 10f);
        } else {
            this.alpha = 1f;
        }
        float offset = (float) (Math.sin((this.age + Minecraft.getInstance().getFrameTime()) / 8.0) / 16.0);

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age >= this.lifetime || this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial().blocksMotion() || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.LAVA)) {
            this.remove();
        } else {
            if (MiscUtil.ifchance(95)) {
                level.addParticle(ParticleTypes.PORTAL, this.x,this.y ,this.z,this.xd,this.yd,this.zd);
            }
            this.xd = 0 - offset;
            this.zd = 0 + offset;
            this.yd = 0.075;
        }
        this.move(this.xd, this.yd, this.zd);
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
            WarpedSpore rain = new WarpedSpore(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
