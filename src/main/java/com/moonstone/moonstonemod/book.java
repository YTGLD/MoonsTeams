package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.entity.cell_zombie;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import vazkii.patchouli.api.PatchouliAPI;

public class book extends Item {
    public book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (ModList.get().isLoaded("patchouli")){
            if (p_41433_ instanceof ServerPlayer player){
                PatchouliAPI.get().openBookGUI(player,new ResourceLocation(MoonStoneMod.MODID,"soul_book"));
            }
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
