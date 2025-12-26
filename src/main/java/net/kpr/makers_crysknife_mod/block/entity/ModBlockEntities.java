package net.kpr.makers_crysknife_mod.block.entity;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MakersCrysknifeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<SpiceRefineryBlockEntity>> SPICE_REFINERY_BE =
            BLOCK_ENTITIES.register("spice_refinery_be", () ->
                    BlockEntityType.Builder.of(SpiceRefineryBlockEntity::new,
                            ModBlocks.SPICE_REFINERY.get()).build(null));

    public static void register(IEventBus eventBus) {BLOCK_ENTITIES.register(eventBus);}
}
