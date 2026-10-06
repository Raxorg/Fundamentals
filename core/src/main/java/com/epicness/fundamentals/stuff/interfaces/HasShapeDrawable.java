package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;

public interface HasShapeDrawable extends ShapeDrawable {

    ShapeDrawable getShapeDrawable();

    @Override
    default void draw(ShapeDrawerPlus shapeDrawer) {
        getShapeDrawable().draw(shapeDrawer);
    }

    @Override
    default void drawDebug(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        getShapeDrawable().drawDebug(spriteBatch, shapeDrawer);
    }
}