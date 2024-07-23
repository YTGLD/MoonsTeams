package com.moonstone.moonstonemod.item.nanodoom.sword;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.sword;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;
import java.util.UUID;

public class million_sword extends Doom {
    /*
        如果在十秒内斩击数超过10次
        你将瞬间释放10枚震爆光剑
        之后每攻击一次都有20%的概率再释放一枚

        光剑会继承你5%的伤害

        你的攻击速度会随着斩击次数的增加而增加，但不会超过100%
        但是你的伤害会逐渐减少，最多降低到原先的33%
     */

    public static final int time = 90;
    public static String attack_size = "AttackSize";
    public static void Sword_m(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.million_sword.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.million_sword.get())){
                                   if (stack.getTag() != null){
                                       int r = Mth.nextInt(RandomSource.create(), 1, 5);

                                       if (stack.getTag().getInt(attack_size) == 9){
                                           for (int j = 0 ;j < 10 ;j++){
                                               float s  = (float) Math.sin(j);
                                               if (s <= 0){
                                                   s = 0.1f;
                                               }
                                               sword item = new sword(EntityTs.sword.get(),player.level());
                                               item.teleportTo(player.getX()+Mth.nextFloat(RandomSource.create(), -s,s),player.getY()+2+s,player.getZ()+Mth.nextFloat(RandomSource.create(), -s,s));
                                               item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),0,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                                               item.setOwnerUUID(player.getUUID());
                                               player.level().addFreshEntity(item);
                                           }

                                       }

                                       if (stack.getTag().getInt(attack_size) >= 9){
                                           if (r == 1){
                                               float s  = (float) Math.sin(Mth.nextFloat(RandomSource.create(), 1, 7));
                                               if (s <= 0){
                                                   s = 0.1f;
                                               }
                                               sword item = new sword(EntityTs.sword.get(),player.level());
                                               item.setPos(player.position());
                                               item.setOwnerUUID(player.getUUID());
                                               item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),s/2,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                                           }
                                       }
                                           if (!player.getCooldowns().isOnCooldown(Items.million_sword.get())){
                                           player.getCooldowns().addCooldown(Items.million_sword.get(), 200);
                                           stack.getTag().remove(attack_size);
                                       }
                                       if (player.getCooldowns().isOnCooldown(Items.million_sword.get())){
                                           if (stack.getTag().getInt(attack_size) <= 10) {
                                               stack.getTag().putInt(attack_size, stack.getTag().getInt(attack_size) + 1);
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

    public Multimap<Attribute, AttributeModifier> getAttributeModifier(ItemStack stack){
        Multimap<Attribute, AttributeModifier> get = HashMultimap.create();
        UUID uuid = UUID.fromString("8610364c-1fe9-3801-96a1-4fb3dc123fc9");
        float s = 0;
        if (stack.getTag()!=null){
            s = stack.getTag().getInt(attack_size);
        }
        s/=10;
        get.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "million_sword",s, AttributeModifier.Operation.ADDITION));
        get.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "million_sword",-s/6, AttributeModifier.Operation.ADDITION));

        return get;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.getOrCreateTag().putString("a","a");
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifier(stack));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifier(stack));
    }
}
