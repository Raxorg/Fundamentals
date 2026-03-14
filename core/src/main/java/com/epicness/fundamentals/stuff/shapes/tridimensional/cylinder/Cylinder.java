package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Cylinder extends Shape3D<CylinderCreator, CylinderProperties> {

    public Cylinder(float width, float height, float depth, float angleTo) {
        super(new CylinderCreator(width, height, depth, angleTo));
    }

    public float getWidth() {
        return properties.width;
    }

    public float getHeight() {
        return properties.height;
    }

    public float getDepth() {
        return properties.depth;
    }
}