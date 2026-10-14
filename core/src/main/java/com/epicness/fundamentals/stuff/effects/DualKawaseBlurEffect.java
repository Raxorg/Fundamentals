package com.epicness.fundamentals.stuff.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Array;
import com.crashinvaders.vfx.VfxRenderContext;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.CompositeVfxEffect;
import com.crashinvaders.vfx.framebuffer.VfxFrameBuffer;
import com.crashinvaders.vfx.framebuffer.VfxPingPongWrapper;

public class DualKawaseBlurEffect extends CompositeVfxEffect implements ChainVfxEffect {

    private final DualKawaseDownsampleEffect down;
    private final DualKawaseUpsampleEffect up;

    private final Array<VfxFrameBuffer> levels = new Array<>();
    private int baseWidth = -1, baseHeight = -1;
    private int passes = 4;

    public DualKawaseBlurEffect() {
        down = register(new DualKawaseDownsampleEffect());
        up = register(new DualKawaseUpsampleEffect());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.baseWidth = width;
        this.baseHeight = height;
        rebuildPyramid();
    }

    @Override
    public void render(VfxRenderContext context, VfxPingPongWrapper buffers) {
        render(context, buffers.getSrcBuffer(), buffers.getDstBuffer());
    }

    public void render(VfxRenderContext context, VfxFrameBuffer src, VfxFrameBuffer dst) {
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // The manager's src buffer defaults to Nearest-filtered. Make it Linear
        Texture srcTex = src.getTexture();
        srcTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Down: src -> L0 -> L1 -> ... -> L(N-1)
        VfxFrameBuffer prev = src;
        for (int i = 0; i < levels.size; i++) {
            VfxFrameBuffer cur = levels.get(i);
            down.render(context, prev, cur);
            prev = cur;
        }

        // Up: L(N-1) -> L(N-2) -> ... -> L0 -> dst (full resolution)
        for (int i = levels.size - 1; i > 0; i--) {
            up.render(context, levels.get(i), levels.get(i - 1));
        }
        up.render(context, levels.get(0), dst);
    }

    private void rebuildPyramid() {
        disposeLevels();
        if (baseWidth <= 0 || baseHeight <= 0 || passes <= 0) {
            return;
        }

        int levelWidth = baseWidth;
        int levelHeight = baseHeight;
        for (int i = 0; i < passes; i++) {
            levelWidth = Math.max(1, (levelWidth + 1) / 2);
            levelHeight = Math.max(1, (levelHeight + 1) / 2);

            VfxFrameBuffer fb = new VfxFrameBuffer(Pixmap.Format.RGBA8888);
            fb.initialize(levelWidth, levelHeight);

            Texture t = fb.getTexture();
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            t.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
            levels.add(fb);
        }
    }

    private void disposeLevels() {
        for (int i = 0; i < levels.size; i++) {
            levels.get(i).dispose();
        }
        levels.clear();
    }

    @Override
    public void dispose() {
        disposeLevels();
        super.dispose();
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        if (passes < 1) throw new IllegalArgumentException("Passes must be >= 1");
        if (this.passes != passes) {
            this.passes = passes;
            rebuildPyramid();
        }
    }

    public float getOffset() {
        return down.getOffset();
    }

    public void setOffset(float offset) {
        down.setOffset(offset);
        up.setOffset(offset);
    }
}