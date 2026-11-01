package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.necora.EmbeddedDNA;
import com.ytgld.moonstone.item.ms.necora.sequence.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DNAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moonstone.MODID);
    //肌肉操控
    //僵尸在死亡时会为召唤者恢复生命值和提供力量效果
    public static final DeferredItem<@NotNull Item> muscle_manipulation = register("muscle_manipulation", (identifier) -> new EmbeddedDNA(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier))));

    //生物酶合成
    //提供10%最大生命值
    public static final DeferredItem<@NotNull Item> biological_enzyme_synthesis = register("biological_enzyme_synthesis", (identifier) -> new EmbeddedDNA(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier))));

    //乳酸堆积
    //奔跑状态下获得击退抗性
    public static final DeferredItem<@NotNull Item> lactic_acid_accumulation = register("lactic_acid_accumulation", (identifier) -> new EmbeddedDNA(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier))));

    //坏死性囊肿
    //杀死生物留下有中毒效果的毒气，并且自身免疫中毒
    public static final DeferredItem<@NotNull Item> necrotizing_cyst = register("necrotizing_cyst", (identifier) -> new EmbeddedDNA(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier))));

    //合成物逸散
    //自身死亡后对攻击者造成100%自身最大生命值的伤害
    public static final DeferredItem<@NotNull Item> compound_dispersion = register("compound_dispersion", (identifier) -> new EmbeddedDNA(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier))));


    public static final DeferredItem<@NotNull Item> methane_synthesis = register("methane_synthesis", (identifier) ->
            new MethaneSynthesis(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> spike_protrusion = register("spike_protrusion", (identifier) ->
            new SpikeProtrusion(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> epidermal_digestion = register("epidermal_digestion", (identifier) ->
            new EpidermalDigestion(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> activation_enzyme = register("activation_enzyme", (identifier) ->
            new ActivationEnzyme(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> mucosal_injury = register("mucosal_injury", (identifier) ->
            new MucosalInjury(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> fat_accumulation = register("fat_accumulation", (identifier) ->
            new FatAccumulation(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> bone_fibers = register("bone_fibers", (identifier) ->
            new BoneFibers(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> blood_reconstruction = register("blood_reconstruction", (identifier) ->
            new BloodReconstruction(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));


    public static DeferredItem<@NotNull Item> register(String name, Function<Identifier, ? extends Item> func) {
        return ITEMS.register(name, func);
    }
}
