package com.ytgld.moonstone.item.si.nightmare;

import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.other.Light;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class NightmareSmall extends ItemBase implements ICurioItem {
    public NightmareSmall(Properties properties) {
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
    public ICurio.@NotNull DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }


    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(0xffff0000));
    }


}
