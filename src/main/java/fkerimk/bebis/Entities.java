package fkerimk.bebis;

import fkerimk.bebis.entity.BebisEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class Entities {

    public static final EntityType<BebisEntity> BEBIS = register(
        "bebis", BebisEntity::new,
        MobCategory.CREATURE,
        1f/16f*7f, 1f/16f*10f,
        BebisEntity.createAttributes()
    );

    private static <T extends LivingEntity> EntityType<T> register(
        String name,
        EntityType.EntityFactory<T> factory,
        MobCategory category,
        float width,
        float height,
        AttributeSupplier.Builder attributes
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(Main.MOD_ID, name);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);

        EntityType<T> type = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            key,
            EntityType.Builder.of(factory, category)
                .sized(width, height)
                .build(key)
        );

        FabricDefaultAttributeRegistry.register(type, attributes);

        return type;
    }

    public static void registerModEntities() {
        Main.LOGGER.info("Registering entities");
    }
}