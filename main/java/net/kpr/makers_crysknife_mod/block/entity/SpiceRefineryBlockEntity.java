package net.kpr.makers_crysknife_mod.block.entity;

import net.kpr.makers_crysknife_mod.block.ModBlocks;
import net.kpr.makers_crysknife_mod.block.screen.SpiceRefineryMenu;
import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpiceRefineryBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3);
    private static final int SAND_INPUT = 0;
    private static final int MELANGE_OUTPUT = 1;
    private static final int CAPSULE_INPUT = 2;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

    public SpiceRefineryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SPICE_REFINERY_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch(pIndex) {
                    case 0 -> SpiceRefineryBlockEntity.this.progress;
                    case 1 -> SpiceRefineryBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex) {
                    case 0 -> SpiceRefineryBlockEntity.this.progress = pValue;
                    case 1 -> SpiceRefineryBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public static void dropInventoryContents(Level level, BlockPos pos, BlockEntity blockEntity) {
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack stack = handler.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    ItemEntity itemEntity = new ItemEntity(level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            stack.copy());
                    level.addFreshEntity(itemEntity);
                }
            }
        });
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.makerscrysknifemod.spice_refinery");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SpiceRefineryMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("spice_refinery.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("spice_refinery.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockState below = pLevel.getBlockState(pPos.below());
        boolean hasRedstonePower = below.isSignalSource();
        if(!hasRedstonePower) {
            resetProgress();
            return;
        }
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.MELANGE.get(), 1);
        this.itemHandler.extractItem(SAND_INPUT, 1, false);
        this.itemHandler.extractItem(CAPSULE_INPUT, 1, false);
        this.itemHandler.setStackInSlot(MELANGE_OUTPUT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(MELANGE_OUTPUT).getCount() + result.getCount()));

    }

    private void resetProgress() { progress = 0; }

    private boolean hasProgressFinished() { return progress >= maxProgress; }

    private void increaseCraftingProgress() { progress++; }

    private boolean hasRecipe() {
        boolean hasSpiceDrumSand = this.itemHandler.getStackInSlot(SAND_INPUT).getItem() == ModBlocks.SPICE_DRUM_SAND.get().asItem();
        boolean hasCapsule = this.itemHandler.getStackInSlot(CAPSULE_INPUT).getItem() == ModItems.CAPSULE.get();
        ItemStack result = new ItemStack(ModItems.MELANGE.get());
        return hasSpiceDrumSand && hasCapsule && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(MELANGE_OUTPUT).isEmpty() || this.itemHandler.getStackInSlot(MELANGE_OUTPUT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(MELANGE_OUTPUT).getCount() + count <= this.itemHandler.getStackInSlot(MELANGE_OUTPUT).getMaxStackSize();
    }
}
