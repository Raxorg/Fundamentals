package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;

public interface Drawable2D {

    void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer);

    void drawDebug(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer);
}