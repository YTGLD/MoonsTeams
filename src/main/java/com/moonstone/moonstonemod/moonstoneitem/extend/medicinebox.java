package com.moonstone.moonstonemod.moonstoneitem.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.catalyzer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class medicinebox extends TheNecoraIC {

    public static void necora(LivingEntityUseItemEvent.Finish event) {
        if (Handler.hascurio(event.getEntity(),Items.medicinebox.get())){
            if (event.getEntity() instanceof Player player) {
                if (event.getItem().is(Items.calcification.get())) {
                    player.addItem(new ItemStack(Items.acid.get()));
                }
                if (event.getItem().is(Items.masticatory.get())) {
                    player.addItem(new ItemStack(Items.atrophy.get()));
                }
                if (event.getItem().is(Items.polyphagia.get())) {
                    player.addItem(new ItemStack(Items.compression.get()));
                }
                if (event.getItem().is(Items.quadriceps.get())) {
                    player.addItem(new ItemStack(Items.enhancemen.get()));
                }
            }
        }

    }
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(com.moonstone.moonstonemod.init.Items.botton.get()));
                    Other.shrink(1);

                    return true;
                }
            }
        }
        return false;
    }

    public static int do_hurt;
    public static int do_apple;
    public static int do_jump;

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {

        return !Handler.hascurio(slotContext.entity(),this);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

        CuriosApi
                .addSlotModifier(linkedHashMultimap, "medicine", uuid, 3, AttributeModifier.Operation.ADDITION);


        return linkedHashMultimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            do_hurt = stack.getOrCreateTag().getInt(AllEvent.hurt_size);
            do_apple = stack.getOrCreateTag().getInt(AllEvent.apple);
            do_jump = stack.getOrCreateTag().getInt(AllEvent.jump_size);
        }
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        ItemStack stack = this.getDefaultInstance();
        if (stack.getTag() != null && stack.getTag().getBoolean(AllEvent.blood_eat) &&
                stack.getTag().getBoolean(AllEvent.blood_hurt) &&
                stack.getTag().getBoolean(AllEvent.blood_jump) &&
                stack.getTag().getBoolean(AllEvent.blood_spawn) &&
                stack.getTag().getBoolean(AllEvent.blood_enchant))
        {
            player.displayClientMessage(Component.translatable("item.medicinebox.tool.string.eat").append("%").withStyle(ChatFormatting.RED), true);
        }
        player.startUsingItem(p_41434_);

        return super.use(level,player,p_41434_);
    }
    public AttributeModifier attributeModifier (){
        return new AttributeModifier(UUID.fromString("f32cf69c-7cce-3f6d-81d4-191672800bce"), "medicinebox", 1, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        if (living instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("medicine").ifPresent(stacks -> {
                stacks.addPermanentModifier(this.attributeModifier());
            }));
        }
        if (!living.level().isClientSide) {
            IForgeRegistry<MobEffect> effects = ForgeRegistries.MOB_EFFECTS;
            @NotNull Set<ResourceLocation> set = effects.getKeys();
            List<ResourceLocation> list = set.stream().toList();
            Random random = new Random();
            ResourceLocation resourceLocation = list.get(random.nextInt(list.size()));
            MobEffect effect = effects.getValue(resourceLocation);
            if (effect != null && !living.hasEffect(effect)) {
                if (effect.isBeneficial() && !effect.isInstantenous()) {
                    living.addEffect(new MobEffectInstance(effect, 100, 0));
                }
            }
        }
        return s;
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.medicinebox.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable(""));
            if (stack.getTag() != null) {
                if (stack.getTag().getBoolean(AllEvent.blood_eat) &&
                        stack.getTag().getBoolean(AllEvent.blood_hurt) &&
                        stack.getTag().getBoolean(AllEvent.blood_jump) &&
                        stack.getTag().getBoolean(AllEvent.blood_spawn) &&
                        stack.getTag().getBoolean(AllEvent.blood_enchant)) {
                    tooltip.add(Component.translatable("item.medicinebox.tool.string.5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
                    tooltip.add(Component.translatable("item.medicinebox.tool.string.6").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
                    tooltip.add(Component.translatable(""));
                    tooltip.add(Component.translatable("item.medicinebox.tool.string.7").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));

                }
            }
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.8").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.medicinebox.tool.string.9").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }


    }
}
