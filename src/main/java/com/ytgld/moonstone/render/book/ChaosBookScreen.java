package com.ytgld.moonstone.render.book;

import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MRender;
import com.ytgld.moonstone.render.gui_particles.BlackKey;
import com.ytgld.moonstone.render.gui_particles.BlackParticlesAdd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2f;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;

public class ChaosBookScreen extends Screen {
    private static final Component TITLE = Component.translatable("moonstone.book.chaos");
    private final Player player;
    private final List<CIBookScreen.CIBookGuiAdd> list;
    private final Map<CIBookScreen.CIBookGuiAdd, Float> itemSizes;
    private float offsetX = 0;
    private float offsetY = 0;
    private boolean dragging = false;
    private double lastMouseX;
    private double lastMouseY;
    private float targetOffsetX;
    private float targetOffsetY;
    private static final float DRAG_SPEED = 0.5f;
    private boolean isMouseClicked = false;
    private CIBookScreen.CIBookGuiAdd lastGuiAdd = null;
    public ItemStack lastItemOnUse = ItemStack.EMPTY;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    public int time = 0;
    public int inOtherBackAlpha = 255;
    public int textAlpha = 0;
    public static final Identifier black_item = Identifier.fromNamespaceAndPath(Moonstone.MODID, "textures/gui/book/black_item.png");
    public static Identifier itemImage (Item item){
        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);

