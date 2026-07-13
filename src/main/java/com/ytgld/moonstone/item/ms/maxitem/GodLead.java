package com.ytgld.moonstone.item.ms.maxitem;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GodLead extends CommonItem {
    public GodLead(Properties properties) {
        super(properties);
    }

    /*
    受到致命伤害时，伤害转移到附近的生物身上(冷却10秒)

	如果此生物死亡，那么冷却清除

	+150%受到伤害
     */

    public static void hurtS(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player){
            if (!event.getSource().is(DamageTypes.DRY_OUT)) {
                if (Handler.hascurio(player, Items.god_lead.get())) {
                    if (!event.getSource().is(DamageTypes.DRY_OUT)) {
                        if (!player.getCooldowns().isOnCooldown(Items.god_lead.get().getDefaultInstance())) {
                            event.setNewDamage(event.getNewDamage() * 2.5f);
                            if (event.getNewDamage() > player.getHealth()) {
                                Vec3 playerPos = player.position().add(0, 0.75, 0);
                                int range = 12;
                                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                                for (LivingEntity living : entities) {
                                    if (!living.is(player)&& !(living instanceof Player)) {
                                        living.hurt(living.damageSources().dryOut(), event.getNewDamage());
                                        living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                                        if (!living.isDeadOrDying()) {
                                            player.getCooldowns().addCooldown(Items.god_lead.get().getDefaultInstance(), 200);
                                        }
                                        break;
                                    }
                                }
                                event.setNewDamage(0);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.god_lead.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.god_lead.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.god_lead.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
    }


}
