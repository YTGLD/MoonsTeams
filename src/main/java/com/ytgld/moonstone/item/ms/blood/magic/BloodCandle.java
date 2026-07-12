package com.ytgld.moonstone.item.ms.blood.magic;
 import com.ytgld.moonstone.enttiy.EntityTs;
 import com.ytgld.moonstone.enttiy.OwnerBlood;
 import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
 import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
 import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
 import top.theillusivec4.curios.api.SlotContext;

 import java.util.List;

public class BloodCandle extends BloodItem {

    public BloodCandle(Properties properties) {
        super(properties);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.entityTags().remove("HasBlood");
        }
    }
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.isEmpty()){
                if (p_150746_.entityTags().contains("HasBlood")){
                    p_150746_.entityTags().remove("HasBlood");

                    p_150746_.getCooldowns().addCooldown(me.getItem().getDefaultInstance(),20);

                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.entityTags().contains("HasBlood")&&!player.getCooldowns().isOnCooldown(this.getDefaultInstance())){
                OwnerBlood owner_blood = new OwnerBlood(EntityTs.owner_blood_.get(),player.level());
                owner_blood.setOwner(player);
                owner_blood.setPos(player.position());
                player.level().addFreshEntity(owner_blood);
                player.entityTags().add("HasBlood");
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (p_41424_.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));

            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.2").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }


}

