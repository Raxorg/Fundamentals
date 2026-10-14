package com.epicness.fundamentals.stuff.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.crashinvaders.vfx.VfxRenderContext;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.ShaderVfxEffect;
import com.crashinvaders.vfx.framebuffer.VfxFrameBuffer;
import com.crashinvaders.vfx.framebuffer.VfxPingPongWrapper;
import com.crashinvaders.vfx.gl.VfxGLUtils;

public class DualKawaseDownsampleEffect extends ShaderVfxEffect implements ChainVfxEffect {

    private static final String U_TEXTURE0 = "u_texture0";
    private static final String U_TEXEL = "u_texel";
    private static final String U_OFFSET = "u_offset";

    private final Vector2 texelSize = new Vector2();
    private float offset = 1f;

    public DualKawaseDownsampleEffect() {
        super(VfxGLUtils.compileShader(
            Gdx.files.classpath("gdxvfx/shaders/screenspace.vert"),
            Gdx.files.internal("fundamentals/shaders/dual_kawase_down.frag")
        ));
        rebind();
    }

    @Override
    public void rebind() {
        super.rebind();
        setUniform(U_TEXTURE0, TEXTURE_HANDLE0);
        setUniform(U_OFFSET, offset);
    }

    @Override
    public void render(VfxRenderContext context, VfxPingPongWrapper buffers) {
        render(context, buffers.getSrcBuffer(), buffers.getDstBuffer());
    }

    public void render(VfxRenderContext context, VfxFrameBuffer src, VfxFrameBuffer dst) {
        setUniform(U_TEXEL, texelSize.set(1f / src.getFbo().getWidth(), 1f / src.getFbo().getHeight()));
        src.getTexture().bind(TEXTURE_HANDLE0);
        renderShader(context, dst);
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
        setUniform(U_OFFSET, offset);
    }
}