package com.ytgld.moonstone.event.loot;


import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ytgld.moonstone.event.AdvancementEvt;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChestLoot extends LootModifier {
    public static final Supplier<MapCodec<ChestLoot>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst,
                    ChestLoot::new)));

    /**
     * Constructs a LootModifier.
     *
     * @param conditions
     * @param priority
     */
    protected ChestLoot(LootItemCondition[] conditions, int priority) {
        super(conditions, priority);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        @Nullable Entity entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);
        Identifier s = lootContext.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        if (idSting.contains("chests/")) {
            if (entity != null) {
                AdvancementEvt.addLoot(objectArrayList, entity, 20);
                AdvancementEvt.nightmare_base_reversal_mysteriousLOOT(objectArrayList, entity);
                AdvancementEvt.nightmare_base_start_pod(objectArrayList, entity);
                AdvancementEvt.tricky_puppets(objectArrayList, entity);

            }
        }
        return objectArrayList;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }


}
