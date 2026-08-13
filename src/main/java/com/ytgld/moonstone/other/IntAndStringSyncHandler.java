package com.ytgld.moonstone.other;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;

import java.util.HashMap;
import java.util.Map;

public class IntAndStringSyncHandler implements AttachmentSyncHandler<IntAndStringSyncHandler.ISClass> {

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        return holder == to;
    }

    @Override
    public void write(RegistryFriendlyByteBuf buf, ISClass attachment, boolean initialSync) {
        buf.writeInt(attachment.map.size());
        for (Map.Entry<String, Integer> entry : attachment.map.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeInt(entry.getValue());
        }
    }

    @Override
    public ISClass read( IAttachmentHolder holder, RegistryFriendlyByteBuf buf, ISClass previousValue) {
        int size = buf.readInt(); // read size first
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String key = buf.readUtf(32767);
            int value = buf.readInt();
            map.put(key, value);
        }

        return new ISClass(map);
    }

    public static final Codec<ISClass> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Codec.INT)
                            .xmap(HashMap::new, map -> (Map<String, Integer>) map)
                            .fieldOf("map")
                            .forGetter(ISClass::map)
            ).apply(instance, ISClass::new)
    );

    public record ISClass(HashMap<String, Integer> map) {
    }
}

