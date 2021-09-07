package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu extends ScreenAdapter {
    final BenthiagoGame game;

    Button iniciar;
    Button creditos;
    Button musica;

    Menu(final BenthiagoGame game) {
        this.game = game;

        iniciar = new Button(new Texture("buttons/play-button.png"), 2.0f);
        creditos = new Button(new Texture("buttons/credits-button.png"), 2.0f);
        musica = new Button(new Texture("buttons/music-button.png"), 2.0f);

        Button.arrangeCenterSpaceBetween(new Button[]{iniciar, creditos, musica}, BenthiagoGame.VIRTUAL_WIDTH, BenthiagoGame.VIRTUAL_HEIGHT);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.1f, 1);

        game.menuViewport.apply();

        game.batch.setProjectionMatrix(game.menuCamera.combined);

        game.batch.begin();
        iniciar.batchDraw(game.batch);
        creditos.batchDraw(game.batch);
        musica.batchDraw(game.batch);
        game.batch.end();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.menuViewport.unproject(touchPos);

        if (Gdx.input.isTouched()) {
            if (iniciar.isTouched(touchPos.x, touchPos.y))
            {
                game.setScreen(game.mundo);
            }
            else if (creditos.isTouched(touchPos.x, touchPos.y))
            {
                game.setScreen(game.credits);
            }
            else if (musica.isTouched(touchPos.x, touchPos.y))
            {
                game.soundtrack.toggle();
            }
        }

        game.soundtrack.keepPlaying();
    }

    @Override
    public void resize(int width, int height) {
        game.menuViewport.update(width, height);
    }

    @Override
    public void dispose() {
        iniciar.dispose();
        creditos.dispose();
        musica.dispose();
    }
}
