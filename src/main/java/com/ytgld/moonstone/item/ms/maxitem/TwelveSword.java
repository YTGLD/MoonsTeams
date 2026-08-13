package com.ytgld.moonstone.item.ms.maxitem;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class TwelveSword extends UnCommonItem {


    public TwelveSword(Properties properties) {
        super(properties);
    }

    public static void att(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.twelve_sword.get())) {
                Vec3 playerPos = player.position();
                float range = 10;
                List<SwordOfTwelve> entities =
                        player.level().getEntitiesOfClass(SwordOfTwelve.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                List<Integer> a8 = new ArrayList<>();
                for (SwordOfTwelve swordOfTwelve : entities) {
                    if (swordOfTwelve.getTags().contains("SwordOfTwelveOFDamage")) {
                        a8.add(1);
                    }
                }

                float s = 0;
                for (int ignored : a8) {
                    s++;
                }
                s /= 10f;
                event.setNewDamage(event.getNewDamage() * (1 + s));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_,  Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.twelve_sword.tool.string.1").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.twelve_sword.tool.string.4").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.twelve_sword.tool.string.2").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.twelve_sword.tool.string.3").withStyle(ChatFormatting.GOLD));
    }
}
