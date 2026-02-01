package shop.notropicalfish.itemsmp.client.screens;

@EventBusSubscriber(Dist.CLIENT)
public class MaceOverlayIconOverlay {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();

		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;

		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);

		if (

		MaceOverlayIconDisplayOverlayIngameProcedure.execute(entity)

		) {

			event.getGuiGraphics().blit(ResourceLocation.parse("itemsmp:textures/screens/mace_ui_0.png"), w / 2 + -15, h - 68, 0, 0, 29, 13, 29, 13);

			event.getGuiGraphics().blit(ResourceLocation.parse("itemsmp:textures/screens/mace_ui_1.png"), w / 2 + -15, h - 68, 0, 0, 29, 13, 29, 13);

			event.getGuiGraphics().blit(ResourceLocation.parse("itemsmp:textures/screens/mace_ui_2.png"), w / 2 + -15, h - 68, 0, 0, 29, 13, 29, 13);

			event.getGuiGraphics().blit(ResourceLocation.parse("itemsmp:textures/screens/mace_ui_3.png"), w / 2 + -15, h - 68, 0, 0, 29, 13, 29, 13);

		}

		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

}