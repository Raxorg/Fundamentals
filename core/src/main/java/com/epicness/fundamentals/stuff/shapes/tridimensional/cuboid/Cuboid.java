package com.epicness.fundamentals.stuff.shapes.tridimensional.cuboid;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Cuboid extends Shape3D<CuboidCreator, CuboidProperties> {

    public Cuboid(float width, float height, float depth) {
        super(new CuboidCreator(width, height, depth));
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