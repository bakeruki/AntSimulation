package com.bakeruki.antsimulation.simulation;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bakeruki.antsimulation.AntSim;
import com.bakeruki.antsimulation.simulation.ant.Ant;

public class SimScreen extends ScreenAdapter{
    //constants
    private final int NUM_ANTS = 10;

    //utilities
    private AntSim game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Random rand;

    //sim objects
    private ArrayList<Ant> ants;

    //textures
    private TextureRegion antImage;

    public SimScreen(OrthographicCamera camera, AntSim game, SpriteBatch batch){
        this.camera = camera;

        this.camera.setToOrtho(false);
        this.game = game;
        this.batch = batch;
        this.rand = new Random();

        this.ants = new ArrayList<>();

        this.antImage = new TextureRegion(new Texture(Gdx.files.internal("ant.png")));

    }

    public void init(){
        for(int i = 0; i < NUM_ANTS; i++){
            float x = rand.nextInt(game.widthScreen);
            float y = rand.nextInt(game.heightScreen);

            Ant a = new Ant(x, y, 50);
            ants.add(a);
        }
    }

    private void simulate(float dt){
        int i = 1;
        for(Ant ant: ants){
            ant.move();
            System.out.println("Ant Number " + i);
            printAntInfo(ant);
            i++;
        }
    }

    private void printAntInfo(Ant a){
        System.out.println("x: " + a.getX() + ", y: " + a.getY() + ", direction: " + a.getDirection() + "\n");
    }

    private void draw(float dt){
        for(Ant ant: ants){
            batch.draw(antImage, ant.getX(), ant.getY(), ant.getWidth() / 2, ant.getHeight() / 2, ant.getWidth(), ant.getHeight(), 1, 1, ant.getDirection() - 90);
        }
    }

    @Override
    public void render(float dt){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        simulate(dt);
        batch.begin();
        draw(dt);
        batch.end();
    }
}
