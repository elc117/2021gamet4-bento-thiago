package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Button {
    private final float height;
    private final float width;
    private Texture texture;
    private int x;
    private int y;

    public Button(Texture texture, float scale) {
        this.texture = texture;
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth() * scale;
        this.height = texture.getHeight() * scale;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void batchDraw(SpriteBatch batch) {
        batch.draw(this.texture, this.x - width / 2, this.y - height / 2, width, height);
    }

    public boolean isTouched(float mouseX, float mouseY) {
        boolean insideX = mouseX >= this.x - width / 2 && mouseX <= this.x + width / 2;
        boolean insideY = mouseY >= this.y - height / 2 && mouseY <= this.y + height / 2;

        if (insideX && insideY) {
            return true;
        }

        return false;
    }

    void dispose() {
        this.texture.dispose();
    }

    static void arrangeCenterSpaceBetween(
            Button[] buttons, int viewportWidth, int viewportHeight) {
        int buttonsHeight = 0;
        for (Button button: buttons) buttonsHeight += button.height;

        int gutter      = (viewportHeight - buttonsHeight) / buttons.length;
        int lowerMargin = gutter;
        for (Button button: buttons) {
            button.setXY(viewportWidth / 2, viewportHeight - lowerMargin);
            lowerMargin += gutter + button.height;
        }
    }
}
