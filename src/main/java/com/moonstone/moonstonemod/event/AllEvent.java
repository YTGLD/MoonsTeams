package com.moonstone.moonstonemod.event;

import com.google.common.collect.Lists;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.cell_giant;
import com.moonstone.moonstonemod.entity.cell_zombie;
import com.moonstone.moonstonemod.entity.flysword;
import com.moonstone.moonstonemod.entity.suddenrain;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.Particles;
import com.moonstone.moonstonemod.item.Perhaps;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_blood;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_boom;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_calcification;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_mummy;
import com.moonstone.moonstonemod.item.buyme.wind_and_rain;
import com.moonstone.moonstonemod.item.maxitem.the_heart;
import com.moonstone.moonstonemod.item.nanodoom.thefruit;
import com.moonstone.moonstonemod.item.plague.ALL.dna;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.item.uncommon.evilmug;
import com.moonstone.moonstonemod.item.uncommon.plague;
import com.moonstone.moonstonemod.moonstoneitem.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class AllEvent {
    private int shield = 1;
    private int Kidney = 100;
     private float clientSideAttackTime = 0;

     public static final String cod = "cod";
     public static final String SALMON = "SALMON";
     public static final String CHICKEN = "CHICKEN";
     public static final String BEEF = "BEEF";
     public static final String RABBIT = "RABBIT";
     public static final String MUTTON = "MUTTON";
     public static final String PORKCHOP = "PORKCHOP";
     public static final String TROPICAL_FISH = "TROPICAL_FISH";
     public static final String give = "GiveItem";
     public static final String fungus = "GiveFungus";
     public static final String virus = "virus";
    public static float aFloat = 0;
    public static final String lvl= "germString";
    public static final String lvlSize= "germStringLvlSize";
    public static String lvl_parasite = "lvl";
    public static String sizeLevel = "sizeLevel";
    public static String blood = "bloodgene";
    public static String rage = "ragegene";
    public static String FlyEye = "FlyNecoraorb";
    public static String FlySword = "FlySword";

    public static final String DamageCell = "DamageCell";
    public static final  String muMMY = cell_mummy.Mummy;
    public static final  String boom = cell_boom.cb;
    public static final  String calcification = cell_calcification.cc;
    public static final  String cb_blood = cell_blood.c_blood;
    public static final String Gorillas ="Gorillas";

    @SubscribeEvent
    public void the_heart(LivingDropsEvent event){
        if ((event.getSource().getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.the_heart.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    CompoundTag compoundtag = stack.getOrCreateTag();
                                    ListTag listtag = compoundtag.getList("Items", 10);
                                    for(int s = 0; s < listtag.size(); ++s){
                                        CompoundTag compoundtag1 = listtag.getCompound(s);
                                        ItemStack itemstack = ItemStack.of(compoundtag1);

                                        Collection<ItemEntity> drop = event.getDrops();
                                        for (ItemEntity entity : drop) {
                                            ItemStack i_stack = entity.getItem();
                                            if (i_stack.is(itemstack.getItem())) {
                                                if (!(i_stack.getMaxStackSize() < 3)) {
                                                    i_stack.setCount(i_stack.getCount() * 3);
                                                    entity.setItem(i_stack);
                                                }
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
    @SubscribeEvent
    public void Boom(LivingHurtEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.cell_boom.get())){
                if (event.getSource().is(DamageTypes.EXPLOSION)){
                    event.setAmount(0);
                }
            }
        }
    }
    @SubscribeEvent
    public void gen(LivingHurtEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.air.get())){
                if (!player.onGround()){
                    event.setAmount(event.getAmount() * 0.8f);
                }
                if (!player.isInWater()){
                    event.setAmount(event.getAmount() * 0.85f);
                }
            }
        }
    }
    @SubscribeEvent
    public void evil(LivingDeathEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.cell_boom.get())){
                player.level().explode(null,player.getX(),player.getY(),player.getZ(),5.5f,true , Level.ExplosionInteraction.MOB);
            }
        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.giant.get())){
                if (!player.getCooldowns().isOnCooldown(Items.giant.get())) {
                    if (Mth.nextInt(RandomSource.create(), 1, 20) == 1) {
                        cell_giant z = new cell_giant(EntityTs.cell_giant.get(), player.level());
                        z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                        z.setOwnerUUID(player.getUUID());
                        player.level().addFreshEntity(z);
                        player.getCooldowns().addCooldown(Items.giant.get(), 600);
                    }
                }
            }
            if (Handler.hascurio(player, Items.cell.get())){
                if (Mth.nextInt(RandomSource.create(),1, 2) ==1 ){
                    cell_zombie z = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                    z.teleportTo(event.getEntity().getX(),event.getEntity().getY(), event.getEntity().getZ());
                    z.setOwnerUUID(player.getUUID());
                    if (Handler.hascurio(player,Items.adrenaline.get())){
                        z.addTag(DamageCell);
                    }
                    if (Handler.hascurio(player,Items.cell_mummy.get())){
                        z.addTag(muMMY);
                    }
                    if (Handler.hascurio(player,Items.cell_boom.get())){
                        z.addTag(boom);
                    }
                    if (Handler.hascurio(player,Items.cell_calcification.get())){
                        z.addTag(calcification);
                    }
                    if (Handler.hascurio(player,Items.cell_blood.get())){
                        z.addTag(cb_blood);
                    }
                    player.level().addFreshEntity(z);
                }
            }
        }
    }
    @SubscribeEvent
    public void evil_zombie(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 10;
            List<cell_zombie> entities = player.level().getEntitiesOfClass(cell_zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            if (Handler.hascurio(player, Items.giant.get())) {
                List<cell_giant> entitie = player.level().getEntitiesOfClass(cell_giant.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (cell_giant zombie : entitie) {
                    if (zombie.getOwner() == player) {
                        if (!(event.getEntity() instanceof cell_giant)) {
                            zombie.setTarget(event.getEntity());
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.cell.get())){
                 for (cell_zombie zombie : entities){
                    if (zombie.getOwner() == player){
                        if (!(event.getEntity() instanceof cell_zombie)) {
                            zombie.setTarget(event.getEntity());
                        }
                    }
                }
            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.cell.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<cell_zombie> entities = player.level().getEntitiesOfClass(cell_zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (cell_zombie zombie : entities){
                    if (zombie.getOwner() == player){
                        if (!(event.getSource().getEntity() instanceof cell_zombie)) {
                            if (event.getSource().getEntity() instanceof LivingEntity living) {
                                zombie.setTarget(living);
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.giant.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<cell_giant> entities = player.level().getEntitiesOfClass(cell_giant.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (cell_giant zombie : entities){
                    if (zombie.getOwner() == player){
                        if (!(event.getSource().getEntity() instanceof cell_giant)) {
                            if (event.getSource().getEntity() instanceof LivingEntity living) {
                                zombie.setTarget(living);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void beacon(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.beacon.get())){
                Collection<MobEffectInstance> collection  = player.getActiveEffects();
                if (!collection.isEmpty()) {
                    if (event.getSource().getEntity() != null) {
                        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(event.getSource().getEntity().level(), event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ());
                        areaeffectcloud.setRadius(2.5F);
                        areaeffectcloud.setRadiusOnUse(-0.5F);
                        areaeffectcloud.setWaitTime(10);
                        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
                        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
                        for(MobEffectInstance mobeffectinstance : collection) {
                            areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
                        }
                        event.getSource().getEntity().level().addFreshEntity(areaeffectcloud);
                        player.removeAllEffects();
                    }
                }
            }
        }
    }



    @SubscribeEvent
    public void brainLHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.brain.get())){
                String name = event.getEntity().getName().getString();
                player.getPersistentData().putInt(name, player.getPersistentData().getInt(name) +1);
                if (player.getPersistentData().getInt(name)>= Config.m_brain_many.get()){
                    event.setAmount((float) (event.getAmount() * Config.m_brain_critical.get()));
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.NEUTRAL, 4.5F, 4.1F);
                    player.getPersistentData().remove(name);
                }
            }
        }

    }
    @SubscribeEvent
    public void evil(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.evilcandle.get())){
                if (event.getSource().is(DamageTypes.IN_FIRE)||
                        event.getSource().is(DamageTypes.ON_FIRE)||
                        event.getSource().is(DamageTypes.LAVA))
                {
                    event.setAmount(event.getAmount() * 0.2f);
                }
            }
        }
    }
    @SubscribeEvent
    public void suddenrainLLivingHurtEvent(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomswoud.get())){
                if (!player.getCooldowns().isOnCooldown(Items.doomswoud.get())){
                    float speed = player.getSpeed();
                    speed/=0.1f;
                    int a = (int) (speed - 1) * 5;
                    a += 2;
                    for (int i = 0;i<a;i++) {
                        float s  = (float) Math.sin(i);
                        if (s <= 0){
                            s = 0.12f;
                        }
                        suddenrain item = new suddenrain(EntityTs.suddenrain.get(),player.level());
                        item.teleportTo(player.getX()+Mth.nextFloat(RandomSource.create(), -s,s),player.getY()+2+s,player.getZ()+Mth.nextFloat(RandomSource.create(), -s,s));
                        item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),s/1.5f,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                        player.level().addFreshEntity(item);
                        player.getCooldowns().addCooldown(Items.doomswoud.get(), 20);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void suddenrainLivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomswoud.get())) {
                for (int i = 0; i < 4; i++) {
                    float s = (float) Math.sin(i);
                    if (s <= 0) {
                        s = 0.12f;
                    }
                    suddenrain item = new suddenrain(EntityTs.suddenrain.get(), player.level());
                    item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                    item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f), s / 1.5f, Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f));
                    player.level().addFreshEntity(item);
                    player.getCooldowns().addCooldown(Items.doomswoud.get(), 50);
                }
            }
        }
    }

    @SubscribeEvent
    public void suddenrainLivingTickEvent(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = event.getEntity();
            Vec3 position = livingEntity.position().add(0,0.75,0);
            int is = 16;
            List<suddenrain> items = livingEntity.level().getEntitiesOfClass(suddenrain.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
            for (suddenrain item : items) {
                if (!Handler.hascurio(livingEntity,Items.doomswoud.get())) {
                    if (item.isAlive()) {
                        if (item.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(Particles.popr.get(), item.getX(), item.getEyeY(), item.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
                        }
                        Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                        if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                            motion = motion.normalize();
                        }
                        if (item.age > 30) {
                            item.setDeltaMovement(motion.scale(1));
                        } else {
                            item.setDeltaMovement(motion.scale(-0.25));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void doomeyeLLivingTickEvent(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = event.getEntity();
            Vec3 position = livingEntity.position();
            int is = 16;
            List<flysword> items = livingEntity.level().getEntitiesOfClass(flysword.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
            for (flysword item : items) {
                if (!Handler.hascurio(livingEntity,Items.doomeye.get())) {
                    if (item.isAlive()) {

                        if (item.age > 20) {
                            if (item.level() instanceof ServerLevel serverLevel) {
                                serverLevel.sendParticles(Particles.blue.get(), item.getX(), item.getEyeY(), item.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
                            }
                            if (item.getTags().contains(FlySword)) {
                                if (item.level() instanceof ServerLevel serverLevel) {
                                    serverLevel.sendParticles(Particles.blue.get(), item.getX(), item.getEyeY(), item.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
                                }
                                Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setDeltaMovement(motion.scale(0.5));
                            }
                        }
                    }
                }
            }
        }

    }
    @SubscribeEvent
    public void doomeyeLivingKnockBackEvent(LivingKnockBackEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomeye.get())){
                if (!player.getCooldowns().isOnCooldown(Items.doomeye.get())){
                    for (int i = 0 ;i < 7 ;i++){
                        float s  = (float) Math.sin(i);
                        if (s <= 0){
                            s = 0.12f;
                        }
                        flysword item = new flysword(EntityTs.flysword.get(),player.level());
                        item.teleportTo(player.getX()+Mth.nextFloat(RandomSource.create(), -s,s),player.getY()+2+s,player.getZ()+Mth.nextFloat(RandomSource.create(), -s,s));
                        item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),s/1.5f,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                        item.setNoGravity(true);
                        item.addTag(FlySword);
                        player.level().addFreshEntity(item);
                        player.getCooldowns().addCooldown(Items.doomeye.get(), 40);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.plague.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        stack.getTag().putFloat(plague.YanJIu, (float) (stack.getOrCreateTag().getFloat(plague.YanJIu)+0.1));

                                        if (!stack.getTag().getBoolean(plague.YanJIuBoolean)){
                                            player.displayClientMessage(Component.translatable(""+(stack.getOrCreateTag().getFloat(plague.YanJIu))).append("%").withStyle(ChatFormatting.RED), true);

                                            if (stack.getOrCreateTag().getFloat(plague.CursePlague)<100) {
                                                stack.getOrCreateTag().putFloat(plague.CursePlague, stack.getOrCreateTag().getFloat(plague.CursePlague) + 0.1f);
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

    @SubscribeEvent
    public void plagueLivingHurtEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.plague.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        if (stack.getTag().getBoolean(plague.YanJIuBoolean)){
                                            if (event.getEntity() instanceof Mob){
                                                event.setAmount(event.getAmount() *2);
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
    @SubscribeEvent
    public void plagueLBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() instanceof Player){

            if (Handler.hascurio(event.getEntity(),  Items.plague.get())){
                CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        if (stack.getTag().getBoolean(plague.YanJIuBoolean)){

                                            event.setNewSpeed(event.getNewSpeed() * 1.7f);
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
    @SubscribeEvent
    public void plagueLivingHurtEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.plague.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        if (stack.getTag().getBoolean(plague.YanJIuBoolean)){

                                            event.setAmount(event.getAmount() *2);
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
    @SubscribeEvent
    public void Boss(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.plague.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        if (stack.getTag().getBoolean(plague.YanJIuBoolean)){
                                            if (event.getEntity().getMaxHealth() > player.getHealth() * 10){
                                                event.setAmount(event.getAmount() *2.5f);
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
    @SubscribeEvent
    public void plague(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.plague.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.plague.get())) {
                                        stack.getTag().putFloat(plague.YanJIu, (float) (stack.getOrCreateTag().getFloat(plague.YanJIu) + Config.plague_speed.get()));

                                        if (!stack.getTag().getBoolean(plague.YanJIuBoolean)){
                                            player.displayClientMessage(Component.translatable(""+(stack.getOrCreateTag().getFloat(plague.YanJIu))).append("%").withStyle(ChatFormatting.RED), true);

                                            if (stack.getOrCreateTag().getFloat(plague.CursePlague)<100) {
                                                stack.getOrCreateTag().putFloat(plague.CursePlague, stack.getOrCreateTag().getFloat(plague.CursePlague) + 0.1f);
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
    @SubscribeEvent
    public void FlyEye(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.flyeye.get())){
                player.invulnerableTime += (int) (player.invulnerableTime / 4);
            }
        }

    }
    @SubscribeEvent
    public void FlyEye(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.flyeye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.getTag() != null) {
                                    if (stack.is(Items.flyeye.get())) {
                                        event.setStrength((float) ((event.getStrength() + 1) * 3));
                                        stack.getTag().putBoolean(FlyEye, true);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

    }
    @SubscribeEvent
    public void thefruitLivingTickEvent(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof Player player){

            /*
            CuriosApi.getCuriosInventory(player).ifPresent((handler)->{
                for (AttributeModifier attributeModifier : handler.getModifiers().values()){
                    if (attributeModifier.equals(com.moonstone.moonstonemod.item.gorillacake.attributeModifier())){
                        player.getPersistentData().putBoolean(gorillacake.gorillacake,true);
                    }
                }
            });


             */
            CuriosApi.getCuriosInventory(player).ifPresent((handler)->{
                for (AttributeModifier attributeModifier : handler.getModifiers().values()){
                    if (attributeModifier.equals(thefruit.attributeModifier())){
                        player.getPersistentData().putBoolean(thefruit.thefruit,true);
                    }
                }
            });

            if (player.getPersistentData().getBoolean(thefruit.thefruit)){
                if (player.hasEffect(MobEffects.GLOWING)) {
                    player.removeEffect(MobEffects.GLOWING);
                }
            }

        }
    }
    @SubscribeEvent
    public void badgeofthedead(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.badgeofthedead.get())){
                if (event.getEntity()  instanceof Mob mob){
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount()*1.25f);
                    }
                }
            }
        }

    }
    @SubscribeEvent
    public void necora(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.necora.get())){
                if (Mth.nextInt(RandomSource.create(), 1, 3) == 1){
                    event.getDrops().add(new ItemEntity(event.getEntity().level() , event.getEntity().getX(), event.getEntity().getY(),event.getEntity().getZ(), new ItemStack(net.minecraft.world.item.Items.ROTTEN_FLESH)));
                }
            }
        }
    }

    @SubscribeEvent
    public void necora(LivingHurtEvent event) {
        if (event.getSource()!= null&&event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.necora.get())) {
                event.setAmount(event.getAmount() * 0.9f);

                if (event.getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount() * 0.8f);
                    }
                }

            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.necora.get())) {
                if (event.getSource() != null && event.getSource().getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount() / 2);
                    }
                }

                if (event.getSource().is(DamageTypes.MAGIC)) {
                    event.setAmount(event.getAmount() * 0.3f);
                }
            }
        }
    }

    @SubscribeEvent
    public void necora(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        if (Handler.hascurio(player, Items.necora.get())){
            if (event.getItemStack().is(net.minecraft.world.item.Items.ROTTEN_FLESH)){
                player.startUsingItem(event.getHand());
            }
        }
    }

    @SubscribeEvent
    public void necora(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()){
                            if (stack.is(Items.necora.get())){
                                if (event.getItem().is(net.minecraft.world.item.Items.ROTTEN_FLESH)){
                                    if (!Handler.hascurio(player, Items.putrefactive.get())) {
                                        player.heal(player.getMaxHealth() / 20);
                                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));
                                    }else {
                                        player.heal(player.getMaxHealth() / 15);
                                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
                                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 1));
                                    }
                                }
                            }
                        }
                    }
                }
            });

        }
    }

    @SubscribeEvent
    public void batskill(LivingHurtEvent event) {
        if (event.getSource()!= null&&event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is( Items.bloodvirus.get())) {
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
                            if (stack.is( Items.bloodvirus.get())) {
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

    @SubscribeEvent
    public void bloodvirusLiving(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.bloodvirus.get())){
                if (event.getItem().getFoodProperties(player)!= null && !event.getItem().getFoodProperties(player).isMeat()){
                    player.addEffect( new MobEffectInstance(MobEffects.WEAKNESS, 300 ,1));
                    player.addEffect( new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300 ,1));
                    player.addEffect( new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300 ,1));

                }
            }
        }
    }
    @SubscribeEvent
    public void bloodvirusLiving(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.bloodvirus.get())){
                event.setStrength(event.getStrength() * 2);
            }
        }

    }
    @SubscribeEvent
    public void bloodvirusLiving(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.bloodvirus.get())){
                event.setAmount(event.getAmount() * 0.7f);
            }
        }

    }
    @SubscribeEvent
    public void bloodvirusLiving(LivingHurtEvent event) {
        if (event.getSource()!= null) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (Handler.hascurio(player, Items.bloodvirus.get())) {
                    if (player.level().isDay()) {
                        if (player.level().canSeeSkyFromBelowWater(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))) {
                            event.setAmount(event.getAmount() * 0.25f);
                        }
                    }


                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 9, 0.33, 0.33, 0.33, 0);
                        serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 9, 0.33, 0.33, 0.33, 0);
                    }
                    if (Handler.hascurio(player,  Items.bloodvirus.get())) {
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
    @SubscribeEvent
    public void blueamout(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.blueamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.BLUE_WOOL.defaultBlockState()));
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.blueamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.BLUE_WOOL.defaultBlockState()));
                }
            }
        }

    }
    @SubscribeEvent
    public void redamout(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.redamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    event.getEntity().knockback(0.2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                }
            }
        }
    }
    @SubscribeEvent
    public void greedamout(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.greedamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));

                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.greedamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));

                    player.heal(4);
                }
            }
        }

    }

    @SubscribeEvent
    public void maxamout(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.maxamout.get())) {

                if (event.getSource().getEntity() != null) {
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (!(living instanceof Guardian)) {
                            if (event.getSource().getEntity() != null) {
                                living.hurt(living.damageSources().playerAttack(player), event.getAmount() / 5);
                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);

                            }
                        }
                    }
                }

                event.setAmount(event.getAmount() * 0.85f);
                if (Mth.nextInt(RandomSource.create(),1, 5) == 1){
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20, 2));
                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.maxamout.get())) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
                player.heal(event.getAmount() / 20);

                if (Mth.nextInt(RandomSource.create(), 1, 12) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0));
                    event.getEntity().knockback(0.2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.YELLOW_WOOL.defaultBlockState()));
                }
            }
        }
    }

    @SubscribeEvent
    public void fermentation(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fermentation.get())){

                if (player.getCooldowns().isOnCooldown(Items.fermentation.get())){
                    event.setAmount(event.getAmount() * 0.3f);
                }else {
                    event.setAmount(event.getAmount() * 4);
                    player.getCooldowns().addCooldown(Items.fermentation.get(), 200);
                }
            }
        }
    }
    @SubscribeEvent
    public  void reanimation(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.reanimation.get())){
                if (!player.getCooldowns().isOnCooldown(Items.reanimation.get())) {
                    if (event.getAmount() > player.getHealth()) {
                        player.heal(player.getMaxHealth() / 2);
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);

                        player.getCooldowns().addCooldown(Items.reanimation.get(), 3000);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public  void masticatory(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.masticatory.get())){
                if (event.getItem().getUseAnimation() == UseAnim.EAT){
                    event.setDuration(event.getDuration() / 2);
                }
            }
        }

    }
    @SubscribeEvent
    public void calcification(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.calcification.get())){
                event.setAmount(event.getAmount() * 0.89f);
            }
        }

    }
    @SubscribeEvent
    public  void die_KILL(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                if (stack.getTag() != null) {
                                    if (event.getEntity() instanceof EnderDragon) {
                                        if (stack.getTag().getBoolean(blood_eat) &&
                                                stack.getTag().getBoolean(blood_hurt) &&
                                                stack.getTag().getBoolean(blood_jump) &&
                                                stack.getTag().getBoolean(blood_spawn) &&
                                                stack.getTag().getBoolean(blood_enchant)) {
                                            player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.catalyzer.get()));
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
    public static String jump_size = "jump_size";
    public static String hurt_size ="hurt_size";
    public static String apple = "apple";

    public static String spawn= "spawn";
    public static String enchant= "enchant";

    public static final String blood_hurt = "blood_hurt";
    public static  final String blood_jump = "blood_jump";
    public static  final String blood_eat = "blood_eat";
    public static  final String blood_spawn = "blood_spawn";
    public static  final String blood_enchant = "blood_enchant";

    @SubscribeEvent
    public  void die(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        if (stack.is(Items.medicinebox.get())) {
                            if (stack.getTag() != null && !stack.getTag().getBoolean(spawn)) {
                                if (Handler.hascurio(player, Items.medicinebox.get())) {
                                    player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.reanimation.get()));
                                    stack.getTag().putBoolean(spawn, true);
                                    stack.getTag().putBoolean(blood_spawn, true);
                                }
                            }
                        }
                    }
                }
            }
        });

    }
    @SubscribeEvent
    public  void apple(LivingEntityUseItemEvent.Finish event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
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

                                    if (stack.getTag()!= null&&stack.getTag().getInt(apple)< 9){
                                        stack.getTag().putInt(apple,stack.getTag().getInt(apple)+1);
                                    }
                                    if (stack.getTag()!= null&&stack.getTag().getInt(apple)== 8){
                                        player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.masticatory.get()));
                                        stack.getTag().putBoolean(blood_eat, true);
                                    }

                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public  void medicinebox(LivingHurtEvent event) {


        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {

                                if (stack.getTag()!= null&&stack.getTag().getInt(hurt_size)< 351){
                                    stack.getTag().putInt(hurt_size,stack.getTag().getInt(hurt_size)+1);
                                }
                                if (stack.getTag()!= null&&stack.getTag().getInt(hurt_size)== 350){
                                    player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.calcification.get()));
                                    stack.getTag().putBoolean(blood_hurt, true);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void medicinebox(LivingEvent.LivingJumpEvent event) {


        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {

                                if (stack.getTag()!= null&&stack.getTag().getInt(jump_size)< 501){
                                    stack.getTag().putInt(jump_size,stack.getTag().getInt(jump_size)+1);

                                }
                                if (stack.getTag()!= null&&stack.getTag().getInt(jump_size)== 500){
                                    player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.quadriceps.get()));
                                    stack.getTag().putBoolean(blood_jump, true);

                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void medicinebox(LivingEntityUseItemEvent.Finish event) {

        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
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

                                    if (stack.getTag() != null && !stack.getTag().getBoolean(enchant)) {
                                        player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.polyphagia.get()));
                                        stack.getTag().putBoolean(enchant, true);
                                        stack.getTag().putBoolean(blood_enchant, true);

                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.ragegene.get())) {
                                if (player.getAttackStrengthScale(1) == 1) {
                                    if (stack.getTag() != null && stack.getTag().getInt(rage) < 100) {
                                        stack.getTag().putFloat(rage, stack.getTag().getInt(rage) + 5);
                                    }
                                    event.setAmount(event.getAmount() * (1 + ((float) stack.getTag().getInt(rage) / 100)));
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
                            if (stack.is(Items.ragegene.get())) {
                                if (stack.getTag()!= null){
                                    stack.getTag().putFloat(rage,0);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void bloodgene(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.bloodgene.get())) {
                                event.setAmount(event.getAmount()* 1.1f);
                                if (stack.getTag()!= null&&stack.getTag().getInt(blood)< 150){
                                    stack.getTag().putFloat(blood,stack.getTag().getInt(blood)+5);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void bloodgene(LivingHealEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.bloodgene.get())) {
                                if (stack.getTag()!= null&&stack.getTag().getInt(blood)> 0){
                                    stack.getTag().putFloat(blood,stack.getTag().getInt(blood) -  1);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void batgene(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.batgene.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                int integers = 0;
                for (LivingEntity living : entities) {
                    if (living instanceof Bat){
                        integers++;
                    }
                }
                float integer = integers;
                integer /= 10;
                event.setAmount((event.getAmount() - event.getAmount() / 10) * (1 + integer));

            }
        }
    }
    @SubscribeEvent
    public  void virus(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.getItem() instanceof com.moonstone.moonstonemod.item.plague.ALL.virus) {
                                if (stack.getTag() != null) {
                                    String name = event.getEntity().getEncodeId();
                                    if (name != null) {
                                        int size = stack.getTag().getInt(name);
                                        float Do = (float) size / 400;
                                        event.setAmount(event.getAmount() * (1 + Do));

                                    }
                                }
                            }

                        }
                    }
                });
            }

        }
    }
    @SubscribeEvent
    public  void virus(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.virus.get())) {
                                if (stack.getTag() != null) {
                                    String name = event.getEntity().getEncodeId();
                                    if (name != null) {
                                        if (stack.getTag().getInt(name) < 400) {
                                            stack.getTag().putInt(name, stack.getTag().getInt(name) + 1);

                                            if (!player.level().isClientSide) {
                                                player.displayClientMessage(Component.translatable("allevent.moonstone.virus").append(name).withStyle(ChatFormatting.RED), true);
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
    @SubscribeEvent
    public  void parasite(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.parasite.get())) {
                            if (Handler.hascurio(player, Items.parasite.get())) {
                                if (event.getAmount() >player.getHealth()){
                                    if (stack.getOrCreateTag().getInt(sizeLevel) > 900){
                                        if (player.getFoodData().getFoodLevel() > player.getFoodData().getFoodLevel() * 0.6){
                                            event.setAmount(event.getAmount() * 1.4f);
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
    @SubscribeEvent
    public  void parasite(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.parasite.get())) {
                            if (stack.getTag()!= null) {
                                if (Handler.hascurio(player, Items.parasite.get())) {
                                    if (Handler.hascurio(player, Items.parasite.get())) {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                            if (event.getItem().getFoodProperties(player) != null) {
                                                int siz = (int) (event.getItem().getFoodProperties(player).getNutrition() + event.getItem().getFoodProperties(player).getSaturationModifier());
                                                if (stack.getOrCreateTag().getInt(lvl_parasite) <= 1) {
                                                    siz /= 2;
                                                    stack.getOrCreateTag().putInt(sizeLevel, stack.getOrCreateTag().getInt(sizeLevel) + siz);

                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                                                    }
                                                }
                                                if (stack.getOrCreateTag().getInt(lvl_parasite) <= 2 && stack.getOrCreateTag().getInt(lvl_parasite) > 1) {
                                                    siz /= 3;
                                                    stack.getOrCreateTag().putInt(sizeLevel, stack.getOrCreateTag().getInt(sizeLevel) + siz);
                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 1);
                                                    }
                                                }
                                                if (stack.getOrCreateTag().getInt(lvl_parasite) <= 3 && stack.getOrCreateTag().getInt(lvl_parasite) > 2) {
                                                    siz /= 4;
                                                    stack.getOrCreateTag().putInt(sizeLevel, stack.getOrCreateTag().getInt(sizeLevel) + siz);
                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                                                        player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 1);
                                                    }
                                                }
                                                if (!player.level().isClientSide) {
                                                    player.displayClientMessage(Component.translatable("寄生虫增长了" + (siz) + "克").withStyle(ChatFormatting.RED), true);
                                                }
                                            }
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
    @SubscribeEvent
    public  void germ(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        if (Handler.hascurio(player, Items.germ.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        {
                            if (stack.getTag() != null) {

                                int s = 250;
                                stack.getTag().putInt(lvlSize,  stack.getTag().getInt(lvlSize)+1);
                                if (stack.getTag().getInt(lvlSize) > s && stack.getTag().getInt(lvlSize) < s*2){
                                    if (stack.getTag().getInt(lvl)!= 1) {
                                        stack.getTag().putInt(lvl, 1);
                                    }
                                }
                                if (stack.getTag().getInt(lvlSize) >  s*2 && stack.getTag().getInt(lvlSize) <  s*3){
                                    if (stack.getTag().getInt(lvl)!= 2) {
                                        stack.getTag().putInt(lvl, 2);
                                    }
                                }
                                if (stack.getTag().getInt(lvlSize) >  s*3){
                                    if (stack.getTag().getInt(lvl)!= 3) {
                                        stack.getTag().putInt(lvl, 3);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }


    }
    @SubscribeEvent
    public  void fungus(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fungus.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                int integers = 0;
                for (LivingEntity living : entities) {
                    if (living.isAlliedTo(player)){
                        if (!living.is(player)) {
                            integers++;
                        }
                    }
                }
                float integer = integers;
                integer /= 10;
                integer *= 2.5f;
                if (integer > 1.5f){
                    integer = 1.5f;
                }
                aFloat = integer;
                event.setAmount(event.getAmount() *(1+integer));

            }
        }
    }
    @SubscribeEvent
    public  void fungus(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fungus.get())){
                LivingEntity living =event.getEntity();
                BlockState state = living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY() - 1, living.getBlockZ()));
                if (state.is(Blocks.GRASS_BLOCK)) {
                    living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY() - 1, living.getBlockZ()), Blocks.MYCELIUM.defaultBlockState(), 3);
                }
                BlockState MYC = living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()));


                if (!player.getCooldowns().isOnCooldown(Items.fungus.get())) {
                    player.getCooldowns().addCooldown(Items.fungus.get(),200);
                }

                if (MYC.is(Blocks.AIR)) {
                    if (living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY()-1, living.getBlockZ())).is(Blocks.MYCELIUM)) {
                        if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                            living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()), Blocks.RED_MUSHROOM.defaultBlockState(), 3);
                        } else {
                            living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()), Blocks.BROWN_MUSHROOM.defaultBlockState(), 3);
                        }
                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void dna(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.dna.get())) {
                if (event.getEntity() instanceof MushroomCow) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof dna) {
                                    if (player.hasEffect(MobEffects.WEAKNESS)) {
                                        if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                                            if (stack.getTag() != null && !stack.getTag().getBoolean(fungus)) {
                                                player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.fungus.get()));
                                                stack.getTag().putBoolean(fungus, true);
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
    }
    @SubscribeEvent
    public void LivingEntityUseItemEvent(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())&&!Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.parasite.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                           if (stack.getTag()!= null){
                                if (stack.getOrCreateTag().getBoolean(cod)&&
                                        stack.getOrCreateTag().getBoolean(SALMON)&&
                                        stack.getOrCreateTag().getBoolean(CHICKEN)&&
                                        stack.getOrCreateTag().getBoolean(BEEF)&&
                                        stack.getOrCreateTag().getBoolean(RABBIT)&&
                                        stack.getOrCreateTag().getBoolean(MUTTON)&&
                                        stack.getOrCreateTag().getBoolean(PORKCHOP)&&
                                        stack.getOrCreateTag().getBoolean(TROPICAL_FISH))
                                {
                                    if (!stack.getOrCreateTag().getBoolean(give)){
                                        player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.parasite.get()));
                                        stack.getOrCreateTag().putBoolean(give, true);
                                    }
                                }
                                if (stack.getOrCreateTag().getBoolean(give)){
                                    return;
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.COD)){
                                    stack.getOrCreateTag().putBoolean(cod, true);
                                }

                                if (event.getItem().is(net.minecraft.world.item.Items.SALMON)){
                                    stack.getOrCreateTag().putBoolean(SALMON, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.CHICKEN)){
                                    stack.getOrCreateTag().putBoolean(CHICKEN, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.BEEF)){
                                    stack.getOrCreateTag().putBoolean(BEEF, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.RABBIT)){
                                    stack.getOrCreateTag().putBoolean(RABBIT, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.MUTTON)){
                                    stack.getOrCreateTag().putBoolean(MUTTON, true);
                                }

                                if (event.getItem().is(net.minecraft.world.item.Items.PORKCHOP)){
                                    stack.getOrCreateTag().putBoolean(PORKCHOP, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.TROPICAL_FISH)){
                                    stack.getOrCreateTag().putBoolean(TROPICAL_FISH, true);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public  void LivingHurtEvent_DNA(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (event.getSource()!= null && event.getSource().getEntity() instanceof WitherSkeleton){
                if (Handler.hascurio(player, Items.dna.get())){
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof dna) {
                                    if (player.hasEffect(MobEffects.WEAKNESS)) {
                                        if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                                            if (stack.getTag() != null && stack.getTag().getString(virus).isEmpty()) {
                                                player.addItem(new ItemStack(com.moonstone.moonstonemod.init.Items.virus.get()));
                                                stack.getTag().putString(virus,virus);
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
    }
    @SubscribeEvent
    public void evilmug(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            ItemStack stack = player.getItemInHand(InteractionHand.OFF_HAND);
            if (stack.is(Items.evilmug.get())){
                if (stack.getTag()!= null) {
                    if (stack.getOrCreateTag().getInt(evilmug.blood) < 100) {
                        int s = (int) event.getAmount();
                        if (s > 100) {
                            s = 100;
                        }
                        if (stack.getTag() != null) {
                            stack.getTag().putInt(evilmug.blood, stack.getTag().getInt(evilmug.blood) + s);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public   void evilcandle(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.evilcandle.get())){
                if (event.getSource().is(DamageTypes.IN_FIRE)&&event.getSource().is(DamageTypes.ON_FIRE)&&event.getSource().is(DamageTypes.LAVA)){
                    event.setAmount(event.getAmount() * 0.2f);
                }
            }
        }
    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
        if (Handler.hascurio(event.getEntity(), Items.soulbattery.get())){
            event.setDamageModifier(event.getDamageModifier() * 1.25f);
        }
    }
    @SubscribeEvent
    public  void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() != null) {
            Player player = event.getEntity();
            if (!player.getTags().contains("welcome_to_moonstone")) {
                int a = Mth.nextInt(RandomSource.create(), 1, 2);
                if (a == 1) {
                    player.addItem(Items.ectoplasmstone.get().getDefaultInstance());
                }
                if (a == 2) {
                    player.addItem(Items.twistedstone.get().getDefaultInstance());
                }
                player.addItem(Items.apple.get().getDefaultInstance());
                player.addTag("welcome_to_moonstone");
            }
        }

    }
    @SubscribeEvent
    public void KnockBack_nightmarestone(LivingKnockBackEvent  event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setStrength(event.getStrength() * (1 - EffectInstance(player) / 14));
            }

            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag()!= null){
                                        float dam =stack.getTag().getInt("kok");
                                        dam /= 100;
                                        event.setStrength(event.getStrength() * (1 - dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }

        }
    }

    @SubscribeEvent
    public void Heal_nightmarestone(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setAmount(event.getAmount() * (1 + EffectInstance(player) / 14));
            }
        }

    }

    @SubscribeEvent
    public void exp_nightmarestone(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setDroppedExperience((int) (event.getDroppedExperience() * (1 + EffectInstance(player) / 10)));
            }
        }
    }
    @SubscribeEvent
    public void BreakSpeed_nightmarestone(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() != null){
            Player player = event.getEntity();
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setNewSpeed(event.getNewSpeed() * (1 + EffectInstance(player) / 10));
            }
        }
    }
    @SubscribeEvent
    public void nightmareeye(LivingHurtEvent event) {

        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 1.33f);
            }

        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 0.75f);
            }
        }

    }

    @SubscribeEvent
    public void nightmareanchor(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.nightmareeye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmareanchor.get())) {
                                if (stack.getTag() != null) {
                                    if (Handler.hascurio(player, Items.nightmareanchor.get())) {

                                        double playerX = player.getX();
                                        double playerY = player.getY();
                                        double playerZ = player.getZ();

                                        stack.getOrCreateTag().putDouble("x", playerX);
                                        stack.getOrCreateTag().putDouble("y", playerY);
                                        stack.getOrCreateTag().putDouble("z", playerZ);


                                        stack.getOrCreateTag().putString("level", player.level().dimension().toString());
                                    }
                                }
                            }
                        }
                    }
                });

            }
        }
    }
    @SubscribeEvent
    public void LivingKnockBackEvent(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.mring.get())){
                event.setStrength(event.getStrength() * 2);
            }
        }
    }
    @SubscribeEvent
    public   void LivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.mring.get())){
                event.setAmount(event.getAmount() * 1.4f);
            }
        }

    }
    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, Items.morb.get())){
            event.setDroppedExperience(((int) ((event.getDroppedExperience()*1.5))) + 1);
        }
    }
    @SubscribeEvent
    public void nightmaretreasure_LivingDropsEvent(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (Handler.hascurio(player,  Items.nightmaretreasure.get())) {
                    Random random = new Random();
                    int s = 33;
                    s += (int) player.getLuck();

                    if (s > 66){
                        s = 66;
                    }
                    if(
                            random.nextInt(100) <= s
                    ) {
                        Collection<ItemEntity> drop = event.getDrops();
                        for (ItemEntity entity : drop) {
                            ItemStack stack = entity.getItem();
                            if (stack.getMaxStackSize() != 1) {
                                stack.setCount(stack.getCount() * 3);
                                entity.setItem(stack);
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void nightmarestone(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmarestone.get())) {
                int s = Mth.nextInt(RandomSource.create(), 1, 5);
                if (s == 1)
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
                if (s == 2)
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                if (s == 3)
                    player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 0));
                if (s == 4)
                    player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                if (s == 5)
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2));
                if (s == 6)
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
            }
        }
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.thedoomstone.get())) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DARKNESS ,120 ,1));

                Vec3 playerPos = event.getEntity().position();
                int range = 12;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));


                for (LivingEntity livingentity : entities) {
                    if (livingentity.hasEffect(MobEffects.DARKNESS )) {
                        if (!livingentity.is(player)) {
                            {
                                if (clientSideAttackTime < 80) {
                                    ++clientSideAttackTime;
                                }
                                double d5 = this.getAttackAnimationScale(0.0F);
                                double d0 = livingentity.getX() - event.getEntity().getX();
                                double d1 = livingentity.getY(0.5D) - event.getEntity().getEyeY();
                                double d2 = livingentity.getZ() - event.getEntity().getZ();
                                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                                d0 /= d3;
                                d1 /= d3;
                                d2 /= d3;
                                double d4 = event.getEntity().getRandom().nextDouble();
                                while (d4 < d3) {
                                    d4 += 1.8D - d5 + event.getEntity().getRandom().nextDouble() * (1.7D - d5);

                                    if (event.getEntity().level() instanceof ServerLevel serverLevel) {
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                    }
                                }
                                livingentity.level().playSound(null, livingentity.getX(), livingentity.getY(), livingentity.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 0.22f, 0.22f);
                                livingentity.hurt(livingentity.damageSources().magic(), 4 + livingentity.getMaxHealth() / 25);
                                livingentity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));

                            }
                        }
                    }
                }
            }

            if (Handler.hascurio(player, Items.magiceye.get())) {
                Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
                int range = 4;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (!living.is(player)) {
                        if (!living.is(event.getEntity())) {
                            living.hurt(living.damageSources().magic(), event.getAmount() / 2);
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (Handler.hascurio(player, Items.nightmaretreasure.get())) {
                    event.setAmount(event.getAmount() / 2);
                }
            }


            if (Handler.hascurio(player, Items.mshell.get())) {
                event.setAmount(event.getAmount() * 0.9f);
            }
            if (Handler.hascurio(player, Items.mbattery.get())) {
                if (event.getEntity() instanceof Zombie) {
                    event.setAmount(event.getAmount() * 1.5f);
                }
            }
            if (Handler.hascurio(player, Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag() != null) {
                                        float dam = stack.getTag().getInt("damage");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }


















        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mkidney.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.mkidney.get())) {
                    if (Mth.nextInt(RandomSource.create(), 0, 100) < Kidney) {
                        Kidney /= 2;
                        event.setAmount(0);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HURT, SoundSource.NEUTRAL, 1, 1);
                    } else {
                        Kidney = 100;

                        event.setAmount(event.getAmount() + player.getMaxHealth() / 3);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 1, 1);
                        player.getCooldowns().addCooldown(Items.mkidney.get(), 200);
                    }
                }
            }



            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag()!= null){
                                        float dam =stack.getTag().getInt("regs");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }
            if (Handler.hascurio(player, Items.mshell.get())) {
                if (event.getSource().getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()) {
                        event.setAmount(event.getAmount() * 0.75f);
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmapple.get())) {
                if (event.getSource() != null) {
                    Entity source = event.getSource().getEntity();
                    if (source instanceof LivingEntity) {
                        Vec3 playerPos = player.position();
                        int range = 8;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(
                                playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
                        for (LivingEntity livingEntity : entities) {
                            if (!livingEntity.is(player)) {
                                livingEntity.hurt(livingEntity.damageSources().magic(), livingEntity.getMaxHealth() / 50);
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmhorseshoe.get())) {
                if (event.getSource().is(DamageTypes.FALL)) {
                    event.setAmount(event.getAmount() / 10);
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmshild.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setAmount(event.getAmount() * 0.7F);
                }
                shield += 1;
                if (shield >= 5) {
                    event.setAmount(0);
                    shield = 0;
                }
            }
        }
    }
    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Tick event){
        if (event.getEntity() instanceof Player player){
            ItemStack stack = event.getItem();
            if (stack.getTag() !=null) {
                if (stack.getTag().getBoolean(wind_and_rain.wind)) {
                    float s = (float) Math.sin(player.tickCount);
                    if (s <= 0) {
                        s = 0.125f;
                    }

                    int b = Mth.nextInt(RandomSource.create(), 1, 50);
                    if (player.tickCount % 5 == 0) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }


                        suddenrain item = new suddenrain(EntityTs.suddenrain.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);
                            stack.hurtAndBreak(3, player, (pl) -> pl.broadcastBreakEvent(event.getEntity().getUsedItemHand()));
                        }

                    } else if (player.tickCount % 5 == 1) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }
                        flysword item = new flysword(EntityTs.flysword.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        item.addTag(AllEvent.FlySword);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);
                            stack.hurtAndBreak(3, player, (pl) -> pl.broadcastBreakEvent(event.getEntity().getUsedItemHand()));
                        }
                    }

                }

            }
        }
    }
    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(net.minecraftforge.event.entity.player.ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.getTag() !=null){
            if (stack.getTag().getBoolean(wind_and_rain.wind)){
                event.getToolTip().add(Component.translatable("item.moonstone.wind_and_rain").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();
        if (stack.getItem() instanceof IEctoplasm) {
            tooltipEvent.setBorderStart(0xFF87CEFA);
            tooltipEvent.setBorderEnd(0xFFF8F8FF);
        }
        if (stack.getItem() instanceof MLS) {
            tooltipEvent.setBorderStart(0xFF006400);
            tooltipEvent.setBorderEnd(0xFF006400);
        }
        if (stack.getItem() instanceof INightmare) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0xFF800080);
        }
        if (stack.getItem() instanceof IDoom||stack.getItem() instanceof Perhaps) {
            tooltipEvent.setBorderStart(0xFF83DEFC);
            tooltipEvent.setBorderEnd(0xFF0296FE);
        }
        if (stack.getItem() instanceof Iplague) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderStart(0xFF800000);
        }

        if (stack.getItem() instanceof the_heart) {
            tooltipEvent.setBorderStart(0xFFFF8C00);
            tooltipEvent.setBorderEnd(0xFFFFD700);
        }
    }
    public static float EffectInstance(Player player) {
        float size = 0;
        List<Integer> Int = Lists.newArrayList();
        Collection<MobEffectInstance> collection = player.getActiveEffects();
        for (MobEffectInstance mobEffectInstance : collection){
            MobEffect mobEffect = mobEffectInstance.getEffect();
            if (!mobEffect.isBeneficial()){
                Int.add(1);
            }
        }
        for (Integer i : Int){
            size += 1;
        }
        return size;
    }
    public float getAttackAnimationScale(float p_32813_) {
        return (clientSideAttackTime + p_32813_) / (float)80;
    }
}

