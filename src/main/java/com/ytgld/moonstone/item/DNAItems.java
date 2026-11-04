package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public class DNAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moonstone.MODID);
    public static DeferredItem<@NotNull Item> register(String name, Function<ResourceLocation, ? extends Item> func) {
        return ITEMS.register(name, func);
    }

    public static class DNAS extends TheNecora {
        private final AttModify[] attModify;
        public DNAS(Properties properties, @Nullable AttModify... attModify) {
            super(properties);
            this.attModify = attModify;
        }

        @Override
        public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            super.text(itemStack, builder, tooltipFlag);
            builder.accept(Component.translatable("item.moonstone.dnas.tool.1").withStyle(ChatFormatting.RED));
            if (attModify != null) {
                builder.accept(Component.translatable("item.moonstone.dna_push.tool.3").setStyle(Style.EMPTY.withColor(0xffff0000)));
                for (AttModify modify : attModify){
                    if (modify != null && modify.holder !=null) {
                        Attribute attribute = BuiltInRegistries.ATTRIBUTE.get(modify.holder.getKey());
                        if (attribute != null) {
                            String string = "";
                            int it = 1;
                            if (modify.attributeModifier.operation() != AttributeModifier.Operation.ADD_VALUE) {
                                string = "%";
                                it = 100;
                            }
                            builder.accept(Component.translatable(attribute.getDescriptionId())
                                    .append(Component.literal("："))
                                    .append(String.valueOf((float)(modify.attributeModifier.amount() * it))).append(string).setStyle(Style.EMPTY.withColor(0xffff0000)));
                        }
                    }
                }
                if (attModify.length < 1) {
                    builder.accept(Component.translatable(this.getDescriptionId() + ".tool.string").setStyle(Style.EMPTY.withColor(0xffff0000)));
                }
            }
        }

        public AttModify[] getAttModify() {
            return attModify;
        }

        public record AttModify(Holder<Attribute> holder , AttributeModifier attributeModifier ){}
    }

    public static final DeferredItem<@NotNull Item> cell_big_boom =register("cell_big_boom",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> speed_metabolism =register("speed_metabolism",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_disorder =register("cell_disorder",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_darwin =register("cell_darwin",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_god =register("cell_god",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_inheritance =register("cell_inheritance",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_digestion =register("cell_digestion",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_acid =register("cell_acid",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_eyes =register("cell_eyes",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_compress =register("cell_compress",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_flu =register("cell_flu",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_constant =register("cell_constant",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));
    public static final DeferredItem<@NotNull Item> cell_cranial =register("cell_cranial",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64)));


    public static final DeferredItem<@NotNull Item> atp_height =register("atp_height",(identifier)-> new DNAS(new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.MAX_HEALTH,new AttributeModifier(
            identifier,
            0.25f,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_off_on =register("cell_off_on",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.ATTACK_DAMAGE,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));

    public static final DeferredItem<@NotNull Item> cell_oxygen =register("cell_oxygen",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.MOVEMENT_SPEED,new AttributeModifier(
            identifier,
            0.005f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_break_down_water =register("cell_break_down_water",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            NeoForgeMod.SWIM_SPEED,new AttributeModifier(
            identifier,
            0.015f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_ground =register("cell_ground",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.BLOCK_BREAK_SPEED,new AttributeModifier(
            identifier,
            0.02f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_in_water =register("cell_in_water",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.WATER_MOVEMENT_EFFICIENCY,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_in_air =register("cell_in_air",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.JUMP_STRENGTH,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_necrosis =register("cell_necrosis",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            AttReg.heal,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
      public static final DeferredItem<@NotNull Item> cell_bone_add =register("cell_bone_add",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.ARMOR,new AttributeModifier(
            identifier,
            0.25F,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_sense =register("cell_sense",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
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
    public static final DeferredItem<@NotNull Item> cell_dna_suppression =register("cell_dna_suppression",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            AttReg.cit,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_putrefactive =register("cell_putrefactive",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.BURNING_TIME,new AttributeModifier(
            identifier,
            -0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_synthesis =register("cell_synthesis",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.ATTACK_SPEED,new AttributeModifier(
            identifier,
            0.01f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> cell_preferential =register("cell_preferential",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
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
    public static final DeferredItem<@NotNull Item> cell_chromosome =register("cell_chromosome",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.SAFE_FALL_DISTANCE,new AttributeModifier(
            identifier,
            0.1,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));


    public static final DeferredItem<@NotNull Item> organizational_calcification =register("organizational_calcification",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.ARMOR_TOUGHNESS,new AttributeModifier(
            identifier,
            0.2,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cell_swelling =register("cell_swelling",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            AttReg.speed,new AttributeModifier(
            identifier,
            0.005f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> muscle_contraction =register("muscle_contraction",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.ATTACK_KNOCKBACK,new AttributeModifier(
            identifier,
            0.03,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> lactate_accumulation =register("lactate_accumulation",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(
            identifier,
            0.01,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> retinal_reconstruction =register("retinal_reconstruction",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.SNEAKING_SPEED,new AttributeModifier(
            identifier,
            0.05F,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> metabolic_load =register("metabolic_load",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.EXPLOSION_KNOCKBACK_RESISTANCE,new AttributeModifier(
            identifier,
            0.05,
            AttributeModifier.Operation.ADD_VALUE)
    )));
    public static final DeferredItem<@NotNull Item> cerebral_cortex_decay =register("cerebral_cortex_decay",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.LUCK,new AttributeModifier(
            identifier,
            0.005,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    )));
    public static final DeferredItem<@NotNull Item> airborne_transmission =register("airborne_transmission",(identifier)-> new  DNAS (new Item.Properties().stacksTo(64),new DNAS.AttModify(
            Attributes.SWEEPING_DAMAGE_RATIO,new AttributeModifier(
            identifier,
            0.01F,
            AttributeModifier.Operation.ADD_VALUE)
    )));
}
