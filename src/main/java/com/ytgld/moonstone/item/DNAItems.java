package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.EmbeddedDNA;
import com.ytgld.moonstone.item.ms.necora.sequence.*;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public static class DNAS extends TheNecora{
        private final AttModify[] attModify;
        public DNAS(Properties properties, @Nullable AttModify... attModify) {
            super(properties);
            this.attModify = attModify;
        }

        public AttModify[] getAttModify() {
            return attModify;
        }

        public record AttModify(Holder<Attribute> holder , AttributeModifier attributeModifier ){}
    }

    public static final DeferredItem<@NotNull Item> atp_height =register("atp_height",(identifier)-> new DNAS(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.MAX_HEALTH,new AttributeModifier(
            identifier,
            0.25f,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_big_boom =register("cell_big_boom",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> speed_metabolism =register("speed_metabolism",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_disorder =register("cell_disorder",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_off_on =register("cell_off_on",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.ATTACK_DAMAGE,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));

    public static final DeferredItem<@NotNull Item> cell_darwin =register("cell_darwin",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_god =register("cell_god",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_inheritance =register("cell_inheritance",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_digestion =register("cell_digestion",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_acid =register("cell_acid",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_eyes =register("cell_eyes",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_compress =register("cell_compress",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_flu =register("cell_flu",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_constant =register("cell_constant",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));
    public static final DeferredItem<@NotNull Item> cell_cranial =register("cell_cranial",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)), (DNAS.AttModify) null));



    public static final DeferredItem<@NotNull Item> cell_oxygen =register("cell_oxygen",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.MOVEMENT_SPEED,new AttributeModifier(
            identifier,
            0.005f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_break_down_water =register("cell_break_down_water",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            NeoForgeMod.SWIM_SPEED,new AttributeModifier(
            identifier,
            0.015f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_ground =register("cell_ground",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.BLOCK_BREAK_SPEED,new AttributeModifier(
            identifier,
            0.02f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_in_water =register("cell_in_water",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.WATER_MOVEMENT_EFFICIENCY,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_in_air =register("cell_in_air",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.JUMP_STRENGTH,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_necrosis =register("cell_necrosis",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            AttReg.heal,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
      public static final DeferredItem<@NotNull Item> cell_bone_add =register("cell_bone_add",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.ARMOR,new AttributeModifier(
            identifier,
            0.25F,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_sense =register("cell_sense",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.OXYGEN_BONUS,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_VALUE)
    ),new DNAS.AttModify(
            Attributes.OXYGEN_BONUS,new AttributeModifier(
            identifier,
            0.1f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_dna_suppression =register("cell_dna_suppression",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            AttReg.cit,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_putrefactive =register("cell_putrefactive",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.BURNING_TIME,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_synthesis =register("cell_synthesis",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.ATTACK_SPEED,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_preferential =register("cell_preferential",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            AttReg.heal,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    ),new DNAS.AttModify(
            Attributes.MAX_HEALTH,new AttributeModifier(
            identifier,
            0.25,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_chromosome =register("cell_chromosome",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, identifier)),new DNAS.AttModify(
            Attributes.SAFE_FALL_DISTANCE,new AttributeModifier(
            identifier,
            0.1,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
}
