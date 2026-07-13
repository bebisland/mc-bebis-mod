package fkerimk.bebis.base;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public abstract class Entity extends Animal implements GeoEntity {

    public MobCategory Category;
    public float MaxHealth;
    public float Width;
    public float Height;
    public float MovementSpeed;
    public float TemptRange;

    public void Setup(){

        Category = MobCategory.CREATURE;
        MaxHealth = 10f;
        Width = 1f;
        Height = 1f;
        MovementSpeed = 0.2f;
        TemptRange = 8f;
    }

    protected Entity(EntityType<? extends Animal> type, Level level) { super(type, level); }

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    @Override protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.COOKIE), false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, net.minecraft.world.entity.player.Player.class, 8.0f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1, 30));
    }

    public AttributeSupplier.Builder CreateAttributes() {

        return PathfinderMob.createMobAttributes()

            .add(Attributes.MOVEMENT_SPEED, MovementSpeed)
            .add(Attributes.MAX_HEALTH, MaxHealth)
            .add(Attributes.TEMPT_RANGE, TemptRange);
    }

    @NonNull @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return this.geoCache; }

    @NonNull @Override protected PathNavigation createNavigation(@NonNull Level level) {

        GroundPathNavigation navigation = (GroundPathNavigation) super.createNavigation(level);
        navigation.setCanOpenDoors(true);
        return navigation;
    }

    @Override public abstract void registerControllers(AnimatableManager.@NonNull ControllerRegistrar controllers);

    @Nullable @Override public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob partner) {

        return (AgeableMob)this.getType().create(level, null, this.blockPosition(),
            net.minecraft.world.entity.EntitySpawnReason.BREEDING, false, false);
    }
}