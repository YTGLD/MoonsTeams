package com.ytgld.moonstone.item.ms.necora.medicine;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MedicineBox extends TheNecora {
    public MedicineBox(Properties properties) {
        super(properties);
    }
    public static final String jump_size = "jump_size";
    public static final String hurt_size ="hurt_size";
    public static final String apple = "apple";
    public static final String spawn= "spawn";
    public static final String enchant= "enchant";

    public static final String blood_hurt = "blood_hurt";
    public static final String blood_jump = "blood_jump";
    public static final String blood_eat = "blood_eat";
    public static final String blood_spawn = "blood_spawn";
    public static final String blood_enchant = "blood_enchant";

    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                Items.calcification.asItem(),
                Items.masticatory.asItem(),
                Items.polyphagia.asItem(),
                Items.quadriceps.asItem(),
                Items.reanimation.asItem()
        )) ;
    }
    @Override
    public int maxSize() {
        return 3;
    }

    private static void giveItem(Player player,ItemStack stack){
        ItemEntity entity = new ItemEntity(player.level(),player.getX(),player.getY(),player.getZ(),stack);
        entity.setGlowingTag(true);
        if (Mth.nextInt(player.getRandom(), 0, 100) <= 50) {
            entity.getItem().setCount(2);
        }
        player.level().addFreshEntity(entity);
    }


    public  static void die(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (!Handler.hascurio(player, Items.medicinebox.get())) {
            return;
        }

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        if (stack.is(Items.medicinebox.get())) {
                            if (stack.get(DataReg.tag) != null && !stack.get(DataReg.tag).getBoolean(spawn)) {
                                if (Handler.hascurio(player, Items.medicinebox.get())) {
                                    giveItem(player,new ItemStack(Items.reanimation.get()));
                                    stack.get(DataReg.tag).putBoolean(spawn, true);
                                    stack.get(DataReg.tag).putBoolean(blood_spawn, true);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    public  static void apple(LivingEntityUseItemEvent.Finish event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            if (!Handler.hascurio(player, Items.medicinebox.get())) {
                return;
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                ItemStack a = event.getItem();
                                if (a.is(net.minecraft.world.item.Items.GOLDEN_APPLE)){

                                    if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(apple)< 9){
                                        stack.get(DataReg.tag).putInt(apple,stack.get(DataReg.tag).getInt(apple)+1);
                                    }
                                    if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(apple)== 8){
                                        giveItem(player,new ItemStack(Items.masticatory.get()));
                                        stack.get(DataReg.tag).putBoolean(blood_eat, true);
                                    }

                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public  static void LivingDamageEvent(LivingDamageEvent.Pre event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            if (!Handler.hascurio(player, Items.medicinebox.get())) {
                return;
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(hurt_size)< 351){
                                    stack.get(DataReg.tag).putInt(hurt_size,stack.get(DataReg.tag).getInt(hurt_size)+1);
                                }
                                if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(hurt_size)== 350){
                                    giveItem(player,new ItemStack(Items.calcification.get()));
                                    stack.get(DataReg.tag).putBoolean(blood_hurt, true);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    public  static void LivingDamageEvent(LivingEvent.LivingJumpEvent event) {


        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            if (!Handler.hascurio(player, Items.medicinebox.get())) {
                return;
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {

                                if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(jump_size)< 501){
                                    stack.get(DataReg.tag).putInt(jump_size,stack.get(DataReg.tag).getInt(jump_size)+1);

                                }
                                if (stack.get(DataReg.tag)!= null&&stack.get(DataReg.tag).getInt(jump_size)== 500){
                                    giveItem(player,new ItemStack(Items.quadriceps.get()));
                                    stack.get(DataReg.tag).putBoolean(blood_jump, true);

                                }
                            }
                        }
                    }
                }
            });
        }
    }
    public  static void LivingDamageEvent(LivingEntityUseItemEvent.Finish event) {

        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            if (!Handler.hascurio(player, Items.medicinebox.get())) {
                return;
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                ItemStack a = event.getItem();
                                if (a.is(net.minecraft.world.item.Items.ENCHANTED_GOLDEN_APPLE)) {

                                    if (stack.get(DataReg.tag) != null && !stack.get(DataReg.tag).getBoolean(enchant)) {
                                        giveItem(player,new ItemStack(Items.polyphagia.get()));
                                        stack.get(DataReg.tag).putBoolean(enchant, true);
                                        stack.get(DataReg.tag).putBoolean(blood_enchant, true);

                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.medicinebox.tool.string.8").withStyle(Style.EMPTY.withColor(0xffff0000)));
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable("item.medicinebox.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.literal(""));
        }else {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }
    }
}
