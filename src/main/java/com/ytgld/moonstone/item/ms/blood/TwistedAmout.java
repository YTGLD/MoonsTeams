package com.ytgld.moonstone.item.ms.blood;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
public class TwistedAmout extends BloodItem {
    public static final String MaxSword = "MaxSword";
    public TwistedAmout(Properties properties) {
        super(properties);
    }
    public static void hurt(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.twistedamout.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.twistedamout.get())){
                                    if (stack.get(DataReg.tag)!=null){
                                        float dam = (float) stack.get(DataReg.tag).getInt(MaxSword) /20;
                                        event.setNewDamage(event.getNewDamage()*(1+dam));
                                        if (Mth.nextInt(RandomSource.create(),1,2)==1) {
                                            if (stack.get(DataReg.tag).getInt(MaxSword)<9) {

                                                stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) + 1);
                                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.75F, 0.75F);
                                            }
                                        }
                                        if (stack.get(DataReg.tag).getInt(MaxSword)>=8){
                                            event.setNewDamage(event.getNewDamage()*4);
                                            stack.get(DataReg.tag).remove(MaxSword);
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_ATTACK_IMPACT, SoundSource.NEUTRAL, 2, 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.twistedamout.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.twistedamout.get())){
                                    if (stack.get(DataReg.tag)!=null){
                                        if (stack.get(DataReg.tag).getInt(MaxSword)<9) {
                                            stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) + Mth.nextInt(RandomSource.create(),1,3));
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.75F, 0.75F);
                                        }
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
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequipUse(slotContext, newStack, stack);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.get(DataReg.tag)!=null) {
            if (slotContext.entity().tickCount % 120 == 0) {
                if (stack.get(DataReg.tag).getInt(MaxSword) > 0) {
                    stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) - 1);
                    slotContext.entity().heal(4 + slotContext.entity().getMaxHealth() / 50);
                    Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
                    int range = 3;
                    List<LivingEntity> entities = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                    for (LivingEntity living : entities) {
                        if (!living.is(slotContext.entity())) {
                            living.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 2));
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable("item.twistedamout.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.5").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.6").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.twistedamout.tool.string.7").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
