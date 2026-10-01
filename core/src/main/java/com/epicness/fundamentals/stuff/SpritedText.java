package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class SpritedText implements Buttonable, Movable {

    private final Sprite background;
    private final Text label;

    public SpritedText(Sprite backgroundSprite, BitmapFont font) {
        background = new Sprite(backgroundSprite);

        label = new Text(font);
        label.setX(background.getX());
        label.setY(background.getY() + background.getHeight() * 0.5f);
        label.hAlignCenter();
        label.setVerticallyCentered(true);
        label.setTargetWidth(background.getWidth());
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        label.draw(spriteBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return background.getBoundingRectangle().contains(x, y);
    }

    @Override
    public float getX() {
        return background.getX();
    }

    @Override
    public void translateX(float amount) {
        background.translateX(amount);
        label.translateX(amount);
    }

    @Override
    public float getY() {
        return background.getY();
    }

    @Override
    public void translateY(float amount) {
        background.translateY(amount);
        label.translateY(amount);
    }

    public float getWidth() {
        return background.getWidth();
    }

    public float getHeight() {
        return background.getHeight();
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void setSize(float width, float height) {
        background.setSize(width, height);
        label.setY(background.getY() + height * 0.5f);
        label.setTargetWidth(width);
    }

    public Color getBackgroundColor() {
        return background.getColor();
    }

    public void setBackgroundColor(Color color) {
        background.setColor(color);
    }

    public void setTextColor(Color color) {
        label.setColor(color);
    }

    public void setColor(Color color) {
        setBackgroundColor(color);
        setTextColor(color);
    }

    public String getText() {
        return label.getText();
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setTextTargetWidth(float width) {
        label.setTargetWidth(width);
    }

    public float getFontScale() {
        return label.getScale();
    }

    public void setFontScale(float scale) {
        label.setScale(scale);
    }
}