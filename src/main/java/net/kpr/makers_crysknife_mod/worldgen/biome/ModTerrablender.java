package net.kpr.makers_crysknife_mod.worldgen.biome;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import net.kpr.makers_crysknife_mod.worldgen.biome.surface.ModSurfaceRules;

public class ModTerrablender {
    public static void registerBiomes() {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "overworld");
        Regions.register(new ModOverworldRegion(location, 10));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
                MakersCrysknifeMod.MOD_ID, ModSurfaceRules.makeRules());
    }
}
