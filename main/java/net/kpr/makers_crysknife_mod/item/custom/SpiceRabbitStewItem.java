package net.kpr.makers_crysknife_mod.item.custom;

import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SpiceRabbitStewItem extends Item {

    public SpiceRabbitStewItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        ItemStack bowl = new ItemStack(Items.BOWL);
        if (entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                if (result.isEmpty()) {
                    return bowl;
                }
                if (!player.addItem(bowl.copy())) {
                    player.drop(bowl.copy(), false);
                }
            }
        } else if (!level.isClientSide) {
            if (result.isEmpty()) {
                return bowl;
            }
            entity.spawnAtLocation(bowl.copy());
        }

        return result;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player) {
        super.onCraftedBy(stack, level, player);

        if (!level.isClientSide && !player.getAbilities().instabuild) {
            ItemStack capsule = new ItemStack(ModItems.CAPSULE.get());
            if (!player.addItem(capsule)) {
                player.drop(capsule, false);
            }
        }
    }
}

