package net.kpr.makers_crysknife_mod.worldgen.biome.surface;

import net.kpr.makers_crysknife_mod.block.ModBlocks;
import net.kpr.makers_crysknife_mod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public final class ModSurfaceRules {

    private ModSurfaceRules() {}

    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TANZEROUFT), drumSandSurface())
        );
    }

    private static SurfaceRules.RuleSource drumSandSurface() {
        BlockState drumSand = ModBlocks.DRUM_SAND.get().defaultBlockState();
        BlockState vanillaSand = Blocks.SAND.defaultBlockState();
        BlockState vanillaSandstone = Blocks.SANDSTONE.defaultBlockState();

        SurfaceRules.RuleSource drumSandRule = SurfaceRules.state(drumSand);
        SurfaceRules.RuleSource sandRule = SurfaceRules.state(vanillaSand);
        SurfaceRules.RuleSource sandstoneRule = SurfaceRules.state(vanillaSandstone);

        SurfaceRules.RuleSource topLayer = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, -1.0D, -0.7D), sandRule),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, 0.65D, 1.0D), sandstoneRule),
                drumSandRule
        );

        SurfaceRules.RuleSource subSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -1.0D, -0.55D), sandRule),
                drumSandRule
        );

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, topLayer),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, subSurface),
                sandstoneRule
        );
    }
}
