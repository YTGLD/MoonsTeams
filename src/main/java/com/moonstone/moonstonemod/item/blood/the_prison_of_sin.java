package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
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

import java.util.*;

public class the_prison_of_sin extends TheNecoraIC {
    public static final String killNameAndSize = "killNameAndSize";
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
                                        int size = stack.getTag().getAllKeys().size();
                                        float incrementFactor = 1.0f;
                                        for (int ia = 0; ia < size; ia++) {
                                            incrementFactor *= 0.99f;
                                        }
                                        stack.getTag().putFloat(killNameAndSize,
                                                stack.getTag().getFloat(killNameAndSize)+incrementFactor);

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
            s+=stack.getTag().getFloat(killNameAndSize);
        }
        Set<String> blacklist = new HashSet<>();
        for (String aaa : Config.SERVER.allAttributeModify.get()) {
            String[] parts = aaa.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0] + ":" + parts[1]);
            }
        }
        s-=100f;
        s/=100f;
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            if (attribute != null) {
                String attributeId = Config.getRegisteredName(attribute);
                if (!blacklist.contains(attributeId)) {
                    modifierMultimap.put(attribute.get(), new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"),
                            "name", s, AttributeModifier.Operation.MULTIPLY_BASE));
                }
            }
        }

        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> Health() {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"),"name", -0.80, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("63489016-3661-38ec-acb6-3029cde6f29c"),"name", -0.80, AttributeModifier.Operation.MULTIPLY_BASE));

        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (pStack.getTag() != null) {
            {
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.1").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.2").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.3").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                float s = 110;
                if (pStack.getTag()!=null){
                    s+=pStack.getTag().getFloat(killNameAndSize);
                }
                s-=100f;
                s/=100f;
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.5").append(String.valueOf(s*100)).append("%").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
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
