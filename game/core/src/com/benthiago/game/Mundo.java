package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Mundo extends ScreenAdapter {
    final BenthiagoGame game;

    private PlayerCharacter protagonist;
    private Character prova;
    private Combat combat;


    public Mundo(final BenthiagoGame game) {
        this.game = game;

        protagonist = new PlayerCharacter();
        prova = new Character();
        combat = new Combat();

        float unitScale = 1 / (float) BenthiagoGame.TILE_HEIGHT;
        //renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.playerViewport.apply();
        game.batch.setProjectionMatrix(game.playerCamera.combined);

        game.renderer.setView(game.playerCamera);
        game.renderer.render();

        game.batch.begin();
        game.batch.end();

        float playerOffsetY = 0.0f;
        float playerOffsetX = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerOffsetX += 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerOffsetX -= 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerOffsetY += 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerOffsetY -= 10.0f;
        }

        game.playerCamera.translate(playerOffsetX, playerOffsetY);
    }

    @Override
    public void resize(int width, int height) {
        game.playerViewport.update(width, height);
    }
}
