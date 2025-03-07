package com.epicness.fundamentals.stuff.interfaces;

import com.epicness.fundamentals.rendering.ShapeDrawerPlus;

public interface HasShapeDrawable extends ShapeDrawable {

    ShapeDrawable getShapeDrawable();

    @Override
    default void draw(ShapeDrawerPlus shapeDrawer) {
        getShapeDrawable().draw(shapeDrawer);
    }
}