package com.benthiago.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BenthiagoGame extends Game {
	public static final int VIRTUAL_WIDTH  = 800;
	public static final int VIRTUAL_HEIGHT = 480;
	public static float TILEMAP_WIDTH;
	public static float TILEMAP_HEIGHT;
	public static float TILE_WIDTH;
	public static float TILE_HEIGHT;

	Soundtrack soundtrack;

	Texture creditsTexture;

	Credits credits;
	Menu menu;
	Mundo mundo;

	SpriteBatch batch;
	Texture playerTexture;

	OrthographicCamera menuCamera;
	OrthographicCamera playerCamera;
	Viewport menuViewport;
	Viewport playerViewport;

	TiledMap tiledMap;
	OrthogonalTiledMapRenderer renderer;

	BitmapFont font;

	@Override
	public void create () {
		soundtrack = new Soundtrack();

		font = new BitmapFont();

		tiledMap = new TmxMapLoader().load("map/Bosque.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);

		{
			TiledMapTileLayer backgroundLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
			TILEMAP_HEIGHT = backgroundLayer.getHeight();
			TILEMAP_WIDTH  = backgroundLayer.getWidth();
			TILE_WIDTH     = backgroundLayer.getTileWidth();
			TILE_HEIGHT    = backgroundLayer.getTileHeight();
		}

		batch = new SpriteBatch();
		playerTexture = new Texture(Gdx.files.internal("characters/player-from-above.png"));

		menuCamera = new OrthographicCamera();
		menuCamera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		menuViewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, menuCamera);

		playerCamera = new OrthographicCamera();
		playerCamera.setToOrtho(false, TILEMAP_WIDTH, TILEMAP_HEIGHT);
		playerViewport = new ExtendViewport(TILE_WIDTH * 18, TILE_HEIGHT * 8, playerCamera);

		this.credits = new Credits(this);
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
		playerTexture.dispose();
		credits.dispose();
		menu.dispose();
		mundo.dispose();
		tiledMap.dispose();
		soundtrack.dispose();
	}
}
