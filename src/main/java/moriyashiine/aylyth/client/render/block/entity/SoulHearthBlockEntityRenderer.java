package moriyashiine.aylyth.client.render.block.entity;

import moriyashiine.aylyth.common.block.SoulHearthBlock;
import moriyashiine.aylyth.common.block.entity.SoulHearthBlockEntity;
import moriyashiine.aylyth.common.registry.ModBlocks;
import moriyashiine.aylyth.common.registry.ModItems;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class SoulHearthBlockEntityRenderer implements BlockEntityRenderer<SoulHearthBlockEntity> {

    public SoulHearthBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(SoulHearthBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.translate(0.5, 0.55, 0.5);
        matrices.scale(0.4f, 0.4f, 0.4f);
        if(entity.getWorld() != null && entity.getWorld().getBlockState(entity.getPos()).isOf(ModBlocks.SOUL_HEARTH) && entity.getWorld().getBlockState(entity.getPos()).get(SoulHearthBlock.HALF) == DoubleBlockHalf.LOWER){
            for(int i = 0; i < entity.getWorld().getBlockState(entity.getPos()).get(SoulHearthBlock.CHARGES); i++){
                matrices.translate(0, 0.06, 0);
                matrices.push();
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((90 + (180 * i)) % 360));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90 * i));
                MinecraftClient.getInstance().getItemRenderer().renderItem(ModItems.POMEGRANATE.getDefaultStack(), ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, null, 0);
                matrices.pop();
            }
        }
    }
}
