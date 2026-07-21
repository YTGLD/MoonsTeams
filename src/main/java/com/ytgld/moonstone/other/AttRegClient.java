package com.ytgld.moonstone.other;

import com.mojang.serialization.Codec;
import com.ytgld.moonstone.Moonstone;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttRegClient {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Moonstone.MODID);

    public static final Supplier<AttachmentType<Integer>> blood_amount = ATTACHMENT_TYPES.register(
            "blood_amount", () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler())
                    .serialize(Codec.INT.fieldOf("blood_amount")).build()
    );
}
