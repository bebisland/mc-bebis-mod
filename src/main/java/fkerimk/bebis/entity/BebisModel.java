package fkerimk.bebis.entity;

import fkerimk.bebis.Main;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import org.jspecify.annotations.NonNull;

public class BebisModel extends GeoModel<BebisEntity> {

    @NonNull @Override public Identifier getModelResource(@NonNull GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(Main.Id, "bebis"); }

    @NonNull @Override public Identifier getTextureResource(@NonNull GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(Main.Id, "textures/entity/bebis.png"); }

    @NonNull @Override public Identifier getAnimationResource(@NonNull BebisEntity animatable) {
        return Identifier.fromNamespaceAndPath(Main.Id, "bebis"); }
}