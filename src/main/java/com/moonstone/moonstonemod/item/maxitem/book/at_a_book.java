package com.moonstone.moonstonemod.item.maxitem.book;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.BookSkill;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class at_a_book  extends BookSkill implements Die {
    public static final int maxLvl = 3000;
    public static final String exp = "BookSize";
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player ) {
            if (stack.getTag() != null) {

                if (!player.level().isClientSide && player.tickCount % 20 == 1) {
                    stack.setDamageValue(stack.getDamageValue() + 1);
                }

                String ss = "small";
                int l = stack.getTag().getInt(exp);
                if (l>300){
                    for (int i = 0; i < maxLvl; i+=300) {
                        int to = i / 10;
                        if ( to != 0) {
                            l = (l / to);
                            break;
                        }
                    }
                    if (l>10&&l<=20){
                        l -= 10;
                    }else if (l>20&&l<=30){
                        l -= 20;
                    }else if (l>30&&l<=40){
                        l -= 30;
                    }else if (l>40&&l<=50){
                        l -= 40;
                    }else if (l>50&&l<=60){
                        l -= 50;
                    }else if (l>60&&l<=70){
                        l -= 60;
                    }else if (l>70&&l<=80){
                        l -= 70;
                    }else if (l>80&&l<=90){
                        l -= 80;
                    }else if (l>90&&l<=100){
                        l -= 90;
                    }
                    stack.getTag().putInt(ss, l);
                }else {
                    stack.getTag().putInt(ss, l / 30);
                }
                if (stack.getTag().getInt(exp) < maxLvl) {
                    if (!player.level().isClientSide && player.tickCount % 40 == 1) {
                        stack.getTag().putInt(exp, stack.getTag().getInt(exp) + 1);
                    }
                }
            } else {
                stack.getOrCreateTag();
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>  multimap = HashMultimap.create();
        if (stack.getTag()!=null){
            /*
              每点经验值都是等于3000/300 == 30；
             */
            float l = stack.getTag().getInt(exp)/150f;


            //1.1*30 == 33 , 33 /100 * 100 == 33%
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    uuid,
                    "s",
                    (1.1*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                    uuid,
                    "s",
                    (1.2*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    uuid,
                    "s",
                    (0.87*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    uuid,
                    "s",
                    (0.685*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ARMOR, new AttributeModifier(
                    uuid,
                    "s",
                    (0.5*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.heal.get(), new AttributeModifier(
                    uuid,
                    "s",
                    (0.66*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.cit.get(), new AttributeModifier(
                    uuid,
                    "s",
                    (0.44*(l))/100f,
                    AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        super.appendHoverText(pStack, p_41422_, pTooltipComponents, p_41424_);
        if (pStack.getTag()!=null) {
            int expValue = pStack.getTag().getInt(exp);

            String baseKey = "item.at_a_book.tool.string.lvl.";
            int l  = 1;
            if (expValue > 300){
                for (int i = 0; i < expValue; i += 300) {
                    l++;
                }
            }
            pTooltipComponents.add(Component.translatable(baseKey + l).withStyle(ChatFormatting.LIGHT_PURPLE));
            addNme(pStack,pTooltipComponents,"");
        }
    }
    private void addNme(ItemStack pStack, List<Component> pTooltipComponents, String translatable) {
        if (pStack.getTag()!=null) {
            //0~3000
            String ss = "small";

            int displayValue = pStack.getTag().getInt(ss);

            // 添加到tooltip中
            pTooltipComponents.add(Component.translatable(translatable)
                    .append(String.valueOf(displayValue))
                    .append(Component.translatable("sword.moonstone.lvl"))
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
