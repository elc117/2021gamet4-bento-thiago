package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {
    float worldSpeed;
    float worldX;
    float worldY;
    int   pixelSizeX;
    int   pixelSizeY;
    float animationTime;
    Animation<TextureRegion> animation;

    Entity(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX) {
        worldX = 0;
        worldY = 0;
        worldSpeed = 100;
        TextureRegion[][] tmp = TextureRegion.split(animationTexture
                , animationTexture.getWidth() / columns, animationTexture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[endX - startX + 1];
        for (int i = startX; i <= endX; i++) {
            frames[i - startX] = tmp[whichColumn][i - startX];
        }

        pixelSizeX = animationTexture.getWidth() / columns;
        pixelSizeY = animationTexture.getHeight() / rows;

        animation = new Animation<TextureRegion>(0.2f, frames);
        animationTime = 0.0f;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public void move(float worldOffsetX, float worldOffsetY, float delta) {
        worldX += worldOffsetX * delta;
        worldY += worldOffsetY * delta;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(animationTime, true), worldX * pixelSizeX, worldY * pixelSizeY, 0, 0, 32,32,1,1, 90,false);
        animationTime += Gdx.graphics.getDeltaTime();
    }
}
