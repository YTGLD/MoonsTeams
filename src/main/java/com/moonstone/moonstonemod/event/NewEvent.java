package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.DNAItems;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.giant_boom_cell;
import com.moonstone.moonstonemod.item.TheNecora.god.GodAmbush;
import com.moonstone.moonstonemod.item.TheNecora.god.GodPutrefactive;
import com.moonstone.moonstonemod.item.TheNecora.small.enhancemen;
import com.moonstone.moonstonemod.item.amout.twistedamout;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.blood_sun;
import com.moonstone.moonstonemod.item.blood.magic.undead_blood_charm;
import com.moonstone.moonstonemod.item.decorated.deceased_contract;
import com.moonstone.moonstonemod.item.maxitem.book.nine_sword_book;
import com.moonstone.moonstonemod.item.maxitem.book.nine_sword_books;
import com.moonstone.moonstonemod.item.maxitem.god_lead;
import com.moonstone.moonstonemod.item.maxitem.malice_die;
import com.moonstone.moonstonemod.item.maxitem.rage.rage_charm;
import com.moonstone.moonstonemod.item.maxitem.rage.rage_lock;
import com.moonstone.moonstonemod.item.maxitem.rage.rage_magnet;
import com.moonstone.moonstonemod.item.maxitem.twelve_sword;
import com.moonstone.moonstonemod.item.nanodoom.as_amout;
import com.moonstone.moonstonemod.item.nanodoom.million;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.pain.pain_candle;
import com.moonstone.moonstonemod.item.pain.pain_ring;
import com.moonstone.moonstonemod.item.pain.the_pain_stone;
import com.moonstone.moonstonemod.item.plague.mobitem.dna;
import com.moonstone.moonstonemod.item.seven_star;
import com.moonstone.moonstonemod.item.universe;
import com.moonstone.moonstonemod.item.ytgld_virus;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class NewEvent {
    public static final String lootTable = "god_loot";
    public static final String die = "the_die";



    @SubscribeEvent
    public void killWither(LivingDeathEvent event) {
        seven_star.killWither(event);
        seven_star.killHealth(event);
        seven_star.dieNumber(event);
    }
    @SubscribeEvent
    public void killWither(LivingDamageEvent event) {
        seven_star.LivingDamageEventPre(event);
    }

    @SubscribeEvent
    public void killWither(TradeWithVillagerEvent event) {
        seven_star.villagerDrop(event);
    }

    @SubscribeEvent
    public void ItemTooltipEvent(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.getTag() !=null) {
            if (stack.getTag().getBoolean(EquippedEvt.isGod)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.true_god").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF4040)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
        }
    }
    @SubscribeEvent
    public  void RightClickItem(PlayerInteractEvent.RightClickItem event){
        max_blood_cube.RightClickItem(event);
    }
    @SubscribeEvent
    public  void necora(LivingEntityUseItemEvent.Finish event){
        medicinebox.necora(event);
        GodPutrefactive.eat(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        pain_candle.Heal(event);
        nightmare_head.LivingHealEvent(event);
        pain_ring.Heal(event);
        nightmare_axe.heals(event);
        undead_blood_charm.LivingHealEvent(event);
        rage_lock.LivingHealEvent(event);
        if (event.getEntity().getAttribute(AttReg.heal.get())!=null){
            float attack = (float) event.getEntity().getAttribute(AttReg.heal.get()).getValue();
            event.setAmount(event.getAmount()*(attack));
        }
    }
    @SubscribeEvent
    public void hurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.hurt.get())!=null){
                float hurt = (float) living.getAttribute(AttReg.hurt.get()).getValue();
                event.setAmount(event.getAmount()*(hurt));
            }
        }

    }
    @SubscribeEvent
    public void BreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().getAttribute(AttReg.break_speed.get())!=null){
            float attack = (float) event.getEntity().getAttribute(AttReg.break_speed.get()).getValue();
            event.setNewSpeed(event.getNewSpeed()*(attack));
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(CriticalHitEvent event) {
        if (event.getEntity().getAttribute(AttReg.cit.get())!=null){
            float attack = (float) event.getEntity().getAttribute(AttReg.cit.get()).getValue();
            event.setDamageModifier(event.getDamageModifier()*(attack));
        }
    }
    @SubscribeEvent
    public void all_attack(LivingHurtEvent event) {
        if (event.getSource().getEntity()instanceof LivingEntity living) {
            if (living.getAttribute(AttReg.all_attack.get()) != null) {
                float attack = (float) living.getAttribute(AttReg.all_attack.get()).getValue();
                event.setAmount(event.getAmount() * (attack));
            }
        }
    }
    @SubscribeEvent
    public void Night(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (stack.getItem() instanceof Nightmare){
            if (!Handler.hascurio(player,Items.nightmareeye.get())) {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.nightmare.name").withStyle(ChatFormatting.DARK_RED));
            }else {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.nightmare.name").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
            }
        }
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        pain_candle.Hurt(event);
        giant_boom_cell.Boom(event);
        bat_cell.Bat(event);
        pain_ring.Hurt(event);
        the_pain_stone.PainStoneAttack(event);
        twistedamout.hurt(event);
        max_eye.A(event);
        blood_amout.Hurt(event);
        as_amout.hurt(event);
        million.hurt(event);
        nine_sword_book.att(event);
        nine_sword_books.att(event);

        dna.hur(event);
        god_lead.hurtS(event);
        malice_die.att(event);
        nightmare_axe.att(event);
        twelve_sword.att(event);
        rage_lock.LivingIncomingDamageEvent(event);
        deceased_contract.attack(event);
        undead_blood_charm.LivingIncomingDamageEvent(event);
        ytgld_virus.LivingHurt(event);
        universe.attack(event);

        GodAmbush.LivingIncomingDamageEvent(event);
        if (event.getSource().getEntity() instanceof Player living) {
            if  (Handler.hascurio(living,Items.probability_stone.get())) {
                if (!living.getCooldowns().isOnCooldown(Items.probability_stone.get())) {
                    LivingDeathEvent deathEvent = new LivingDeathEvent(event.getEntity(), event.getSource());
                    MinecraftForge.EVENT_BUS.post(deathEvent);

                    living.getCooldowns().addCooldown(Items.probability_stone.get(),400);
                }
            }
        }

        if (Handler.hascurio(event.getEntity(),Items.acid.get())){
            event.setAmount(event.getAmount()*50);
        }
        if (Handler.hascurio(event.getEntity(),Items.compression.get())){
            event.setAmount(event.getAmount()*2);
        }
        if (event.getSource().getEntity() instanceof small_zombie smallZombie) {
            if (Handler.hascurio(smallZombie, Items.compression.get())){
                smallZombie.heal(20);
            }
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        the_pain_stone.PainStone(event);
        twistedamout.die(event);
        enhancemen.Death(event);
        the_prison_of_sin.LivingDeathEvent(event);
        max_eye.Die(event);
        blood_snake.Die(event);
        blood_magic_box.Did(event);
        nine_sword_book.die(event);
        blood_sun.Did(event);
        dna.dieD(event);
        nightmare_axe.Nig(event);
        rage_charm.die(event);
        rage_lock.LivingDeathEvent(event);
        deceased_contract.Did(event);
        ytgld_virus.ytgld_virusLivingDeathEvent(event);
    }
    @SubscribeEvent
    public  void pick(PlayerEvent.ItemPickupEvent event){
        rage_magnet.pick(event);
    }

    @SubscribeEvent
    public void Finish(LivingEntityUseItemEvent.Finish event) {
        dna.eat(event);
        ytgld_virus.Finish(event);
    }
    @SubscribeEvent
    public void Start(LivingEntityUseItemEvent.Start event) {
        dna.doBreak(event);
    }
    @SubscribeEvent
    public void PlayerInteractEvent(PlayerInteractEvent.EntityInteract event) {
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.acid.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.compression.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.atrophy.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.enhancemen.get(),"zombie");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.motor.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.watergen.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.air.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bat_cell.get(),"ncrdna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.adrenaline.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_blood.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_boom.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_calcification.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_mummy.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_nightmare.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bone_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.disgusting_cells.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.mother_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.parasitic_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.anaerobic_cell.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_boom_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.not_blood_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.subspace_cell.get(),"dna");


    }
    public void addV(ItemStack stack,Item Dhis,ItemTooltipEvent event,String string){
        if (stack.is(Dhis)) {
            event.getToolTip().add(1,Component.translatable(string).withStyle(ChatFormatting.RED));
        }
    }
    @SubscribeEvent
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        addV(stack, DNAItems.cell_disorder.get(),event,"item.moonstone.cell_disorder.tool");
        addV(stack,DNAItems.cell_god.get(),event,"item.moonstone.cell_god.tool");
        addV(stack,DNAItems.cell_inheritance.get(),event,"item.moonstone.cell_inheritance.tool");
        addV(stack,DNAItems.cell_big_boom.get(),event,"item.moonstone.cell_big_boom.tool");
        addV(stack,DNAItems.cell_darwin.get(),event,"item.moonstone.cell_darwin.tool");
        addV(stack,DNAItems.speed_metabolism.get(),event,"item.moonstone.speed_metabolism.tool");
        addV(stack,DNAItems.cell_acid.get(),event,"item.moonstone.cell_acid.tool");
        addV(stack,DNAItems.cell_eyes.get(),event,"item.moonstone.cell_eyes.tool");
        addV(stack,DNAItems.cell_digestion.get(),event,"item.moonstone.cell_digestion.tool");
        addV(stack,DNAItems.cell_cranial.get(),event,"item.moonstone.cell_cranial.tool");
        addV(stack,DNAItems.cell_compress.get(),event,"item.moonstone.cell_compress.tool");
        addV(stack,DNAItems.cell_flu.get(),event,"item.moonstone.cell_flu.tool");
        addV(stack,DNAItems.cell_constant.get(),event,"item.moonstone.cell_constant.tool");


        if (stack.getTag() !=null){
            if (stack.getTag().getBoolean("ALLBattery")){
                event.getToolTip().add(Component.translatable("item.moonstone.battery").withStyle(ChatFormatting.GOLD));
            }
        }

        if (stack.getItem() instanceof IBattery){
            event.getToolTip().add(Component.translatable("item.moonstone.tooltip.battery").withStyle(ChatFormatting.GOLD));

        }
        if (stack.getTag() !=null) {
            if (stack.getTag().getBoolean(Difficulty.PEACEFUL.getKey())) {

                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.peaceful").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.getTag().getBoolean(Difficulty.EASY.getKey())) {

                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.easy").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.getTag().getBoolean(Difficulty.NORMAL.getKey())) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.normal").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.getTag().getBoolean(Difficulty.HARD.getKey())) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.hard").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }
            if (stack.getTag().getBoolean(lootTable)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.god").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }

        }

    }

    public void PlayerInteractZombie(Player player, Entity target, Item doItem,String slot) {
        if (target instanceof small_zombie smallZombie){
            if (player.getMainHandItem().is(doItem)&&!player.isShiftKeyDown()){

                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            if (!Handler.hascurio(smallZombie,doItem)
                                    && stacksHandler.getIdentifier().contains(slot)) {
                                ItemStack present = stackHandler.getStackInSlot(i);
                                if (present.isEmpty()) {
                                    stackHandler.setStackInSlot(i, new ItemStack(doItem));
                                    player.getMainHandItem().shrink(1);
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.NEUTRAL, 1F, 1F);
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.NEUTRAL, 1F, 1F);
                                }
                            }
                        }
                    }
                });
            } else if (player.getMainHandItem().isEmpty()&&player.isShiftKeyDown()) {
                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            if (!stackHandler.getStackInSlot(i).isEmpty()
                                    && stackHandler.getStackInSlot(i).is(doItem)
                                    && stacksHandler.getIdentifier().contains(slot))
                            {

                                player.addItem(new ItemStack(doItem));
                                stackHandler.getStackInSlot(i).shrink(1);

                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void Book(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (stack.is(Items.nine_sword_books.get())) {
            if ( stack.getTag() != null) {
                float level = (1f + stack.getTag().getInt(nine_sword_books.lvl) / 10f);
                if (stack.getTag().getInt(nine_sword_books.small)>900){
                    level*=0.875f;
                }
                event.getToolTip().add(1, Component.translatable("item.nine_sword_books.tool.string.14").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.13").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.12").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.11").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.10").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.9").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.8").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.7").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.literal("+").append(String.valueOf(1)).append(Component.translatable("item.nine_sword_book.tool.string.6")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 1f * level)).append("%").append(Component.translatable("item.nine_sword_book.tool.string.5")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 3f * level)).append("%").append(Component.translatable("item.nine_sword_book.tool.string.4")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 5f * level)).append("%").append(Component.translatable("item.nine_sword_book.tool.string.3")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 10f * level)).append("%").append(Component.translatable("item.nine_sword_book.tool.string.2")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.1").withStyle(ChatFormatting.GOLD));
            }
        }

        if (stack.is(Items.nine_sword_book.get())) {
            if ( stack.getTag() != null) {
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.13").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.12").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.11").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.10").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.9").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.8").append(String.format("%.2f", (30f-((stack.getTag().getInt(nine_sword_book.small))/40f)))).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.7").withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.literal("+").append(String.valueOf(1)).append(Component.translatable("item.nine_sword_book.tool.string.6")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", ((stack.getTag().getInt(nine_sword_book.small)/77F/4f)))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.5")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", ((stack.getTag().getInt(nine_sword_book.small)/35F/7f)))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.4")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", ((stack.getTag().getInt(nine_sword_book.attackSpeedLvlSmall)/20F/5F)))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.3")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", ((stack.getTag().getInt(nine_sword_book.attackLvlsmall)/15f/6F)))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.2")).withStyle(ChatFormatting.GOLD));
                event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.1").withStyle(ChatFormatting.GOLD));
            }
        }
    }
}
