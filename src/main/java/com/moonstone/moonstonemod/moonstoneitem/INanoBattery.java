package com.moonstone.moonstonemod.moonstoneitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class INanoBattery extends Doom {

    public int t;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        t = 600;
        if (slotContext.entity() instanceof Player player){
            if (Handler.hascurio(player, Items.battery.get())||
                    Handler.hascurio(player, Items.soulbattery.get())||
                    Handler.hascurio(player, Items.ectoplasmbattery.get())||
                    Handler.hascurio(player, Items.mbattery.get())){
                t = (int) (t *  0.7);
            }else t = 600;
        }
    }
}
