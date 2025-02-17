package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class god_lead extends CommonItem implements Die {

    /*
    受到致命伤害时，伤害转移到附近的生物身上(冷却10秒)

	如果此生物死亡，那么冷却清除

	+150%受到伤害
     */

    public static void hurtS(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.god_lead.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.god_lead.get())){
                    event.setAmount(event.getAmount() * 2.5f);
                    if (event.getAmount() > player.getHealth()) {
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        int range = 12;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                        for (LivingEntity living : entities) {
                            if (!living.is(player)) {
                                living.hurt(living.damageSources().dryOut(), event.getAmount());
                                living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                                if (!living.isDeadOrDying()) {
                                    player.getCooldowns().addCooldown(Items.god_lead.get(), 200);
                                }

                                break;
                            }
                        }
                        event.setAmount(0);
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
