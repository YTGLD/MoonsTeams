package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At("RETURN"), method = "isInvulnerableTo", cancellable = true)
    public void mhead(DamageSource p_20122_, CallbackInfoReturnable<Boolean> cir) {
       if (p_20122_.getEntity() instanceof Player player){
           if (Handler.hascurio(player, Items.mhead.get())){
               cir.setReturnValue(true);
           }
       }
        if (p_20122_.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nine_sword_books.get())){
                ItemStack sword = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!(sword.getItem() instanceof SwordItem)){
                    player.displayClientMessage(Component.translatable("moonstone.nine_sword_books.tool").withStyle(ChatFormatting.RED), true);

                    cir.setReturnValue(true);
                }
            }
        }
    }

}
