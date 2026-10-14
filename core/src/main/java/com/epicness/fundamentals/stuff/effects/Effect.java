package com.epicness.fundamentals.stuff.effects;

import com.badlogic.gdx.Gdx;
import com.crashinvaders.vfx.VfxRenderContext;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.ShaderVfxEffect;
import com.crashinvaders.vfx.framebuffer.VfxPingPongWrapper;
import com.crashinvaders.vfx.gl.VfxGLUtils;

public abstract class Effect extends ShaderVfxEffect implements ChainVfxEffect {

    public Effect(String fragmentPath) {
        super(VfxGLUtils.compileShader(
            Gdx.files.classpath("gdxvfx/shaders/screenspace.vert"),
            Gdx.files.internal(fragmentPath)
        ));
        rebind();
    }

    @Override
    public void render(VfxRenderContext context, VfxPingPongWrapper buffers) {
        buffers.getSrcBuffer().getTexture().bind(TEXTURE_HANDLE0);
        renderShader(context, buffers.getDstBuffer());
    }
}