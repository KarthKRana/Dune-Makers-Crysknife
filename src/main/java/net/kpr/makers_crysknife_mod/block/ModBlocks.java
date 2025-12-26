package net.kpr.makers_crysknife_mod.block;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.block.custom.SpiceRefineryBlock;
import net.kpr.makers_crysknife_mod.block.custom.DrumSandBlock;
import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MakersCrysknifeMod.MOD_ID);

    public static final RegistryObject<Block> DRUM_SAND = registerBlock("drum_sand",
            () -> new DrumSandBlock(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SAND)));

    public static final RegistryObject<Block> SPICE_DRUM_SAND = registerBlock("spice_drum_sand",
            () -> new DrumSandBlock(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SAND)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static final RegistryObject<Block> SPICE_REFINERY = registerBlock("spice_refinery",
            () -> new SpiceRefineryBlock(BlockBehaviour.Properties
                    .of().strength(0.5F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .noOcclusion())
            );


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
