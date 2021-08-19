package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Menu implements Screen {
    final BenthiagoGame game;

    private Button iniciar;
    private Button creditos;
    private Button opcoes;

    Menu(final BenthiagoGame game) {
        this.game = game;
        
        iniciar    = new Button(Gdx.files.internal("iniciar.png"), game.WIDTH / 2, game.HEIGHT - 120);
        creditos   = new Button(Gdx.files.internal("creditos.png"), game.WIDTH / 2, game.HEIGHT / 2);
        opcoes     = new Button(Gdx.files.internal("opcoes.png"), game.WIDTH / 2, 120);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.1f, 1);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        iniciar.batchDraw(game.batch);
        opcoes.batchDraw(game.batch);
        creditos.batchDraw(game.batch);
        game.batch.end();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.camera.unproject(touchPos);

        if (Gdx.input.isTouched() && iniciar.isTouched(touchPos.x, touchPos.y)) {
            game.mundo = new Mundo(game);
            game.setScreen(game.mundo);
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        iniciar.dispose();
        opcoes.dispose();
        creditos.dispose();
    }
}
