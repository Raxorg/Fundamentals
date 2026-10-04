package com.epicness.fundamentals.logic.collision3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Hit2D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ScreenHit3D;
import com.epicness.fundamentals.utils.CollisionUtils;
import com.epicness.fundamentals.utils.HitCalculator;

public class RayHitHandler {

    private final Line3D line;
    private float lineLength;
    private final DelayedRemovalArray<ScreenHit3D> hits;
    private final Hit2D hit2D;
    private ScreenHit3D closestHit3D;
    // Optimization
    private final Vector3 aux;
    private Screen3D<?> auxScreen;

    public RayHitHandler() {
        line = new Line3D();
        lineLength = 50f;
        hits = new DelayedRemovalArray<>();
        aux = new Vector3();
        hit2D = new Hit2D();
    }

    public void setRayLength(float lineLength) {
        this.lineLength = lineLength;
    }

    public Hit2D calculate2DHit(PerspectiveCamera camera, DelayedRemovalArray<Screen3D<?>> screens) {
        calculateHits(camera.getPickRay(Gdx.input.getX(), Gdx.input.getY()), screens);
        if (hits.isEmpty()) return null;
        calculateClosest3DHit(camera);
        return processClosestHit();
    }

    public ScreenHit3D calculate3DHit(PerspectiveCamera camera, DelayedRemovalArray<Screen3D<?>> screens) {
        calculateHits(camera.getPickRay(Gdx.input.getX(), Gdx.input.getY()), screens);
        if (hits.isEmpty()) return null;
        calculateClosest3DHit(camera);
        return closestHit3D;
    }

    private void calculateHits(Ray ray, DelayedRemovalArray<Screen3D<?>> screens) {
        line.set(ray.origin, ray.direction.cpy().scl(lineLength).add(ray.origin));

        hits.clear();
        hits.begin();
        Screen3D<?> screen;
        for (int i = 0; i < screens.size; i++) {
            screen = screens.get(i);
            if (CollisionUtils.intersects(ray, screen, aux)) {
                hits.add(new ScreenHit3D(aux.cpy(), screen));
            }
        }
        hits.end();
    }

    private void calculateClosest3DHit(PerspectiveCamera camera) {
        closestHit3D = hits.first();
        if (hits.size > 1) {
            for (int i = 0; i < hits.size; i++) {
                if (hits.get(i).location().dst(camera.position) < closestHit3D.location().dst(camera.position)) {
                    closestHit3D = hits.get(i);
                }
            }
        }
    }

    private Hit2D processClosestHit() {
        auxScreen = closestHit3D.screen();
        hit2D.setScreen(auxScreen);
        HitCalculator.calculateHit(auxScreen, closestHit3D.location(), hit2D);
        return hit2D;
    }
}