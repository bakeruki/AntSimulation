package com.bakeruki.antsimulation.simulation.ant;

import java.util.Random;

import com.bakeruki.antsimulation.simulation.SimScreen;
import com.bakeruki.antsimulation.simulation.colony.Colony;
import com.bakeruki.antsimulation.simulation.pheremones.Pheremone;

public class Ant {
    private final float width = 14;
    private final float height = 12.5f;
    private final float speed = 0.5f;
    private final float wanderStrength = 0.02f;

    /*
     * How often an ant should drop a pheremone (in s)
     */
    private final float timeBetweenPheremoneDrops = 0.5f;

    private Random rand;

    private float x;
    private float y;
    private float direction;
    /*
     * How long it has been since the last pheremone was dropped (in s)
     */
    private float timeSincePheremoneDrop = 0f;
    private boolean carryingFood = false;
    private Colony home;
    private SimScreen world;

    public Ant(float x, float y, float direction, Colony colony, SimScreen world){
        this.rand = new Random();

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.home = colony;
        this.world = world;
    }

    public void move(float dt){
        if(!carryingFood){
            wander(dt);
        }else{
            //go home
        }
    }

    private void wander(float dt){
        updateWanderDirection();

        double dx = speed * Math.cos(Math.toRadians(direction));
        double dy = speed * Math.sin(Math.toRadians(direction));

        x += dx;
        y += dy;

        timeSincePheremoneDrop += dt;
        if(timeSincePheremoneDrop >= timeBetweenPheremoneDrops){
            timeSincePheremoneDrop = 0;
            dropPheremone();
        }
    }

    private void dropPheremone(){
        if(carryingFood){
            world.addPheremone(x, y, direction, Pheremone.FOOD_PHEREMONE);
        }else{
            world.addPheremone(x, y, direction, Pheremone.HOME_PHEREMONE);
        }
    }

    private void updateWanderDirection(){
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
