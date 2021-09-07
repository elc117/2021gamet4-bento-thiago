package com.benthiago.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button {
    private final float height;
    private final float width;
    private Texture animationTexture;
    private Animation animation;
    private int x;
    private int y;
    private float animationDelay;
    private float animationTime;

    public Button(Texture animationTexture, float scale, int columns, int rows, int startX, int endX, int whichRow) {
        this.animationTexture = animationTexture;
        this.x = 0;
        this.y = 0;
        this.width = animationTexture.getWidth() * scale / (endX - startX + 1);
        this.height = animationTexture.getHeight() * scale;

        TextureRegion[][] tmp = TextureRegion.split(animationTexture
                , animationTexture.getWidth() / columns, animationTexture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[endX - startX + 1];
        for (int i = startX; i <= endX; i++) {
            frames[i - startX] = tmp[whichRow][i - startX];
        }
        animation = new Animation<TextureRegion>(0.2f, frames);
        animationDelay = 5.0f;
        animationTime = 0.0f;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void batchDraw(SpriteBatch batch, float delta) {
        batch.draw((TextureRegion) this.animation.getKeyFrame(animationTime, false)
                , this.x - width / 2, this.y - height / 2, width, height);
        if (animationDelay > 0) {
            animationDelay -= delta;
        }
        else {
            animationTime += delta;
        }
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
        this.animationTexture.dispose();
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
