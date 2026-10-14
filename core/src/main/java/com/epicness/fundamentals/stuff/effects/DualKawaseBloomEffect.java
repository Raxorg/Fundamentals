package com.epicness.fundamentals.stuff.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.crashinvaders.vfx.VfxRenderContext;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.CompositeVfxEffect;
import com.crashinvaders.vfx.effects.util.CombineEffect;
import com.crashinvaders.vfx.effects.util.CopyEffect;
import com.crashinvaders.vfx.effects.util.GammaThresholdEffect;
import com.crashinvaders.vfx.framebuffer.VfxFrameBuffer;
import com.crashinvaders.vfx.framebuffer.VfxPingPongWrapper;

public class DualKawaseBloomEffect extends CompositeVfxEffect implements ChainVfxEffect {

    private final CopyEffect copy;
    private final DualKawaseBlurEffect blur;
    private final GammaThresholdEffect threshold;
    private final CombineEffect combine;

    public DualKawaseBloomEffect() {
        copy = register(new CopyEffect());
        blur = register(new DualKawaseBlurEffect());
        threshold = register(new GammaThresholdEffect(GammaThresholdEffect.Type.RGBA));
        combine = register(new CombineEffect());

        // Defaults matching stock BloomEffect:
        threshold.setGamma(0.85f);
        combine.setSource1Intensity(1f);
        combine.setSource1Saturation(0.85f);   // base scene
        combine.setSource2Intensity(1.1f);
        combine.setSource2Saturation(0.85f);   // bloom
    }

    @Override
    public void render(VfxRenderContext context, VfxPingPongWrapper buffers) {
        // Preserve the unmodified scene.
        VfxFrameBuffer origSrc = context.getBufferPool().obtain();
        copy.render(context, buffers.getSrcBuffer(), origSrc);

        Gdx.gl.glDisable(GL20.GL_BLEND);

        threshold.render(context, buffers);   // high-pass
        buffers.swap();

        blur.render(context, buffers);        // dual Kawase blur, result in dst
        buffers.swap();

        // original scene + blurred highlights -> dst
        combine.render(context, origSrc, buffers.getSrcBuffer(), buffers.getDstBuffer());
        context.getBufferPool().free(origSrc);
    }

    public float getThreshold() {
        return threshold.getGamma();
    }

    public void setThreshold(float gamma) {
        threshold.setGamma(gamma);
    }

    public float getBaseIntensity() {
        return combine.getSource1Intensity();
    }

    public void setBaseIntensity(float intensity) {
        combine.setSource1Intensity(intensity);
    }

    public float getBloomIntensity() {
        return combine.getSource2Intensity();
    }

    public void setBloomIntensity(float intensity) {
        combine.setSource2Intensity(intensity);
    }

    public float getBaseSaturation() {
        return combine.getSource1Saturation();
    }

    public void setBaseSaturation(float saturation) {
        combine.setSource1Saturation(saturation);
    }

    public float getBloomSaturation() {
        return combine.getSource2Saturation();
    }

    public void setBloomSaturation(float saturation) {
        combine.setSource2Saturation(saturation);
    }

    public int getBlurPasses() {
        return blur.getPasses();
    }

    public void setBlurPasses(int passes) {
        blur.setPasses(passes);
    }

    public float getBlurOffset() {
        return blur.getOffset();
    }

    public void setBlurOffset(float offset) {
        blur.setOffset(offset);
    }
}