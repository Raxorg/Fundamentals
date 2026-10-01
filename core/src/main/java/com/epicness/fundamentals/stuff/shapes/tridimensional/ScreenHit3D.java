package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.math.Vector3;

public class ScreenHit3D {

    private final Vector3 location;
    private Screen3D<?> screen;

    public ScreenHit3D(Vector3 location, Screen3D<?> screen) {
        this.location = new Vector3(location);
        this.screen = screen;
    }

    public ScreenHit3D() {
        location = new Vector3();
    }

    public Vector3 location() {
        return location;
    }

    public void setLocation(Vector3 location) {
        this.location.set(location);
    }

    public Screen3D<?> screen() {
        return screen;
    }

    public void setScreen(Screen3D<?> screen) {
        this.screen = screen;
    }
}