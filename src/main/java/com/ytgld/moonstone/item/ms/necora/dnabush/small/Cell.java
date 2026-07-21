package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.CellZombie;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.IKet;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

import static com.ytgld.moonstone.event.AllEvent.*;

public class Cell extends TheNecoraDNABush implements IKet {
    public Cell(Properties properties) {
        super(properties);
    }

    @Override
    public int maxSize() {
        return 2;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.cell.tool.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        tooltip.add(Component.translatable("item.cell.tool.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));

        tooltip.add(Component.literal("").withStyle(ChatFormatting.DARK_RED));
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable("item.cell.tool.string").withStyle(ChatFormatting.DARK_RED));
        }else {
            tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("dnabush"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("dnabush").build())

        ), true);
    }

    @Override
    public KeyMapping theKeyMapping() {
        return Keys.ZombieC;
    }
}
