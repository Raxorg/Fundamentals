package com.epicness.fundamentals.stuff.box2d.curves;

import com.badlogic.gdx.math.Vector2;

public abstract class CurveDef {

    protected final float startX, endX, scaleFactor;
    protected final int numSegments;

    protected CurveDef(float startX, float endX, int numSegments, float scaleFactor) {
        this.startX = startX;
        this.endX = endX;
        this.numSegments = numSegments;
        this.scaleFactor = scaleFactor;
    }

    public abstract Vector2[] buildVertices();
}