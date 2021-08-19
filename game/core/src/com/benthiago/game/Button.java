package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Button extends Texture {
    private final int x;
    private final int y;

    public Button(FileHandle file, final int x, final int y) {
        super(file);
        this.x = x;
        this.y = y;
    }

    public void batchDraw(SpriteBatch batch) {
        int width = this.getWidth();
        int height = this.getHeight();
        batch.draw(this, this.x - width / 2, this.y - height / 2, width, height);
    }

    public boolean isTouched(float mouseX, float mouseY) {
        int width = this.getWidth();
        int height = this.getHeight();
        boolean insideX = mouseX >= this.x - width / 2 && mouseX <= this.x + width / 2;
        boolean insideY = mouseY >= this.y - height / 2 && mouseY <= this.y + height / 2;
        return (insideX && insideY);
    }
}
