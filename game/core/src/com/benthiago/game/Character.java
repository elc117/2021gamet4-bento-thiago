package com.benthiago.game;

public class Character extends Entity{
    public int getKnowledge() {
        return knowledge;
    }

    public boolean isAlive() {
        return alive;
    }

    private int knowledge;
    private boolean alive;

    Character(){
        this.knowledge = 10;
    }
    Character(int knowledge){
        this.knowledge = knowledge;
    }
    public void move(){
        //Moves
    }
    public void setDead(){
        this.alive = false;
    }
}
