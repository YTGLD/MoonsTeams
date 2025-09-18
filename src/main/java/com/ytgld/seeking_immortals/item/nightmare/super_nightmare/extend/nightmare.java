package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.item.nightmare.Terror;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Map;

public class nightmare extends Item implements ICurioItem, INightmare, Terror {
    public nightmare() {

        super(new Properties().stacksTo(1)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getTag() == null) {
            stack.getOrCreateTag();
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),this)){
            return false;
        }
        return true;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/tooltip/fire.png");
    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        @Nullable Map<Integer, Component> map = this.describe(stack);
        if (map != null) {
            Integer integer = map.keySet().stream().toList().get(this.nowLevel(stack)-1);
            if (integer!=-1) {
                return map.get(integer).copy().append(component);
            }else {
                return component;
            }
        }


        return component;
    }
    @Override
    @Nullable
    public Map<Integer ,Component> describe(ItemStack stack) {
        return null;
    }

    @Override
    public int maxLevel(ItemStack stack) {
        return 1;
    }
    @Override
    public int nowLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,255,50,255);
    }
}

