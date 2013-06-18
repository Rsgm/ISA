package isa.gui;

import isa.Gameplay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameScreen extends IsaScreen {
	// screen sprites

	public GameScreen(Game game) {
		super(game);

		Gameplay.start();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(new GameInput(game, cam, this)); // I guess this has to be set in the show method
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();

		// update display();
		// update game
		// update other, I don't know

		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
