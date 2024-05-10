package com.moonstone.moonstonemod.Item.NaNoDoom;

import com.moonstone.moonstonemod.Item.MoonStoneItem.IDoom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.UUID;

public class thefruit extends Item implements IDoom {
    public thefruit() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("thefruit", ChatFormatting.AQUA)).food(
                new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(10).build()));

        MinecraftForge.EVENT_BUS.addListener(this::p);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    private void p(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent((handler)->{
                for (AttributeModifier attributeModifier : handler.getModifiers().values()){
                    if (attributeModifier.equals(this.attributeModifier())){
                        player.getPersistentData().putBoolean(thefruit,true);
                    }
                }
            });

            if (player.getPersistentData().getBoolean(thefruit)){
                if (player.hasEffect(MobEffects.GLOWING)) {
                    player.removeEffect(MobEffects.GLOWING);
                }
            }

        }
    }
    private final UUID uuid = UUID.fromString("8fffffe9-1697-3450-bdc4-1834288fdb99");
    public static final String thefruit = "TheFruit";
    private AttributeModifier attributeModifier (){
        return new AttributeModifier(uuid, "thefruit", 0, AttributeModifier.Operation.ADDITION);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        ItemStack stack = super.finishUsingItem(s, level, living);
        if (living instanceof Player player) {

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
                stacks.addPermanentModifier(this.attributeModifier());
            }));
        }
        return stack;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.thefruit.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thefruit.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thefruit.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}

