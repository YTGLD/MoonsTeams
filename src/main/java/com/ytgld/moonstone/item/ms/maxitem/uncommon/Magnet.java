package com.ytgld.moonstone.item.ms.maxitem.uncommon;

import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Magnet extends UnCommonItem {

    public Magnet(Properties properties) {
        super(properties);
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
            if (item.tickCount>35) {
                Vec3 direction = playerPos.subtract(item.position());
                direction = direction.normalize().scale(0.1);
                item.setDeltaMovement(item.getDeltaMovement().add(direction));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.magnet.tool.string").withStyle(ChatFormatting.GOLD));
    }
}
