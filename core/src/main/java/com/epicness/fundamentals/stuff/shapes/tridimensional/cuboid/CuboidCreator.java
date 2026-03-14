package com.epicness.fundamentals.stuff.shapes.tridimensional.cuboid;

import static com.epicness.fundamentals.constants.Constants3D.CUBOID_VERTEX_SECTIONS;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.model.ModelCreator;

public class CuboidCreator extends ModelCreator<CuboidProperties> {

    protected CuboidCreator(float width, float height, float depth) {
        super(new CuboidProperties(width, height, depth), CUBOID_VERTEX_SECTIONS);
    }

    @Override
    protected Model build(CuboidProperties properties) {
        return modelBuilder.createBox(
            properties.width,
            properties.height,
            properties.depth,
            properties.material,
            properties.attributes
        );
    }

    @Override
    protected Model buildDebug(CuboidProperties properties) {
        return modelBuilder.createBox(
            properties.width,
            properties.height,
            properties.depth,
            GL20.GL_LINES,
            properties.material,
            properties.attributes
        );
    }
}