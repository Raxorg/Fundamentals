package com.epicness.fundamentals.stuff.box2d.curves;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.utils.CurveVertexGenerator;

public class ParabolicDef extends CurveDef {

    protected final float baseY, peakY;

    public ParabolicDef(float startX, float endX, float baseY, float peakY, int numSegments, float scaleFactor) {
        super(startX, endX, numSegments, scaleFactor);
        this.baseY = baseY;
        this.peakY = peakY;
    }

    @Override
    public Vector2[] buildVertices() {
        return CurveVertexGenerator.createParabolicVertices(startX, endX, baseY, peakY, numSegments, scaleFactor);
    }
}