package com.epicness.fundamentals.stuff.box2d;

import static com.badlogic.gdx.math.MathUtils.degRad;
import static com.badlogic.gdx.math.MathUtils.radDeg;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.Rotatable;

public class BodyPlus implements Movable, Rotatable {

    protected Body body;
    private final World world;

    // In the future a BodyPlus could be made out of multiple shapes, don't forget to
    // change methods that use getFixtureList().first() accordingly
    public BodyPlus(World world, BodyType type, Shape shape) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public float getX() {
        return body.getPosition().x;
    }

    @Override
    public void translateX(float amount) {
        body.setTransform(body.getPosition().add(amount, 0f), body.getAngle());
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public void translateY(float amount) {
        body.setTransform(body.getPosition().add(0f, amount), body.getAngle());
    }

    @Override
    public float getRotation() {
        return body.getAngle() * radDeg;
    }

    @Override
    public void rotate(float degrees) {
        body.setTransform(body.getPosition(), body.getAngle() + degrees * degRad);
    }

    /**
     * Default is 0
     **/
    public void setDensity(float density) {
        body.getFixtureList().first().setDensity(density);
        body.resetMassData();
    }

    /**
     * Usually in the range [0,1] Default is 0.2
     **/
    public void setFriction(float friction) {
        body.getFixtureList().first().setFriction(friction);
    }

    /**
     * Usually in the range [0,1] Default is 0
     **/
    public void setRestitution(float restitution) {
        body.getFixtureList().first().setRestitution(restitution);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setLinearVelocity(float xVelocity, float yVelocity) {
        body.setLinearVelocity(xVelocity, yVelocity);
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        body.setLinearVelocity(linearVelocity);
    }

    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    public void setAngularVelocity(float angularVelocity) {
        body.setAngularVelocity(angularVelocity);
    }

    public void setBullet() {
        body.setBullet(true);
    }

    public void setFixedRotation() {
        body.setFixedRotation(true);
    }

    public void setGravityScale(float scale) {
        body.setGravityScale(scale);
    }

    public void setUserData(Object data) {
        body.setUserData(data);
    }

    public void destroy() {
        world.destroyBody(body);
    }
}