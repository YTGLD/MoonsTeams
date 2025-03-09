package com.moonstone.moonstonemod.item.maxitem.rage;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_stone  extends Item implements ICurioItem ,RAGE{
    public rage_stone() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
        float range = 8;
        List<ItemEntity> itemEntities =
                slotContext.entity().level().getEntitiesOfClass(ItemEntity.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
        for (ItemEntity item : itemEntities){
            if (item.getItem().is(Blocks.NETHERRACK.asItem())){
                item.level().addFreshEntity(new ExperienceOrb(item.level(),item.getX(),item.getY(),item.getZ(),item.getItem().getCount()));
                item.discard();
            }

        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.rage_stone.tool.string").withStyle(ChatFormatting.GOLD));
    }
}

