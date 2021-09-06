package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class Credits extends ScreenAdapter {
    final BenthiagoGame game;
    Texture theCredits;

    Credits(BenthiagoGame game) {
        this.game = game;
        theCredits = new Texture(Gdx.files.internal("credits.png"));
    }

    @Override
    public void render(float delta) {
        game.menuViewport.apply();

        game.batch.begin();
        game.batch.draw(theCredits, 0, 0);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(game.menu);
        }
    }

    @Override
    public void resize(int width, int height) {
        game.menuViewport.update(width, height);
    }

    @Override
    public void dispose() {
        theCredits.dispose();
    }
}
