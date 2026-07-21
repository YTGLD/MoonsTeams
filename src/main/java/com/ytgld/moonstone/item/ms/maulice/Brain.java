package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Brain extends MLS {
    public Brain(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.Brain")
                    .defineInRange("Brain", 5F, 0, Integer.MAX_VALUE);
            intValue2 = builder.translation("moonstone.config.Brain2")
                    .defineInRange("Brain2", 2f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryMLS;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("Brain",
                            "莫里斯脑子", "次数"),
                    new CIString("Brain2",
                            "莫里斯脑子2", "伤害"));
        }
    }

    public static void brainLHurt(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.brain.get())) {
                String name = event.getEntity().getName().getString();
                player.getPersistentData().putInt(name, player.getPersistentData().getIntOr(name, 0) + 1);
                if (player.getPersistentData().getIntOr(name, 0) >= ConfigItem.intValue.get().intValue()) {
                    event.setNewDamage(event.getNewDamage() * ConfigItem.intValue2.get().floatValue());
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.NEUTRAL, 4.5F, 4.1F);
                    player.getPersistentData().remove(name);
                }
            }
        }

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Handler.stackCreateTag(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.brain.tool.string").withStyle(ChatFormatting.DARK_GREEN));
    }

    public static final String brain = "brain_brain";
}
