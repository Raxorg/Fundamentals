package com.epicness.fundamentals.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Utility class to generate vertices for various curve shapes,
 * suitable for use with Box2D ChainShapes.
 * <p>
 * Assumes generation in pixel coordinates, intended to be converted
 * to Box2D meters later. If you want direct meter generation,
 * pass in meter values and set PIXELS_TO_METERS to 1.0f.
 */
public class CurveVertexGenerator {

    /**
     * Generates vertices for a parabolic curve opening downwards.
     * The peak is horizontally centered between startX and endX.
     *
     * @param startX      The starting X coordinate (pixels).
     * @param endX        The ending X coordinate (pixels).
     * @param baseY       The Y coordinate at the start and end points (pixels).
     * @param peakY       The highest Y coordinate of the curve (pixels).
     * @param numSegments The number of line segments to approximate the curve.
     * @param scaleFactor For conversion between pixels and meters.
     * @return An array of Vector2 vertices representing the curve.
     */
    public static Vector2[] createParabolicVertices(float startX, float endX, float baseY, float peakY, int numSegments, float scaleFactor) {
        if (numSegments < 1) numSegments = 1;
        Vector2[] vertices = new Vector2[numSegments + 1];
        float curveWidth = endX - startX;
        float curveHeight = peakY - baseY;

        for (int i = 0; i <= numSegments; i++) {
            float t = (float) i / numSegments; // Parameter [0, 1]

            // Calculate pixel coordinates
            float pixelX = startX + t * curveWidth;

            // Map t from [0, 1] to xNormalized [-1, 1] for y = -x^2 shape
            float xNormalized = (t * 2.0f) - 1.0f;
            // Parabolic function (yNormalized ranges from -1 at ends to 0 at peak)
            float yNormalized = -(xNormalized * xNormalized);

            // Scale and position the y-coordinate
            float pixelY = peakY + yNormalized * curveHeight; // Start at peakY and go down

            // Apply scale factor
            vertices[i] = new Vector2(pixelX * scaleFactor, pixelY * scaleFactor);
        }
        return vertices;
    }

    /**
     * Generates vertices for a half-period sine wave curve (an upward arch).
     *
     * @param startX      The starting X coordinate (pixels).
     * @param endX        The ending X coordinate (pixels).
     * @param baseY       The Y coordinate at the start and end points (pixels).
     * @param peakY       The highest Y coordinate of the curve (amplitude adjusted) (pixels).
     * @param numSegments The number of line segments to approximate the curve.
     * @param scaleFactor For conversion between pixels and meters.
     * @return An array of Vector2 vertices representing the curve.
     */
    public static Vector2[] createSineVertices(float startX, float endX, float baseY, float peakY, int numSegments, float scaleFactor) {
        if (numSegments < 1) numSegments = 1;
        Vector2[] vertices = new Vector2[numSegments + 1];
        float curveWidth = endX - startX;
        float amplitude = peakY - baseY; // Height of the sine arch

        for (int i = 0; i <= numSegments; i++) {
            float t = (float) i / numSegments; // Parameter [0, 1]

            // Calculate pixel coordinates
            float pixelX = startX + t * curveWidth;

            // Map t to angle [0, PI] for one half sine wave
            float angle = t * (float) Math.PI;
            float sineValue = (float) Math.sin(angle); // Ranges from 0 to 1 to 0

            // Scale and position the y-coordinate
            float pixelY = baseY + sineValue * amplitude;

            // Apply scale factor
            vertices[i] = new Vector2(pixelX * scaleFactor, pixelY * scaleFactor);
        }
        return vertices;
    }

    /**
     * Generates vertices for a quadratic Bézier curve.
     * P0 is the start point, P2 is the end point.
     * The control point P1 determines the curvature. A good default for a
     * symmetric roof is placing P1 horizontally centered and vertically
     * at `2 * peakY - baseY`.
     *
     * @param startX      The starting X coordinate (P0.x) (pixels).
     * @param startY      The starting Y coordinate (P0.y) (pixels).
     * @param endX        The ending X coordinate (P2.x) (pixels).
     * @param endY        The ending Y coordinate (P2.y) (pixels).
     * @param controlX    The control point X coordinate (P1.x) (pixels).
     * @param controlY    The control point Y coordinate (P1.y) (pixels).
     * @param numSegments The number of line segments to approximate the curve.
     * @param scaleFactor For conversion between pixels and meters.
     * @return An array of Vector2 vertices representing the curve.
     */
    public static Vector2[] createQuadraticBezierVertices(float startX, float startY, float endX, float endY,
                                                          float controlX, float controlY, int numSegments, float scaleFactor) {
        if (numSegments < 1) numSegments = 1;
        Vector2[] vertices = new Vector2[numSegments + 1];
        Vector2 p0 = new Vector2(startX, startY);
        Vector2 p1 = new Vector2(controlX, controlY);
        Vector2 p2 = new Vector2(endX, endY);

        for (int i = 0; i <= numSegments; i++) {
            float t = (float) i / numSegments; // Parameter [0, 1]
            float oneMinusT = 1.0f - t;

            // Quadratic Bézier formula: B(t) = (1-t)^2 * P0 + 2*(1-t)*t*P1 + t^2*P2
            float pixelX = (oneMinusT * oneMinusT * p0.x) + (2.0f * oneMinusT * t * p1.x) + (t * t * p2.x);
            float pixelY = (oneMinusT * oneMinusT * p0.y) + (2.0f * oneMinusT * t * p1.y) + (t * t * p2.y);

            // Apply scale factor
            vertices[i] = new Vector2(pixelX * scaleFactor, pixelY * scaleFactor);
        }
        return vertices;
    }

    /**
     * Helper overload for creating a symmetric quadratic Bézier curve peaking at peakY.
     * Calculates a suitable control point automatically.
     *
     * @param startX      The starting X coordinate (pixels).
     * @param endX        The ending X coordinate (pixels).
     * @param baseY       The Y coordinate at the start and end points (pixels).
     * @param peakY       The desired highest Y coordinate of the curve (approximately) (pixels).
     * @param numSegments The number of line segments.
     * @param scaleFactor For conversion between pixels and meters.
     * @return An array of Vector2 vertices representing the curve.
     */
    public static Vector2[] createSymmetricBezierVertices(float startX, float endX, float baseY, float peakY,
                                                          int numSegments, float scaleFactor) {
        // Calculate the control point for a symmetric curve reaching peakY
        // P1 = (midX, 2 * peakY - baseY) - derived from setting B(0.5).y = peakY
        float controlX = (startX + endX) * 0.5f;
        float controlY = 2.0f * peakY - baseY;
        return createQuadraticBezierVertices(startX, baseY, endX, baseY, controlX, controlY, numSegments, scaleFactor);
    }
}