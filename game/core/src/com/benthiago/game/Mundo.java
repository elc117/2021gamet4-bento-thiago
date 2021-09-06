package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.World;

public class Mundo extends ScreenAdapter {
    final BenthiagoGame game;

    private Player protagonist;
    private Array<Enemy> enemies;
    private Array<Projectile> projectiles;

    private World<Entity> world;

    public Mundo(final BenthiagoGame game) {
        this.game = game;

        {
            //RectangleMapObject spawnPoint = (RectangleMapObject) game.tiledMap.getLayers().get("objects").getObjects().get("playerSpawnpoint");
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
            //Gdx.app.log("Player", "X: " + spawnPoint.getRectangle().x);
            //Gdx.app.log("Player", "Y: " + spawnPoint.getRectangle().y);
            //game.playerCamera.lookAt((float) (spawnPoint.getRectangle().getX() / 32), (float) (spawnPoint.getRectangle().getY() / 32), 0);
            //for (MapObject object: game.tiledMap.getLayers().get("objects").getObjects()) {
            //    Gdx.app.debug("test", "lala" + object.getName());
            //}

        }

        protagonist = new Player(game.playerTexture, 3, 1, 0, 1, 3);
                //game.playerViewport.getWorldWidth() / 2, game.playerViewport.getWorldHeight() / 2);
        world = new World<Entity>();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.playerViewport.apply();
        game.batch.setProjectionMatrix(game.playerCamera.combined);

        game.renderer.setView(game.playerCamera);
        game.renderer.render();

        game.batch.begin();
        protagonist.draw(game.batch);
        game.batch.end();

        float playerOffsetY = 0.0f;
        float playerOffsetX = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerOffsetX += 100.0f * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerOffsetX -= 100.0f * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerOffsetY += 100.0f * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerOffsetY -= 100.0f * delta;
        }

        game.playerCamera.translate(playerOffsetX, playerOffsetY);
    }

    @Override
    public void resize(int width, int height) {
        game.playerViewport.update(width, height);
    }
}
