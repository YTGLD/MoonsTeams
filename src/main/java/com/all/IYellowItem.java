package com.all;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;

public interface IYellowItem extends IGUILight{
   default int guiColor(ItemStack stack){
       return Light.ARGB.color(190,255,255,100);
   }
   default Vec2 posOffset(){
       return new Vec2(0,0);
   }
   default ResourceLocation img(){
       return new ResourceLocation(MoonStoneMod.MODID,"textures/shadow.png");
   }

}
