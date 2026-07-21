package com.ytgld.moonstone.item.si.nightmare;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.Light;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.DropRule;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;

public class NightmareBase extends ItemBase implements ICurioItem {
    public NightmareBase(Properties properties) {
        super(properties);
    }

    @Override
    public int color() {
        return 0xffff0000;
    }

    @Override
    public int colorEQ() {
        return Light.ARGB.color(255,200,50,100);
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_divine_fall_ring.asItem())) {
                return true;
            }
            return player.isCreative();
        }
        return false;
    }

    @Override
    public @NonNull DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return DropRule.ALWAYS_KEEP;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(0xffff0000));
    }

//
//    @Override
//    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
//        return new CurioAttributeModifiers(List.of(
//                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("nightmare"),
//                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
//                                1, AttributeModifier.Operation.ADD_VALUE)
//                        , SlotTypePredicate.builder().withId("nightmare").build())
//        ), true);
//    }
//
//    @Override
//    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
//        super.inventoryTick(itemStack, level, owner, slot);
//        AttributeModifier attributeModifier = new AttributeModifier(Identifier.parse(this.descriptionId),
//                1, AttributeModifier.Operation.ADD_VALUE);
//        if (owner instanceof Player player) {
//            CuriosApi.getCuriosInventory(player).flatMap(handler -> handler.getStacksHandler("nightmare"))
//                    .ifPresent(stacks -> {
//                        stacks.addTransientModifier(attributeModifier);
//                    });
//        }
//    }

    public static final String ITEMCategory = "Nightmare";
}
