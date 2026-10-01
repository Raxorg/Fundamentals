package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.math.Vector2;

public class Hit2D {

    private final Vector2 location;
    private Screen3D<?> screen;

    public Hit2D(float x, float y, Screen3D<?> screen) {
        location = new Vector2(x, y);
        this.screen = screen;
    }

    public Hit2D() {
        this(0f, 0f, null);
    }

    public void set(Hit2D hit) {
        location.set(hit.location);
        screen = hit.screen;
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }

    public void setLocation(Vector2 location) {
        this.location.set(location);
    }

    public Hit2D setLocation(float x, float y) {
        location.set(x, y);
        return this;
    }

    public Screen3D<?> getScreen() {
        return screen;
    }

    public void setScreen(Screen3D<?> screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return location + " " + screen.getClass().getSimpleName();
    }
}