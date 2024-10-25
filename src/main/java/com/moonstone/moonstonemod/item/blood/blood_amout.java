package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.line;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_amout extends Item implements ICurioItem, Blood {
    public blood_amout() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    public static void Hurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (event.getSource().getEntity()!=null&& Handler.hascurio(player, Items.blood_amout.get())){
                if (!player.getCooldowns().isOnCooldown( Items.blood_amout.get())) {
                    line line = new line(EntityTs.line.get(), player.level());
                    line.setPos(player.position());
                    line.setOwnerUUID(player.getUUID());
                    player.level().addFreshEntity(line);
                    player.getCooldowns().addCooldown( Items.blood_amout.get(),10);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_amout.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }


}
