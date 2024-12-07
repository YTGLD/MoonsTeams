package com.moonstone.moonstonemod.item.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class million extends Doom {
    public static final String sizeLvl = "swordSize";
    public static final String attackLvl = "attackLvlSize";
    public static final String allAttackTime = "allAttackTime";


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag() != null) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(Head(stack));
            if (stack.getTag().getInt(allAttackTime)>0){
                stack.getTag().putInt(allAttackTime,stack.getTag().getInt(allAttackTime)-1);
            }

            if (stack.getTag().getInt(allAttackTime)<=0){
                stack.getTag().putInt(attackLvl,0);
            }
        }else {
            stack.getOrCreateTag().putInt(sizeLvl,30);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(Head(stack));
    }

    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.million.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (player.getAttackStrengthScale(1) >= 1) {
                                if (stack.getTag() != null) {

                                    if (stack.getTag().getInt(sizeLvl) >= 3) {

                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 2, 2);

                                        LivingEntity target = event.getEntity();


                                        for (int j = 0; j < 3; j++) {

                                            float lvl = Mth.nextFloat(RandomSource.create(), -0.6f, 0.6f);

                                            as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                                            as_sword.setPos(target.position().x, target.position().y + 1, target.position().z);
                                            Vec3 forward = player.getLookAngle();
                                            double speed = 0.25;

                                            as_sword.setDeltaMovement(forward.add(lvl, lvl, lvl).x * speed, forward.add(lvl, lvl, lvl).y * speed, forward.add(lvl, lvl, lvl).z * speed);

                                            as_sword.setOwner(player);

                                            player.level().addFreshEntity(as_sword);
                                            as_sword.setTarget(target);

                                            if (stack.getTag().getInt(sizeLvl) > 0) {
                                                stack.getTag().putInt(sizeLvl, stack.getTag().getInt(sizeLvl) - 1);
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
    }
    private Multimap<Attribute, AttributeModifier> Head(ItemStack stack){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();

        if (stack.getTag() != null) {
            float dam = stack.getTag().getInt(attackLvl) / 100f;
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("0cad2f47-2665-3067-89f3-6434c639de1f"),"million",
                    dam,
                    AttributeModifier.Operation.MULTIPLY_BASE));

        }

        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.million.tool.string.1").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.2").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.3").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.4").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.5").withStyle(ChatFormatting.GOLD));
        } else {
            pTooltipComponents.add(Component.translatable("item.million.tool.string.6").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            if (pStack.getTag()!=null) {
                pTooltipComponents.add(Component.translatable("item.million.tool.string.7").append(pStack.getTag().getInt(attackLvl) + "%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("item.million.tool.string.8").append(pStack.getTag().getInt(sizeLvl) + "").withStyle(ChatFormatting.YELLOW));
            }
        }
    }
}
