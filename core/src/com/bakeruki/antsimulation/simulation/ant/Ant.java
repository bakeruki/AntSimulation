package com.bakeruki.antsimulation.simulation.ant;

import java.util.Random;

import com.bakeruki.antsimulation.simulation.colony.Colony;

public class Ant {
    private final float width = 14;
    private final float height = 12.5f;
    private final float speed = 1f;
    private final float wanderStrength = 0.02f;

    private Random rand;

    private float x;
    private float y;
    private float direction;
    private Colony home;

    public Ant(float x, float y, float direction, Colony colony){
        this.rand = new Random();

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.home = colony;
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
