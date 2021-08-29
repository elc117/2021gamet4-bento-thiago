package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Mundo extends ScreenAdapter {
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

        tiledMap = new TmxMapLoader().load("games-from-scratch-tutorial.tmx");

        float unitScale = 1 / (float) BenthiagoGame.TILE_HEIGHT;
        //renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.1f, 1);

        game.playerCamera.update();
        game.batch.setProjectionMatrix(game.playerCamera.combined);

        renderer.setView(game.playerCamera);
        renderer.render();

        game.batch.begin();
        game.batch.end();

        float playerOffsetY = 0.0f;
        float playerOffsetX = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerOffsetX += 100.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerOffsetX -= 100.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerOffsetY += 100.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerOffsetY -= 100.0f;
        }

        game.playerCamera.translate(playerOffsetX, playerOffsetY);
    }

    @Override
    public void resize(int width, int height) {
        game.playerViewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
