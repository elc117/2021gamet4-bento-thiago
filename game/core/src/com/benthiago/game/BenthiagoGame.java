package com.benthiago.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BenthiagoGame extends Game {
	public static final int VIEWPORT_WIDTH  = 800;
	public static final int VIEWPORT_HEIGHT = 480;
	public static final int TILEMAP_WIDTH   = 50;
	public static final int TILEMAP_HEIGHT  = 50;
	public static final int TILE_WIDTH      = 36;
	public static final int TILE_HEIGHT     = TILE_WIDTH;
	public static final int WORLD_WIDTH     = TILEMAP_WIDTH * TILE_WIDTH;
	public static final int WORLD_HEIGHT    = TILEMAP_HEIGHT * TILE_HEIGHT;

	Menu menu;
	Mundo mundo;

	SpriteBatch batch;
	OrthographicCamera menuCamera;
	OrthographicCamera playerCamera;
	Viewport menuViewport;
	Viewport playerViewport;

	@Override
	public void create () {
		batch = new SpriteBatch();

		menuCamera = new OrthographicCamera();
		menuCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		menuViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, menuCamera);

		playerCamera = new OrthographicCamera();
		playerCamera.setToOrtho(false, TILE_WIDTH, TILE_HEIGHT);
		playerViewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, playerCamera);

		this.menu = new Menu(this);
		this.mundo = new Mundo(this);

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
