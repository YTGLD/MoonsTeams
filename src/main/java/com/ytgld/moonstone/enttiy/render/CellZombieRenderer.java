package com.ytgld.moonstone.enttiy.render;

import com.ytgld.moonstone.enttiy.CellZombie;
import com.ytgld.moonstone.enttiy.zombie.MoonAbstractZombieRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

public class CellZombieRenderer extends MoonAbstractZombieRenderer<CellZombie, ZombieRenderState, ZombieModel<ZombieRenderState>> {
    public CellZombieRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_BABY, ModelLayers.ZOMBIE_ARMOR, ModelLayers.ZOMBIE_BABY_ARMOR);
    }

    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    public CellZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation body, ModelLayerLocation babyBody, ArmorModelSet<ModelLayerLocation> armorSet, ArmorModelSet<ModelLayerLocation> babyArmorSet) {
        super(context, new ZombieModel(context.bakeLayer(body)), new BabyZombieModel(context.bakeLayer(babyBody)), ArmorModelSet.bake(armorSet, context.getModelSet(), ZombieModel::new), ArmorModelSet.bake(babyArmorSet, context.getModelSet(), BabyZombieModel::new));
    }
}

