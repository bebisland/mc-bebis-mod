package fkerimk.bebis.entity;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.RawAnimation;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BebisEntity extends Animal implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("walk");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("idle");

    public BebisEntity(EntityType<? extends Animal> type, Level level) { super(type, level); }

    @Override protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.COOKIE), false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, net.minecraft.world.entity.player.Player.class, 8.0f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1, 30));
    }

    public static AttributeSupplier.Builder createAttributes() {

        return Animal.createMobAttributes()
            .add(Attributes.MOVEMENT_SPEED, 0.2)
            .add(Attributes.MAX_HEALTH, 10.0)
            .add(Attributes.TEMPT_RANGE, 16);
    }

    @Override public boolean isFood(ItemStack itemStack) { return itemStack.is(net.minecraft.world.item.Items.COOKIE); }

    @Nullable @Override public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob partner) {

        return fkerimk.bebis.Entities.BEBIS.create(level, null, this.blockPosition(),
            net.minecraft.world.entity.EntitySpawnReason.BREEDING, false, false);
    }

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(new AnimationController<>("movement", 5, this::movementController));
    }

    protected PlayState movementController(AnimationTest<BebisEntity> state) {

        if (state.isMoving())
            return state.setAndContinue(WALK_ANIM);

        return state.setAndContinue(IDLE_ANIM);
    }

    @NonNull @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return this.geoCache; }

    @NonNull @Override protected PathNavigation createNavigation(@NonNull Level level) {

        GroundPathNavigation navigation = (GroundPathNavigation) super.createNavigation(level);
        navigation.setCanOpenDoors(true);

        return navigation;
    }
}