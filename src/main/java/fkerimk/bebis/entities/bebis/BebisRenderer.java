package fkerimk.bebis.entities.bebis;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import com.geckolib.renderer.base.BoneSnapshots;
import org.jspecify.annotations.NonNull;

public class BebisRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BebisEntity, @NonNull R> {

    public BebisRenderer(EntityRendererProvider.Context context) {
        super(context, new BebisModel());
    }

    @Override public void adjustModelBonesForRender(@NonNull RenderPassInfo<@NonNull R> renderPassInfo, @NonNull BoneSnapshots snapshots) {

        super.adjustModelBonesForRender(renderPassInfo, snapshots);

        R renderState = renderPassInfo.renderState();

        if (renderState.isBaby) {

            snapshots.ifPresent("root", root -> {

                var scale = renderState.ageScale;

                root.setScaleX(scale);
                root.setScaleY(scale);
                root.setScaleZ(scale);
            });
        }

        snapshots.ifPresent("head", head -> {

            float clampedXRot = Mth.clamp(renderState.xRot, -25f, 25f);
            float clampedYRot = Mth.clamp(renderState.yRot, -25f, 25f);

            head.setRotX(-clampedXRot * Mth.DEG_TO_RAD);
            head.setRotY(-clampedYRot * Mth.DEG_TO_RAD);
        });
    }
}