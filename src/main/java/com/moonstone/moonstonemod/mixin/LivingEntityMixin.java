package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.IFluidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin  extends Entity implements Attackable, net.minecraftforge.common.extensions.IForgeLivingEntity {
    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow public abstract ItemStack getItemInHand(InteractionHand p_21121_);

    @Shadow protected abstract void setLivingEntityFlag(int p_21156_, boolean p_21157_);

    @Shadow public abstract Vec3 handleRelativeFrictionAndCalculateMovement(Vec3 p_21075_, float p_21076_);

    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    @Shadow @Nullable public abstract MobEffectInstance getEffect(MobEffect p_21125_);

    @Shadow public abstract boolean shouldDiscardFriction();

    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    public void getMaxHealth(CallbackInfoReturnable<Float> cir) {
        if ((LivingEntity) (Object) this instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_head.get())){
                cir.setReturnValue(8f);
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getTag()!=null){
                            if (stack.getTag().getBoolean(Difficulty.EASY.getKey())){
                                cir.setReturnValue(cir.getReturnValue()+0.175f);
                            }
                            if (stack.getTag().getBoolean(Difficulty.NORMAL.getKey())){
                                cir.setReturnValue(cir.getReturnValue()+0.33f);
                            }
                            if (stack.getTag().getBoolean(Difficulty.HARD.getKey())){
                                cir.setReturnValue(cir.getReturnValue()+0.5f);
                            }
                            if (stack.getTag().getBoolean(NewEvent.lootTable)){
                                cir.setReturnValue(cir.getReturnValue()+0.66f);
                            }
                        }
                    }
                }
            });
        }
    }
    @Inject(at = @At("RETURN"), method = "canStandOnFluid", cancellable = true)
    public void canStandOnFluid(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player,Items.evilcandle.get())){
                if (fluidState.is(Fluids.LAVA)) {
                    cir.setReturnValue(true);
                }
            }
            if (Handler.hascurio(player,Items.ambush.get())){
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "canBeSeenByAnyone", cancellable = true)
    public void mhead(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.mhead.get())) {
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "travel")
    public void moonstone$travel(Vec3 p_21280_, CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;

        if (player.isSprinting()) {
            if (Handler.hascurio(player, Items.flygene.get())) {
                player.moveRelative((float) (player.getSpeed() * Config.SERVER.flygene_speed.get()), p_21280_);
                if (!player.onGround()) {
                    player.moveRelative((float) (player.getSpeed() * Config.SERVER.flygene_speed.get()), p_21280_);
                }
            }
            if (Handler.hascurio(player, Items.bloodvirus.get())) {
                player.moveRelative((float) (player.getSpeed() * Config.SERVER.bloodvirus_speed.get()), p_21280_);
            }
            if (Handler.hascurio(player, Items.motor.get())) {
                player.moveRelative((float) (player.getSpeed() * Config.SERVER.motor_speed.get()), p_21280_);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "getJumpPower", cancellable = true)
    public void getJumpPower(CallbackInfoReturnable<Float> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.quadriceps.get())) {
                cir.setReturnValue(cir.getReturnValue() * 1.5f);
            }
        }
    }
}
