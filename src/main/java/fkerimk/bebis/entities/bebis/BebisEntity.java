package fkerimk.bebis.entities.bebis;

import com.geckolib.animation.AnimationController;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animatable.manager.AnimatableManager;
import fkerimk.bebis.base.ModEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BebisEntity extends ModEntity {

    @Override public void Setup() {

        super.Setup();

        Width = 7f / 16f;
        Height = 10f / 16f;
    }

    protected static final RawAnimation AnimIdle = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation AnimWalk = RawAnimation.begin().thenLoop("walk");

    public BebisEntity(EntityType<? extends Animal> type, Level level) { super(type, level); }

    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(net.minecraft.world.item.Items.COOKIE); }

    @Override public void registerControllers(AnimatableManager.@NonNull ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("movement", 5, this::MovementController)); }

    protected PlayState MovementController(AnimationTest<BebisEntity> state) {

        if (state.isMoving())
            return state.setAndContinue(AnimWalk);

        return state.setAndContinue(AnimIdle);
    }
}