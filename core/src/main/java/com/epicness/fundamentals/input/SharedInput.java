package com.epicness.fundamentals.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.rendering.Renderer;

public class SharedInput implements InputProcessor, ControllerListener {

    // Structure
    private OrthographicCamera staticCamera, dynamicCamera;
    private Renderer<?> renderer;
    // Input related
    private final OrderedSet<LogicInputHandler<?, ?, ?, ?, ?>> inputHandlers;
    private boolean enabled, inputConsumed;
    private final Vector3 unprojected;

    public SharedInput() {
        inputHandlers = new OrderedSet<>();
        enabled = false;
        unprojected = new Vector3();
        Gdx.input.setInputProcessor(this);
        Controllers.addListener(this);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        // Static camera
        unproject(staticCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).mouseMoved(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        unproject(dynamicCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).mouseMovedDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).scrolled(amountX, amountY);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        // Static camera
        unproject(staticCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchDown(unprojected.x, unprojected.y, button);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        unproject(dynamicCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchDownDynamic(unprojected.x, unprojected.y, button);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        // Static camera
        unproject(staticCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchDragged(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        unproject(dynamicCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchDraggedDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        // Static camera
        unproject(staticCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchUp(unprojected.x, unprojected.y, button);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        unproject(dynamicCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchUpDynamic(unprojected.x, unprojected.y, button);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        // Static camera
        unproject(staticCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchCancelled(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        unproject(dynamicCamera, screenX, screenY);
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).touchCancelledDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).keyDown(keycode);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).keyUp(keycode);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).keyTyped(character);
            if (inputConsumed) return true;
        }
        return false;
    }

    private void unproject(Camera camera, int screenX, int screenY) {
        unprojected.set(screenX, screenY, 0f);
        Viewport viewport = renderer.getViewport();
        camera.unproject(unprojected, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
    }

    // Controller-based input
    @Override
    public void connected(Controller controller) {
        if (!enabled) return;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).connected(controller);
            if (inputConsumed) return;
        }
    }

    @Override
    public void disconnected(Controller controller) {
        if (!enabled) return;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).disconnected(controller);
            if (inputConsumed) return;
        }
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).buttonDown(controller, buttonCode);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).buttonUp(controller, buttonCode);
            if (inputConsumed) return true;
        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (!enabled) return false;
        inputConsumed = false;
        Array<LogicInputHandler<?, ?, ?, ?, ?>> handlers = inputHandlers.orderedItems();
        for (int i = 0; i < handlers.size; i++) {
            handlers.get(i).axisMoved(controller, axisCode, value);
            if (inputConsumed) return true;
        }
        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void clearInputHandlers() {
        inputHandlers.clear();
    }

    public void addInputHandler(LogicInputHandler<?, ?, ?, ?, ?> inputHandler) {
        inputHandlers.add(inputHandler);
    }

    public void removeInputHandler(LogicInputHandler<?, ?, ?, ?, ?> inputHandler) {
        inputHandlers.remove(inputHandler);
    }

    public void consumeInput() {
        inputConsumed = true;
    }

    // Structure
    public void setRenderer(Renderer<?> renderer) {
        this.renderer = renderer;
    }

    public void setScreen(SharedScreen screen) {
        staticCamera = screen.getStaticCamera();
        dynamicCamera = screen.getDynamicCamera();
    }
}