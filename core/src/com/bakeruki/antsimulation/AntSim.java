package com.bakeruki.antsimulation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bakeruki.antsimulation.simulation.SimScreen;

public class AntSim extends Game {
	public int widthScreen;
	public int heightScreen;
	public static AntSim INSTANCE;

	private SimScreen simScreen;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;

	public AntSim(){
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, widthScreen, heightScreen);
		this.batch = new SpriteBatch();
		this.simScreen = new SimScreen(camera, this, batch);
		this.simScreen.init();
		this.setScreen(simScreen);
	}

}
