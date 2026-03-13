package com.epicness.fundamentals.stuff.box2d.curves;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.utils.CurveVertexGenerator;

public class QuadraticBezierDef extends CurveDef {

    protected final float startY, endY, controlX, controlY;

    public QuadraticBezierDef(float startX, float startY, float endX, float endY, float controlX, float controlY,
                              int numSegments, float scaleFactor) {
        super(startX, endX, numSegments, scaleFactor);
        this.startY = startY;
        this.endY = endY;
        this.controlX = controlX;
        this.controlY = controlY;
    }

    @Override
    public Vector2[] buildVertices() {
        return CurveVertexGenerator.createQuadraticBezierVertices(
            startX, startY, endX, endY, controlX, controlY,
            numSegments, scaleFactor
        );
    }
}