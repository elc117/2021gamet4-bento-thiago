package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu extends ScreenAdapter {
    final BenthiagoGame game;

    private Array<Button> buttons;

    Menu(final BenthiagoGame game) {
        this.game = game;

        this.buttons = new Array<Button>(new Button[] {
            new Button(new Texture("iniciar.png")) {
                @Override
                void touchCallback() {
                    game.setScreen(game.mundo);
                }
            },
            new Button(new Texture("creditos.png")) {
                @Override
                void touchCallback() {
                    game.setScreen(game.credits);
                }
            },
            new Button(new Texture("opcoes.png")) {
                @Override
                void touchCallback() {
                    game.soundtrack.toggle();
                }
            }
        });

        Button.arrangeCenterSpaceBetween(buttons, BenthiagoGame.VIRTUAL_WIDTH, BenthiagoGame.VIRTUAL_HEIGHT);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.1f, 1);

        game.menuViewport.apply();

        game.batch.setProjectionMatrix(game.menuCamera.combined);

        game.batch.begin();
        for (Button button: buttons) {
            button.batchDraw(game.batch);
        }
        game.batch.end();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.menuCamera.unproject(touchPos);

        if (Gdx.input.isTouched()) {
            for (Button button: buttons) {
                button.checkTouched(touchPos.x, touchPos.y);
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
        for (Button button: buttons) {
            button.dispose();
        }
    }
}
