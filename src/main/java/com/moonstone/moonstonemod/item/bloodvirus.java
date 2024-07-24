package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.batskill;
import com.moonstone.moonstonemod.moonstoneitem.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class bloodvirus extends BloodViru {
    public bloodvirus (){
    }
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof batskill) {
                    if (me.getTag() != null) {
                        if (!me.getTag().getBoolean(batskill.batskill)) {
                            String size = "SizeBlood";
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
    public static double calculateDistance(LivingEntity entity1, LivingEntity entity2) {
        Vec3 pos1 = entity1.position();
        Vec3 pos2 = entity2.position();

        double dx = pos1.x - pos2.x;
        double dy = pos1.y - pos2.y;
        double dz = pos1.z - pos2.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "ncrdna", uuid, 2, AttributeModifier.Operation.ADDITION);

        return multimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {

            {
                Vec3 position = player.position();
                int is = 18;
                List<blood_bat> items = player.level().getEntitiesOfClass(blood_bat.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
                for (blood_bat item : items) {
                    if (item.getOwner()!= null && item.getOwner().is(player)) {
                        if (item.getTarget()==null) {
                            if (item.isAlive()) {
                                Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                float move = (float) calculateDistance(player, item);
                                if (move > 1.8) {
                                    item.setDeltaMovement(motion.scale(0.5));
                                }
                            }
                        }else if (item.getTarget().isAlive()){

                        }
                    }
                }
            }


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
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0,false,false));

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
                        MoonStoneMod.MODID+":bloodvirus",
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
