package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DNAItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonStoneMod.MODID);
    public static final RegistryObject<Item> atp_height =REGISTRY.register("atp_height",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_big_boom =REGISTRY.register("cell_big_boom",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> speed_metabolism =REGISTRY.register("speed_metabolism",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_disorder =REGISTRY.register("cell_disorder",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_off_on =REGISTRY.register("cell_off_on",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_darwin =REGISTRY.register("cell_darwin",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_god =REGISTRY.register("cell_god",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_inheritance =REGISTRY.register("cell_inheritance",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_oxygen =REGISTRY.register("cell_oxygen",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_break_down_water =REGISTRY.register("cell_break_down_water",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_ground =REGISTRY.register("cell_ground",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_in_water =REGISTRY.register("cell_in_water",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_in_air =REGISTRY.register("cell_in_air",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_necrosis =REGISTRY.register("cell_necrosis",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_digestion =REGISTRY.register("cell_digestion",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_acid =REGISTRY.register("cell_acid",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_eyes =REGISTRY.register("cell_eyes",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_bone_add =REGISTRY.register("cell_bone_add",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_sense =REGISTRY.register("cell_sense",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_cranial =REGISTRY.register("cell_cranial",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_dna_suppression =REGISTRY.register("cell_dna_suppression",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_putrefactive =REGISTRY.register("cell_putrefactive",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_synthesis =REGISTRY.register("cell_synthesis",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_compress =REGISTRY.register("cell_compress",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_preferential =REGISTRY.register("cell_preferential",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_flu =REGISTRY.register("cell_flu",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_constant =REGISTRY.register("cell_constant",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static final RegistryObject<Item> cell_chromosome =REGISTRY.register("cell_chromosome",()->{return new DNAS(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));});
    public static class DNAS extends Item implements Iplague {

        public DNAS(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
            super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
            p_41423_.add(Component.translatable("item.dnas.tool.string").withStyle(ChatFormatting.RED));
        }
    }

}
