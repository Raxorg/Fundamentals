package com.epicness.fundamentals;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.rendering.Renderer;

public class SharedScreen extends ScreenAdapter {

    // Structure
    private Logic logic;
    private Renderer<?> renderer;
    // Screen related
    private final OrthographicCamera dynamicCamera, staticCamera;

    public SharedScreen() {
        dynamicCamera = new OrthographicCamera();
        dynamicCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void render(float delta) {
        logic.update();
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        logic.resize(width, height);
    }

    @Override
    public void pause() {
        logic.pause();
    }

    public OrthographicCamera getDynamicCamera() {
        return dynamicCamera;
    }

    public OrthographicCamera getStaticCamera() {
        return staticCamera;
    }

    // Structure
    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setRenderer(Renderer<?> renderer) {
        this.renderer = renderer;
    }
}