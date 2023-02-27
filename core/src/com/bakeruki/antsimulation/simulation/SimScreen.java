package com.bakeruki.antsimulation.simulation;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.bakeruki.antsimulation.AntSim;
import com.bakeruki.antsimulation.simulation.colony.Colony;
import com.bakeruki.antsimulation.simulation.pheremones.Pheremone;

public class SimScreen extends ScreenAdapter{
    //constants
    private final int NUM_ANTS = 10;

    //utilities
    private AntSim game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //sim objects
    private Colony colony;
    private ArrayList<Pheremone> pheremones;

    //textures
    private TextureRegion antImage;
    private TextureRegion foodPheremoneImage;
    private TextureRegion homePheremoneImage;


    public SimScreen(OrthographicCamera camera, AntSim game, SpriteBatch batch){
        this.camera = camera;

        this.camera.setToOrtho(false);
        this.game = game;
        this.batch = batch;

        this.colony = new Colony(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, NUM_ANTS, this);
        this.pheremones = new ArrayList<>();

        this.antImage = new TextureRegion(new Texture(Gdx.files.internal("ant.png")));
        this.foodPheremoneImage = new TextureRegion(new Texture(Gdx.files.internal("foodPheremone.png")));
        this.homePheremoneImage = new TextureRegion(new Texture(Gdx.files.internal("homePheremone.png")));

    }

    public void init(){
        colony.init();
    }

    public void addPheremone(float x, float y, float direction, int type){
        pheremones.add(new Pheremone(x, y, direction, type));
    }

    private void updatePheremones(float dt){
        if(pheremones.size() != 0){
            ArrayList<Pheremone> remove = new ArrayList<>();
            for(Pheremone pheremone: pheremones){
                pheremone.update(dt);
                if(pheremone.destroy()){
                    remove.add(pheremone);
                }
            }
            pheremones.removeAll(remove);
        }   
    }

    private void simulate(float dt){
        colony.simulate(dt);
        updatePheremones(dt);
    }

    private void draw(float dt){
        batch.setColor(Color.WHITE);
        colony.draw(batch, antImage);
        for(Pheremone pheremone : pheremones){
            pheremone.draw(batch, foodPheremoneImage, homePheremoneImage);
        }
    }

    @Override
    public void render(float dt){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        simulate(dt);

        batch.enableBlending();
        batch.begin();
        draw(dt);
        batch.end();
    }
}
