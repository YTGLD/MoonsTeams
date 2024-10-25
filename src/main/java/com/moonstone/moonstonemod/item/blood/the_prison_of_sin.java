package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class the_prison_of_sin extends Item implements ICurioItem, Blood {
    public the_prison_of_sin() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_prison_of_sin.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (stack.is(Items.the_prison_of_sin.get())) {
                                if (stack.getTag()!=null
                                        && event.getEntity().getEncodeId()!=null)
                                {
                                    String name = event.getEntity().getEncodeId();

                                    if (stack.getTag().getString(name).isEmpty()) {
                                        stack.getTag().putString(name, name);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {

        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
        slotContext.entity().getAttributes().addTransientAttributeModifiers(Health());
    }


    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null) {
            stack.getOrCreateTag();
        }

        stack.setDamageValue(stack.getDamageValue()+1);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(Health());
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s = 110;
        if (stack.getTag()!=null){
            s += stack.getTag().getAllKeys().size();
        }
        s -= 100;
        s /= 100;
        for (Attribute attribute : BuiltInRegistries.ATTRIBUTE) {
            modifierMultimap.put(attribute, new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"), "name", s, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> Health() {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"),"name", -0.80, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"),"name", -0.80, AttributeModifier.Operation.MULTIPLY_BASE));

        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (pStack.getTag() != null) {
            if (Screen.hasShiftDown()) {
                if (pStack.getTag() != null) {
                    pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));

                    pTooltipComponents.add(Component.translatable(""));
                    if (pStack.getTag() != null) {
                        for (String s : pStack.getTag().getAllKeys()) {
                            pTooltipComponents.add(Component.translatable(s).withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
                        }
                        pTooltipComponents.add(Component.translatable(""));
                    }
                } else {
                    pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
                }
            } else {
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.1").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.2").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.3").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.5").append(String.valueOf(pStack.getTag().getAllKeys().size())).append("%").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            }
        } else {
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.7").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.6").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
        }
    }
}
