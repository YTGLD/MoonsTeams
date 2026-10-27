package com.ytgld.moonstone.render;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class NightmareShieldRenderHandler {

    public static int glow;
    public static double lastShield;
    public static float time = 0;
    public static float aSize = 1;
    public static float aGlow = 1f;
    public static float aGlowMin = 0F;
    public static float aGlowDOLDOWN = 0f;
    public static float glowRed = 0f;
    public static void tick(ClientTickEvent.Pre event) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            double max = player.getAttributeValue(AttReg.nightmare_shield);
            double now = player.getData(AttReg.nightmareShieldTypeSupplier);
            if (max > 0) {
                if (glowRed > 0) {
                    glowRed -= 0.05f;
                }
                if (aFloatCool > 0) {
                    aFloatCool--;
                } else {
                    aFloatCool = 1;
                }
                if (player.hurtDuration == 10) {
                    aFloat = 1;
                    aFloatCool = 40;
                }
                if (now < max) {
                    aFloat = 1;
                    aFloatCool = 40;
                }else {
                    if (aFloatCool <= 0) {
                        if (aFloat > 0) {
                            aFloat -= 0.025f;
                        }

                        if (aFloat <= 0) {
                            aFloat = 0;
                        }
                    }
                }
                if (glow > 0) {
                    glow--;
                }
                if (lastShield != now) {
                    glow = 20;
                    aGlowDOLDOWN = 1;
                    aGlow = 1f;
                    aGlowMin = 0f;
                }
                if (aFloat < 1) {
                    aGlow = 1f;
                    aGlowMin = 0f;
                }
                if (aGlowDOLDOWN > 0) {
                    aGlowDOLDOWN -= 0.1f;
                    if (aGlowMin < 1) {
                        aGlowMin += 0.1f;
                    }
                    if (aGlow > 0) {
                        aGlow -= 0.1f;
                    }
                }
                if (aFloat < 1) {
                    if (aSize < 1.5f) {
                        aSize += 0.003f;
                    }
                }else {
                    aSize = 1;
                }

                lastShield = now;
                time += 1;
                if (soundCool > 0) {
                    soundCool--;
                }
            }
        }
    }
    public static float aFloat = 1;
    public static float aFloatCool = 40;
    public static int soundCool = 1;

    public static void renderShield(GuiGraphicsExtractor guiGraphics) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        var player = minecraft.player;
        if (player != null && minecraft.level != null) {
            if (!player.isCreative() && !player.isSpectator()) {
                if (player.getData(AttReg.nightmareShieldTypeSupplier) <= 0) {
                    return;
                }
                double max = player.getAttributeValue(AttReg.nightmare_shield);
                if (max > 0) {
                    if (aFloat < 1) {
                        int s = 48;
                        poseStack.pushMatrix();
                        poseStack.translate(
                                ((guiGraphics.guiWidth() / 2f) - (float) (s) /2),
                                ((guiGraphics.guiHeight() - 47f) - (float) (s) / 2));
                        guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED
                                , Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                        "textures/gui/all.png"),0,0,0,0,
                                s,s,
                                s,s,
                                Light.ARGB.color((int) (aFloat * 255),255,50, 100));

                        poseStack.popMatrix();
                    }
                }

                {
                    int s = 48;
                    poseStack.pushMatrix();
                    poseStack.translate(
                            (guiGraphics.guiWidth() / 2f) - (float) (s) / 2,
                            (guiGraphics.guiHeight() - 47f) - (float) (s) / 2);
                    guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED
                            , Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                    "textures/gui/all.png"), 0, 0, 0, 0,
                            s, s,
                            s, s,
                            Light.ARGB.color((int) ((glow / 20f) * 255), 255, 50, 100));
                    poseStack.popMatrix();
                }
                poseStack.pushMatrix();
                poseStack.translate(
                        (guiGraphics.guiWidth() / 2f) - (float) (24) / 2,
                        (guiGraphics.guiHeight() - 47f)- (float) (24) / 2);
                if (max > 0) {
                    float s = aFloat;
                    if (s < 0) {
                        s = 0;
                    }
                    float delta = (float) (player.getData(AttReg.nightmareShieldTypeSupplier) / max);

                    if (delta > 1) {
                        delta = 1;
                    }
                    if (delta <= 0.25 && delta > 0.1f) {
                        renderSs4(guiGraphics, s, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_4.png"),Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_glow_4.png"));
                    }else if (delta > 0.25 && delta <= 0.5) {
                        renderSs4(guiGraphics, s, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_3.png"),Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_glow_3.png"));
                    }else if (delta > 0.5 && delta <= 0.75) {
                        renderSs4(guiGraphics, s, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_2.png"),Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_glow_2.png"));
                    }else if (delta > 0.75 && delta <= 1) {
                        renderSs4(guiGraphics, s, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_1.png"), Identifier.fromNamespaceAndPath(Moonstone.MODID,
                                "textures/gui/nightmare_shield_glow_1.png"));
                    }
                }
                poseStack.popMatrix();
            }
        }
    }

    public static void renderSs4(GuiGraphicsExtractor guiGraphics, float s, Identifier resourceLocation, Identifier glowRes){
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;
        if (player != null) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED
                    , resourceLocation,0,0,0,0,
                    24,24,
                    24,24,
                    Light.ARGB.color( (int) (s * 255),255,255,255));

            guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED
                    , glowRes,0,0,0,0,
                    24,24,
                    24,24,
                    Light.ARGB.color( (int) ((glow / 20f) * 255),255,255,255));

            guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED
                    , resourceLocation,0,0,0,0,
                    24,24,
                    24,24,
                    Light.ARGB.color((int) Math.min(aFloat * 255,Math.min(aGlowMin* 255,aGlow* 255)),255,255, 255));

        }
    }
}
