package com.ytgld.moonstone.enttiy.state;

import com.ytgld.moonstone.enttiy.AtSword;
import com.ytgld.moonstone.enttiy.FlySword;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class FlySwordState extends EntityRenderState {
    public FlySword entity;
    public float partialTick;
    public float xRot;
    public float yRot;
    public float shake;


    public FlySwordState() {
    }


}


