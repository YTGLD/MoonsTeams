package com.ytgld.moonstone.item.si.nightmare;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.Light;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

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
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(0xffff0000));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity owner, int slot, boolean isSelected) {
        super.inventoryTick(itemStack, level, owner, slot, isSelected);
        AttributeModifier attributeModifier = new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()),
                1, AttributeModifier.Operation.ADD_VALUE);
        if (owner instanceof Player player) {
            CuriosApi.getCuriosInventory(player).flatMap(handler -> handler.getStacksHandler("nightmare"))
                    .ifPresent(stacks -> {
                        stacks.addTransientModifier(attributeModifier);
                    });
        }
    }
    public static void renderBack(RenderTooltipEvent.Color event) {
        if (event.getItemStack().getItem() instanceof NightmareBase item) {
            event.setBackground(Light.ARGB.color(255, 20, 5, 5));
            event.setBorderEnd(Light.ARGB.color(255, 100, 5, 5));
            event.setBorderStart(Light.ARGB.color(255, 100, 5, 5));
        }
    }
    public static final String ITEMCategory = "Nightmare";
}
