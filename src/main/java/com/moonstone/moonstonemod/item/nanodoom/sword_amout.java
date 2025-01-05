package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.moonstoneitem.Doom;

public class sword_amout extends Doom {
    /*

    你的攻击速度会随着斩击次数的增加而增加，但不会超过100%
    但是你的伤害会逐渐减少，最多降低到原先的33%

    全力攻击时不会造成无敌帧保护

    当你的斩击次数到达10时，每次攻击都会造成范围效果
     */
    /*
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.sword_amout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.sword_amout.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.sword_amout.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.sword_amout.tool.string.3").withStyle(ChatFormatting.GOLD));

    }
    public static String attack_size = "AttackSize";
    public Multimap<Attribute, AttributeModifier> getAttributeModifier(ItemStack stack){
        Multimap<Attribute, AttributeModifier> get = HashMultimap.create();
        UUID uuid = UUID.fromString("8610364c-1fe9-3801-96a1-4fb3dc123fc9");
        float s = 0;
        if (stack.getTag()!=null){
            s = stack.getTag().getInt(attack_size);//10
        }
        s/=10;//1
        get.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "million_sword",s, AttributeModifier.Operation.MULTIPLY_BASE));
        get.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "million_sword",-s*0.66, AttributeModifier.Operation.MULTIPLY_BASE));

        return get;
    }
    public static void Sword_m(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.sword_amout.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (!stack.isEmpty()){
                                if (stack.is(Items.sword_amout.get())){
                                    if (stack.getTag() != null){
                                        if (player.getAttackStrengthScale(0.5f)==1) {
                                            event.getEntity().invulnerableTime = 0;
                                            sword sword = new sword(EntityTs.sword.get(), player.level());
                                            if (stack.getTag().getInt(attack_size) < 11) {
                                                stack.getTag().putInt(attack_size, stack.getTag().getInt(attack_size) + 1);
                                            }
                                            if (stack.getTag().getInt(attack_size)>=9){
                                                sword.setPos(event.getEntity().getX(), event.getEntity().getY() -0.2f, event.getEntity().getZ());
                                                sword.setOwnerUUID(player.getUUID());
                                                player.level().addFreshEntity(sword);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.getOrCreateTag().putString("a","a");
        bolt bolt = new bolt(EntityTs.bolt.get(),slotContext.entity().level());

        if (stack.getOrCreateTag().getInt(attack_size)>10){

            if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount% 10 == 0) {
                bolt.setPos(slotContext.entity().getX() + Mth.nextFloat(RandomSource.create(), -0.51f, 0.51f), slotContext.entity().getEyeY(), slotContext.entity().getZ() + Mth.nextFloat(RandomSource.create(), -0.5f, 0.5f));
                slotContext.entity().level().addFreshEntity(bolt);
            }

        }
        sword sword = new sword(EntityTs.sword.get(),slotContext.entity().level());
        if (stack.getOrCreateTag().getInt(attack_size)>0){
            if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount% 40 == 0) {

                stack.getOrCreateTag().putInt(attack_size, stack.getOrCreateTag().getInt(attack_size) - 1);

                sword.setPos(slotContext.entity().getX(),slotContext.entity().getY(),slotContext.entity().getZ());
                sword.setOwnerUUID(slotContext.entity().getUUID());
                slotContext.entity().level().addFreshEntity(sword);
            }
        }

        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifier(stack));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifier(stack));
    }


             */
}
