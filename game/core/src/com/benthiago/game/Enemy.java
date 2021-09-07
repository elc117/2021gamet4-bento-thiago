package com.benthiago.game;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Character {
    Enemy(Texture animationTexture, int columns, int rows, int whichColumn, int startX, int endX, float worldX, float worldY) {
        super(animationTexture, columns, rows, whichColumn, startX, endX, worldX, worldY);
    }
}
