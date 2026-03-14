package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Plane extends Shape3D<PlaneCreator, PlaneProperties> {

    public Plane(float width, float height) {
        super(new PlaneCreator(width, height));
    }

    public float getWidth() {
        return properties.width;
    }

    public float getHeight() {
        return properties.height;
    }
}