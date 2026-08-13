package com.ytgld.moonstone.item.si.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareBase;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDownAndOut;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
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
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NightmareBaseItem extends NightmareBase {

    public NightmareBaseItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));

        }
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.IntValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.NightmareBaseItem")
                    .defineInRange("NightmareBaseItem", 3, 0, 7);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseItem",
                    "噩梦基座", "开局基于几个物品"));
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
            stack.set(DataReg.tag, new CompoundTag());
        }

        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            if (!compoundTag.getBoolean("canDo")) {
                Random random = new Random();
                ArrayList<Item> items = new ArrayList<>(List.of(
                        Items.nightmare_base_stone.get(),
                        Items.nightmare_base_reversal.get(),
                        Items.nightmare_base_black_eye.get(),
                        Items.nightmare_base_redemption.get(),
                        Items.nightmare_base_fool.get(),
                        Items.nightmare_base_insight.get(),
                        Items.nightmare_base_start.get()
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

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        float s = -0.3f;
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_redemption_down_and_out.asItem())) {
            s += (float) (RedemptionDownAndOut.ConfigItem.intValue.getAsDouble() / 100F);
        }
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_reversal_mysterious.asItem())) {
            s = 0;
        }
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
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

