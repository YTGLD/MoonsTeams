package com.ytgld.moonstone.item.si.nightmare.redemption;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;


/**
 * 虚伪的自尊
 * <p>
 * 附近每存在一个实体你都会获得下列加强<p>
 * +5%治疗<p>
 * +5%伤害<p>
 * +5%暴击<p>
 * +5%护甲<p>
 * +5%攻速<p>
 * <p>
 * 如果你承受了邪念之窥眸的诅咒..那么：
 * <p>
 * 撕咬附近的生物，每秒造成20%你的面板伤害
 * <p>
 * 每多一个目标，伤害提高20%
 * <p>
 * 每次撕咬都会吸取生命值并对目标造成削弱
 */
public class HypocriticalSelfEsteem extends NightmareSmall {
    public static final String MALICE_DIE = "HypocriticalSelfEsteem";

    public HypocriticalSelfEsteem(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.ConfigValue<List<String>> intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.HypocriticalSelfEsteem")
                    .define("HypocriticalSelfEsteem", new ArrayList<>(List.of("")));
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("HypocriticalSelfEsteem",
                    "虚伪自尊", "黑名单")
            );
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                if (SIHandler.hascurio(player, Items.hypocritical_self_esteem.get())) {

                    player.getAttributes().addTransientAttributeModifiers(this.Head(stack));
                    int size = 0;
                    Vec3 playerPos = player.position();
                    int range = 24;
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    for (LivingEntity living : entities) {
                        if (!living.is(player) && living.isAlive()) {
                            if (living.tickCount % 30 == 0) {
                                living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.PHANTOM_BITE, SoundSource.AMBIENT, 0.2f, 0.2f);
                                break;
                            }
                        }
                    }

                    for (LivingEntity living : entities) {
                        size = entities.size();

                        if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                            if (!living.is(player) && living.isAlive()) {
                                if (living.tickCount % 10 == 0) {
                                    ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(living.getType());
                                    Set<ResourceLocation> blacklist = new HashSet<>();
                                    for (String aaa : ConfigItem.intValue.get()) {
                                        if (!aaa.isEmpty()) {
                                            String[] parts = aaa.split(":");
                                            if (parts.length > 0) {
                                                blacklist.add(ResourceLocation.fromNamespaceAndPath(parts[0], parts[1]));
                                            }
                                        }
                                    }
                                    if (!blacklist.contains(name)) {
                                        int l = entities.size() + 1;
                                        if (l > 10) {
                                            l = 10;
                                        }
                                        living.hurt(living.damageSources().dryOut(),
                                                (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * (0.2f * (l)));

                                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false));
                                        living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false));
                                        living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 1, false, false));
                                        living.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2, false, false));
                                        living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 3, false, false));
                                        living.invulnerableTime = 0;
                                        player.heal(2);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (stack.get(DataReg.tag) != null) {
                        stack.get(DataReg.tag).putInt(MALICE_DIE, size);
                    } else {
                        stack.set(DataReg.tag, new CompoundTag());
                    }
                }
            }
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(this.Head(stack));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
        if (tooltipFlag.hasShiftDown()) {
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("effect.minecraft.regeneration").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.attack_damage").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.armor").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.attack_speed").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attrib.moonstone.critical").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDC143C))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        } else {
            tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }

    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        if (stack.get(DataReg.tag) != null) {

            float s = stack.get(DataReg.tag).getInt(MALICE_DIE);//1 == 100%
            if (s > 10) {
                s = 10;
            }
            s /= 100f;//0.01 = 1%
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()),
                    s * 5F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.heal, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()),
                    s * 5F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()),
                    s * 5F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.ARMOR, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()),
                    s * 5F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.cit, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()),
                    s * 5F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
        return multimap;
    }


}
