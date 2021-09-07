package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.util.Iterator;
import java.util.Set;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class Mundo extends ScreenAdapter {
    final BenthiagoGame game;
    TiledMapTileLayer collisions;
    private Player protagonist;

    TiledMapTile noTile;

    int paginas;

    float timerFim;
    float timerComeco;

    private World<Entity> world;

    public Mundo(final BenthiagoGame game) {
        this.game = game;

        paginas = 0;

        protagonist = new Player(game.playerTexture
                , 3, 1, 0, 1, 3
                , 3, 0);

        world = new World<Entity>();

        protagonist.setItem(world.add(new Item<Entity>(protagonist)
                , protagonist.getWorldX()
                , protagonist.getWorldY()
                , 1
                , 1
        ));

        timerComeco = 5;
        timerFim = 60;

        {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
            collisions = (TiledMapTileLayer) game.tiledMap.getLayers().get("collisions");
            for (int x = 0; x < collisions.getWidth(); x++) {
                for (int y = 0; y < collisions.getHeight(); y++) {
                    if (collisions.getCell(x, y) != null) {
                        world.add(
                                new Item<Entity>(new Entity(x, y, game.TILE_WIDTH, game.TILE_HEIGHT))
                                , x, y, 1, 1);
                    }
                }
            }
            TiledMapTileLayer pages = (TiledMapTileLayer) game.tiledMap.getLayers().get("pages");
            for (int x = 0; x < pages.getWidth(); x++) {
                for (int y = 0; y < pages.getHeight(); y++) {
                    if (pages.getCell(x, y) != null) {
                        world.add(
                                new Item<Entity>(new Page(x, y
                                        , game.TILE_WIDTH, game.TILE_HEIGHT, pages, x, y))
                                , x, y, 1, 1);
                        paginas++;
                    }
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        {
            float actualWorldViewX = game.playerCamera.viewportWidth / game.TILE_WIDTH;
            float actualWorldViewY = game.playerCamera.viewportHeight / game.TILE_HEIGHT;
            float worldCameraX = clamp(protagonist.getWorldX()
                    , actualWorldViewX / 2.0f
                    , game.TILEMAP_WIDTH - actualWorldViewX / 2.0f);
            float worldCameraY = clamp(protagonist.getWorldY()
                    , actualWorldViewY / 2.0f
                    , game.TILEMAP_HEIGHT - actualWorldViewY / 2.0f);
            game.playerCamera.position.set(
                    game.TILE_WIDTH * worldCameraX
                    , game.TILE_HEIGHT * worldCameraY
                    , 0f
            );
        }
        game.playerViewport.apply();
        game.batch.setProjectionMatrix(game.playerCamera.combined);

        game.renderer.setView(game.playerCamera);
        game.renderer.render(new int[]{0, 1, 3});

        game.batch.begin();
        protagonist.draw(game.batch);
        game.batch.end();

        game.renderer.render(new int[]{2, 4});

        game.menuViewport.apply();
        game.secondaryBatch.setProjectionMatrix(game.menuCamera.combined);
        game.secondaryBatch.begin();
        if (timerComeco > 0) {
            game.secondaryBatch.draw(game.start, 0, 0, game.start.getWidth() * 4, game.start.getHeight() * 4);
        } else {
            game.font.draw(game.secondaryBatch, "Tempo restante: " + Math.round(timerFim), 10, 440);
            game.font.draw(game.secondaryBatch, "Paginas restante: " + paginas, 500, 440);
        }
        game.secondaryBatch.end();

        protagonist.move(
                Gdx.input.isKeyPressed(Input.Keys.UP)
                , Gdx.input.isKeyPressed(Input.Keys.DOWN)
                , Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                , Gdx.input.isKeyPressed(Input.Keys.LEFT)
                , delta, world
                , 0, game.TILEMAP_WIDTH
                , 0, game.TILEMAP_HEIGHT
                , game.tiledMap, this);

        if (timerFim < 0 || paginas == 0) {
            if (paginas == 0) {
                game.fim = new Fim(game, game.winnerMax);
            } else if (paginas > 3) {
                game.fim = new Fim(game, game.loser);
            } else {
                game.fim = new Fim(game, game.winnerPartial);
            }
            game.setScreen(game.fim);
        }

        if (timerComeco > 0) {
            timerComeco -= delta;
        } else {
            timerFim -= delta;
        }

        game.soundtrack.keepPlaying();
    }

    @Override
    public void resize(int width, int height) {
        game.playerViewport.update(width, height);
        game.menuViewport.update(width, height);
    }
}
