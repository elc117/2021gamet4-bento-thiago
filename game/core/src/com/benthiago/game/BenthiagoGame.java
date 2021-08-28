package com.benthiago.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
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
	SpriteBatch batch;
	Menu menu;
	Mundo mundo;
	OrthographicCamera camera;
	Viewport viewport;

	@Override
	public void create () {
		assetManager = new AssetManager(new InternalFileHandleResolver());
		batch        = new SpriteBatch();
		menu         = new Menu(this);
		camera       = new OrthographicCamera();

		camera.setToOrtho(false, TILEMAP_WIDTH, TILEMAP_HEIGHT);
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("games-from-scratch-tutorial.tmx", TiledMap.class);

		assetManager.finishLoading();

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

		assetManager.dispose();
	}
}
