package net.kpr.makers_crysknife_mod.worldgen.biome;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.kpr.makers_crysknife_mod.worldgen.feature.ModPlacedFeatures;

public class ModBiomes {
    public static final ResourceKey<Biome> TANZEROUFT =
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "tanzerouft"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(TANZEROUFT, tanzerouftBiome(context));
    }

    private static Biome tanzerouftBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.creatureGenerationProbability(0.2F);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 2, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 95, 4, 4));

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generationBuilder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);

        BiomeDefaultFeatures.addDefaultMonsterRoom(generationBuilder);
        BiomeDefaultFeatures.addFossilDecoration(generationBuilder);
        BiomeDefaultFeatures.addDefaultOres(generationBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationBuilder);
        BiomeDefaultFeatures.addDesertVegetation(generationBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(generationBuilder);

        generationBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                placedFeatures.getOrThrow(ModPlacedFeatures.SPICE_DRUM_SAND_ORE_PLACED));

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.5F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(12039807)
                        .waterFogColor(12039807)
                        .fogColor(16053503)
                        .skyColor(14474751)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(music)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    private static int calculateSkyColor(float temperature) {
        float f = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }


}
