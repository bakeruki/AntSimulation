package com.bakeruki.antsimulation.simulation.pheremones;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Pheremone {
    private final float evaporationTime = 30f;

    public final static int HOME_PHEREMONE = 0;
    public final static int FOOD_PHEREMONE = 1;
    public final float r = 2;

    private float x;
    private float y;
    private float direction;
    private int type;
    private float timeSincePlaced = 0;
    private float alpha = 1;
    
    public Pheremone(float x, float y, float direction, int type){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
    }

    public void update(float dt){
        timeSincePlaced += dt;
        alpha = 1 - (timeSincePlaced / evaporationTime);
    }

    public void draw(SpriteBatch batch, TextureRegion foodPheremoneImage, TextureRegion homePheremoneImage){
        batch.setColor(255,255,255, alpha);
        switch(type){
            case HOME_PHEREMONE:
                batch.draw(homePheremoneImage, x, y, r*2, r*2);
                break;
            case FOOD_PHEREMONE:
                batch.draw(foodPheremoneImage, x, y, r*2, r*2);
                break;
        }
    }

    public boolean destroy(){
        if(timeSincePlaced >= evaporationTime){
            return true;
        }
        return false;
    }
}
