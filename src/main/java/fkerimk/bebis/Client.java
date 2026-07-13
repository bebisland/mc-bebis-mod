package fkerimk.bebis;

import fkerimk.bebis.entity.BebisRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class Client implements ClientModInitializer {

    @Override public void onInitializeClient() {

        EntityRenderers.register(ModEntities.Bebis, BebisRenderer::new);
    }
}