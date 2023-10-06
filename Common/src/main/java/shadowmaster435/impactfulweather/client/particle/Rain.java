package shadowmaster435.impactfulweather.client.particle;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.Heightmap;
import shadowmaster435.impactfulweather.config.ClientConfig;
import shadowmaster435.impactfulweather.init.ModRegistry;


public class Rain extends SimpleAnimatedParticle {
    public static int rainamount = 1;
    public static ClientLevel cworld;
    public float light;

   
    public Rain(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0f);
        this.xd = 0.0D;
        this.yd = -3;
        this.zd = 0.0D;
        this.gravity = 0f;
        this.quadSize = 0.125f;
        cworld = world;
        this.setSprite(sprites.get(world.random));
        this.setSize(0.02F, 0.02F);
        rainamount = 2;


        this.light = world.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light), (15f / this.light), (15f / this.light));

    }

    public void tick() {
        this.light = level.getLightLevelDependentMagicValue(new BlockPos((int)this.x, (int)this.y, (int)this.z)) + 0.01f;
        this.setColor((15f / this.light),(15f / this.light), (15f / this.light));
        this.quadSize = 0.125f;

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.onGround || this.level.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).blocksMotion() || this.level.getFluidState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).is(FluidTags.WATER) || this.level.getFluidState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).is(FluidTags.LAVA)) {


            if (ClientConfig.INSTANCE.particleToggles.rainsplash.get()) {
                if (!this.level.getFluidState(new BlockPos((int)this.x, (int)this.y, (int)this.z)).is(FluidTags.WATER)) {
                    level.addParticle(ModRegistry.RAINSPLASH.get(), xo, yo + 0.1, zo, 0, 0, 0);

                }
            }
            this.remove();
        } else {
            this.xd = 0;
            this.zd = 0;
            this.yd = -3;
        }
        this.move(this.xd, this.yd, this.zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class RainFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public RainFactory(SpriteSet sprites) {
            this.spriteProvider = sprites;

        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double Xv, double Yv, double Zv) {
            Rain rain = new Rain(world, x, y, z, spriteProvider);
            rain.pickSprite(this.spriteProvider);
            return rain;
        }
    }
}
