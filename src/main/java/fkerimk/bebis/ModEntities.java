package fkerimk.bebis;

import fkerimk.bebis.base.Entity;
import fkerimk.bebis.entity.BebisEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

@SuppressWarnings({"unchecked", "DataFlowIssue", "SameParameterValue"})
public class ModEntities {

    public static EntityType<BebisEntity> Bebis;

    @SuppressWarnings("SpellCheckingInspection") public static void Register() throws Exception {

        Bebis = Register("bebis", BebisEntity::new, BebisEntity.class);
    }

    private static <T extends Entity> EntityType<T> Register(String id, EntityType.EntityFactory<T> factory, Class<T> entityClass) throws Exception {

        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Main.Id(id));

        T dummyEntity = (T) Main.Unsafe.allocateInstance(entityClass);
        dummyEntity.Setup();

        var entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, key,EntityType.Builder.of(factory, dummyEntity.Category).sized(dummyEntity.Width, dummyEntity.Height).build(key));

        FabricDefaultAttributeRegistry.register(entityType, dummyEntity.CreateAttributes());

        return entityType;
    }
}