package com.ytgld.moonstone.item.ms.necora;

import com.ytgld.moonstone.Handler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class NecoraHandler {
    public static boolean has(Player player, Item item) {
        return Handler.hascurio(player, item);
    }
}
