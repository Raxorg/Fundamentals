package com.epicness.fundamentals.input;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class LogicInputHandler<A extends Assets, L extends Logic, R extends Renderer<S>, S extends Stuff<A>>
        extends LogicHandler<A, L, R, S> {

    public void register() {
        input.addInputHandler(this);
    }

    // Input related to the static camera
    public boolean mouseMoved(float x, float y) {
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean touchDown(float x, float y) {
        return false;
    }

    public boolean touchDragged(float x, float y) {
        return false;
    }

    public boolean touchUp(float x, float y) {
        return false;
    }

    public boolean touchCancelled(float x, float y) {
        return false;
    }

    // Input related to the dynamic camera
    public boolean mouseMovedDynamic(float x, float y) {
        return false;
    }

    public boolean touchDownDynamic(float x, float y) {
        return false;
    }

    public boolean touchDraggedDynamic(float x, float y) {
        return false;
    }

    public boolean touchUpDynamic(float x, float y) {
        return false;
    }

    public boolean touchCancelledDynamic(float x, float y) {
        return false;
    }

    // Camera-agnostic input
    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }
}