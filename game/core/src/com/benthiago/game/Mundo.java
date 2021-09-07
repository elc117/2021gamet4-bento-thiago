package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.util.Iterator;
import java.util.Set;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class Mundo extends ScreenAdapter {
    final BenthiagoGame game;

    private Player protagonist;
    private Array<Enemy> enemies;
    private Array<Projectile> projectiles;

    private World<Entity> world;

    public Mundo(final BenthiagoGame game) {
        this.game = game;

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

        {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
            TiledMapTileLayer collisions = (TiledMapTileLayer) game.tiledMap.getLayers().get("collisions");
            for (int x = 0; x < collisions.getWidth(); x++) {
                for (int y = 0; y < collisions.getHeight(); y++) {
                    if (collisions.getCell(x, y) != null) {
                        world.add(
                                new Item<Entity>(new Entity(x, y, game.TILE_WIDTH, game.TILE_HEIGHT))
                                , x, y, 1, 1);
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
        game.renderer.render(new int[]{0, 1, 2, 3});

        game.batch.begin();
        protagonist.draw(game.batch);
        game.batch.end();

        //game.renderer.render(new int[]{2});

        protagonist.move(
                Gdx.input.isKeyPressed(Input.Keys.UP)
                , Gdx.input.isKeyPressed(Input.Keys.DOWN)
                , Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                , Gdx.input.isKeyPressed(Input.Keys.LEFT)
                , delta, world
                , 0, game.TILEMAP_WIDTH
                , 0, game.TILEMAP_HEIGHT);

        game.soundtrack.keepPlaying();
    }

    @Override
    public void resize(int width, int height) {
        game.playerViewport.update(width, height);
    }
}
