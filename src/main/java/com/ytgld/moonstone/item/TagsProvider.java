package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.Doom;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.DNASequence;
import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TagsProvider extends ItemTagsProvider {
    public TagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider,  Moonstone.MODID);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagAppender<Item> toEctoplasmball2 = createTag("ectoplasmball_2");
        TagAppender<Item> toEctoplasmball4 = createTag("ectoplasmball_4");
        TagAppender<Item> toEctoplasmprism2 = createTag("ectoplasmprism_3");
        TagAppender<Item> toEctoplasmball = createTag("ectoplasmball");
        TagAppender<Item> toEctoplasmcloub = createTag("ectoplasmcloub");
        TagAppender<Item> toEctoplasmcube = createTag("ectoplasmcube");
        TagAppender<Item> toEctoplasmprism = createTag("ectoplasmprism");

        addItemsByClass(toEctoplasmball2, TheNecoraDNABush.class);
        addItemsByClass(toEctoplasmball2,TheNecora.class);
        addItemsByClass(toEctoplasmball2, DNASequence.class);
        addItemsByClass(toEctoplasmball4,Doom.class);
        addItemsByClass(toEctoplasmprism2,NightmareSmall.class);

        toEctoplasmball
                .add(InitItems.soulbattery.getKey())
                .add(InitItems.blood_jelly.getKey())
                .add(InitItems.magnet.getKey())
                .add(InitItems.obsidianring.getKey())
                .add(InitItems.ectoplasmapple.getKey())
                .add(InitItems.badgeofthedead.getKey())
                .add(InitItems.battery.getKey())
                .add(InitItems.blackeorb.getKey())
                .add(InitItems.blueamout.getKey())
        ;

        toEctoplasmcloub
                .add(InitItems.mbattery.getKey())
                .add(InitItems.brain.getKey())
                .add(InitItems.brain.getKey())
                .add(InitItems.mblock.getKey())
                .add(InitItems.mbox.getKey())
                .add(InitItems.mbottle.getKey())
                .add(InitItems.meye.getKey())
                .add(InitItems.mhead.getKey())
                .add(InitItems.mkidney.getKey())
                .add(InitItems.morb.getKey())
                .add(InitItems.mring.getKey())
                .add(InitItems.mshell.getKey())
                .add(InitItems.soulcube.getKey())
                .add(InitItems.beacon.getKey())
                .add(InitItems.ectoplasmbattery.getKey())
                .add(InitItems.ectoplasmsoul.getKey())
                .add(InitItems.twistedamout.getKey())
                .add(InitItems.ectoplasmtree.getKey())

                .add(InitItems.rage_crystal.getKey())
                .add(InitItems.greedcrystal.getKey())
                .add(InitItems.warcrystal.getKey())
                .add(InitItems.greedamout.getKey())
                .add(InitItems.redamout.getKey())
                .add(InitItems.whiteorb.getKey())

                .add(InitItems.evilcandle.getKey())
                .add(InitItems.luck_ring.getKey())
                .add(InitItems.luck_stone.getKey())

        ;

        toEctoplasmcube
                .add(InitItems.ectoplasmhorseshoe.getKey())
                .add(InitItems.ectoplasmshild.getKey())
                .add(InitItems.max_eye.getKey())
                .add(InitItems.killer.getKey())
                .add(InitItems.blood_snake.getKey())
                .add(InitItems.bigwarcrystal.getKey())
                .add(InitItems.biggreedcrystal.getKey())
                .add(InitItems.rage_crystal_big.getKey())
                .add(InitItems.ectoplasmstar.getKey());

        toEctoplasmprism
                .add(InitItems.blood_candle.getKey())
                .add(InitItems.blood_magic_box.getKey())
                .add(InitItems.the_blood_book.getKey())
                .add(InitItems.the_prison_of_sin.getKey())
                .add(InitItems.twelve_sword.getKey())
                .add(InitItems.rage_crystal_max.getKey())
                .add(InitItems.fortunecrystal.getKey())
                .add(InitItems.mayhemcrystal.getKey())
                .add(InitItems.the_heart.getKey())
                .add(InitItems.malice_die.getKey())
                .add(InitItems.maxamout.getKey())
                .add(InitItems.god_lead.getKey())
                .add(InitItems.evil_mob.getKey())
                .add(InitItems.consciousness.getKey());
    }
    private <T extends Item> void addItemsByClass(
            TagAppender<Item> tag,
            Class<T> type
    ) {
        InitItems.ITEMS.getEntries().forEach(holder -> {
            if (type.isInstance(holder.get())) {
                tag.add(holder.getKey());
            }
        });
    }
    private TagAppender<Item> createTag(String name) {
        return this.tag(ItemTags.create(Identifier.fromNamespaceAndPath(Moonstone.MODID, name)));
    }
}
