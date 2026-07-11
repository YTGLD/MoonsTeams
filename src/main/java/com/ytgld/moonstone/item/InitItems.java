package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.nightmare.base.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class InitItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moonstone.MODID);
    public static final DeferredItem<@NotNull Item> NightmareBaseItem_ = register("nightmare_base", (Identifier)-> new NightmareBaseItem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_black_eye  = register("nightmare_base_black_eye", (Identifier)-> new NightmareBaseBlackEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_fool  = register("nightmare_base_fool", (Identifier)-> new NightmareBaseFool(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_insight  = register("nightmare_base_insight", (Identifier)-> new NightmareBaseInsight(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_redemption  = register("nightmare_base_redemption", (Identifier)-> new NightmareBaseRedemption(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_reversal  = register("nightmare_base_reversal", (Identifier)-> new NightmareBaseItemReversal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_start  = register("nightmare_base_start", (Identifier)-> new NightmareBaseStart(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_stone  = register("nightmare_base_stone", (Identifier)-> new NightmareBaseStone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,Identifier))));

    public static class TabChestItem{
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Moonstone.MODID);
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tab = CREATIVE_MODE_TABS.register(Moonstone.MODID, () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.moonstone"))
                .icon(()->NightmareBaseItem_.asItem().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(NightmareBaseItem_);
                    output.accept(nightmare_base_black_eye);
                    output.accept(nightmare_base_fool);
                    output.accept(nightmare_base_insight);
                    output.accept(nightmare_base_redemption);
                    output.accept(nightmare_base_reversal);
                    output.accept(nightmare_base_start);
                    output.accept(nightmare_base_stone);
                }).build());

    }

    public static DeferredItem<@NotNull Item> register(String name, Function<Identifier, ? extends Item> func) {
        return ITEMS.register(name,func);
    }
}
