package com.benthiago.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends Entity {
    Projectile(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX) {
        super(animationTexture, columns, rows, whichColumn, startX, endX);
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
