package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class speed_seed extends CommonItem {
    private final UUID uuid =UUID.fromString("64b1f788-7df1-3200-9eb6-3f1908ccafaa");

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(speed(player));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

        CuriosApi
                .addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(speed(player));
        }
    }


    public Multimap<Attribute, AttributeModifier> speed(Player player){
        Multimap<Attribute, AttributeModifier> s= HashMultimap.create();
        float as = 0;
        if (player.getAttribute(Attributes.MOVEMENT_SPEED)!=null){
            //基础值：j
            //目前值：z
            //减值：as
            //假设基础速度是0.1
            //增加了100%的速度
            //那么目前速度就是0.2
            //那么减速系数就是  目前-基础 = 0.1
            //那么差值 = 0.1，只需要将差值应用到减速即可

            float j = (float) player.//获取速度
                            getAttribute(Attributes.MOVEMENT_SPEED)
                            //获取基础值
                            .getAttribute().getDefaultValue();

            float z = (float) player.//获取速度
                            getAttribute(Attributes.MOVEMENT_SPEED)
                            //获取现有值
                            .getValue();

            //防止现象发生：
                 //但是减少后的值是0.1
                 //那么目前值也是0.1
                 //基础值仍然是0.1
                 //所以 目前-基础 = 0
                 //减值是0
            if (z!= j) {
                as = z - j;

            }
            as = -as;
        }

        s.put(Attributes.MOVEMENT_SPEED, new AttributeModifier((this.uuid), MoonStoneMod.MODID + "speed_seed", as, AttributeModifier.Operation.ADDITION));
        return s;
    }
}
