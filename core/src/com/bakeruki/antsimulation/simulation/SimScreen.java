package com.bakeruki.antsimulation.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bakeruki.antsimulation.AntSim;
import com.bakeruki.antsimulation.simulation.colony.Colony;

public class SimScreen extends ScreenAdapter{
    //constants
    private final int NUM_ANTS = 1000;

    //utilities
    private AntSim game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //sim objects
    private Colony colony;

    //textures
    private TextureRegion antImage;

    public SimScreen(OrthographicCamera camera, AntSim game, SpriteBatch batch){
        this.camera = camera;

        this.camera.setToOrtho(false);
        this.game = game;
        this.batch = batch;

        this.colony = new Colony(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, NUM_ANTS);

        this.antImage = new TextureRegion(new Texture(Gdx.files.internal("ant.png")));

    }

    public void init(){
        colony.init();
    }

    private void simulate(float dt){
        colony.simulate();
    }

    private void draw(float dt){
        colony.draw(batch, antImage);
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
