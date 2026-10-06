package com.ytgld.moonstone.render.book;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.CIStateShardsHasBlack;
import com.ytgld.moonstone.render.MGuiGraphics;
import com.ytgld.moonstone.render.MRender;
import com.ytgld.moonstone.render.book.tool.AddBookPage;
import com.ytgld.moonstone.render.book.tool.BookPageFinder;
import com.ytgld.moonstone.render.book.tool.RegisterBookPage;
import com.ytgld.moonstone.render.gui_particles.BlackKey;
import com.ytgld.moonstone.render.gui_particles.BlackParticlesAdd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2f;

import java.util.*;

public class MoonBookScreen extends Screen {
    public static final ResourceLocation window = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/window.png");
    public static final ResourceLocation back = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/back.png");
    public static final ResourceLocation look_black = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/look_black.png");
    public static final ResourceLocation back_small = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/back_small.png");
    public static final ResourceLocation back_small_black = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/black_all.png");
    public static final ResourceLocation book_small = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/book_small.png");
  public static final ResourceLocation glow = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/all.png");
    public static final ResourceLocation shadow_2 = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/shadow/black_2.png");
    public static final ResourceLocation shadow_3 = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/shadow/black_3.png");


    public static final ResourceLocation nightmare_book_main = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/nightmare_book_main.png");
    public static final ResourceLocation black_book_main_back = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/black_book_main_back.png");

    public static final Component TITLE = Component.translatable("itemGroup.tabmoonstone");
    public final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    public final Player player;
    public float offsetX = 0;
    public float offsetY = 0;
    public boolean dragging = false;
    public double lastMouseX;
    public double lastMouseY;
    public float targetOffsetX;
    public float targetOffsetY;
    public static final float DRAG_SPEED = 0.5f;
    public final List<CIBookGuiAdd> list = new ArrayList<>(); /* * 每个条目单独保存缩放值。 */
    public final Map<CIBookGuiAdd, Float> itemSizes = new HashMap<>();
    public boolean isMouseClicked = false; /* * 当前点击的条目。 */
    public CIBookGuiAdd lastGuiAdd = null; /* * 当前点击条目的 Item。 */
    public Item lastItem = ItemStack.EMPTY.getItem();

    public MoonBookScreen(Player player) {
        super(TITLE);
        this.player = player;
    }

