package com.moonstone.moonstonemod.init.moonstoneitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class BookItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonStoneMod.MODID);

    public static final String blood_stasisTAG ="blood_stasisTAG";
    public static final String bone_structureTAG ="bone_structureTAG";
    public static final String mummificationTAG ="mummificationTAG";
    public static final String organizational_regenerationTAG ="organizational_regenerationTAG";
    public static final String tumourTAG ="tumourTAG";


    public static final RegistryObject<Item> blood_stasis =
            REGISTRY.register("blood_stasis",()-> new BookItem(
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    0.1f,
            AttributeModifier.Operation.MULTIPLY_BASE
            ,"item.blood_stasis.tool.string"));
    public static final RegistryObject<Item> bone_structure =
            REGISTRY.register("bone_structure",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ARMOR,
                    0.12f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.bone_structure.tool.string"));
    public static final RegistryObject<Item> mummification =
            REGISTRY.register("mummification",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    6,
                    AttributeModifier.Operation.ADDITION
                    ,"item.mummification.tool.string"));
    public static final RegistryObject<Item> organizational_regeneration =
            REGISTRY.register("organizational_regeneration",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    AttReg.heal.get(),
                    0.1f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.organizational_regeneration.tool.string"));

    public static final RegistryObject<Item> tumour =
            REGISTRY.register("tumour",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    AttReg.cit.get(),
                    0.1f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.tumour.tool.string"));

    public static final RegistryObject<Item> bloodstain =
            REGISTRY.register("bloodstain",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    0.07f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.bloodstain.tool.string"));

    public static final RegistryObject<Item> detect =
            REGISTRY.register("detect",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ATTACK_SPEED,
                    0.14f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.detect.tool.string"));

    public static final RegistryObject<Item> exercise_reinforcement =
            REGISTRY.register("exercise_reinforcement",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MOVEMENT_SPEED,
                    0.1f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.exercise_reinforcement.tool.string"));

    public static final RegistryObject<Item> plague_book =
            REGISTRY.register("plague_book",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ATTACK_DAMAGE,
                    0.08f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.plague_book.tool.string"));

    public static final RegistryObject<Item> spore_outbreak =
            REGISTRY.register("spore_outbreak",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.KNOCKBACK_RESISTANCE,
                    0.18f,
                    AttributeModifier.Operation.MULTIPLY_BASE
                    ,"item.spore_outbreak.tool.string"));

    public static final RegistryObject<Item> weak =
            REGISTRY.register("weak",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    4,
                    AttributeModifier.Operation.ADDITION
                    ,"item.weak.tool.string"));

    public static class BookItem extends TheNecoraIC implements ICurioItem, Iplague {
        public final Attribute attribute;
        public final float size;
        public final AttributeModifier.Operation operation ;

        public final String[] stringName;

        public BookItem(Properties properties,  Attribute attribute, float size,AttributeModifier.Operation operation, String... stringName) {
            this.operation = operation;
            this.attribute = attribute;
            this.size = size;
            this.stringName = stringName;
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
            Multimap<Attribute, AttributeModifier>modifierMultimap = HashMultimap.create();
            modifierMultimap.put(this.attribute,
                    new AttributeModifier(uuid,"1",this.size,operation));
            return modifierMultimap;
        }



        @Override
        public void curioTick(SlotContext slotContext, ItemStack stack) {
            if (stack.getTag()==null){
                stack.getOrCreateTag();
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);

            pTooltipComponents.add(Component.translatable("moonstone.use.dna").withStyle(ChatFormatting.RED));

            for (String string : stringName) {
                pTooltipComponents.add(Component.translatable(string).withStyle(ChatFormatting.RED));
            }
        }
    }
}
