package net.kpr.makers_crysknife_mod.block.custom;

import net.kpr.makers_crysknife_mod.block.entity.ModBlockEntities;
import net.kpr.makers_crysknife_mod.block.entity.SpiceRefineryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiceRefineryBlock extends BaseEntityBlock {

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SpiceRefineryBlockEntity) {
                SpiceRefineryBlockEntity.dropInventoryContents(level, pos, blockEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            Block.dropResources(state, level, pos, null, player, player.getItemInHand(InteractionHand.MAIN_HAND));
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    public SpiceRefineryBlock(Properties pProperties) {
        super(pProperties);
        System.out.println("SpiceRefineryBlock constructor called - Block registered!");
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) { return RenderShape.MODEL; }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
            System.out.println("SpiceRefineryBlock use() called");
            if (!pLevel.isClientSide()) {
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if(entity instanceof SpiceRefineryBlockEntity) {
                    //Will not work in 1.20.2+
                    NetworkHooks.openScreen(((ServerPlayer)pPlayer), (SpiceRefineryBlockEntity)entity, pPos);
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    //Custom tooltip
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.makers_crysknife_mod.spice_refinery.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SpiceRefineryBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.SPICE_REFINERY_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) ->
                        pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
