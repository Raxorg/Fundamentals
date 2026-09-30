package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;

public interface SpriteBatchDrawable extends Drawable2D {

    @Override
    default void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        draw(spriteBatch);
    }

    void draw(SpriteBatch spriteBatch);
}