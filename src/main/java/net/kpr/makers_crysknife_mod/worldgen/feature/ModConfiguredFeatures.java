package net.kpr.makers_crysknife_mod.worldgen.feature;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public final class ModConfiguredFeatures {

    private ModConfiguredFeatures() {}

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPICE_DRUM_SAND_ORE = key("spice_drum_sand");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest drumSandRule = new BlockMatchTest(ModBlocks.DRUM_SAND.get());

        List<OreConfiguration.TargetBlockState> targets = List.of(
                OreConfiguration.target(drumSandRule, ModBlocks.SPICE_DRUM_SAND.get().defaultBlockState())
        );

        context.register(SPICE_DRUM_SAND_ORE, new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(targets, 8)
        ));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, path));
    }
}

