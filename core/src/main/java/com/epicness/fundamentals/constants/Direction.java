package com.epicness.fundamentals.constants;

import com.badlogic.gdx.math.Vector2;

public enum Direction {

    UP(new Vector2(0f, 1f)),
    LEFT(new Vector2(-1f, 0f)),
    DOWN(new Vector2(0f, -1f)),
    RIGHT(new Vector2(1f, 0f)),
    UP_LEFT(new Vector2(-1f, 1f)),
    UP_RIGHT(new Vector2(1f, 1f)),
    DOWN_LEFT(new Vector2(-1f, -1f)),
    DOWN_RIGHT(new Vector2(1f, -1f)),
    NONE(new Vector2(0f, 0f));

    public final Vector2 vector, opposite;
    public static final Direction[] COMPASS_DIRECTIONS, CARDINAL_DIRECTIONS, DIAGONAL_DIRECTIONS;

    static {
        COMPASS_DIRECTIONS = new Direction[]{
            UP, LEFT, DOWN, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        };
        CARDINAL_DIRECTIONS = new Direction[]{
            UP, LEFT, DOWN, RIGHT
        };
        DIAGONAL_DIRECTIONS = new Direction[]{
            UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        };
    }

    Direction(Vector2 vector) {
        this.vector = vector;
        opposite = vector.cpy().scl(-1f);
    }

    public static Direction fromVector(Vector2 vector) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i].vector.equals(vector)) {
                return values()[i];
            }
        }
        return null;
    }
}