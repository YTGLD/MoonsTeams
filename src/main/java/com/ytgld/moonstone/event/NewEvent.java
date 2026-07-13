package com.ytgld.moonstone.event;

import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.blood.MaxEye;
import com.ytgld.moonstone.item.ms.blood.PrisonOfSin;
import com.ytgld.moonstone.item.ms.blood.magic.BloodMagicBox;
import com.ytgld.moonstone.item.ms.blood.magic.UndeadBloodCharm;
import com.ytgld.moonstone.item.ms.ectoplasm.Beacon;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmApple;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmHorseshoe;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmShild;
import com.ytgld.moonstone.item.ms.maulice.*;
import com.ytgld.moonstone.item.ms.maxitem.Maxamout;
import com.ytgld.moonstone.item.ms.maxitem.TheHeart;
import com.ytgld.moonstone.item.ms.maxitem.TwelveSword;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.common.BadgeOfTheDead;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.common.BlueAmout;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.common.GreedAmout;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.common.RedAmout;
import com.ytgld.moonstone.item.ms.nanodoom.*;
import com.ytgld.moonstone.item.ms.necora.Necora;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.CellBoom;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.Cell;
import com.ytgld.moonstone.item.ms.necora.dna.Fermentation;
import com.ytgld.moonstone.item.ms.necora.dna.god.GodAmbush;
import com.ytgld.moonstone.item.ms.necora.dna.god.GodPutrefactive;
import com.ytgld.moonstone.item.si.fall.DivineFallRing;
import com.ytgld.moonstone.item.si.nightmare.base.*;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeEye;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeHeart;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeRed;
import com.ytgld.moonstone.item.si.nightmare.fool.Apple;
import com.ytgld.moonstone.item.si.nightmare.fool.FoolBone;
import com.ytgld.moonstone.item.si.nightmare.insight.HiddenBlade;
import com.ytgld.moonstone.item.si.nightmare.insight.InsightInsane;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDeception;
import com.ytgld.moonstone.item.si.nightmare.reversal.Candle;
import com.ytgld.moonstone.item.si.nightmare.reversal.ReversalOrb;
import com.ytgld.moonstone.item.si.nightmare.start.StartPod;
import com.ytgld.moonstone.item.si.nightmare.start.Wolf;
import com.ytgld.moonstone.item.si.nightmare.stone.EndBone;
import com.ytgld.moonstone.item.si.nightmare.stone.StoneBrain;
import com.ytgld.moonstone.item.si.nightmare.stone.StoneVirus;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.impl.CuriosRegistry;

import java.util.Map;

public class NewEvent {

    public static float time = 0;

    @SubscribeEvent
    public  void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.entityTags().contains(Moonstone.MODID+"nightmare")) {
            player.addItem(Items.NightmareBaseItem_.get().getDefaultInstance());
            player.addTag(Moonstone.MODID+"nightmare");
        }
    }

    @SubscribeEvent
    public void tick(ClientTickEvent.Pre event) {
        time++;
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingDamageEvent.Pre event) {
        NightmareBaseStone.LivingHurtEvent(event);
        NightmareBaseStart.damage(event);
        EndBone.hurts(event);
        StoneBrain.hurts(event);
        StoneVirus.h(event);
        BlackEyeHeart.hurt(event);
        BlackEyeEye.attLook(event);
        HiddenBlade.hurt_cit(event);
        InsightInsane.damage(event);
        Candle.hurt(event);
        FoolBone.attLook(event);
        Wolf.attack(event);
        StartPod.damage(event);
        EctoplasmHorseshoe.hurt(event);
        EctoplasmShild.hurt(event);
        Brain.brainLHurt(event);
        MKidney.brainLHurt(event);
        MShell.LivingHurtEvent(event);
        UndeadBloodCharm.LivingIncomingDamageEvent(event);
        MaxEye.A(event);
        GodAmbush.LivingIncomingDamageEvent(event);
        Fermentation.fermentation(event);
        CellBoom.Boom(event);
        GreedAmout.greedamout(event);
        BlueAmout.blueamout(event);
        RedAmout.redamout(event);
        BadgeOfTheDead.badgeofthedead(event);
        TwelveSword.att(event);
        AsAmout.hurt(event);
        Maxamout.maxamout(event);
        MagicEye.damage(event);
        SevenSword.doomeyeLivingKnockBackEvent(event);
        RineSword.suddenrainLLivingHurtEvent(event);
        Million.hurt(event);
        Beacon.beacon(event);

        Apple.damage(event);
        RedemptionDeception.LivingHurtEvent(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingDamageEvent.Post event) {
        EctoplasmApple.hurt(event);

    }

    @SubscribeEvent
    public  void eat(LivingEntityUseItemEvent.Finish event){
        GodPutrefactive.eat(event);
        Necora.necora(event);
    }
    @SubscribeEvent
    public void target(LivingChangeTargetEvent event){
        MHead.target(event);
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingDeathEvent event) {
        NightmareBaseItemReversal.LivingDeathEvent(event);
        BlackEyeRed.kill(event);
        InsightInsane.LivingDeathEvents(event);
        Wolf.kill(event);
        BloodMagicBox.Did(event);
        MaxEye.Die(event);
        RineSword.suddenrainLivingDeathEvent(event);


        Cell.evil(event);
        PrisonOfSin.LivingDeathEvent(event);
    }

    @SubscribeEvent
    public void exp(LivingExperienceDropEvent event) {
        NightmareBaseInsight.exp(event);
        DivineFallRing.exp(event);
        MOrb.LivingExperienceDropEvent(event);

    }
    @SubscribeEvent
    public void the_heart(LivingDropsEvent event) {
        TheHeart.the_heart(event);
    }
    @SubscribeEvent
    public void effect(MobEffectEvent.Applicable event) {
        NightmareBaseBlackEye.exp(event);
        DivineFallRing.exp(event);
    }
    @SubscribeEvent
    public void effect(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getItem() instanceof ItemBase) {
                            if (stack.get(DataReg.tag) == null) {
                                stack.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void exp(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            float a = (float) player.getAttributeValue(AttReg.heal);
            if (a != 1) {
                event.setAmount(event.getAmount() * a);
            }
        }
        ReversalOrb.LivingHealEvent(event);
        BlackEyeHeart.heal(event);
        MRing.LivingExperienceDropEvent(event);
        UndeadBloodCharm.LivingHealEvent(event);
    }

    @SubscribeEvent
    public void exp(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player player) {
            float a = (float) player.getAttributeValue(AttReg.cit);
            if (a != 1 && event.isCriticalHit()) {
                event.setDamageMultiplier(event.getDamageMultiplier() * a);
            }
        }
        HiddenBlade.cit(event);
    }
    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingKnockBackEvent event) {
        MRing.LivingExperienceDropEvent(event);
    }
    @SubscribeEvent
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        CompoundTag  compoundTag =  stack.get(DataReg.tag);
        if (compoundTag !=null) {
            if (compoundTag.getBooleanOr(Difficulty.PEACEFUL.getSerializedName(),false)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.peaceful").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (compoundTag.getBooleanOr(Difficulty.EASY.getSerializedName(),false))  {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.easy").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (compoundTag.getBooleanOr(Difficulty.NORMAL.getSerializedName(),false)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.normal").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (compoundTag.getBooleanOr(Difficulty.HARD.getSerializedName(),false)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.hard").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }
            if (compoundTag.getBooleanOr(EquippedEvt.lootTable,false)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.god").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }

        }

    }
}
