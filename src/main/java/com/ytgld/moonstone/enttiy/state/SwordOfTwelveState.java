package com.ytgld.moonstone.enttiy.state;

import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class SwordOfTwelveState extends EntityRenderState {
    public SwordOfTwelve entity;
    public float partialTick;
    public final ItemStackRenderState item;

    public SwordOfTwelveState() {
        item = new ItemStackRenderState();
    }


}


