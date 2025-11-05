package net.kpr.makers_crysknife_mod.item;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.item.custom.SpiceRabbitStewItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, MakersCrysknifeMod.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
    /**
     * <p>Crysknife: <b> 5.5 Attack Damage </b> & <b> 3.5 Attack Speed </b></p>
     */
    public static final RegistryObject<Item> CRYSKNIFE = ITEMS.register("crysknife",
            () -> new SwordItem(ModToolTiers.CRYSKNIFE, 2, -0.5f,
                    new Item.Properties().rarity(Rarity.RARE)));
    /**
     * <p>Fedaykin Crysknife: <b> 7.75 Attack Damage </b> & <b> 2.25 Attack Speed </b></p>
     */
    public static final RegistryObject<Item> FEDAYKIN_CRYSKNIFE = ITEMS.register("fedaykin_crysknife",
            () -> new SwordItem(ModToolTiers.FEDAYKIN_CRYSKNIFE, 3, -1.75f,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CAPSULE = ITEMS.register("capsule",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> MELANGE = ITEMS.register("melange",
            () -> new Item(new Item.Properties().stacksTo(16).food(ModFoods.MELANGE)
                    .craftRemainder(ModItems.CAPSULE.get()).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SPICE_FABRIC = ITEMS.register("spice_fabric",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> SPICE_RABBIT_STEW = ITEMS.register("spice_rabbit_stew",
            () -> new SpiceRabbitStewItem(new Item.Properties().stacksTo(1).food(ModFoods.SPICE_RABBIT_STEW)));
}
