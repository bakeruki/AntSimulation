package com.bakeruki.antsimulation.simulation.ant;

import java.util.Random;

public class Ant {
    private final float width = 55;
    private final float height = 50;
    private final float speed = 1f;
    private final float wanderStrength = 0.02f;

    private Random rand;

    private float x;
    private float y;
    private float direction;

    public Ant(float x, float y, float direction){
        this.rand = new Random();

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move(){
        updateDirection();

        double dx = speed * Math.cos(Math.toRadians(direction));
        double dy = speed * Math.sin(Math.toRadians(direction));

        x += dx;
        y += dy;
    }

    private void updateDirection(){
        direction = (direction + rand.nextFloat(-360, 360) * wanderStrength) % 360;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public float getDirection(){
        return direction;
    }
}
