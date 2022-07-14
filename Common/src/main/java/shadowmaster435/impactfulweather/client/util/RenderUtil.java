package shadowmaster435.impactfulweather.client.util;

import com.mojang.math.Vector4f;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class RenderUtil {
    private static final ResourceLocation SWAMPFOG = new ResourceLocation("impactfulweather:textures/environment/swampfog.png");
    public static List<BlockPos> posList = new ArrayList<>();
    public static float v1a;
    public static float v2a;
    public static float v3a;
    public static float v4a;
    public static int successcount;
    public static int diagsuccesscount;



    public static double burnx = 0;
    public static double burny = 0;
    public static double burnz = 0;
    public static ArrayList<BlockPos> burnmarklist = new ArrayList<>();
    public static ArrayList<Vec3> generatedmarkvectors = new ArrayList<>();
    public static ArrayList<Vector4f> generatedmarkquads = new ArrayList<>();
    
    public static ArrayList<ArrayList<Vector4f>> markgenvectposlist = new ArrayList<>();

    public static ArrayList<String> timerwithid = new ArrayList<>();

    /*public static void RenderLightningBurnMark(MatrixStack matrices, float ticks) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        for (ArrayList<Vector4f> list : markgenvectposlist) {
            for (Vector4f quad : list) {
                bufferBuilder.vertex(matrices.peek().getPositionMatrix(), quad.getX(), quad.getY(), quad.getZ()).color( 0, 0, 0, quad.getW());
                bufferBuilder.vertex(matrices.peek().getPositionMatrix(), quad.getX() + 0.0625f, quad.getY(), quad.getZ()).color( 0, 0, 0, quad.getW());
                bufferBuilder.vertex(matrices.peek().getPositionMatrix(), quad.getX() + 0.0625f, quad.getY(), quad.getZ() + 0.0625f).color( 0, 0, 0, quad.getW());
                bufferBuilder.vertex(matrices.peek().getPositionMatrix(), quad.getX(), quad.getY(), quad.getZ() + 0.0625f).color( 0, 0, 0, quad.getW());

            }
        }
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        tessellator.draw();
        if (ticks >= 1) {
        for (String timer : timerwithid) {
            String id = timer.substring(0, timer.indexOf(":"));
            int actualtimer = Integer.parseInt(timer.substring(timer.indexOf(":" + 1), timer.indexOf("|")));
            String owner = timer.substring(timer.indexOf("|"));


            if (actualtimer > ParticleUtil.config.misc.lightningburnmarklifespan) {
                timerwithid.remove(timer);
                if (Integer.parseInt(owner) != markgenvectposlist.size()) {
                    ArrayList<Vector4f> removalflagged = new ArrayList<>();
                    removalflagged.add(new Vector4f(0,0,0,0));

                    // Make Sure We Don't Remove Stuff From The Array And Shift Indexes
                    if (markgenvectposlist.get(Integer.parseInt(owner)) != removalflagged) {
                        markgenvectposlist.set(Integer.parseInt(owner), removalflagged);
                    }
                    removalflagged.clear();
                } else {
                    markgenvectposlist.remove(Integer.parseInt(owner));
                }


            } else {
                // Remove if at end of list
                timerwithid.set(timerwithid.indexOf(timer), timer.substring(0, timer.indexOf("|")) + (actualtimer + 1));
            }
        }
    }
    }

    // Caclulate The Way For The Burn Mark To Look And Set Result In Array
    public static void SetBurnArrayPos(BlockPos hitpos) {
        burnmarklist.add(hitpos);
        for (int i = 0; i < 5; ++i) {
            // Add Points To Array
            if (Math.random() > 0.5) {
                generatedmarkvectors.add(new Vec3d(Math.min(Math.random() * 16, 16), hitpos.getY(), Math.min(Math.random() * 16, 16)));
            } else {
                generatedmarkvectors.add(new Vec3d(Math.min(Math.random() * -16, -16), hitpos.getY(), Math.min(Math.random() * -16, -16)));
            }

        }
        for (Vec3d pos: generatedmarkvectors) {
                for (int s = 0; s < 16; ++s) {
                    // Pixel Unit Distances
                    double distx = MathHelper.lerp((s + 1f) / 16f, 0, Math.min(pos.x, Math.round(pos.x)));
                    double distz = MathHelper.lerp((s + 1f) / 16f, 0, Math.min(pos.z, Math.round(pos.z)));

                    // Wideness of the generated triangle at position along line
                    double wideness = 1 - (s * 0.25);

                    // Check for full surface
                    if (MinecraftClient.getInstance().world.getBlockState(new BlockPos(pos).offset(Direction.DOWN, 1)).isSideSolidFullSquare(MinecraftClient.getInstance().world, new BlockPos(pos), Direction.UP)) {
                        generatedmarkquads.add(new Vector4f((float) distx, (float) pos.y, (float) distz, 1f));
                    }
                    // The W postion is used for the alpha Value
                    for (int h = 1; h < 9; ++h) {
                        if (MinecraftClient.getInstance().world.getBlockState(new BlockPos(pos).offset(Direction.DOWN, 1)).isSideSolidFullSquare(MinecraftClient.getInstance().world, new BlockPos(pos), Direction.UP)) {
                            if (Math.abs(h - 1) - 4 < wideness * 4) {
                                generatedmarkquads.add(new Vector4f((float) distx + (h - 5) * 0.25f, (float) pos.y, (float) distz + (h - 5) * 0.25f, (float) wideness));
                            }
                        }
                    }
                }
                // Add and id quads to lists
                timerwithid.add(Math.floor(Math.random() * 100) + "" + Math.floor(Math.random() * 100) + "" + Math.floor(Math.random() * 100) + ":" + "0|" + markgenvectposlist.size());
                markgenvectposlist.add(generatedmarkquads);
                generatedmarkquads.clear();
            }
    }*/
    
    /*public static void RenderSwampFog(MatrixStack matrices, int ticks, Camera camera, Matrix4f matrix4f) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        MinecraftClient instance = MinecraftClient.getInstance();
        ClientWorld worldView = instance.world;
        assert instance.player != null;
        BlockState[] states = {Blocks.GRASS_BLOCK.getDefaultState(), Blocks.GRAVEL.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.WATER.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.DIORITE.getDefaultState(), Blocks.GRANITE.getDefaultState(), Blocks.ANDESITE.getDefaultState(),  Blocks.TALL_SEAGRASS.getDefaultState().with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), Blocks.KELP.getDefaultState(), Blocks.SEAGRASS.getDefaultState()};
        List<BlockState> actualstates = Arrays.stream(states).toList();

        for (int x2 = 1; x2 < 32; ++x2) {
            for (int y2 = 1; y2 < 32; ++y2) {
                for (int z2 = 1; z2 < 32; ++z2) {
                    int xps = (camera.getBlockPos().getX() - 16) + x2;
                    int yps = (camera.getBlockPos().getY() - 16) + y2;
                    int zps = (camera.getBlockPos().getZ() - 16) + z2;

                    assert worldView != null;
                    if (actualstates.contains(worldView.getBlockState(new BlockPos(xps, yps, zps)))) {
                        if ((worldView.isAir(new BlockPos(xps, yps + 1, zps)) || !worldView.getBlockState(new BlockPos(xps, yps + 1, zps)).getMaterial().blocksMovement()) && worldView.isSkyVisible(new BlockPos(xps, yps + 1, zps))) {
                            posList.add(new BlockPos(xps, yps, zps));
                        }
                    }
                }
            }
        }
        Matrix4f matrix4f2 = matrices.peek().getPositionMatrix();

        if (config.swampfog) {
            matrices.push();
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderTexture(0, SWAMPFOG);
            RenderSystem.enableDepthTest();
            RenderSystem.enableTexture();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);


            for (int i = 1; i < posList.size(); i++) {
                float camx = (float) camera.getPos().x;
                float camy = (float) camera.getPos().y;
                float camz = (float) camera.getPos().z;
                float playerx = (float) instance.player.getPos().getX();
                float playery = (float) instance.player.getPos().getY() + 1;
                float playerz = (float) instance.player.getPos().getZ();
                float distx = camx - playerx;
                float disty = camy - playery;
                float distz = camz - playerz;

                float x = posList.get(i).getX() - playerx - distx;
                float y = posList.get(i).getY() - playery - disty;
                float z = posList.get(i).getZ() - playerz - distz;



                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH))))) {
                    ++successcount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.SOUTH))))) {
                    ++successcount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.EAST))))) {
                    ++successcount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST))))) {
                    ++successcount;
                }

                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(1,0,-1))))) {
                    ++diagsuccesscount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(-1,0,-1))))) {
                    ++diagsuccesscount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(1,0,1))))) {
                    ++diagsuccesscount;
                }
                if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(-1,0,1))))) {
                    ++diagsuccesscount;
                }



                if ((successcount == 1 ) && (diagsuccesscount < 2 + successcount)) {

                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH))))) {
                        bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                    }
                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.SOUTH))))) {
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                    }
                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.EAST))))) {
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                    }
                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST))))) {
                        bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                    }

                } else if ((diagsuccesscount == 1)) {

                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(1,0,-1))))) {
                        if (actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH))) && actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST)))) {

                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        }  else {
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        }
                    } else
                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(-1,0,-1))))) {
                        if (actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH))) && actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST)))) {
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        }  else {
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        }
                    } else

                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(1,0,1))))) {
                        if (actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.SOUTH))) && actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST)))) {

                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        } else {
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                        }



                    } else
                    if (!(actualstates.contains(worldView.getBlockState(posList.get(i).add(-1,0,1))))  && actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH)))) {
                        if (actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.NORTH))) && actualstates.contains(worldView.getBlockState(posList.get(i).offset(Direction.WEST)))) {
                            bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();
                        }  else {
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z).texture(0.0F + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x, y + 1, z + 1).texture(1 + (ticks * 0.0625F), 0.0F + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1, z + 1).texture(1F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f, 0).next();
                            bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * 0.0625F), 1 + (ticks * 0.0625F)).color(1f, 1f, 1f,  0.25f).next();

                        }
                    }

                } else {
                    if ((successcount != 2) && (diagsuccesscount == 0)) {
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z).texture(0.0F + (ticks * (0.0625F / 8)), 0.0F + (ticks * (0.0625F / 8))).color(1f, 1f, 1f, .25f).next();
                        bufferBuilder.vertex(matrix4f2, x, y + 1.25f, z + 1).texture(1 + (ticks * (0.0625F / 8)), 0.0F + (ticks * (0.0625F / 8))).color(1f, 1f, 1f, .25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z + 1).texture(1 + (ticks * (0.0625F / 8)), 1 + (ticks * (0.0625F / 8))).color(1f, 1f, 1f, .25f).next();
                        bufferBuilder.vertex(matrix4f2, x + 1, y + 1.25f, z).texture(0.0F + (ticks * (0.0625F / 8)), 1 + (ticks * (0.0625F / 8))).color(1f, 1f, 1f, .25f).next();
                    }
                }
                successcount = 0;
                diagsuccesscount = 0;
            }
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            matrices.pop();
        }
        successcount = 0;
        diagsuccesscount = 0;
        posList.clear();
    }*/

    /*public static List<Float> segmentvertlist = new ArrayList<>();

    public static void RenderLightning(MatrixStack matrices, int ticks, Camera camera, Matrix4f matrix4f) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        MinecraftClient instance = MinecraftClient.getInstance();
        matrices.push();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SWAMPFOG);
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        float randposx = (float) (Math.random() * 15f);
        float randposz = (float) (Math.random() * 15f);
        float posx = 0;
        float posz = 0;
        float posy = 0;
        float posxprev = 0;
        float poszprev = 0;
        float posyprev = 0;
        Matrix4f matrix4f2 = matrices.peek().getPositionMatrix();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        for (int length = 0; length < 16; ++length) {
            boolean reset;

            reset = ticks % 40 == 0;
            if (reset) {
                posx = randposx < 3 ? randposx : 3;
                assert instance.player != null;
                posy = (float) (instance.player.getPos().y - (length * 4));
                posz = randposz < 3 ? randposz : 3;
            } else {
                posxprev = posx;
                poszprev = posy;
                posyprev = posz;

            }
            for (float i = 0.5f; i > 0; i = i - 0.05f) {
                bufferBuilder.vertex(matrix4f2, posx - i, posy, posz - i).color(1f, 1f, 1f, 1f).next();
                bufferBuilder.vertex(matrix4f2, posx + i, posy, posz - i).color(1f, 1f, 1f, 1f).next();
                bufferBuilder.vertex(matrix4f2, posxprev + i, posyprev, poszprev + i).color(1f, 1f, 1f, 1f).next();
                bufferBuilder.vertex(matrix4f2, posxprev - i, posyprev, poszprev + i).color(1f, 1f, 1f, 1f).next();

            }

        }



        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        matrices.pop();
        segmentvertlist.clear();

    }*/


}
