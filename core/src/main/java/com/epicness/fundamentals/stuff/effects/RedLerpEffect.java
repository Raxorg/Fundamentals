package com.epicness.fundamentals.stuff.effects;

public class RedLerpEffect extends Effect {

    private float redTint;

    public RedLerpEffect() {
        super("fundamentals/shaders/redLerp.frag");
        setRedTint(0.5f);
    }

    @Override
    public void rebind() {
        super.rebind();
        if (program != null) {
            program.bind();
            if (program.hasUniform("u_tintAmount")) {
                program.setUniformf("u_tintAmount", redTint);
            }
        }
    }

    public float getRedTint() {
        return redTint;
    }

    public void setRedTint(float redTint) {
        this.redTint = redTint;
        rebind();
    }
}
