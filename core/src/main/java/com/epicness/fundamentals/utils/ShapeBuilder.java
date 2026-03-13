package com.epicness.fundamentals.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.epicness.fundamentals.stuff.box2d.curves.CurveDef;

public final class ShapeBuilder {

    private static final Vector2 AUX = new Vector2();

    public static EdgeShape buildLine(float x1, float y1, float x2, float y2) {
        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        return shape;
    }

    public static EdgeShape buildLine(float x1, float y1, float x2, float y2, float gameToBox2DScale) {
        return buildLine(
            x1 * gameToBox2DScale, y1 * gameToBox2DScale,
            x2 * gameToBox2DScale, y2 * gameToBox2DScale
        );
    }

    public static CircleShape buildCircle(float x, float y, float radius) {
        CircleShape shape = new CircleShape();
        shape.setPosition(AUX.set(x, y));
        shape.setRadius(radius);
        return shape;
    }

    public static CircleShape buildCircle(float radius) {
        return buildCircle(0f, 0f, radius);
    }

    public static PolygonShape buildRectangle(float w, float h, float centerX, float centerY) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w * 0.5f, h * 0.5f, AUX.set(centerX, centerY), 0f);
        return shape;
    }

    public static PolygonShape buildRectangle(float w, float h) {
        return buildRectangle(w, h, w * 0.5f, h * 0.5f);
    }

    public static ChainShape buildChain(float[] vertices) {
        ChainShape shape = new ChainShape();
        shape.createChain(vertices);
        return shape;
    }

    public static ChainShape buildCurve(CurveDef curveDef) {
        ChainShape shape = new ChainShape();
        shape.createChain(curveDef.buildVertices());
        return shape;
    }
}