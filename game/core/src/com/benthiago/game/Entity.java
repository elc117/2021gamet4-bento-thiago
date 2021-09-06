package com.benthiago.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Entity {
    float x;
    float y;
    float animationTime;
    Animation<TextureRegion> animation;

    Entity(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX) {
        TextureRegion[][] tmp = TextureRegion.split(animationTexture
                , animationTexture.getWidth() / columns, animationTexture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[endX - startX + 1];
        for (int i = startX; i <= endX; i++) {
            frames[i - startX] = tmp[whichColumn][i - startX];
        }

        animation = new Animation<TextureRegion>(0.2f, frames);
        animationTime = 0.0f;
    }

    public abstract void move();

    public abstract void draw(SpriteBatch batch);
}
