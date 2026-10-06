package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Movable {

    float getX();

    void translateX(float amount);

    default void setX(float x) {
        translateX(x - getX());
    }

    default void copyX(Movable other) {
        setX(other.getX());
    }

    float getY();

    void translateY(float amount);

    default void setY(float y) {
        translateY(y - getY());
    }

    default void copyY(Movable other) {
        setY(other.getY());
    }

    default void translate(float xAmount, float yAmount) {
        translateX(xAmount);
        translateY(yAmount);
    }

    default void translate(float amount) {
        translate(amount, amount);
    }

    default void translate(Vector2 amount) {
        translate(amount.x, amount.y);
    }

    /**
     * Gets the current position of this movable object
     *
     * @param result The vector that will store the position (modified by this method)
     * @return The result vector with updated values, for method chaining
     */
    default Vector2 getPosition(Vector2 result) {
        return result.set(getX(), getY());
    }

    /**
     * Gets the current position of this movable object
     *
     * @return A new Vector2 containing the position
     */
    default Vector2 getPosition() {
        return getPosition(new Vector2());
    }

    default void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    default void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    default void copyPosition(Movable other) {
        setPosition(other.getPosition());
    }
}