package com.ytgld.moonstone.other;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StringSetSync implements AttachmentSyncHandler<Set<String>> {

    @Override
    public void write(RegistryFriendlyByteBuf buf, Set<String> attachment, boolean initialSync) {
        buf.writeVarInt(attachment.size());
        for (String s : attachment) {
            buf.writeUtf(s);
        }
    }

    @Override
    @Nullable
    public Set<String> read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable Set<String> previousValue) {
        int size = buf.readVarInt();
        Set<String> set = previousValue != null ? previousValue : new HashSet<>();
        set.clear();
        for (int i = 0; i < size; i++) {
            set.add(buf.readUtf(32767)); // 32767 是字符串最大长度限制
        }
        return set;
    }

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        return holder == to;
    }

    public static class StringSetCodec {
        public static final Codec<Set<String>> CODEC = Codec.STRING.listOf()
                .xmap(HashSet::new, ArrayList::new);

    }
}
