package com.moonstone.moonstonemod.item.man;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public abstract class ManDNA extends Item  implements ICurioItem, Iplague {
    public ManDNA(Properties properties) {
        super(properties);
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
    }

    public abstract@Nullable List<Item> getDrug();

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal(""));
        if (getDrug()!=null) {
            for (Item item : getDrug()) {
                ResourceLocation resourceLocation= BuiltInRegistries.ITEM.getKey(item);
                String s = resourceLocation.toString().replace(":",".");
                tooltipComponents.add(Component.translatable("item."+s).withStyle(ChatFormatting.GOLD));
            }
        }
    }

    public static void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                               Entity entity ,
                               int gLvl){
        if (entity instanceof Player player ){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getItem() instanceof ManDNA manDNA){
                            if (Handler.hascurio(player,manDNA)) {
                                if (manDNA.getDrug() != null) {
                                    List<Item> list = manDNA.getDrug();
                                    if (!list.isEmpty()) {
                                        if (Mth.nextInt(net.minecraft.util.RandomSource.create(), 1, 100) <= gLvl) {
                                            generatedLoot.add(new ItemStack(list.get(new Random().nextInt(list.size()))));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

        }
    }

    public static class Drug  extends Item  implements ICurioItem{
        public final List<String> stringName;
        @Override
        public void curioTick(SlotContext slotContext, ItemStack stack) {
            if (stack.getTag()==null){
                stack.getOrCreateTag();
            }
        }

        public Drug(List<String> stringName) {
            super(new Properties().stacksTo(1).rarity(Rarity.RARE));
            this.stringName = new ArrayList<>(new LinkedHashSet<>(stringName));

        }
        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
            for (String string : stringName) {
                pTooltipComponents.add(Component.translatable(string).withStyle(ChatFormatting.GOLD));
            }
        }
    }
}
