package com.bakeruki.antsimulation.simulation.colony;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bakeruki.antsimulation.simulation.ant.Ant;

public class Colony {
    private Random rand;

    private int NUM_ANTS;

    /*
     * Center x coordinate.
     */
    private float x;
    /*
     * Center y coordinate.
     */
    private float y;
    /*
     * Radius of colony.
     */
    private float r;

    /**
     * ArrayList of all ants that belong to this colony.
     */
    private ArrayList<Ant> ants;

    private Texture colonyTexture;

    public Colony(float x, float y, int numAnts){
        this.rand = new Random();
        this.x = x;
        this.y = y;
        this.r = 100;
        this.ants = new ArrayList<>();
        this.NUM_ANTS = numAnts;
        this.colonyTexture = new Texture(Gdx.files.internal("colony.png"));
    }

    /**
     * Initializes the colony.
     */
    public void init(){
        createAnts(NUM_ANTS);
    }

    /**
     * Create all of the ants for this colony.
     * @param numAnts Number of ants to create.
     */
    private void createAnts(int numAnts){
        //spawn a bunch of ants that belong to this colony
        for(int i = 0; i < NUM_ANTS; i++){
            //random angle around the colony
            float t = rand.nextInt(360);
            //get coordinates relative to the angle around the colony
            float x = (float)(r * Math.cos(Math.toRadians(t)) + this.x);
            float y = (float)(r * Math.sin(Math.toRadians(t)) + this.y);
            //add to arraylist of ants
            ants.add(new Ant(x, y, t, this));
        }
    }

    /**
     * Prints out all of the ant's positions and directions in the console.
     * This method causes MASSIVE amounts of lag so only use for testing purposes :)
     * @param a Ant to print.
     */
    private void printAntInfo(Ant a, int i){
        System.out.println("Ant Number " + i);
        System.out.println("x: " + a.getX() + ", y: " + a.getY() + ", direction: " + a.getDirection() + "\n");
    }

    /**
     * Moves all of the ants.
     */
    public void simulate(){
        //int i = 1;
        for(Ant ant: ants){
            ant.move();
            //printAntInfo(ant, i);
            //i++;
        }
    }

    /**
     * Draws the colony and all of the ants.
     * @param batch SpriteBatch to use.
     * @param antImage Image to draw for the ant.
     */
    public void draw(SpriteBatch batch, TextureRegion antImage){
        batch.draw(colonyTexture, x-r, y-r, r*2, r*2);
        for(Ant ant: ants){
            batch.draw(antImage, ant.getX(), ant.getY(), ant.getWidth() / 2, ant.getHeight() / 2, ant.getWidth(), ant.getHeight(), 1, 1, ant.getDirection() - 90);
        }  
    }
}
