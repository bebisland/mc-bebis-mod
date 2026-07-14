package fkerimk.bebis.mixin;

import fkerimk.bebis.ModFluids;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class) public abstract class MilkBucketMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void bebis$placeMilk(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {

        if ((Object) this != Items.MILK_BUCKET) return;

        var stack = player.getItemInHand(hand);

        var from = player.getEyePosition();
        var to = from.add(player.calculateViewVector(player.getXRot(), player.getYRot()).scale(player.blockInteractionRange()));

        var hitResult = level.clip(new ClipContext(from, to, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        if (hitResult.getType() != HitResult.Type.BLOCK) return;

        var pos = hitResult.getBlockPos();
        var direction = hitResult.getDirection();
        var placePos = pos.relative(direction);

        if (!level.mayInteract(player, pos) || !player.mayUseItemAt(placePos, direction, stack)) return;

        var clicked = level.getBlockState(pos);
        var targetPos = clicked.getBlock() instanceof LiquidBlockContainer ? pos : placePos;
        var targetState = level.getBlockState(targetPos);

        var milk = ModFluids.Milk.Source;

        boolean canPlace = targetState.canBeReplaced(milk)
            || (targetState.getBlock() instanceof LiquidBlockContainer container
            && container.canPlaceLiquid(player, level, targetPos, targetState, milk));

        if (!canPlace) return;

        if (!level.setBlock(targetPos, milk.defaultFluidState().createLegacyBlock(), 11)) return;

        player.awardStat(Stats.ITEM_USED.get((Item) (Object) this));

        if (player instanceof ServerPlayer serverPlayer)
            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, targetPos, stack);

        var emptyResult = !player.hasInfiniteMaterials() ? new ItemStack(Items.BUCKET) : stack;
        var result = ItemUtils.createFilledResult(stack, player, emptyResult);

        cir.setReturnValue(InteractionResult.SUCCESS.heldItemTransformedTo(result));
    }
}