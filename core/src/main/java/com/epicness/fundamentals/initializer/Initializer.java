package com.epicness.fundamentals.initializer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epicness.fundamentals.SharedResources;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.rendering.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Initializer<A extends Assets, L extends Logic, R extends Renderer<S>, S extends Stuff<A>> {

    protected final A assets;
    protected L logic;
    protected R renderer;
    protected S stuff;
    private boolean initialized;

    public Initializer(A assets, L logic, R renderer, S stuff) {
        this.assets = assets;
        this.logic = logic;
        this.renderer = renderer;
        this.stuff = stuff;
    }

    public final void initialize(SharedResources sharedResources) {
        Game game = (Game) Gdx.app.getApplicationListener();
        SharedInput input = sharedResources.getInput();
        SharedScreen screen = sharedResources.getScreen();

        logic.setStructure(
            game,
            sharedResources.getAssets(),
            input,
            sharedResources.getLogic(),
            screen,
            sharedResources.getStuff(),
            assets,
            renderer,
            stuff
        );
        input.setRenderer(renderer);
        renderer.setScreen(screen);
        renderer.setSharedStuff(sharedResources.getStuff());
        renderer.setStuff(stuff);
        screen.setLogic(logic);
        screen.setRenderer(renderer);
        stuff.setSharedAssets(sharedResources.getAssets());
        stuff.setAssets(assets);

        input.clearInputHandlers();
        input.setEnabled(true);
        renderer.useStaticCamera();
        stuff.initializeStuff();

        initialized = true;
        sharedResources.registerInitializer(this);

        logic.initialLogic();
        game.setScreen(screen);
    }

    public final void fastInitialize(SharedResources sharedResources) {
        Game game = (Game) Gdx.app.getApplicationListener();
        SharedInput input = sharedResources.getInput();
        SharedScreen screen = sharedResources.getScreen();

        input.setRenderer(renderer);
        screen.setLogic(logic);
        screen.setRenderer(renderer);

        input.clearInputHandlers();
        input.setEnabled(true);

        logic.initialLogic();
        game.setScreen(screen);
    }

    public Assets getAssets() {
        return assets;
    }

    public boolean wasInitialized() {
        return initialized;
    }
}