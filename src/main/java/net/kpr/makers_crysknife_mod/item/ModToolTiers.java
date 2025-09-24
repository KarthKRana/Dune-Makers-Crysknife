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

    /**
     * <p>Durability: 1070</p>
     */
    public static final Tier CRYSKNIFE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1070, 4.5f, 2.5f, 16,
                    ModTags.Blocks.INCORRECT_FOR_CRYSKNIFE, () -> Ingredient.EMPTY),
            ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "crysknife"),
            List.of(Tiers.DIAMOND), List.of());

    /**
     * <p>Durability: 1670</p>
     */
    public static final Tier FEDAYKIN_CRYSKNIFE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1670, 5f, 3.75f, 17,
                    ModTags.Blocks.INCORRECT_FOR_CRYSKNIFE, () -> Ingredient.EMPTY),
            ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "fedaykin_crysknife"),
            List.of(Tiers.NETHERITE), List.of());

}
