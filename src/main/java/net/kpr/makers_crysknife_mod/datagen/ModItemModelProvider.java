package net.kpr.makers_crysknife_mod.datagen;

import net.kpr.makers_crysknife_mod.MakersCrysknifeMod;
import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MakersCrysknifeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() { handheldItem(ModItems.CRYSKNIFE); }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        String path = item.getId().getPath();
        return withExistingParent(path, ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MakersCrysknifeMod.MOD_ID, "item/" + path));
    }

}
