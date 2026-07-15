package fkerimk.bebis;

import fkerimk.bebis.entities.ModEntities;
import fkerimk.bebis.fluids.ModFluids;
import net.fabricmc.api.ClientModInitializer;

public class Client implements ClientModInitializer {

    @Override public void onInitializeClient() {

        ModFluids.RegisterClient();
        ModEntities.RegisterClient();
    }
}