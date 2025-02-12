package com.moonstone.moonstonemod.client.renderer;

import net.minecraft.world.inventory.Slot;

public class GuiHandler {
    private static Slot currentlyRenderingSlot = null;

    public static Slot getCurrentlyRenderingSlot() {
        return currentlyRenderingSlot;
    }

    public static void postRenderSlot() {
        currentlyRenderingSlot = null;
    }

    public static void preRenderSlotItem(Slot slot) {
        currentlyRenderingSlot = slot;
    }


}
