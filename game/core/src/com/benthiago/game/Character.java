package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Rect;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class Character extends Entity {
    float worldSpeed;
    float animationTime;
    Animation<TextureRegion> animation;

    enum Direction {RIGHT, LEFT, UP, DOWN, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN};

    Direction direction;

    Character(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX, float worldX, float worldY) {
        super(worldX, worldY
                , animationTexture.getWidth() / columns
                , animationTexture.getHeight() / rows);
        worldSpeed = 4;
        TextureRegion[][] tmp = TextureRegion.split(animationTexture
                , animationTexture.getWidth() / columns, animationTexture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[endX - startX + 1];
        for (int i = startX; i <= endX; i++) {
            frames[i - startX] = tmp[whichColumn][i - startX];
        }
        direction = Direction.UP;

        animation = new Animation<TextureRegion>(0.2f, frames);
        animationTime = 0.0f;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public void move(Boolean up, Boolean down, Boolean right, Boolean left
            , float delta, World world
            , float minX, float maxX, float minY, float maxY) {
        float tryWorldX = worldX;
        float tryWorldY = worldY;

        if (left != right) {
            tryWorldX += (left ? -1 : 1) * worldSpeed * delta;
        }
        if (up != down) {
            tryWorldY += (down ? -1 : 1) * worldSpeed * delta;
        }

        tryWorldX = clamp(tryWorldX, minX, maxX - 1);
        tryWorldY = clamp(tryWorldY, minY, maxY - 1);

        if (up) {
            if (right) {
                direction = Direction.RIGHT_UP;
            } else if (left) {
                direction = Direction.LEFT_UP;
            } else {
                direction = Direction.UP;
            }
        } else if (down) {
            if (right) {
                direction = Direction.RIGHT_DOWN;
            } else if (left) {
                direction = Direction.LEFT_DOWN;
            } else {
                direction = Direction.DOWN;
            }
        } else if (right) {
            direction = Direction.RIGHT;
        } else if (left) {
            direction = Direction.LEFT;
        }

        Response.Result result = world.move(jbumpItem, tryWorldX, tryWorldY, CollisionFilter.defaultFilter);

        worldX = result.goalX;
        worldY = result.goalY;
    }

    public void draw(SpriteBatch batch) {
        int rotation = 0;
        switch (direction) {
            case UP: rotation = 90; break;
            case DOWN: rotation = -90; break;
            case RIGHT: rotation = 0; break;
            case LEFT: rotation = 180; break;

            case RIGHT_UP: rotation = 45; break;
            case RIGHT_DOWN: rotation = -45; break;
            case LEFT_UP: rotation = 135; break;
            case LEFT_DOWN: rotation = 225; break;
        }
        batch.draw(animation.getKeyFrame(animationTime, true)
                , worldX * pixelSizeX
                , worldY * pixelSizeY
                , 16, 16, 32,32,1,1
                , rotation,false);
        animationTime += Gdx.graphics.getDeltaTime();
    }
}
