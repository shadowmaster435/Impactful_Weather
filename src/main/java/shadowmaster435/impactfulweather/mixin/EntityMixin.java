package shadowmaster435.impactfulweather.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public float horizontalSpeed;

    @Shadow
    private Vec3d velocity = Vec3d.ZERO;

    @Shadow
    private BlockPos blockPos;

    @Shadow public abstract World getWorld();

    @Inject(at = @At("RETURN"), method = "move")
    private void move(MovementType movementType, Vec3d movement, CallbackInfo info) {
        World world = this.getWorld();
        assert world != null;
        if (
                (
                world.getBiome(this.blockPos).getCategory() == Biome.Category.PLAINS ||
                world.getBiome(this.blockPos).getCategory() == Biome.Category.FOREST ||
                world.getBiome(this.blockPos).getCategory() == Biome.Category.BEACH ||
                world.getBiome(this.blockPos).getCategory() == Biome.Category.OCEAN ||
                world.getBiome(this.blockPos).getCategory() == Biome.Category.SWAMP ||
                world.getBiome(this.blockPos).getCategory() == Biome.Category.RIVER
                ) &&
                world.isRaining()
        ) {
            this.horizontalSpeed = (float) (this.velocity.horizontalLength() * 0.1f);
        } else {
            this.horizontalSpeed = (float) (this.velocity.horizontalLength() * 0.6f);
        }
    }
}
