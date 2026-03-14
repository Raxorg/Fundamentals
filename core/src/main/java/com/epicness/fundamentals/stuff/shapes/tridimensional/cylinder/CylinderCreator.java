package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.epicness.fundamentals.constants.Constants3D.CYLINDER_VERTEX_SECTIONS;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.model.ModelCreator;

public class CylinderCreator extends ModelCreator<CylinderProperties> {

    public CylinderCreator(float width, float height, float depth, float angleTo) {
        super(new CylinderProperties(width, height, depth, angleTo), CYLINDER_VERTEX_SECTIONS);
    }

    @Override
    protected Model build(CylinderProperties properties) {
        return modelBuilder.createCylinder(
            properties.width,
            properties.height,
            properties.depth,
            properties.divisions,
            properties.material,
            properties.angleFrom,
            properties.angleTo
        );
    }

    @Override
    protected Model buildDebug(CylinderProperties properties) {
        return modelBuilder.createCylinder(
            properties.width,
            properties.height,
            properties.depth,
            properties.divisions,
            GL20.GL_LINES,
            properties.material,
            Position,
            properties.angleFrom,
            properties.angleTo,
            false
        );
    }
}