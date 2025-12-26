package net.kpr.makers_crysknife_mod;

import net.kpr.makers_crysknife_mod.advancement.criteria.ObtainMelangeTrigger;
import net.kpr.makers_crysknife_mod.advancement.criteria.ObtainSpiceDrumSandTrigger;
import net.kpr.makers_crysknife_mod.block.ModBlocks;
import net.kpr.makers_crysknife_mod.block.entity.ModBlockEntities;
import net.kpr.makers_crysknife_mod.block.screen.ModMenuTypes;
import net.kpr.makers_crysknife_mod.block.screen.SpiceRefineryScreen;
import net.kpr.makers_crysknife_mod.item.ModCreativeModTabs;
import net.kpr.makers_crysknife_mod.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import net.kpr.makers_crysknife_mod.worldgen.biome.ModTerrablender;

@Mod(MakersCrysknifeMod.MOD_ID)
public class MakersCrysknifeMod {

    public static final String MOD_ID = "makerscrysknifemod";
    public static final ObtainSpiceDrumSandTrigger OBTAIN_SPICE_DRUM_SAND = new ObtainSpiceDrumSandTrigger();
    public static final ObtainMelangeTrigger OBTAIN_MELANGE = new ObtainMelangeTrigger();

    public MakersCrysknifeMod(@NotNull FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        CriteriaTriggers.register(OBTAIN_SPICE_DRUM_SAND);
        CriteriaTriggers.register(OBTAIN_MELANGE);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModTerrablender::registerBiomes);
    }

    private void addCreative(@NotNull BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.CRYSKNIFE);
            event.accept(ModItems.FEDAYKIN_CRYSKNIFE);
        } else if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CAPSULE);
            event.accept(ModItems.MELANGE);
            //event.accept(ModItems.SPICE_FABRIC);
        } else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.DRUM_SAND);
            event.accept(ModBlocks.SPICE_DRUM_SAND);
        } else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.SPICE_REFINERY);
        } else if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.SPICE_RABBIT_STEW);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ItemStack stack = event.getItem().getItem();
        if (stack.getItem() == ModBlocks.SPICE_DRUM_SAND.get().asItem()) {
            OBTAIN_SPICE_DRUM_SAND.trigger(player);
        }
        if (stack.getItem() == ModItems.MELANGE.get()) {
            OBTAIN_MELANGE.trigger(player);
        }
    }

    private static final java.util.Map<Player, Boolean> previousOnGround = new java.util.WeakHashMap<>();
    private static final java.util.Map<Player, Integer> lastSoundTick = new java.util.WeakHashMap<>();
    private static final int SOUND_COOLDOWN_TICKS = 5;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        boolean wasOnGround = previousOnGround.getOrDefault(player, true);
        boolean isOnGround = player.onGround();

        if (!wasOnGround && isOnGround) {
            BlockPos pos = BlockPos.containing(player.getX(), player.getY() - 0.1, player.getZ());
            BlockState state = player.level().getBlockState(pos);

            if (state.is(ModBlocks.DRUM_SAND.get()) || state.is(ModBlocks.SPICE_DRUM_SAND.get())) {
                int currentTick = (int) player.level().getGameTime();
                int lastTick = lastSoundTick.getOrDefault(player, -SOUND_COOLDOWN_TICKS);

                if (currentTick - lastTick >= SOUND_COOLDOWN_TICKS) {
                    player.level().playSound(null, pos, SoundEvents.COW_STEP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    lastSoundTick.put(player, currentTick);
                }
            }
        }
        previousOnGround.put(player, isOnGround);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModMenuTypes.SPICE_REFINERY_MENU.get(), SpiceRefineryScreen::new);
            });
        }
    }


}
