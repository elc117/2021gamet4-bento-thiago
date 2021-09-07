package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class Fim extends ScreenAdapter {
    final BenthiagoGame game;
    Texture ending;

    Fim(BenthiagoGame game, Texture ending) {
        this.game = game;
        this.ending = ending;
    }

    @Override
    public void render(float delta) {
        game.menuViewport.apply();
        game.batch.setProjectionMatrix(game.menuCamera.combined);

        game.batch.begin();
        game.batch.draw(ending, 0, 0, ending.getWidth() * 4, ending.getHeight() * 4);
        game.batch.end();

        game.soundtrack.keepPlaying();
    }

    @Override
    public void resize(int width, int height) {
        game.menuViewport.update(width, height);
    }
}
