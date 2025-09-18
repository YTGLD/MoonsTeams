package com.ytgld.seeking_immortals.item.nightmare;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

/**
 *你将不再受到任何形式的属性加成和减少
 * <p>
 *  伤害固定为15
 * <p>
 *  攻速固定为4
 * <p>
 *  最大生命值固定为30
 * <p>
 *  受到伤害固定为2
 * <p>
 *  护甲固定为10

 */
public class falling_immortals extends nightmare implements SuperNightmare {
    public static void dieEqItem(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getEntity() instanceof WitherBoss) {
                if (Handler.hascurio(player, Items.apple.get()) && player.getMainHandItem().is(net.minecraft.world.item.Items.ENDER_EYE)) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is( Items.apple.get())) {
                                    stack.shrink(1);
                                }
                                if (stack.isEmpty()&&!Handler.hascurio(player, Items.falling_immortals.get())){
                                    if (stacksHandler.getIdentifier().equals("nightmare")) {

                                        stackHandler.setStackInSlot(i, Items.falling_immortals.get().getDefaultInstance());
                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);

                                        break;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }
    public static void damage(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.falling_immortals.get())) {
                event.setAmount(15);
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.falling_immortals.get())) {
                event.setAmount(2);
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(Effects.life.get(),100,0,false,false));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.falling_immortals.tool.string.6").withStyle(ChatFormatting.DARK_RED));

    }
}
