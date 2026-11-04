package com.ytgld.moonstone.event;

import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.LoveCharm;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.CellBlood;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.CellBoom;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.cell_calcification;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.cell_mummy;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AllEvent {
    public static final String cod = "cod";
    public static final String SALMON = "SALMON";
    public static final String CHICKEN = "CHICKEN";
    public static final String BEEF = "BEEF";
    public static final String RABBIT = "RABBIT";
    public static final String MUTTON = "MUTTON";
    public static final String PORKCHOP = "PORKCHOP";
    public static final String TROPICAL_FISH = "TROPICAL_FISH";
    public static final String give = "GiveItem";
    public static final String fungus = "GiveFungus";
    public static final String virus = "virus";
    public static float aFloat = 0;
    public static final String lvl= "germString";
    public static final String lvlSize= "germStringLvlSize";
    public static final String lvl_parasite = "lvl";
    public static final String sizeLevel = "sizeLevel";
    public static final String blood = "bloodgene";
    public static final String rage = "ragegene";
    public static final String FlySword = "FlySword";

    public static final String blood_hurt = "blood_hurt";
    public static  final String blood_jump = "blood_jump";
    public static  final String blood_eat = "blood_eat";
    public static  final String blood_spawn = "blood_spawn";
    public static  final String blood_enchant = "blood_enchant";

    public static final String DamageCell = "DamageCell";
    public static final  String muMMY = cell_mummy.Mummy;
    public static final  String boom = CellBoom.cb;
    public static final  String calcification = cell_calcification.cc;
    public static final  String cb_blood = CellBlood.c_blood;
    public static final String Giant_Time ="Giant_Time";
    public static final String Giant_Boom ="Giant_Boom";
    public static final String Not_Giant_BLOOD ="Not_Giant_BLOOD";
    public static final String Subspace_Giant ="Subspace_Giant";

    public static final String Bone_Giant = "Bone_Giant";
    public static final String Parasitic_cell_Giant = "Parasitic_cell_Giant";
    public static final String Disgusting__cell_Giant = "Disgusting__cell_Giant";
    @SubscribeEvent
    public void setUUIDPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Set<UUID> setUUID = new HashSet<>();
        setUUID.add(UUID.fromString("3cc7f94f-5dfc-46a2-9fa3-6be4f46d0cba"));
        setUUID.add(UUID.fromString("081c4a0e-d3d6-4a5b-9dec-3c03c92cdc8e"));
        setUUID.add(UUID.fromString("00000000-0000-3005-998f-5030997cf9c8"));
        setUUID.add(UUID.fromString("70f68910-6833-401b-988b-30ceeb675b60"));
        setUUID.add(UUID.fromString("5939a1ab-6e04-4511-af2e-2817cdda3089"));
        setUUID.add(UUID.fromString("66b8e3c0-c0f9-47ec-b016-aaa61d112c76"));
        setUUID.add(UUID.fromString("52d022e0-4060-4dde-9f68-03d454e47150"));

        Player player = event.getEntity();
        if (setUUID.contains(player.getUUID())) {
            if (!player.getTags().contains("YtgldAndMyFriends")) {
                ItemStack stack = InitItems.love_charm.get().getDefaultInstance();
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putString(LoveCharm.name, player.getDisplayName().getString());
                stack.set(DataReg.tag,compoundTag);
                player.addItem(stack);
                player.addTag("YtgldAndMyFriends");
            }
        }
    }
}
