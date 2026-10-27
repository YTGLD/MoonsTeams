package com.ytgld.moonstone.item.ms.necora;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public class NecoraHandler {
    public static boolean has(Player player, Item item) {
        return Handler.hascurio(player, item);
    }
}
