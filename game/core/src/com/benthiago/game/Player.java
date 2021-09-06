package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity{
    Player(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX) {
        super(animationTexture, columns, rows, whichColumn, startX, endX);
    }
}
