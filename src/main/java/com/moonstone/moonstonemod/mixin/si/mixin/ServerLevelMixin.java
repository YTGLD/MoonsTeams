package com.moonstone.moonstonemod.mixin.si.mixin;

import com.moonstone.moonstonemod.Config;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(at = @At("HEAD"), method = "addEntity", cancellable = true)
    private void addFreshEntity(Entity p_8873_, CallbackInfoReturnable<Boolean> cir){
        Set<String> blacklist = new HashSet<>();
        for (String  s : Config.SERVER.disItemOfNightmare.get()){
            String[] parts = s.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0]+":"+parts[1]);
            }
        }
        if (p_8873_ instanceof ItemEntity entity) {
            if  (blacklist.contains(BuiltInRegistries.ITEM.getKey(entity.getItem().getItem()).toString())){
                if (entity.getOwner()==null) {
                    entity.setItem(ItemStack.EMPTY);
                    cir.setReturnValue(false);
                }
            }
        }

    }
}
