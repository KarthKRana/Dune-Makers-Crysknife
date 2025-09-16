package net.kpr.makers_crysknife_mod.util;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class BLocks {
        public static final TagKey<Block> INCORRECT_FOR_CRYSKNIFE =
                TagKey.create(Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID,
                                "incorrect_for_crysknife"));
    }
}
