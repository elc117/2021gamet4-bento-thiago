package com.benthiago.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Page extends Entity {
    Page(float worldX, float worldY, int pixelSizeX, int pixelSizeY, TiledMapTileLayer layer, int cellX, int cellY) {
        super(worldX, worldY, pixelSizeX, pixelSizeY);
        this.pageLayer = layer;
        this.cellX = cellX;
        this.cellY = cellY;
    }
}
