package net.kpr.makers_crysknife_mod.event;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MakersCrysknifeMod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModifier {

    private static final ResourceLocation DESERT_PYRAMID =
            ResourceLocation.fromNamespaceAndPath("minecraft",
                    "chests/desert_pyramid");

    @SubscribeEvent
    public static void onLootTableModify(LootTableLoadEvent event) {
        if (event.getName().equals(DESERT_PYRAMID)) {
            LootPool pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.CRYSKNIFE.get())
                            .when(LootItemRandomChanceCondition
                                    .randomChance(1.0F / 32.0F)))
                    // 1/32 chance of a Crysknife appearing in a desert temple chest
                    .build();
            event.getTable().addPool(pool);
        }
    }
}

