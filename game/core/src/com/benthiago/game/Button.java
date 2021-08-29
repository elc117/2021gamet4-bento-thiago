package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class Button {
    private Texture texture;
    private int x;
    private int y;

    public Button(Texture texture) {
        this.texture = texture;
        this.x = 0;
        this.y = 0;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void batchDraw(SpriteBatch batch) {
        int width  = this.texture.getWidth();
        int height = this.texture.getHeight();
        batch.draw(this.texture, this.x - width / 2, this.y - height / 2, width, height);
    }

    public boolean checkTouched(float mouseX, float mouseY) {
        int     width   = this.texture.getWidth();
        int     height  = this.texture.getHeight();
        boolean insideX = mouseX >= this.x - width / 2 && mouseX <= this.x + width / 2;
        boolean insideY = mouseY >= this.y - height / 2 && mouseY <= this.y + height / 2;

        if (insideX && insideY) {
            touchCallback();
            return true;
        }

        return false;
    }

    void dispose() {
        this.texture.dispose();
    }

    static void arrangeCenterSpaceBetween(
            Array<Button> buttons, int viewportWidth, int viewportHeight) {
        int buttonsHeight = 0;
        for (Button button: buttons) buttonsHeight += button.texture.getHeight();

        int gutter      = (viewportHeight - buttonsHeight) / buttons.size;
        int lowerMargin = gutter;
        for (Button button: buttons) {
            button.setXY(viewportWidth / 2, viewportHeight - lowerMargin);
            lowerMargin += gutter + button.texture.getHeight();
        }
    }

    abstract void touchCallback();
}
