package com.ytgld.moonstone.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.necora.DNASequence;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.util.HashMap;
import java.util.Map;

public class RenderDNAItem {
    private static final Map<Item, AlphaItem> itemStackIntegerHashMap = new HashMap<>();
    private static int time = 0;
    public static void clientTick(ClientTickEvent.Pre event){
        time++;
        for (AlphaItem alphaItem : itemStackIntegerHashMap.values()){
            if (alphaItem.look) {
                if (alphaItem.alpha < 200) {
                    alphaItem.setAlpha(alphaItem.alpha + 40);
                }
                alphaItem.setLook(false);
            }else {
                if (alphaItem.alpha > 0) {
                    alphaItem.setAlpha(alphaItem.alpha - 20);
                    if (alphaItem.alpha < 0) {
                        alphaItem.setAlpha(0);
                    }
                }
            }
        }
    }

    public static void renderItem(GuiGraphics guiGraphics, PoseStack pose, ItemStack stack, int x, int y, int seed) {
        if (stack.getItem() instanceof DNASequence dna) {
            if (!itemStackIntegerHashMap.containsKey(dna)) {
                itemStackIntegerHashMap.put(dna, new AlphaItem(0, true));
            }
            itemStackIntegerHashMap.get(dna).setLook(true);
            addBlackLight(guiGraphics, pose, dna, x, y, seed);
        }
    }

    private static void  addBlackLight(GuiGraphics guiGraphics,PoseStack pose, DNASequence dna,int x, int y,int seed){
        int a = (int) (itemStackIntegerHashMap.get(dna).alpha / 1.5f);
        int r = 20;
        int g = 0;
        int b = 10;
        ResourceLocation base = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/gui/all.png");
        float timeBase = time / 75F;
        addCom(32,timeBase, Light.ARGB.color(Math.min(255,a), r, g, b), guiGraphics, pose, base, x, y, seed);
        addCom(32,timeBase,Light.ARGB.color(Math.min(255,a / 2), r, g, b), guiGraphics, pose, base, x, y, seed);
        addCom(32,timeBase,Light.ARGB.color(Math.min(255,a / 4), r, g, b), guiGraphics, pose, base, x, y, seed);

        addCom(32,timeBase,Light.ARGB.color(a, r, g, b), guiGraphics, pose,
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/gui/ci_star.png"), x, y, seed);
        addCom(18,-timeBase * 2  + (float)Math.PI / 2  ,Light.ARGB.color(a, r, g, b), guiGraphics, pose,
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/gui/ci_star.png"), x, y, seed);
        addCom(18,-timeBase * 2  + (float)Math.PI / 8  ,Light.ARGB.color(a, r, g, b), guiGraphics, pose,
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/gui/ci_star.png"), x, y, seed);


        addShadow(-timeBase * 2  ,Light.ARGB.color(a, 120, 15, 15),
                guiGraphics, pose,
                base, x, y, 0);
        addShadow(-timeBase * 2  ,Light.ARGB.color(a, 120, 15, 15),
                guiGraphics, pose,
                base, x, y, 180);

    }

    private static void addCom(int size,float time , int color,GuiGraphics guiGraphics,PoseStack pose, ResourceLocation fire, int x, int y,int seed){
        pose.pushPose();
        pose.translate(8,8,0);
        pose.pushPose();
        pose.translate(x, y,0);
        pose.mulPose(Axis.ZN.rotationDegrees(time * 40 + seed));
        pose.translate(-x, -y,0);
        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(guiGraphics, fire,
                (int) (x - size / 2f), (int) (y - size /2f), 0, 0,
                size, size, size, size,
                color);
        pose.popPose();
        pose.popPose();
    }
    private static void addShadow(float time , int color, GuiGraphics guiGraphics, PoseStack pose, ResourceLocation fire, int x, int y, int age){
        float orbSize = 6;
        pose.pushPose();
        pose.translate(8,8,1);
        pose.translate(x, y,1);
        pose.mulPose(Axis.ZN.rotationDegrees(time * 40 + age));
        pose.translate(-x, -y,1);
        for (float theta = 0f; theta <= Math.PI; theta += (float) (Math.PI / 16)) {
            float xMath = (float) (Math.cos(theta) * 8);
            float yMath = (float) (Math.sin(theta) * 8);
            int as = ((color >> 24) & 0xFF);
            int rs = ((color >> 16) & 0xFF);
            int gs = ((color >> 8) & 0xFF);
            int bs = (color & 0xFF);
            float alpha = (float) Math.pow(Math.sin(theta), 2.5) * 255;
            int alphaColor = (int) Math.min(as,alpha);
            int drawColor = Light.ARGB.color(alphaColor,rs,gs,bs);

            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(guiGraphics, fire,
                    (int) (x - orbSize / 2f) + xMath, (int) (y - orbSize /2f) + yMath, 0, 0,
                    orbSize, orbSize, orbSize, orbSize,
                    drawColor);
        }
        pose.popPose();
    }
    private static class AlphaItem {
        public int alpha;
        public boolean look;
        public AlphaItem(int alpha,boolean look ){
            this.alpha = alpha;
            this.look = look;
        }

        public void setLook(boolean look) {
            this.look = look;
        }

        public void setAlpha(int a){
            this.alpha = a;
        }
    }
}
