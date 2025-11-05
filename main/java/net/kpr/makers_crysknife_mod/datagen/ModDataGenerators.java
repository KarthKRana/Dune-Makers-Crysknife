package net.kpr.makers_crysknife_mod.datagen;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MakersCrysknifeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        // 👇 Hook in your loot table provider
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output));
        
        generator.addProvider(event.includeServer(), new ModWorldGenProvider(output, lookupProvider));
    }
}

