package moriyashiine.aylyth.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import moriyashiine.aylyth.common.Aylyth;
import moriyashiine.aylyth.common.registry.key.ModBiomeKeys;
import moriyashiine.aylyth.common.registry.key.ModDimensionKeys;
import moriyashiine.aylyth.mixin.client.WorldRendererAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

public class AylythDimensionRenderer {
	public static final DimensionEffects DIMENSION_EFFECTS = new AylythDimensionEffects();
	public static final DimensionRenderingRegistry.SkyRenderer SKY_RENDERER = new AylythSkyRenderer();
	public static final Identifier SUN = new Identifier(Aylyth.MOD_ID, "textures/environment/sun.png");
	public static final Identifier MOON = new Identifier(Aylyth.MOD_ID, "textures/environment/moon.png");
	public static int goalFogStrength = 0;
	private static float currentFogStrength;

	public static void renderFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta) {
		float fogStrength = currentFogStrength;
		if (camera.getSubmersionType().equals(CameraSubmersionType.WATER)) {
			ClientPlayerEntity player = MinecraftClient.getInstance().player;
			ClientWorld world = MinecraftClient.getInstance().world;
			if (world.getBiome(player.getBlockPos()).isIn(BiomeTags.HAS_CLOSER_WATER_FOG)) {
				fogStrength *= 0.75;
			}
		}
		RenderSystem.setShaderFogStart(0F);
		RenderSystem.setShaderFogEnd(fogStrength);
		if (goalFogStrength < currentFogStrength) {
			currentFogStrength -= 0.1F;
		} else if (goalFogStrength > currentFogStrength) {
			currentFogStrength += 0.1F;
		}
	}

	public static void determineConditions(ClientWorld world, RegistryEntry<Biome> biome) {// TODO: Use tags
		if (world.getRegistryKey() == ModDimensionKeys.AYLYTH) {
			Identifier biomeId = world.getRegistryManager().get(RegistryKeys.BIOME).getId(biome.value());
			if (biomeId == ModBiomeKeys.CLEARING.getValue() || biomeId == ModBiomeKeys.UPLANDS.getValue()) {
				goalFogStrength = 40;
			}
			else if (biomeId == ModBiomeKeys.OVERGROWN_CLEARING.getValue()) {
				goalFogStrength = 24;
			}
			else {
				goalFogStrength = 16;
			}
		}
		else {
			goalFogStrength = 0;
		}
	}
	
	private static class AylythDimensionEffects extends DimensionEffects {
		public AylythDimensionEffects() {
			super(-70, false, SkyType.NONE, false, true);
		}
		
		@Override
		public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
			return color;
		}
		
		@Override
		public boolean useThickFog(int camX, int camY) {
			return false;
		}
	}

	// [VanillaCopy] WorldRenderer::renderSky
	private static class AylythSkyRenderer implements DimensionRenderingRegistry.SkyRenderer {

		@Override
		public void render(WorldRenderContext context) {
			ClientWorld world = context.world();
			Camera camera = context.camera();
			float tickDelta = context.tickDelta();
			var renderer = (WorldRendererAccessor) context.worldRenderer();
			VertexBuffer lightSkyBuffer = renderer.getLightSkyBuffer();
			VertexBuffer starsBuffer = renderer.getStarsBuffer();
			MatrixStack matrices = context.matrixStack();
			var matrix4f = context.projectionMatrix();
			Vec3d vec3d = world.getSkyColor(camera.getPos(), tickDelta);
			float skyRed = (float) vec3d.x;
			float skyGreen = (float) vec3d.y;
			float skyBlue = (float) vec3d.z;
			BackgroundRenderer.setFogBlack();
			BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
			RenderSystem.depthMask(false);
			RenderSystem.setShaderColor(skyRed, skyGreen, skyBlue, 1.0F);
			ShaderProgram shader = RenderSystem.getShader();
			lightSkyBuffer.bind();
			lightSkyBuffer.draw(matrices.peek().getPositionMatrix(), matrix4f, shader);
			VertexBuffer.unbind();
			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
			matrices.push();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0);
			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(world.getSkyAngle(tickDelta) * 360.0F));
			matrices.translate(0, -80, 0);
			var positionMatrix = matrices.peek().getPositionMatrix();
			float celestialSize = 13.0F;
			RenderSystem.setShader(GameRenderer::getPositionTexProgram);
			RenderSystem.setShaderTexture(0, SUN);
			bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
			bufferBuilder.vertex(positionMatrix, -celestialSize, 100.0F, -celestialSize).texture(0.0F, 0.0F).next();
			bufferBuilder.vertex(positionMatrix, celestialSize, 100.0F, -celestialSize).texture(0.0F, 1.0F).next();
			bufferBuilder.vertex(positionMatrix, celestialSize, 100.0F, celestialSize).texture(1.0F, 1.0F).next();
			bufferBuilder.vertex(positionMatrix, -celestialSize, 100.0F, celestialSize).texture(1.0F, 0.0F).next();
			BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
			matrices.translate(0, 160, 0);
			RenderSystem.setShaderTexture(0, MOON);
			bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
			bufferBuilder.vertex(positionMatrix, -celestialSize, -100.0F, celestialSize).texture(0.0F, 0.0F).next();
			bufferBuilder.vertex(positionMatrix, celestialSize, -100.0F, celestialSize).texture(0.0F, 1.0F).next();
			bufferBuilder.vertex(positionMatrix, celestialSize, -100.0F, -celestialSize).texture(1.0F, 1.0F).next();
			bufferBuilder.vertex(positionMatrix, -celestialSize, -100.0F, -celestialSize).texture(1.0F, 0.0F).next();
			BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
			float starPower = world.method_23787(tickDelta);
			if (starPower > 0.0F) {
				matrices.push();
				matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
				matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(world.getSkyAngle(tickDelta) * 360.0F));
				matrices.scale(0.1f, 0.1f, 0.1f);
				RenderSystem.setShaderColor(starPower, starPower, starPower, starPower);
				BackgroundRenderer.clearFog();
				starsBuffer.bind();
				starsBuffer.draw(matrices.peek().getPositionMatrix(), matrix4f, GameRenderer.getPositionProgram());
				VertexBuffer.unbind();
				matrices.pop();
			}

			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
			matrices.pop();
			RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
			double d = MinecraftClient.getInstance().player.getCameraPosVec(tickDelta).y - world.getLevelProperties().getSkyDarknessHeight(world);
			if (d < 0.0) {
				matrices.push();
				matrices.translate(0.0F, 12.0F, 0.0F);
				renderer.getDarkSkyBuffer().bind();
				renderer.getDarkSkyBuffer().draw(matrices.peek().getPositionMatrix(), matrix4f, shader);
				VertexBuffer.unbind();
				matrices.pop();
			}

			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.depthMask(true);
		}
	}
}