        return Identifier.fromNamespaceAndPath(itemId.getNamespace(),
                "textures/item/" + itemId.getPath() + ".png");
    };

    private final RandomSource randomSource = RandomSource.create();

    protected ChaosBookScreen(Player player,
                              List<CIBookScreen.CIBookGuiAdd> list,
                              Map<CIBookScreen.CIBookGuiAdd, Float> itemSizes) {
        super(TITLE);
        this.player = player;
        this.list = list;
        this.itemSizes = itemSizes;
    }
    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        targetOffset();
        float s = 1.2f;
        int xo = (int) ((this.width - 255 * s) / 2);
        int yo = (int) ((this.height - 155 * s) / 2);
        graphics.nextStratum();
        graphics.nextStratum();
        this.extractWindow(graphics, xo, yo, mouseX, mouseY);
        graphics.blit(RenderPipelines.GUI_TEXTURED, CIBookScreen.back_small_black,
                (int) ((this.width - 1024 * s) / 2), (int) ((this.height - 1024 * s) / 2),

                0.0F, 0.0F,
                (int) (1024 * s), (int) (1024 * s), (int) (1024 * s), (int) (1024 * s),

                Light.ARGB.color(inOtherBackAlpha,255,255,255));

    }


    public void extractWindow(GuiGraphicsExtractor graphics, int xo, int yo, int mouseX, int mouseY) {
        float s = 1.2f;

        int width = (int) (255 * s);
        int height = (int) (155 * s);
        int texWidth = (int) (256 * s);
        int texHeight = (int) (256 * s);
        graphics.blit(RenderPipelines.GUI_TEXTURED, CIBookScreen.black_book_main_back, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight,
                Light.ARGB.color(255, 255, 255, 255));

        for (CIBookScreen.CIBookGuiAdd ciBookGuiAdd : list) {
            addItem(ciBookGuiAdd, graphics, xo, yo, mouseX, mouseY);
        }

        graphics.blit(RenderPipelines.GUI_TEXTURED, CIBookScreen.look_black, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight);
        graphics.blit(RenderPipelines.GUI_TEXTURED, CIBookScreen.black_book_main, xo, yo, 0.0F, 0.0F, width, height, texWidth, texHeight,
                Light.ARGB.color(255, 255, 255, 255));

        graphics.blit(RenderPipelines.GUI_TEXTURED, CIBookScreen.back_small_black,
                (int) ((this.width - 1024 * s) / 2), (int) ((this.height - 1024 * s) / 2),

                0.0F, 0.0F,
                (int) (1024 * s), (int) (1024 * s), (int) (1024 * s), (int) (1024 * s),

                Light.ARGB.color(textAlpha,255,255,255));


        if (lastGuiAdd!=null) {
            int size = 128;
            graphics.blit(RenderPipelines.GUI_TEXTURED, itemImage(lastGuiAdd.item),
                    graphics.guiWidth() / 2 - size / 2, graphics.guiHeight() / 2 - size / 2,
                    0.0F, 0.0F,
                    size, size, size, size,
                    Light.ARGB.color(textAlpha / 3, 255, 255, 255));



            if (lastGuiAdd.item instanceof ItemBase itemBase) {
                int color = itemBase.color();
                int as = (color >> 24) & 0xFF;
                int rs = (int) (((color >> 16) & 0xFF) / 1.25f);
                int gs = (int) (((color >> 8) & 0xFF) / 1.25f);
                int bs = (int) ((color & 0xFF) / 1.25f);
                float speed = 80f;
                int sizeParticle = 8;
                BlackKey.ColorImage colorImage = new BlackKey.ColorImage(textAlpha, rs, gs, bs);
                for (int i = 0; i < 3; i++) {
                    BlackParticlesAdd.markSeen(
                            (int) -Mth.nextFloat(randomSource,-graphics.guiWidth(),sizeParticle),
                            (int) (graphics.guiHeight() + sizeParticle / 2),
                            new BlackKey.ImageColorAndRenderPipeline(8,
                                    colorImage,
                                    Identifier.fromNamespaceAndPath(Moonstone.MODID, "textures/item_glowing/cube.png"),
                                    MRender.RenderPs.GUI_TEXTURED,
                                    new Vector2f(),
                                    new Vector2f(0,-0.007f),
                                    new Vector2f(), true), 5, textAlpha);
                }

            }
        }
        graphics.pose().pushMatrix();
        addText(lastGuiAdd, graphics, xo, yo, mouseX,mouseY);
        graphics.pose().popMatrix();
    }

    public void addItem(CIBookScreen.CIBookGuiAdd ciBookGuiAdd, GuiGraphicsExtractor graphics, int windowLeft, int windowTop, int mouseX, int mouseY) {
        if (!ciBookGuiAdd.isInOther()) {
            return;
        }
        int centerX = (int) (windowLeft + 252 / 2f + ciBookGuiAdd.vecPos().x + offsetX);
        int centerY = (int) (windowTop + 140 / 2f + ciBookGuiAdd.vecPos().y + offsetY);
        int windowRight = windowLeft + 255;
        int windowBottom = windowTop + 155;
        int itemSize = 16;
        if (centerX - 24 + itemSize / 2 < windowLeft || centerX - 20 - itemSize / 2 > windowRight || centerY - 24 + itemSize / 2 < windowTop || centerY - 4 - itemSize / 2 > windowBottom) {
            return;
        }
        ItemStack stack = new ItemStack(ciBookGuiAdd.item());
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
        graphics.blit(RenderPipelines.GUI_TEXTURED, ciBookGuiAdd.thePage.identifier, centerX - 9, centerY - 9, 0, 0, 18, 18, 18, 18);
        int sss = 64;
        Matrix3x2fStack pose = graphics.pose();
        pose.pushMatrix();
        pose.translate(centerX, centerY);
        pose.scale(size, size);
        pose.translate(-sss / 2f, -sss / 2f);

        graphics.blit(MRender.VowGlow,
                Identifier.fromNamespaceAndPath(
                        Moonstone.MODID,"textures/gui/color.png"
                ), 0,0, 0, 0,
                sss, sss, sss, sss,ciBookGuiAdd.lightColor);
        pose.popMatrix();

        pose.pushMatrix();
        pose.translate(centerX, centerY);
        pose.scale(size, size);
        pose.translate(-8, -8);
        graphics.item(stack, 0, 0);
        pose.popMatrix();
    }

    private static final float TEXT_COLOR_RADIUS = 80f;
    public void addText(
            CIBookScreen.CIBookGuiAdd ciBookGuiAdd,
            GuiGraphicsExtractor graphics,
            int windowLeft,
            int windowTop,
            int mouseX,
            int mouseY
    ) {
        Minecraft mc = Minecraft.getInstance();

        if (!isMouseClicked) {
            return;
        }

        float textX = windowLeft + 20;
        float textY = windowTop + 30;

        graphics.pose().pushMatrix();

        int mainLines = renderColorfulText(
                graphics,
                mc.font,
                ciBookGuiAdd.mainText,
                textX,
                textY,
                ciBookGuiAdd.colorMain,
                mouseX,
                mouseY,
                1.5f
        );

        graphics.pose().popMatrix();

        float currentY = textY + mainLines * mc.font.lineHeight * 1.5f;

        for (Component text : ciBookGuiAdd.text) {

            int lineCount = renderColorfulText(
                    graphics,
                    mc.font,
                    text,
                    textX,
                    currentY,
                    ciBookGuiAdd.colorText,
                    mouseX,
                    mouseY,
                    1.5f
            );

            currentY += lineCount * mc.font.lineHeight * 1.5f;
        }
    }
    private int renderColorfulText(
            GuiGraphicsExtractor graphics,
            Font font,
            Component component,
            float x,
            float y,
            int originalColor,
            float mouseX,
            float mouseY,
            float scale
    ) {
        String text = component.getString();

        float currentX = x;
        float currentY = y;

        float lineHeight = font.lineHeight * scale;

        int lineCount = 1;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '\n') {
                currentX = x;
                currentY += lineHeight;
                lineCount++;
                continue;
            }

            String character = String.valueOf(c);

            float charWidth = font.width(character) * scale;

            float centerX = currentX + charWidth * 0.5f;
            float centerY = currentY + lineHeight * 0.5f;

            float dx = mouseX - centerX;
            float dy = mouseY - centerY;

            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            float proximity = 1.0f - Mth.clamp(
                    distance / TEXT_COLOR_RADIUS,
                    0.0f,
                    1.0f
            );

            int color = makeColorVivid(originalColor, proximity);

            graphics.text(
                    font,
                    character,
                    (int) currentX,
                    (int) currentY,
                    color,
                    false
            );

            currentX += charWidth;
        }

        return lineCount;
    }
    private static int makeColorVivid(int color, float proximity) {
        int alpha = (color >> 24) & 0xFF;
        int red   = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue  = color & 0xFF;

        float[] hsv = rgbToHsv(red, green, blue);

        float p = proximity * proximity;

        hsv[1] = Mth.lerp(
                p,
                hsv[1],
                1.0f
        );

        hsv[2] = Mth.lerp(
                p,
                hsv[2],
                1.0f
        );

        int[] rgb = hsvToRgb(
                hsv[0],
                hsv[1],
                hsv[2]
        );

        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];

        float highlight = p * 0.8f;

        r = Mth.lerp(highlight, r, 255.0f);
        g = Mth.lerp(highlight, g, 255.0f);
        b = Mth.lerp(highlight, b, 255.0f);

        return (alpha << 24)
                | ((int) r << 16)
                | ((int) g << 8)
                | (int) b;
    }
    private static float[] rgbToHsv(int r, int g, int b) {
        float rf = r / 255.0f;
        float gf = g / 255.0f;
        float bf = b / 255.0f;

        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));

        float delta = max - min;

        float h = 0.0f;

        if (delta != 0.0f) {
            if (max == rf) {
                h = ((gf - bf) / delta) % 6.0f;
            } else if (max == gf) {
                h = ((bf - rf) / delta) + 2.0f;
            } else {
                h = ((rf - gf) / delta) + 4.0f;
            }

            h /= 6.0f;

            if (h < 0.0f) {
                h += 1.0f;
            }
        }

        float s = max == 0.0f ? 0.0f : delta / max;

        return new float[]{h, s, max};
    }
    private static int[] hsvToRgb(float h, float s, float v) {
        float r;
        float g;
        float b;

        float hh = h * 6.0f;
        int sector = (int) Math.floor(hh);
        float f = hh - sector;

        float p = v * (1.0f - s);
        float q = v * (1.0f - s * f);
        float t = v * (1.0f - s * (1.0f - f));

        switch (sector % 6) {
            case 0 -> {
                r = v;
                g = t;
                b = p;
            }
            case 1 -> {
                r = q;
                g = v;
                b = p;
            }
            case 2 -> {
                r = p;
                g = v;
                b = t;
            }
            case 3 -> {
                r = p;
                g = q;
                b = v;
            }
            case 4 -> {
                r = t;
                g = p;
                b = v;
            }
            default -> {
                r = v;
                g = p;
                b = q;
            }
        }

        return new int[]{
                Math.round(r * 255.0f),
                Math.round(g * 255.0f),
                Math.round(b * 255.0f)
        };
    }
    @Override
    protected void init() {
        this.layout.addTitleHeader(TITLE, this.font);
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, _ -> this.onClose()).width(200).build());
        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }
    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
    }
    @Override
    public void tick() {
        super.tick();
        time++;
        if (isMouseClicked && lastGuiAdd != null) {
            if (textAlpha < 255) {
                textAlpha += 10;
                textAlpha = Math.min(255, textAlpha);
            }
        }else {
            if (textAlpha > 0) {
                textAlpha -= 20;
                textAlpha = Math.max(0, textAlpha);
            }
        }
        if (inOtherBackAlpha > 0) {
            if (inOtherBackAlpha == 255) {
                CIBookScreen.sound(SoundEvents.BOOK_PAGE_TURN,1);
                CIBookScreen.sound(SoundEvents.WARDEN_HEARTBEAT,1);
            }
            if (inOtherBackAlpha == 200) {
                CIBookScreen.sound( SoundEvents.WARDEN_HEARTBEAT ,0.8f);
            }
            if (inOtherBackAlpha == 150) {
                CIBookScreen.sound( SoundEvents.WARDEN_HEARTBEAT ,0.6f);
            }
            if (inOtherBackAlpha == 100) {
                CIBookScreen.sound( SoundEvents.WARDEN_HEARTBEAT ,0.4f);
            }
            if (inOtherBackAlpha == 50) {
                CIBookScreen.sound( SoundEvents.WARDEN_HEARTBEAT ,0.2f);
            }

            inOtherBackAlpha -= 5;
            inOtherBackAlpha = Math.max(0, inOtherBackAlpha);
        }
    }
    @Override
    public void removed() {
        ClientPacketListener connection = this.minecraft.getConnection();
        if (connection != null) {
            connection.send(ServerboundSeenAdvancementsPacket.closedScreen());
        }
    }

    @Override
    public boolean mouseDragged(@NonNull MouseButtonEvent event, double dx, double dy) {
        float mouseX = (float) event.x();
        float mouseY = (float) event.y();
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
        return super.mouseDragged(event, dx, dy);
    }
    @Override
    public boolean mouseReleased(@NonNull MouseButtonEvent event) {
        dragging = false;
        return super.mouseReleased(event);
    }
    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            if (!isMouseClicked) {
                dragging = true;
                lastMouseX = event.x();
                lastMouseY = event.y();
                CIBookScreen.CIBookGuiAdd clicked = findEntryAt(event.x(), event.y());
                if (clicked != null) {
                    isMouseClicked = true;
                    lastGuiAdd = clicked;
                    lastItemOnUse = clicked.item().getDefaultInstance();
                    CIBookScreen.sound(SoundEvents.BOOK_PAGE_TURN,3);
                }
            }
        } else {
            isMouseClicked = false;
            lastGuiAdd = null;
            CIBookScreen.sound(SoundEvents.BOOK_PAGE_TURN,3);
        }
        return super.mouseClicked(event, doubleClick);
    }

    private CIBookScreen.CIBookGuiAdd findEntryAt(double mouseX, double mouseY) {
        float s = 1.2f;
        int xo = (int) ((this.width - 255 * s) / 2);
        int yo = (int) ((this.height - 155 * s) / 2);
        for (CIBookScreen.CIBookGuiAdd ciBookGuiAdd : list) {
            if (ciBookGuiAdd.isInOther()) {
                int centerX = (int) (xo + 252 / 2f + ciBookGuiAdd.vecPos().x + offsetX);
                int centerY = (int) (yo + 140 / 2f + ciBookGuiAdd.vecPos().y + offsetY);
                if (mouseX >= centerX - 10 && mouseX <= centerX + 10 && mouseY >= centerY - 10 && mouseY <= centerY + 10) {
                    return ciBookGuiAdd;
                }
            }
        }
        return null;
    }
    public void targetOffset() {
        offsetX += (targetOffsetX - offsetX) * 0.15f;
        offsetY += (targetOffsetY - offsetY) * 0.15f;
    }
}
