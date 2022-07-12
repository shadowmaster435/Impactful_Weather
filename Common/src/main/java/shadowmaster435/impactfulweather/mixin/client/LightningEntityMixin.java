package shadowmaster435.impactfulweather.mixin.client;


import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightningBolt.class)
public abstract class LightningEntityMixin {

//    @Shadow
//    private BlockPos getAffectedBlockPos() {
//        return null;
//    }
//
//    @Inject(at = @At("HEAD"), method = "spawnFire")
//    public void setposarray(int spreadAttempts, CallbackInfo ci) {
//       BlockPos bpos = new BlockPos(MinecraftClient.getInstance().world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, new BlockPos(this.getAffectedBlockPos().getX(), 1.0E-6, this.getAffectedBlockPos().getZ())));
//        RenderUtil.SetBurnArrayPos(bpos);
//        System.out.println(bpos);
//    }

}