    @Override
    protected void init() {
        list.clear();
        itemSizes.clear();
        for (RegisterBookPage registerItemConfig : BookPageFinder.getModPlugins()) {
            registerItemConfig.addPage(list);
        }
        for (CIBookGuiAdd ciBookGuiAdd : list) {
            itemSizes.put(ciBookGuiAdd, 1.0f);
        }
        this.layout.addTitleHeader(TITLE, this.font);
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onClose()).width(200).build());
        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
    }

    @Override
    public void removed() {
        ClientPacketListener connection = this.minecraft.getConnection();
        if (connection != null) {
            connection.send(ServerboundSeenAdvancementsPacket.closedScreen());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (!isMouseClicked) {
                dragging = true;
                lastMouseX = mouseX;
                lastMouseY = mouseY;
                CIBookGuiAdd clicked = findEntryAt(mouseX, mouseY);
                if (clicked != null) {
                    isMouseClicked = true;
                    lastGuiAdd = clicked;
                    lastItem = clicked.item;
                    lastItemOnUse = clicked.item.getDefaultInstance();
                    sound(SoundEvents.BOOK_PAGE_TURN,3);
                    if (lastItemOnUse.getItem() == InitItems.NightmareBaseItem_.asItem() ) {
                        Minecraft.getInstance().setScreen(new NightmareBookScreen(player,list,itemSizes));
                    }
                }
            }
        } else {
            isMouseClicked = false;
            lastGuiAdd = null;
            lastItem = ItemStack.EMPTY.getItem();
            sound(SoundEvents.BOOK_PAGE_TURN,3);
        }
        return super.mouseClicked(mouseX,mouseY, button);
    }

    private CIBookGuiAdd findEntryAt(double mouseX, double mouseY) {
        float s = 1.2f;
        int xo = (int) ((this.width - 255 * s) / 2);
        int yo = (int) ((this.height - 155 * s) / 2);
        for (CIBookGuiAdd ciBookGuiAdd : list) {
            int centerX = (int) (xo + 252 / 2f + ciBookGuiAdd.vecPos.x + offsetX);
            int centerY = (int) (yo + 140 / 2f + ciBookGuiAdd.vecPos.y + offsetY);
            if (mouseX >= centerX - 10 && mouseX <= centerX + 10 && mouseY >= centerY - 10 && mouseY <= centerY + 10) {
                if (ciBookGuiAdd.isInOther()) {
                    continue;
                }
                return ciBookGuiAdd;
            }
        }
        return null;
    }


    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (dragging) {
            targetOffsetX += (float) (mouseX - lastMouseX) * DRAG_SPEED;
            targetOffsetY += (float) (mouseY - lastMouseY) * DRAG_SPEED;
            int size = 350;
            targetOffsetX = Math.clamp(targetOffsetX, -size, size);
            targetOffsetY = Math.clamp(targetOffsetY, -size, size);
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    public void targetOffset() {
        offsetX += (targetOffsetX - offsetX) * 0.15f;
        offsetY += (targetOffsetY - offsetY) * 0.15f;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public RandomSource source = RandomSource.create();

    private int smallAlpha = 0;
    public int time = 0;
    private enum BookStyle {
        WINDOW,
        EVIL,
        MEAT,
        BLACK
    }
    @Override
    public void tick() {
        super.tick();
        time++;

        if (isMouseClicked && lastGuiAdd != null) {
            if (smallAlpha < 255) {
                smallAlpha += 20;
                smallAlpha = Math.min(255, smallAlpha);
            }
        } else {
            if (smallAlpha > 0) {
                smallAlpha -= 20;
                smallAlpha = Math.max(0, smallAlpha);
            }
        }
    }

    private int smoothAlpha(int current, int target) {
        if (current < target) {
            return Math.min(current + 15, target);
        }

        if (current > target) {
            return Math.max(current - 15, target);
        }

        return current;
    }
    public ItemStack lastItemOnUse = ItemStack.EMPTY;
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float a) {
        super.render(graphics, mouseX, mouseY, a);
        targetOffset();


        float s = 1.2f;
        int xo = (int) ((this.width - 255 * s) / 2);
        int yo = (int) ((this.height - 155 * s) / 2);
        this.extractWindow(graphics, xo, yo, mouseX, mouseY);


        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                back_small_black,
                (int) ((this.width - 1024 * s) / 2), (int) ((this.height - 1024 * s) / 2),

                0.0F, 0.0F,
                (int) (1024 * s), (int) (1024 * s), (int) (1024 * s), (int) (1024 * s),

                Light.ARGB.color((int) (smallAlpha / 1.5f),255,255,255));

        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                back_small, (int) ((this.width - 128 * s) / 2), (int) ((this.height - 128 * s) / 2), 0.0F, 0.0F, (int) (128 * s), (int) (128 * s), (int) (128 * s), (int) (128 * s),
                Light.ARGB.color(smallAlpha, 255, 255, 255));
        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                book_small, (int) ((this.width - 128 * s) / 2), (int) ((this.height - 128 * s) / 2), 0.0F, 0.0F, (int) (128 * s), (int) (128 * s), (int) (128 * s), (int) (128 * s),
                Light.ARGB.color(smallAlpha, 255, 255, 255));

        if (isMouseClicked && lastGuiAdd != null) {
            ItemStack stack = lastGuiAdd.item.getDefaultInstance();
            if (!stack.isEmpty()) {
                Optional<TooltipComponent> image = stack.getTooltipImage();
                List<Component> lines = Screen.getTooltipFromItem(minecraft, stack);
                List<ClientTooltipComponent> components = new ArrayList<>();
                image.ifPresent(img -> components.add(ClientTooltipComponent.create(img)));
                for (Component line : lines) {
                    components.add(ClientTooltipComponent.create(line.getVisualOrderText()));
                }
                List<Component> component = lastGuiAdd.otherText;
                if (!component.isEmpty()) {
                    for (int i = 0; i < component.size(); i++) {
                        graphics.drawString(Minecraft.getInstance().font, component.get(i), width / 2 - 72, height / 2 - 48 + i * 10, Light.ARGB.color(255, 200, 200, 200));
                    }
                } else {
                    graphics.drawString(Minecraft.getInstance().font, Component.translatable("moonstone.book.not"), width / 2 - 72, height / 2 - 48, Light.ARGB.color(255, 200, 200, 200));
                }
                int itemX = width / 2 - 8;
                int itemY = height / 2 - 74;
                if (!lastGuiAdd.isInOther()) {
                    graphics.renderItem(stack, itemX, itemY);
                    if (mouseX >= itemX - 16 && mouseX <= itemX + 16 && mouseY >= itemY - 16 && mouseY <= itemY + 16) {
                        graphics.renderTooltip(
                                font,
                                lastItem.getDefaultInstance(),
                                mouseX, mouseY
                        );
                    }
                }

            }
        }
    }


    public void extractWindow(GuiGraphics graphics, int xo, int yo, int mouseX, int mouseY) {
        float s = 1.2f;
        int width = (int) (255 * s);
        int height = (int) (155 * s);
        int texWidth = (int) (256 * s);
        int texHeight = (int) (256 * s);
        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                back, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight, Light.ARGB.color(255, 255, 255, 255));

        for (CIBookGuiAdd ciBookGuiAdd : list) {
            addItem(ciBookGuiAdd, graphics, xo, yo, mouseX, mouseY);
        }

        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                look_black, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight,0xffffffff);
        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                window, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight, Light.ARGB.color(255, 255, 255, 255));
        for (CIBookGuiAdd ciBookGuiAdd : list) {
            addText(ciBookGuiAdd, graphics, xo, yo, mouseX, mouseY);
        }
    }


    public void addItem(CIBookGuiAdd ciBookGuiAdd, GuiGraphics graphics, int windowLeft, int windowTop, int mouseX, int mouseY) {
        if (ciBookGuiAdd.isInOther()) {
            return;
        }
        int centerX = (int) (windowLeft + 252 / 2f + ciBookGuiAdd.vecPos.x + offsetX);
        int centerY = (int) (windowTop + 140 / 2f + ciBookGuiAdd.vecPos.y + offsetY);
        int windowRight = windowLeft + 255;
        int windowBottom = windowTop + 155;
        int itemSize = 16;
        if (centerX - 24 + itemSize / 2 < windowLeft || centerX - 20 - itemSize / 2 > windowRight || centerY - 24 + itemSize / 2 < windowTop || centerY - 4 - itemSize / 2 > windowBottom) {
            return;
        }
        ItemStack stack = new ItemStack(ciBookGuiAdd.item);
        boolean big = mouseX >= centerX - 8 && mouseX <= centerX + 8 && mouseY >= centerY - 8 && mouseY <= centerY + 8; /* * 每个条目使用自己的 size。 */
        float size = itemSizes.getOrDefault(ciBookGuiAdd, 1.0f);
        float speed = 20;
        float max = 5 / speed;
        if (big) {
            size += max;
            size = Math.min(size, 1.25f);
        } else {
            size -= max;
            size = Math.max(size, 1.0f);
        }
        itemSizes.put(ciBookGuiAdd, size);
        new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics,
                ciBookGuiAdd.thePage.identifier, centerX - 9, centerY - 9, 0, 0, 18, 18, 18, 18,0xffffffff);
        int color = ciBookGuiAdd.lightColor;
        int rs = (color >> 16) & 0xFF;
        int gs = (color >> 8) & 0xFF;
        int bs = color & 0xFF;
        int sss = 64;
        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(centerX, centerY,0);
        pose.scale(size, size,0);
        pose.translate(-sss / 2f, -sss / 2f,0);
        int alpha = Mth.clamp((int) (size * 400 - 400), 0, 255);
        if (ciBookGuiAdd.item == InitItems.NightmareBaseItem_.asItem()) {
            BlackKey.ColorImage colorImage = new BlackKey.ColorImage((255 - smallAlpha) / 4, 100,50,255);
            float cs = 10f;
            int sizeS = 16;
            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs) * 12), (int) (centerY+ Math.sin(time / cs) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/cube.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0),
                            new Vector2f(), true),8);

            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs + 90) * 12), (int) (centerY+ Math.sin(time / cs+ 90) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/cube.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0),
                            new Vector2f(), true),8);

            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs + 180) * 12), (int) (centerY+ Math.sin(time / cs+ 180) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/cube.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0),
                            new Vector2f(), true),8);
        }
        if (ciBookGuiAdd.isChaos) {
            new MGuiGraphics.GUI(MRender::getVows, true)
                    .blit(graphics,
                            ResourceLocation.fromNamespaceAndPath(
                                    Moonstone.MODID, "textures/gui/color.png"
                            ), 0, 0, 0, 0,
                            sss, sss, sss, sss, color);
        }
        if (ciBookGuiAdd.thePage != ThePage.EVILMOTHER) {
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock ,true).blit(graphics
                    , glow, 0, 0, 0, 0, sss, sss, sss, sss, Light.ARGB.color(alpha, rs, gs, bs));
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock, true).blit(graphics
                    , shadow_2, 0, 0, 0, 0, sss, sss, sss, sss, Light.ARGB.color(alpha, rs, gs, bs));
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock, true).blit(graphics
                    , shadow_3, 0, 0, 0, 0, sss, sss, sss, sss, Light.ARGB.color(alpha, rs, gs, bs));
        }else {
            BlackKey.ColorImage colorImage = new BlackKey.ColorImage(Math.min(255 - smallAlpha,alpha), 70,240,210);

            float cs = 10f;
            int sizeS = 16;
            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs) * 12), (int) (centerY+ Math.sin(time / cs) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/all.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0.01f),
                            new Vector2f(), false));

            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs + 90) * 12), (int) (centerY+ Math.sin(time / cs+ 90) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/all.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0.01f),
                            new Vector2f(), false));

            BlackParticlesAdd.markSeen((int) (centerX + Math.cos(time / cs + 180) * 12), (int) (centerY+ Math.sin(time / cs+ 180) * 12),
                    new BlackKey.ImageColorAndRenderPipeline(sizeS,
                            colorImage,
                            ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/all.png"),
                            new BlackKey.ShadowImage(CIStateShardsHasBlack::getHasBlock, true),
                            new Vector2f(),
                            new Vector2f(0, -0.01f),
                            new Vector2f(), false));
        }
        pose.popPose(); /* * Item。 */
        pose.pushPose();
        pose.translate(centerX, centerY,0);
        pose.scale(size, size,0);
        pose.translate(-8, -8,0);
        if (!ciBookGuiAdd.isInOther()) {
            graphics.renderItem(stack, 0, 0);

        }
        pose.popPose(); /* * 已获得 / 未获得。 */
    }

    public void addText(CIBookGuiAdd ciBookGuiAdd, GuiGraphics graphics, int windowLeft, int windowTop, int mouseX, int mouseY) {
        if (isMouseClicked) {
            return;
        }
        if (ciBookGuiAdd.isInOther()) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        int centerX = (int) (windowLeft + 252 / 2f + ciBookGuiAdd.vecPos.x + offsetX);
        int centerY = (int) (windowTop + 140 / 2f + ciBookGuiAdd.vecPos.y + offsetY);
        boolean b = mouseX >= centerX - 8 && mouseX <= centerX + 8 && mouseY >= centerY - 8 && mouseY <= centerY + 8;
        if (b) {
            int paddingX = 4;
            int paddingY = 2;
            int mainWidth = (int) (mc.font.width(ciBookGuiAdd.mainText) * 1.25f);
            int mainHeight = mc.font.lineHeight;
            graphics.fill(mouseX - paddingX, mouseY - paddingY, mouseX + mainWidth + paddingX, mouseY + mainHeight + paddingY, Light.ARGB.color(200, 0, 0, 0));
            for (int i = 0; i < ciBookGuiAdd.text.size(); i++) {
                String line = String.valueOf(ciBookGuiAdd.text.get(i));
                int lineWidth = mc.font.width(line);
                int lineHeight = mc.font.lineHeight;
                int y = mouseY + (i + 1) * 12;
                graphics.fill(mouseX - paddingX, y - paddingY, mouseX + lineWidth + paddingX, y + lineHeight + paddingY, Light.ARGB.color(200, 0, 0, 0));
            }
            graphics.pose().pushPose();
            graphics.pose().translate(mouseX, mouseY,0);
            graphics.pose().scale(1.25f, 1.25f,0);
            graphics.drawString(mc.font, ciBookGuiAdd.mainText, 0, 0, ciBookGuiAdd.colorMain);
            graphics.pose().popPose();
            for (int i = 0; i < ciBookGuiAdd.text.size(); i++) {
                graphics.drawString(mc.font, ciBookGuiAdd.text.get(i), mouseX, mouseY + (i + 1) * 12, ciBookGuiAdd.colorText);
                if (i == ciBookGuiAdd.text.size() - 1) {
                    graphics.drawString(mc.font, Component.translatable("moonstone.book.mouse"), mouseX, mouseY + (i + 2) * 12, Light.ARGB.color(255, 200, 150, 50));
                }
            }
            ItemStack stack = new ItemStack(ciBookGuiAdd.item);
        }
    }
    public static void event(ClientTickEvent.Pre event){
    }
    public static void sound(SoundEvent event, float v){
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(
                event,
                1,
                v
        ));
    }
    @AddBookPage
    public static class AddPageClass implements RegisterBookPage {
        @Override
        public void addPage(List<CIBookGuiAdd> list) {
            list.add(new CIBookGuiAdd(InitItems.NightmareBaseItem_.asItem(), new Vec2(0, 0), Component.translatable("moonstone.book.test.main"),
                    List.of(Component.translatable("moonstone.book.test.1"),
                            Component.translatable("moonstone.book.test.2")),
                    Light.ARGB.color(255, 100,50,255),
                    Light.ARGB.color(255, 50, 50, 50),
                    ThePage.BASE, Light.ARGB.color(255, 255, 255, 100)));
        }
    }

    public static class CIBookGuiAdd {
        public final Item item;
        public final Vec2 vecPos;
        public final Component mainText;
        public final List<Component> text;
        public final int colorMain;
        public final int colorText;
        public final ThePage thePage;
        public final int lightColor;
        public final List<Component> otherText;
        public final boolean isChaos;
        public final boolean inOther;

        public CIBookGuiAdd(Item item, Vec2 vecPos, Component mainText, List<Component> text, int colorMain, int colorText, ThePage thePage, int lightColor) {
            this.item = item;
            this.vecPos = vecPos;
            this.mainText = mainText;
            this.text = text;
            this.colorMain = colorMain;
            this.colorText = colorText;
            this.thePage = thePage;
            this.lightColor = lightColor;
            this.otherText = new ArrayList<>();
            this.isChaos = false;
            this.inOther = false;
        }

        public CIBookGuiAdd(Item item, Vec2 vecPos, Component mainText, List<Component> text, int colorMain, int colorText, ThePage thePage, int lightColor, List<Component> otherText) {
            this.item = item;
            this.vecPos = vecPos;
            this.mainText = mainText;
            this.text = text;
            this.colorMain = colorMain;
            this.colorText = colorText;
            this.thePage = thePage;
            this.lightColor = lightColor;
            this.otherText = otherText;
            this.isChaos = false;
            this.inOther = false;
        }
        public CIBookGuiAdd(Item item, Vec2 vecPos, Component mainText, List<Component> text, int colorMain, int colorText, ThePage thePage, int lightColor, List<Component> otherText,boolean isChaos) {
            this.item = item;
            this.vecPos = vecPos;
            this.mainText = mainText;
            this.text = text;
            this.colorMain = colorMain;
            this.colorText = colorText;
            this.thePage = thePage;
            this.lightColor = lightColor;
            this.otherText = otherText;
            this.isChaos = isChaos;
            this.inOther = false;
        }
        public CIBookGuiAdd(Item item, Vec2 vecPos, Component mainText, List<Component> text, int colorMain, int colorText, ThePage thePage, int lightColor, List<Component> otherText,boolean isChaos,boolean inOther) {
            this.item = item;
            this.vecPos = vecPos;
            this.mainText = mainText;
            this.text = text;
            this.colorMain = colorMain;
            this.colorText = colorText;
            this.thePage = thePage;
            this.lightColor = lightColor;
            this.otherText = otherText;
            this.isChaos = isChaos;
            this.inOther = inOther;
        }
        public Item item() {
            return item;
        }

        public Vec2 vecPos() {
            return vecPos;
        }

        public Component mainText() {
            return mainText;
        }

        public List<Component> text() {
            return text;
        }

        public int colorMain() {
            return colorMain;
        }

        public int colorText() {
            return colorText;
        }

        public ThePage thePage() {
            return thePage;
        }

        public int lightColor() {
            return lightColor;
        }

        public List<Component> otherText() {
            return otherText;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (CIBookGuiAdd) obj;
            return Objects.equals(this.item, that.item) && Objects.equals(this.vecPos, that.vecPos) && Objects.equals(this.mainText, that.mainText) && Objects.equals(this.text, that.text) && this.colorMain == that.colorMain && this.colorText == that.colorText && Objects.equals(this.thePage, that.thePage) && this.lightColor == that.lightColor;
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, vecPos, mainText, text, colorMain, colorText, thePage, lightColor);
        }

        @Override
        public String toString() {
            return "CIBookGuiAdd[" + "item=" + item + ", " + "vecPos=" + vecPos + ", " + "mainText=" + mainText + ", " + "text=" + text + ", " + "colorMain=" + colorMain + ", " + "colorText=" + colorText + ", " + "thePage=" + thePage + ", " + "lightColor=" + lightColor + ']';
        }

        public boolean isInOther() {
            return inOther;
        }
    }

    public enum ThePage {
        BASE(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/base.png")),
        BLACK(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/black.png")),
        EVILMOTHER(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/evil.png")),
        MEAT(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/meat.png"));
        public final ResourceLocation identifier;

        ThePage(ResourceLocation identifier) {
            this.identifier = identifier;
        }

        public ResourceLocation getResourceLocation() {
            return identifier;
        }
    }

}