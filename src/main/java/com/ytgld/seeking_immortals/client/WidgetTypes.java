package com.ytgld.seeking_immortals.client;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;

public enum WidgetTypes {
    OBTAINED(
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/box_obtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/task_frame_obtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/challenge_frame_obtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/goal_frame_obtained")),
    UNOBTAINED(
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/box_unobtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/task_frame_unobtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/challenge_frame_unobtained"),
            new ResourceLocation(SeekingImmortalsMod.MODID,"advancements/goal_frame_unobtained"));


    private final ResourceLocation boxSprite;
    private final ResourceLocation taskFrameSprite;
    private final ResourceLocation challengeFrameSprite;
    private final ResourceLocation goalFrameSprite;

    WidgetTypes(ResourceLocation boxSprite, ResourceLocation taskFrameSprite, ResourceLocation challengeFrameSprite, ResourceLocation goalFrameSprite) {
        this.boxSprite = boxSprite;
        this.taskFrameSprite = taskFrameSprite;
        this.challengeFrameSprite = challengeFrameSprite;
        this.goalFrameSprite = goalFrameSprite;
    }

    public ResourceLocation boxSprite() {
        return this.boxSprite;
    }

    public ResourceLocation frameSprite(FrameType type) {
        return switch (type) {
            case TASK -> this.taskFrameSprite;
            case CHALLENGE -> this.challengeFrameSprite;
            case GOAL -> this.goalFrameSprite;
        };
    }
}
