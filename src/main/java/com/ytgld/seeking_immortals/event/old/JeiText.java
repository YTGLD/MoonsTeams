package com.ytgld.seeking_immortals.event.old;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.Items;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


@JeiPlugin
public class JeiText implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(SeekingImmortalsMod.MODID, "jei_plugin");
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }



    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        {

            registration.addIngredientInfo(new ItemStack(Items.defend_against_runestone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.defend_against_runestone").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base"))));
            registration.addIngredientInfo(new ItemStack(Items.revive_runestone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.revive_runestone").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base"))));
            registration.addIngredientInfo(new ItemStack(Items.strengthen_runestone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.strengthen_runestone").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base"))));


            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_heart.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_heart").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_eye.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_eye").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_red.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_red").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));
            registration.addIngredientInfo(new ItemStack(Items.tricky_puppets.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.tricky_puppets").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));

            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_brain.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_stone_brain").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_meet.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_stone_meet").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_virus.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_stone_virus").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));
            registration.addIngredientInfo(new ItemStack(Items.end_bone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.end_bone").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_stone"))));

            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_card.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_card").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_mysterious.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_mysterious").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_orb.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_orb").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));
            registration.addIngredientInfo(new ItemStack(Items.candle.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.candle").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_reversal"))));

            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_redemption_deception.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_redemption_deception").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_redemption"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_redemption_degenerate.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_redemption_degenerate").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_redemption"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_redemption_down_and_out.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_redemption_down_and_out").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_redemption"))));
            registration.addIngredientInfo(new ItemStack(Items.hypocritical_self_esteem.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.hypocritical_self_esteem").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_redemption"))));

            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_fool_betray.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_fool_betray").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_fool"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_fool_bone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_fool_bone").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_fool"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_fool_soul.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_fool_soul").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_fool"))));
            registration.addIngredientInfo(new ItemStack(Items.apple.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.apple").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_fool"))));


            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_insight_collapse.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_insight_collapse").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_insight"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_insight_drug.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_insight_drug").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_insight"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_insight_insane.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_insight_insane").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_insight"))));
            registration.addIngredientInfo(new ItemStack(Items.ring.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.ring").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_insight"))));


            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_start_egg.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_start_egg").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_start"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_start_pod.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_start_pod").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_start"))));
            registration.addIngredientInfo(new ItemStack(Items.nightmare_base_start_power.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_start_power").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_start"))));
            registration.addIngredientInfo(new ItemStack(Items.wolf.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.wolf").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.nightmare_base_start"))));

            registration.addIngredientInfo(new ItemStack(Items.falling_immortals.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.falling_immortals").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all")
                    .append(Component.translatable("item.moonstone.apple"))));


        }
    }
}
