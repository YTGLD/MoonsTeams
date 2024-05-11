package com.moonstone.moonstonemod.Item.Plague.medicine.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Event.AllEvent;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex.catalyzer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AnvilUpdateEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class medicinebox extends TheNecoraIC {
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(com.moonstone.moonstonemod.Init.Items.botton.get()));
                    Other.shrink(1);

                    return true;
                }
            }
        }
        return false;
    }

    public static int do_hurt;
    public static int do_apple;
    public static int do_jump;

    private void bbb(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty()) {
            if (you != null) {
                if (zuo.is(Items.CHEST) && you.is(com.moonstone.moonstonemod.Init.Items.ectoplasmcube.get())) {
                    event.setCost(1);
                    event.setMaterialCost(1);
                    event.setOutput(this.asItem().getDefaultInstance());
                }
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

        CuriosApi
                .addSlotModifier(linkedHashMultimap, "medicine", uuid, 3, AttributeModifier.Operation.ADDITION);


        return linkedHashMultimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            do_hurt = stack.getOrCreateTag().getInt(AllEvent.hurt_size);
            do_apple = stack.getOrCreateTag().getInt(AllEvent.apple);
            do_jump = stack.getOrCreateTag().getInt(AllEvent.jump_size);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        tooltip.add(Component.translatable("item.medicinebox.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable("item.medicinebox.tool.string.1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.medicinebox.tool.string.2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable("item.medicinebox.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.medicinebox.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        tooltip.add(Component.translatable(""));
        if (stack.getTag()!= null) {
            if (stack.getTag().getBoolean(AllEvent.blood_eat)&&
                    stack.getTag().getBoolean(AllEvent.blood_hurt)&&
                    stack.getTag().getBoolean(AllEvent.blood_jump)&&
                    stack.getTag().getBoolean(AllEvent.blood_spawn)&&
                    stack.getTag().getBoolean(AllEvent.blood_enchant)) {
                tooltip.add(Component.translatable("item.medicinebox.tool.string.5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
                tooltip.add( Component.translatable("item.medicinebox.tool.string.6").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));


            }
        }
    }
}
