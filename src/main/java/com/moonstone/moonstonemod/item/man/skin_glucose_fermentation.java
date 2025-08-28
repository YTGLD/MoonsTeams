package com.moonstone.moonstonemod.item.man;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.ExtendEntityLiving;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 肌糖原酵解
 * <p>
 * 	召唤的僵尸被杀死时
 * <p>
 * 	攻击者会受到基于僵尸50%最大生命值和护甲总和的伤害
 */
public class skin_glucose_fermentation  extends ManDNA implements Iplague {
    public skin_glucose_fermentation() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }
    @Override
    public @Nullable List<Item> getDrug() {
        return List.of();
    }
    public static void dieZombie(LivingDeathEvent event, int size){
        if (event.getEntity() instanceof ExtendEntityLiving moonTamableAnimal){
            if (moonTamableAnimal.getOwner() instanceof Player player){
                if (Handler.hascurio(player, Items.skin_glucose_fermentation.get())){
                    float a =  size /100f;


                    float health = (float) moonTamableAnimal.getAttributeValue(Attributes.MAX_HEALTH)*a;
                    float armor = (float) moonTamableAnimal.getAttributeValue(Attributes.ARMOR)*a;

                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (!living.is(player)) {
                            living.hurt(living.damageSources().dryOut(), health + armor);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.skin_glucose_fermentation.tool.string").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.skin_glucose_fermentation.tool.string.1").withStyle(ChatFormatting.GOLD));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
