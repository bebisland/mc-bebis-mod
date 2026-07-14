package fkerimk.bebis;

import net.fabricmc.api.ClientModInitializer;

public class Client implements ClientModInitializer {

    @Override public void onInitializeClient() {

        ModFluids.RegisterClient();
        ModEntities.RegisterClient();
    }
}