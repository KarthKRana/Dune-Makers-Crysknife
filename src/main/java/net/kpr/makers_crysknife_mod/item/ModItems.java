package net.kpr.makers_crysknife_mod.item;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MakersCrysknifeMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Base Atk Dmg: 7, Atk Speed: 3
    public static final RegistryObject<Item> CRYSKNIFE = ITEMS.register("crysknife",
            () -> new SwordItem(ModToolTiers.CRYSKNIFE, 2, -1f,
                    new Item.Properties().rarity(Rarity.RARE)));
}
