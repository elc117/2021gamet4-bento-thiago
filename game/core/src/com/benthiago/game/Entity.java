package com.benthiago.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.dongbat.jbump.Item;

public class Entity {
    float worldX;
    float worldY;
    int   pixelSizeX;
    int   pixelSizeY;
    Item<Entity> jbumpItem;
    int cellX;
    int cellY;
    TiledMapTileLayer pageLayer;

    Entity(float worldX, float worldY, int pixelSizeX, int pixelSizeY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.pixelSizeX = pixelSizeX;
        this.pixelSizeY = pixelSizeY;
    }

    public void setItem(Item<Entity> jbumpItem) {
        this.jbumpItem = jbumpItem;

    }
}
