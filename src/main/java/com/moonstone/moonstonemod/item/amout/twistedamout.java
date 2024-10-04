package com.moonstone.moonstonemod.item.amout;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
public class twistedamout extends TheNecoraIC {
    public static final String MaxSword = "MaxSword";
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("y","y");
    }

    public static void hurt(LivingHurtEvent event){
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
                                    if (stack.getTag()!=null){
                                        float dam = (float) stack.getTag().getInt(MaxSword) /20;
                                        event.setAmount(event.getAmount()*(1+dam));
                                        if (Mth.nextInt(RandomSource.create(),1,2)==1) {
                                            if (stack.getTag().getInt(MaxSword)<9) {
                                                stack.getTag().putInt(MaxSword, stack.getTag().getInt(MaxSword) + 1);
                                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.75F, 0.75F);
                                            }
                                        }
                                        if (stack.getTag().getInt(MaxSword)>=8){
                                            event.setAmount(event.getAmount()*4);
                                            stack.getTag().remove(MaxSword);
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_ATTACK_IMPACT, SoundSource.NEUTRAL, 2, 2);
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 0), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            player.level().levelEvent(2001, new BlockPos((int)player.getX(), (int) (player.getY() + 1), (int) player.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

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
                                    if (stack.getTag()!=null){
                                        if (stack.getTag().getInt(MaxSword)<9) {
                                            stack.getTag().putInt(MaxSword, stack.getTag().getInt(MaxSword) + Mth.nextInt(RandomSource.create(),1,3));
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
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.getTag()!=null) {
            if (!slotContext.entity().level().isClientSide){
                if (slotContext.entity().tickCount%120==0){
                    if (stack.getTag().getInt(MaxSword) > 0) {
                        stack.getTag().putInt(MaxSword, stack.getTag().getInt(MaxSword) - 1);
                        slotContext.entity().heal(4+slotContext.entity().getMaxHealth()/50);
                        slotContext.entity().level().levelEvent(2001, new BlockPos((int) slotContext.entity().getX(), (int) (slotContext.entity().getY() + 1), (int) slotContext.entity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                        Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
                        int range = 3;
                        List<LivingEntity> entities = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                        for (LivingEntity living : entities){
                            if (!living.is(slotContext.entity())){
                                living.addEffect(new MobEffectInstance(MobEffects.POISON,200,2));
                            }
                        }
                    }
                }
            }

        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
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
