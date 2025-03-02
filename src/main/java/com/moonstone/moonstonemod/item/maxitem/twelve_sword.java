package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.SwordOfTwelve;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class twelve_sword extends CommonItem implements Die {



    public static void att(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.twelve_sword_.get())) {
                Vec3 playerPos = player.position();
                float range =10;
                List<SwordOfTwelve> entities =
                        player.level().getEntitiesOfClass(SwordOfTwelve.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                List<Integer> a8 = new ArrayList<>();
                for (SwordOfTwelve swordOfTwelve : entities){
                    if (swordOfTwelve.getTags().contains("SwordOfTwelveOFDamage")){
                        a8.add(1);
                    }
                }

                float s  = 0;
                for (int ignored : a8){
                    s++;
                }
                s/=10f;
                event.setAmount(event.getAmount()*(1+s));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
       p_41423_.add(Component.translatable("item.twelve_sword.tool.string.1").withStyle(ChatFormatting.GOLD));
       p_41423_.add(Component.translatable("item.twelve_sword.tool.string.4").withStyle(ChatFormatting.GOLD));
       p_41423_.add(Component.translatable("item.twelve_sword.tool.string.2").withStyle(ChatFormatting.GOLD));
       p_41423_.add(Component.translatable("item.twelve_sword.tool.string.3").withStyle(ChatFormatting.GOLD));
    }



    public static class at_sword extends CommonItem{

    }
    public static class sword extends CommonItem{

    }
    public static class god_sword extends CommonItem{

    }
}
