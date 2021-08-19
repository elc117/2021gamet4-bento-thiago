package com.benthiago.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BenthiagoGame extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	SpriteBatch batch;
	Menu menu;
	Mundo mundo;
	OrthographicCamera camera;
	Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		menu = new Menu(this);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		viewport = new FitViewport(800, 480, camera);

		this.setScreen(menu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		menu.dispose();
		mundo.dispose();
	}
}
