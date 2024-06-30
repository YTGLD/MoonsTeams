package com.moonstone.moonstonemod.event;

public class Tool {

    /*
    @SubscribeEvent
    public void ItemTooltipEventASD(ItemTooltipEvent event){
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (stack.is(Items.gorillacake.get())) {
            List<Component> list = new ArrayList<>();
            list.add(Component.translatable("moonstone.attribute.name.ectoplasm").withStyle(ChatFormatting.GOLD));
            if (player != null) {
                double ectoplasm = Handler.getAttSize(player, MAttribute.ectoplasm.get());

                list.add(Component.literal(ectoplasm +"%").withStyle(ChatFormatting.GOLD));
            }
            list.add(Component.translatable("moonstone.attribute.name.meet").withStyle(ChatFormatting.GOLD));
            if (player != null) {
                double ectoplasm = Handler.getAttSize(player, MAttribute.nano.get());
                list.add(Component.literal(ectoplasm +"%").withStyle(ChatFormatting.GOLD));
            }
            list.add(Component.translatable("moonstone.attribute.name.nano").withStyle(ChatFormatting.GOLD));
            if (player != null) {
                double ectoplasm = Handler.getAttSize(player, MAttribute.meet.get());
                list.add(Component.literal(ectoplasm +"%").withStyle(ChatFormatting.GOLD));
            }
            list.add(Component.literal(""));
            event.getToolTip().addAll(list);
        }

    }

     */
}
