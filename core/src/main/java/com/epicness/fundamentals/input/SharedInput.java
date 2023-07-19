package com.epicness.fundamentals.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.SharedScreen;

import java.util.ArrayList;
import java.util.List;

public class SharedInput implements InputProcessor {

    private final List<LogicInputHandler<?, ?, ?, ?>> inputHandlers;
    private OrthographicCamera staticCamera, dynamicCamera;
    private boolean enabled;
    private Vector3 unprojected;

    public SharedInput() {
        inputHandlers = new ArrayList<>();
        enabled = false;
        unprojected = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!enabled) return false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).mouseMoved(unprojected.x, unprojected.y)) return true;
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0, n = inputHandlers.size(); i < n; i++)
            if (inputHandlers.get(i).mouseMovedDynamic(unprojected.x, unprojected.y)) return true;

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!enabled) return false;

        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).scrolled(amountX, amountY)) return true;

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        // Static camera
        unprojected = staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchDown(unprojected.x, unprojected.y)) return true;
        // Dynamic camera
        unprojected = dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchDownDynamic(unprojected.x, unprojected.y)) return true;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer != 0 || !enabled) return false;
        // Static camera
        unprojected = staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchDragged(unprojected.x, unprojected.y)) return true;
        // Dynamic camera
        unprojected = dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchDraggedDynamic(unprojected.x, unprojected.y)) return true;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        // Static camera
        unprojected = staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchUp(unprojected.x, unprojected.y)) return true;
        // Dynamic camera
        unprojected = dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchUpDynamic(unprojected.x, unprojected.y)) return true;

        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        // Static camera
        unprojected = staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchCancelled(unprojected.x, unprojected.y)) return true;
        // Dynamic camera
        unprojected = dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).touchCancelledDynamic(unprojected.x, unprojected.y)) return true;

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!enabled) return false;

        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).keyDown(keycode)) return true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!enabled) return false;

        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).keyUp(keycode)) return true;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (!enabled) return false;

        for (int i = 0; i < inputHandlers.size(); i++)
            if (inputHandlers.get(i).keyTyped(character)) return true;

        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Structure
    public void setScreen(SharedScreen screen) {
        staticCamera = screen.getStaticCamera();
        dynamicCamera = screen.getDynamicCamera();
    }

    public void addInputHandler(LogicInputHandler<?, ?, ?, ?> inputHandler) {
        inputHandlers.add(inputHandler);
    }

    public void clearInputHandlers() {
        inputHandlers.clear();
    }
}