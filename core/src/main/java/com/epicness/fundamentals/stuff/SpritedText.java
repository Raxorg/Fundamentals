package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class SpritedText implements Buttonable, Movable {

    private final Sprite background;
    private final Text label;

    public SpritedText(Sprite backgroundSprite, BitmapFont font) {
        background = new Sprite(backgroundSprite);
        label = new Text(font);
        label.setHorizontalAlignment(Align.center);
        label.setCenterVertical(true);
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
    public void translateX(float x) {
        background.translateX(x);
        label.translateX(x);
    }

    @Override
    public float getY() {
        return background.getY();
    }

    @Override
    public void translateY(float y) {
        background.translateY(y);
        label.translateY(y);
    }

    @Override
    public void setY(float y) {
        background.setY(y);
        label.setY(y + background.getHeight() / 2f);
    }

    public void setPosition(float x, float y) {
        background.setPosition(x, y);
        label.setPosition(x, y + background.getHeight() / 2f);
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
        label.setY(background.getY() + height / 2f);
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

    public String getText() {
        return label.getText();
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setTextTargetWidth(float width) {
        label.setTextTargetWidth(width);
    }
}