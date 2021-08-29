package com.benthiago.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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

	AssetManager assetManager;

	Carregador carregador;
	Menu menu;
	Mundo mundo;

	SpriteBatch batch;
	OrthographicCamera menuCamera;
	OrthographicCamera playerCamera;
	Viewport viewport;

	@Override
	public void create () {
		assetManager = new AssetManager(new InternalFileHandleResolver());

		batch = new SpriteBatch();

		menuCamera = new OrthographicCamera();
		menuCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, menuCamera);

		playerCamera = new OrthographicCamera();
		playerCamera.setToOrtho(false, TILE_WIDTH, TILE_HEIGHT);
		viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, playerCamera);

		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("games-from-scratch-tutorial.tmx", TiledMap.class);

		carregador = new Carregador(this);
		this.setScreen(carregador);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		carregador.dispose();
		menu.dispose();
		mundo.dispose();

		assetManager.dispose();
	}
}
