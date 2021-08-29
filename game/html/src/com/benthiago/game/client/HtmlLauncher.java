package com.benthiago.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.benthiago.game.BenthiagoGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                //return new GwtApplicationConfiguration(true);
                // Fixed size application:
                return new GwtApplicationConfiguration(BenthiagoGame.VIEWPORT_WIDTH, BenthiagoGame.VIEWPORT_HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new BenthiagoGame();
        }
}