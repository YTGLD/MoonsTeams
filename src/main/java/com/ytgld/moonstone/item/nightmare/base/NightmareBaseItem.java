package com.ytgld.moonstone.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.nightmare.NightmareBase;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class NightmareBaseItem extends NightmareBase {

    public NightmareBaseItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.IntValue intValue ;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            builder.push("NightmareBaseItem");
            intValue =  builder.translation("chest_item.config.NightmareBaseItem")
                    .defineInRange("number",5,0,Integer.MAX_VALUE);
            builder.pop();
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseItem",
                    "噩梦基座","开局基于几个物品"));
        }
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
            stack.set(DataReg.tag,new CompoundTag());
        }

        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag !=null) {
            if (!compoundTag.getBooleanOr("canDo", false)) {
                Random random = new Random();
                ArrayList<Item> items = new ArrayList<>(List.of(
                        InitItems.nightmare_base_stone.get(),
                        InitItems.nightmare_base_reversal.get(),
                        InitItems.nightmare_base_black_eye.get(),
                        InitItems.nightmare_base_redemption.get(),
                        InitItems.nightmare_base_fool.get(),
                        InitItems.nightmare_base_insight.get(),
                        InitItems.nightmare_base_start.get()
                ));
                for (int i = 0; i < ConfigItem.intValue.getAsInt(); i++) {
                    if (!items.isEmpty()) {
                        int index = random.nextInt(items.size());
                        Item selectedItem = items.remove(index);
                        addLoot(slotContext.entity(), selectedItem, stack);
                    }
                }
                compoundTag.putBoolean("canDo", true);
            }
        }
    }

    public  Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
         Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        float s = -0.3f;
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.parse(this.descriptionId), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.parse(this.descriptionId), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(Identifier.parse(this.descriptionId), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }

    private void addLoot(Entity entity,
                         Item itemList,
                         ItemStack stack) {
        if (entity instanceof Player player) {
            if (stack.get(DataReg.tag) != null) {
                player.addItem(itemList.getDefaultInstance());
            }
        }
    }
}

