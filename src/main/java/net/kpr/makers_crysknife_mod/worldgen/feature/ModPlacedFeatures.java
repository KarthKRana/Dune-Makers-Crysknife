package net.kpr.makers_crysknife_mod.worldgen.feature;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public final class ModPlacedFeatures {

    private ModPlacedFeatures() {}

    public static final ResourceKey<PlacedFeature> SPICE_DRUM_SAND_ORE_PLACED = key("spice_drum_sand_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configured = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(SPICE_DRUM_SAND_ORE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.SPICE_DRUM_SAND_ORE),
                orePlacement(CountPlacement.of(16),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(96)))
        ));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static ResourceKey<PlacedFeature> key(String path) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, path));
    }
}

