package net.kpr.makers_crysknife_mod.advancement.criteria;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class ObtainSpiceDrumSandTrigger extends SimpleCriterionTrigger<ObtainSpiceDrumSandTrigger.Instance> {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath("makerscrysknifemod", "obtain_spice_drum_sand");

    @Override
    public @NotNull ResourceLocation getId() { return ID; }

    @Override
    protected @NotNull Instance createInstance(@NotNull JsonObject json, @NotNull ContextAwarePredicate player, @NotNull DeserializationContext context) {
        return new Instance(player);
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(ContextAwarePredicate player) {
            super(ID, player);
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            return super.serializeToJson(context);
        }
    }
}
