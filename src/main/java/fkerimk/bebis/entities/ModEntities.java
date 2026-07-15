package fkerimk.bebis.entities;

import fkerimk.bebis.Main;
import fkerimk.bebis.base.ModEntity;
import fkerimk.bebis.entities.bebis.BebisEntity;
import fkerimk.bebis.entities.bebis.BebisRenderer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

@SuppressWarnings({"unchecked", "DataFlowIssue", "SameParameterValue", "rawtypes"})
public class ModEntities {

    public static EntityType<BebisEntity> Bebis;

    @SuppressWarnings("SpellCheckingInspection") public static void Register() throws Exception {

        Bebis = Register("bebis", BebisEntity::new, BebisEntity.class);
    }

    public static void RegisterClient (){

        RegisterClient(Bebis, BebisRenderer::new);
    }

    private static <T extends ModEntity> EntityType<T> Register(String id, EntityType.EntityFactory<T> factory, Class<T> entityClass) throws Exception {

        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Main.Id(id));

        T dummyEntity = (T) Main.Unsafe.allocateInstance(entityClass);
        dummyEntity.Setup();

        var entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, key,EntityType.Builder.of(factory, dummyEntity.Category).sized(dummyEntity.Width, dummyEntity.Height).build(key));

        FabricDefaultAttributeRegistry.register(entityType, dummyEntity.CreateAttributes());

        return entityType;
    }

    public static void RegisterClient (EntityType entityType, EntityRendererProvider renderer) {

        EntityRenderers.register(entityType, renderer);
    }
}