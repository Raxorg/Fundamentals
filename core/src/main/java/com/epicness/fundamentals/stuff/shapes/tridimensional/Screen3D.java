package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.GL20.GL_ONE;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.rendering.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Drawable2D;

public class Screen3D<S extends Shape3D<?, ?>> {

    protected final S shape;
    public final float offsetX2D, offsetY2D, cameraX, cameraY;
    private final FrameBuffer frameBuffer;
    private final Sprite bufferSprite;
    private Drawable2D drawable2D;
    private final Color clearColor;

    public Screen3D(S shape, float offsetX2D, float offsetY2D, float surfaceWidth, float surfaceHeight, Drawable2D drawable2D) {
        this.shape = shape;
        this.offsetX2D = offsetX2D;
        this.offsetY2D = offsetY2D;
        this.cameraX = offsetX2D + surfaceWidth * 0.5f;
        this.cameraY = offsetY2D + surfaceHeight * 0.5f;
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) surfaceWidth, (int) surfaceHeight, false);
        bufferSprite = new Sprite();
        bufferSprite.setSize(surfaceWidth, surfaceHeight);
        bufferSprite.setRegion(frameBuffer.getColorBufferTexture()); // Resets UVs
        bufferSprite.setFlip(false, true); // Affects UVs
        shape.setSprite(bufferSprite); // Captures final UVs
        this.drawable2D = drawable2D;
        clearColor = new Color();
    }

    public Screen3D(S shape, float offsetX2D, float offsetY2D, float surfaceWidth, float surfaceHeight) {
        this(shape, offsetX2D, offsetY2D, surfaceWidth, surfaceHeight, null);
    }

    public final void draw2D(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, OrthographicCamera camera) {
        begin(spriteBatch, shapeDrawer, camera);
        spriteBatch.setBlendFunctionSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);
        drawable2D.draw(spriteBatch, shapeDrawer);
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        end(spriteBatch);
    }

    private void begin(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, OrthographicCamera camera) {
        // Move the camera and use its new projection matrix
        camera.position.set(cameraX, cameraY, 0f);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeDrawer.update();
        // Render to frame buffer
        frameBuffer.begin();
        ScreenUtils.clear(clearColor);
    }

    private void end(SpriteBatch spriteBatch) {
        spriteBatch.flush();
        frameBuffer.end();
    }

    public final void draw3D(ModelBatch modelBatch) {
        shape.draw(modelBatch);
    }

    public final void drawDebug3D(ModelBatch modelBatch) {
        shape.drawDebug(modelBatch);
    }

    public S getShape() {
        return shape;
    }

    public void setDrawable2D(Drawable2D drawable2D) {
        this.drawable2D = drawable2D;
    }

    public boolean isFlipX() {
        return bufferSprite.isFlipX();
    }

    public void setFlipX(boolean flipX) {
        bufferSprite.setFlip(flipX, bufferSprite.isFlipY());
        shape.setSprite(bufferSprite); // Updates UVs on the material
    }

    public void setClearColor(Color color) {
        clearColor.set(color);
    }
}