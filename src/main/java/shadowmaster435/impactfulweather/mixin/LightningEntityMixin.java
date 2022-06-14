package shadowmaster435.impactfulweather.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import shadowmaster435.impactfulweather.util.RenderUtil;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

   /* @Shadow
    private BlockPos getAffectedBlockPos() {
        return null;
    }

     @Inject(at = @At("HEAD"), method = "spawnFire")
    public void setposarray(int spreadAttempts, CallbackInfo ci) {
       BlockPos bpos = new BlockPos(MinecraftClient.getInstance().world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, new BlockPos(this.getAffectedBlockPos().getX(), 1.0E-6, this.getAffectedBlockPos().getZ())));
        RenderUtil.SetBurnArrayPos(bpos);
        System.out.println(bpos);
    }*/

}
