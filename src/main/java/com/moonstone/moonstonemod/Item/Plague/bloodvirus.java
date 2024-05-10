package com.moonstone.moonstonemod.Item.Plague;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex.BloodViru;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class bloodvirus extends BloodViru {
    public bloodvirus (){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
        MinecraftForge.EVENT_BUS.addListener(this::sss);

        MinecraftForge.EVENT_BUS.addListener(this::sss);
        MinecraftForge.EVENT_BUS.addListener(this::sda);
        MinecraftForge.EVENT_BUS.addListener(this::ddd);
        MinecraftForge.EVENT_BUS.addListener(this::batskill);
    }

    private final String size = "SizeBlood";
    private void batskill(LivingHurtEvent event) {
        if (event.getSource()!= null&&event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(this)) {
                                if (stack.getTag()!= null){
                                    if (stack.getTag().getBoolean(batskill.batskill)){
                                        Bat b1 = new Bat(EntityType.BAT, player.level());
                                        b1.setPos(event.getEntity().position());
                                        Bat b12 = new Bat(EntityType.BAT, player.level());
                                        b12.setPos(event.getEntity().position());
                                        Bat b13 = new Bat(EntityType.BAT, player.level());
                                        b13.setPos(event.getEntity().position());
                                        if (Mth.nextInt(RandomSource.create(), 1, 7) == 1){
                                            player.level().addFreshEntity(b1);
                                            player.level().addFreshEntity(b12);
                                            player.level().addFreshEntity(b13);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(this)) {
                                if (stack.getTag()!= null){
                                    if (stack.getTag().getBoolean(batskill.batskill)){
                                        Vec3 playerPos = player.position();
                                        int range = 12;
                                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                                        int integers = 0;
                                        for (LivingEntity living : entities) {
                                            if (living instanceof Bat){
                                                integers++;
                                            }
                                        }
                                        float integer = integers;
                                        integer /= 20;
                                        if (integer > 0.8){
                                            integer = 0.8f;
                                        }
                                        event.setAmount(event.getAmount()*(1-integer));
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
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof batskill) {
                    if (me.getTag() != null) {
                        if (!me.getTag().getBoolean(batskill.batskill)) {
                            if (me.getTag().getInt(size) < 2) {
                                me.getTag().putBoolean(batskill.batskill, true);
                                Other.shrink(1);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }



    private void sss(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (event.getItem().getFoodProperties(player)!= null && !event.getItem().getFoodProperties(player).isMeat()){
                    player.addEffect( new MobEffectInstance(MobEffects.WEAKNESS, 300 ,1));
                    player.addEffect( new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300 ,1));
                    player.addEffect( new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300 ,1));

                }
            }
        }
    }
    private void ddd(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                event.setStrength(event.getStrength() * 2);
            }
        }

    }
    private void sda(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                event.setAmount(event.getAmount() * 0.7f);
            }
        }

    }
    private void aaa(LivingHurtEvent event) {
        if (event.getSource()!= null) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (Handler.hascurio(player, this)) {
                    if (player.level().isDay()) {
                        if (player.level().canSeeSkyFromBelowWater(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))) {
                            event.setAmount(event.getAmount() * 0.25f);
                        }
                    }


                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 9, 0.33, 0.33, 0.33, 0);
                        serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 9, 0.33, 0.33, 0.33, 0);
                    }
                    if (Handler.hascurio(player, this)) {
                        player.heal(event.getAmount() * 0.1f);
                        if (event.getEntity() instanceof Mob mob) {
                            if (!mob.isInvertedHealAndHarm()) {
                                event.setAmount(event.getAmount() * 1.25f);
                            }
                        }
                        if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                            player.heal(event.getAmount() * 0.2f);
                            event.getEntity().hurt(event.getSource(), 8);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (stack.getTag()!= null){
                if (stack.getTag().getBoolean(batskill.batskill)){
                    Vec3 playerPos = player.position().add(0, 0.75, 0);
                    int range = 12;
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                    for (LivingEntity living : entities){
                        if (!living.is(player)){
                            if (living instanceof Bat bat){
                                if (bat.tickCount % 20 == 0) {
                                    bat.setHealth(bat.getHealth() - (bat.getMaxHealth() / 10));
                                }
                            }
                        }
                    }
                }

            }
            if (!Handler.hascurio(player, Items.sleepgene.get())) {
                if (!player.level().isClientSide && player.tickCount % 10 == 0) {
                    if (player.level().isDay()) {
                        if (player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))) {
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 1));
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 1));
                            player.setSecondsOnFire(3);
                        }
                    } else {
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0));

                    }
                }
            }
        }
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.necora.get())){
                return false;
            }
            return !Handler.hascurio(player, stack.getItem());

        }
        return true;

    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(  slotContext.entity()));
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.getAttributeModifiers(  slotContext.entity()));
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(LivingEntity player) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("ac41f76c-b1dd-32f9-a5d3-3eb94da3e653");
        modifierMultimap.put(Attributes.MAX_HEALTH,
                new AttributeModifier(

                        uuid,
                        MoonStoneMod.MODID+"ec",
                        -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL)

        );


       return modifierMultimap;
    }

    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.bloodvirus.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.5").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.6").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.7").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.8").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.9").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.bloodvirus.tool.string.10").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.bloodvirus.tool.string.11").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        if (stack.getTag() != null){
            if (stack.getTag().getBoolean(batskill.batskill)){
                tooltip.add(Component.translatable("item.bloodvirus.tool.string.12").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        }
    }
}
