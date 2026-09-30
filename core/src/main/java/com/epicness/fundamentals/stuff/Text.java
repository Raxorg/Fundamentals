package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.utils.Align.center;
import static com.badlogic.gdx.utils.Align.left;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.SpriteBatchDrawable;
import com.epicness.fundamentals.utils.TextUtils;

public class Text implements Buttonable, SpriteBatchDrawable, Movable {

    private final BitmapFont font;
    private String text, truncate;
    private boolean verticallyCentered, wrap;
    private float xOffset, yOffset, targetWidth;
    private int hAlign;
    private final Rectangle bounds;
    private final Color color;

    public Text(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
        hAlign = left;
        bounds = new Rectangle();
        targetWidth = 500f;
        color = new Color(1f, 1f, 1f, 1f);
        updateBounds();
    }

    public Text(BitmapFont font) {
        this(font, "");
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        font.setColor(color);
        font.draw(
            spriteBatch,
            text,
            bounds.x,
            bounds.y + yOffset,
            0,
            text.length(),
            targetWidth,
            hAlign,
            wrap,
            truncate
        );
    }

    @Override
    public void drawDebug(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(
            bounds.x + xOffset,
            bounds.y + yOffset,
            bounds.width,
            -bounds.height
        );
        shapeDrawer.rectangle(
            bounds.x,
            bounds.y + yOffset,
            targetWidth,
            -bounds.height
        );
    }

    @Override
    public boolean contains(float x, float y) {
        float textX = bounds.x + xOffset;
        return x >= textX && x <= textX + bounds.width &&
            y >= bounds.y && y <= bounds.y + bounds.height;
    }

    @Override
    public float getX() {
        return bounds.x;
    }

    @Override
    public void translateX(float amount) {
        bounds.x += amount;
    }

    @Override
    public float getY() {
        return bounds.y;
    }

    @Override
    public void translateY(float amount) {
        bounds.y += amount;
    }

    public BitmapFont getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updateBounds();
    }

    public int getHAlign() {
        return hAlign;
    }

    public void hAlignLeft() {
        hAlign = left;
        xOffset = 0f;
    }

    public void hAlignCenter() {
        hAlign = Align.center;
        xOffset = (targetWidth - bounds.width) * 0.5f;
    }

    public void hAlignRight() {
        hAlign = Align.right;
        xOffset = targetWidth - bounds.width;
    }

    public void setVerticallyCentered(boolean centered) {
        verticallyCentered = centered;
        yOffset = centered ? bounds.height * 0.5f : 0f;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
        updateBounds();
    }

    public String getTruncate() {
        return truncate;
    }

    public void setTruncate(String truncate) {
        this.truncate = truncate;
        updateBounds();
    }

    public float getScale() {
        return font.getScaleX();
    }

    public void setScale(float scale) {
        font.getData().setScale(scale);
        updateBounds();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public float getTargetWidth() {
        return targetWidth;
    }

    public void setTargetWidth(float width) {
        targetWidth = width;
        updateBounds();
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    private void updateBounds() {
        bounds.width = TextUtils.getTextWidth(this);
        bounds.height = TextUtils.getTextHeight(this);
        xOffset = hAlign == left ? 0f : hAlign == center ? (targetWidth - bounds.width) * 0.5f : targetWidth - bounds.width;
        yOffset = verticallyCentered ? bounds.height * 0.5f : 0f;
    }
}