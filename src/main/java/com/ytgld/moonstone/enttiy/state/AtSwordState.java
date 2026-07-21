package com.ytgld.moonstone.enttiy.state;

import com.ytgld.moonstone.enttiy.AtSword;
import com.ytgld.moonstone.enttiy.AttackBlood;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class AtSwordState extends EntityRenderState {
    public AtSword entity;
    public float partialTick;
    public final ItemStackRenderState item;

    public AtSwordState() {
        item = new ItemStackRenderState();
    }


}


