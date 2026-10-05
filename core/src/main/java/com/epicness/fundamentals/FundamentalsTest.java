package com.epicness.fundamentals;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.badlogic.gdx.Input.Keys.T;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITESLINEAR_ATLAS;
import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITESNEAREST_ATLAS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.GradientFont;
import com.epicness.fundamentals.stuff.Text;

// TODO: 9/12/2024 "Asset groups" allow you to load or dispose assets at runtime from a single asset manager
// A Game subclass like this one (not this one) should be the entry point for your game
public class FundamentalsTest extends Game {

    private SpriteBatch spriteBatch;
    private ShapeDrawerPlus shapeDrawerPlus;
    private BitmapFont font;
    private GradientFont gradientFont;
    private DualSprited dualSprited;
    private String test;
    private float fontX, degrees;
    private Text text;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Sprite pixel = new TextureAtlas(SPRITESNEAREST_ATLAS.fileName).createSprite("pixel");
        shapeDrawerPlus = new ShapeDrawerPlus(spriteBatch, pixel);

        font = new BitmapFont(Gdx.files.internal("fundamentals/fonts/pixelFont.fnt"));

        gradientFont = new GradientFont("Gradient Font", 20f, 500f, RED, BLUE);
        gradientFont.getData().setScale(2f);

        Sprite weirdShape = new TextureAtlas(SPRITESLINEAR_ATLAS.fileName).createSprite("weirdShape");
        dualSprited = new DualSprited(weirdShape, weirdShape);
        dualSprited.setSize(100f);

        test = "ABC.DEF!GHIJKL\"MNOPQR'S\nTUVWX,YZ0123:456789?ab\ncdefghijklmnopqrstuvw\nxyz";

        text = new Text(font, "Test Text");
        text.setY(175f);
        text.hAlignRight();
        text.setWrap(true);
        text.setScale(4f);
    }

    private void update() {
        degrees += Gdx.graphics.getDeltaTime() * 45f;
        fontX = (MathUtils.sinDeg(degrees) + 1) * 100f;

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (text.contains(x, y)) System.out.println("Text clicked");
        }
    }

    @Override
    public void render() {
        update();

        ScreenUtils.clear(Color.FOREST);
        spriteBatch.begin();
        font.draw(spriteBatch, test, fontX, 400f);
        gradientFont.drawGradient(spriteBatch);
        dualSprited.draw(spriteBatch);
        text.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(K)) {
            dualSprited.stretchWidth(10f);
        }
        if (Gdx.input.isKeyPressed(L)) {
            dualSprited.setWidth(100f);
        }
        if (Gdx.input.isKeyPressed(D)) {
            text.drawDebug(spriteBatch, shapeDrawerPlus);
        }
        if (Gdx.input.isKeyPressed(T)) {
            text.setText("A very long test text that will surely wrap");
        }
        spriteBatch.end();
    }
}