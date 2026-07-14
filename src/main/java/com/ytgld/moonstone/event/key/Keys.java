package com.ytgld.moonstone.event.key;

import com.mojang.blaze3d.platform.InputConstants;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class Keys {
    public static KeyMapping.Category moonstone = new KeyMapping.Category(Identifier.fromNamespaceAndPath(Moonstone.MODID,"moonstone"));


    public static final KeyMapping KEY_MAPPING_LAZY_R =
            (new KeyMapping("key.moonstone.r", InputConstants.KEY_R, moonstone));


}
