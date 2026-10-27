package com.ytgld.moonstone.other;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;

public class IntSyncHandler implements AttachmentSyncHandler<Integer> {

    @Override
    public void write(RegistryFriendlyByteBuf buf, Integer attachment, boolean initialSync) {
        if (initialSync) {
            buf.writeInt(attachment);
        } else {
            buf.writeInt(attachment);
        }
    }

    @Override
    @Nullable
    public Integer read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable Integer previousValue) {
        Integer newValue = buf.readInt();
        if (previousValue == null) {
            return newValue;
        } else {
            return newValue;
        }
    }

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        return holder == to;
    }
}

