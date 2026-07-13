package fkerimk.bebis;

import fkerimk.bebis.entity.BebisRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class Client implements ClientModInitializer {

    @Override public void onInitializeClient() {

        EntityRendererRegistry.register(Entities.BEBIS, BebisRenderer::new);
    }
}