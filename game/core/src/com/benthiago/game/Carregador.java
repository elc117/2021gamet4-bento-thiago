package com.benthiago.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class Carregador extends ScreenAdapter {

    final BenthiagoGame game;
    BitmapFont font;

    Carregador(final BenthiagoGame game) {
        this.game = game;
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        if (game.assetManager.update()) {
            game.menu = new Menu(game);
            game.setScreen(game.menu);
        }

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        game.menuCamera.update();
        game.batch.setProjectionMatrix(game.menuCamera.combined);

        game.batch.begin();
        font.draw(
                game.batch,
                game.assetManager.getProgress() * 100 + " % loaded",
                0,
                game.VIEWPORT_HEIGHT / 2
        );
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
