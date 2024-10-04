package com.moonstone.moonstonemod.item.pain;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.ThePain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class the_pain_stone extends ThePain {

    /*
    杀死生物后将其50%的最大生命值转换成下一次的额外攻击伤害
	如果下次造成的伤害大于该生物的最大生命值
	那么额外伤害将等比例恢复自身血量

	-15%任何伤害
     */
    public static final String pain = "PainStone";
    public static void PainStone(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.the_pain_stone.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.the_pain_stone.get())){
                                if (stack.getTag()!=null){
                                    stack.getOrCreateTag().putFloat(pain, event.getEntity().getMaxHealth()/ Config.SERVER.the_pain_stone.get());
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void PainStoneAttack(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.the_pain_stone.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.the_pain_stone.get())){
                                if (stack.getTag()!=null){
                                    float damage = stack.getOrCreateTag().getFloat(pain);
                                    event.setAmount((event.getAmount()+damage)* 0.85f);
                                    if (event.getAmount()>event.getEntity().getMaxHealth()){
                                        float health = event.getAmount() -event.getEntity().getMaxHealth();
                                        player.heal(health);
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
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.the_pain_stone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.the_pain_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.the_pain_stone.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.the_pain_stone.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.the_pain_stone.tool.string.5").append(String.valueOf(stack.getOrCreateTag().getFloat(pain))).withStyle(ChatFormatting.RED));
    }
}
