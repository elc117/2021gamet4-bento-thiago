package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Mundo implements Screen {
    final BenthiagoGame game;

    private Texture background;

    private PlayerCharacter protagonist;
    private Character prova;
    private Combat combat;

    private TiledMap tiledMap;

    private OrthogonalTiledMapRenderer renderer;

    public Mundo(final BenthiagoGame game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("background.png"));

        protagonist = new PlayerCharacter();
        prova = new Character();
        combat = new Combat();

        tiledMap = game.assetManager.get("games-from-scratch-tutorial.tmx");

        float unitScale = 1 / (float) BenthiagoGame.TILE_HEIGHT;
        //renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.1f, 1);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        renderer.setView(game.camera);
        renderer.render();

        game.batch.begin();
        game.batch.end();

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
        background.dispose();
    }
}
