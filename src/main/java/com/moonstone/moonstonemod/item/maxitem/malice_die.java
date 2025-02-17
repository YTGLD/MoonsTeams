package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class malice_die extends CommonItem implements Die {
    /*
      	如果附近生物的攻击目标是你，那么每增加一个生物：
      	+10%伤害
      	+9%生命恢复
      	+8%速度
      	+7%攻击速度
      	+6%护甲
      	+5%生命值

        如果目标数量少于3，那么你造成的伤害减35%

      	攻击会使目标8格半径内的生物主动攻击你
     */

    public static final String MALICE_DIE = "MaliceDie";
    public static void att(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.malice_die.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities){
                    if (living instanceof Mob mob) {
                        mob.setTarget(player);
                    }
                }
            }
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (Handler.hascurio(player,Items.malice_die.get())) {
                    int size = 0;
                    Vec3 playerPos = player.position().add(0, 0.75, 0);
                    int range = 24;
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    for (LivingEntity living : entities) {
                        if (living instanceof Mob mob) {
                            if (mob.getTarget() != null && mob.getTarget().is(player)) {
                                size = entities.size();
                            }
                        }
                    }
                    if (stack.getTag() != null) {
                        stack.getTag().putInt(MALICE_DIE, size);
                    } else {
                        stack.getOrCreateTag();
                    }
                }
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.Head(stack));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head(stack));
    }
    private Multimap<Attribute, AttributeModifier> Head(ItemStack stack){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (stack.getTag()!=null) {
            float s = stack.getTag().getInt(MALICE_DIE);//1 == 100%
            if (s > 18) {
                s=18;
            }
            s /= 100f;//0.01 = 1%
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*10*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*9*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*8*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*7*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*6*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.cit.get(), new AttributeModifier(UUID.fromString("8b099b45-7c1f-3749-8e57-5c8f500dad04"),
                    "s",
                                s*5*0.33f,
                                AttributeModifier.Operation.MULTIPLY_BASE));

        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.8").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.9").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
    }

}
