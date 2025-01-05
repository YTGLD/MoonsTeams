package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.nightmare_entity;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.MSound;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmare_heart extends nightmare {
    public static void NigH(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_heart.get())){
                if (event.getSource().getEntity()!= null&& event.getSource().getEntity() instanceof nightmare_entity){
                    event.setAmount(0);
                }
            }
        }
    }
    public static void Nig(LivingDeathEvent event){
        if (event.getSource() != null) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (Handler.hascurio(player, Items.nightmare_heart.get())) {
                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_heart.get())) {
                        nightmare_entity e = new nightmare_entity(EntityTs.nightmare_entity.get(), event.getEntity().level());
                        e.setPos(new Vec3(event.getEntity().getX(), event.getEntity().getY()-1, event.getEntity().getZ()));

                        e.setNoAi(true);
                        e.setNoGravity(true);
                        e.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 12000, 0, false, false));


                        event.getEntity().level().addFreshEntity(e);

                        player.level().playSound(null,new BlockPos((int)player.getX(), (int) player.getY(), (int) player.getZ()), MSound.black_hold.get(), SoundSource.MUSIC,0.75f,0.75f);
                        player.getCooldowns().addCooldown(Items.nightmare_heart.get(),50);

                    }
                }
            }

        }

    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID+ ":nightmare_heart", -0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID+ ":nightmare_heart", -0.15, AttributeModifier.Operation.MULTIPLY_BASE));
         return modifierMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.nightmare_heart.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}
