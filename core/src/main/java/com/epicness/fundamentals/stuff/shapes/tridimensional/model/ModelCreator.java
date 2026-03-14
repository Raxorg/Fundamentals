package com.epicness.fundamentals.stuff.shapes.tridimensional.model;

import com.badlogic.gdx.graphics.g3d.Model;

public abstract class ModelCreator<T extends ModelProperties> {

    protected static ModelBuilderPlus modelBuilder;
    public final T properties;
    public final int vertexSections;
    public final Model model, debugModel;

    protected ModelCreator(T properties, int vertexSections) {
        if (modelBuilder == null) modelBuilder = new ModelBuilderPlus();
        this.properties = properties;
        this.vertexSections = vertexSections;
        model = build(properties);
        debugModel = buildDebug(properties);
    }

    protected abstract Model build(T properties);

    protected abstract Model buildDebug(T properties);
}