package net.kpr.makers_crysknife_mod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties SPICE_RABBIT_STEW = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 1200, 0), 0.05F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 1200, 0), 0.05F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 1200, 0), 0.05F)
            .build();

    public static final FoodProperties MELANGE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.5F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 5), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 1800, 0), 1.0F)
            .build();
}
