package net.kpr.makers_crysknife_mod.item;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import java.util.List;

public class ModToolTiers {
    public static final Tier CRYSKNIFE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1670, 5f, 4f, 16,
                    ModTags.BLocks.INCORRECT_FOR_CRYSKNIFE, () -> Ingredient.EMPTY),
            ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "crysknife"),
            List.of(Tiers.DIAMOND), List.of());
}
