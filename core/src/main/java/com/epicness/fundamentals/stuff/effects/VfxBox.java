package com.epicness.fundamentals.stuff.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.ChainVfxEffect;

import java.util.HashMap;
import java.util.Map;

public class VfxBox<T> {

    private final VfxManager vfxManager;
    private final Map<T, ChainVfxEffect> effectMap;

    public VfxBox(int bufferWidth, int bufferHeight) {
        vfxManager = new VfxManager(Pixmap.Format.RGBA8888, bufferWidth, bufferHeight);
        effectMap = new HashMap<>();
    }

    public VfxBox() {
        this(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public void addEffect(T t, ChainVfxEffect chainVfxEffect) {
        vfxManager.addEffect(chainVfxEffect);
        effectMap.put(t, chainVfxEffect);
    }

    public ChainVfxEffect get(T t) {
        return effectMap.get(t);
    }

    public VfxManager getVfxManager() {
        return vfxManager;
    }

    public void enableEffect(T effectEnum) {
        effectMap.get(effectEnum).setDisabled(false);
    }

    public void disableEffect(T effectEnum) {
        effectMap.get(effectEnum).setDisabled(true);
    }

    public void toggleEffect(T effectEnum) {
        ChainVfxEffect effect = effectMap.get(effectEnum);
        effect.setDisabled(!effect.isDisabled());
    }
}