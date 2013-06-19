package isa.gui;

import isa.Gameplay;
import isa.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends IsaScreen {
	public float	logTimer;
	public float	monthTimer;

	public Sprite	outerBackground;
	public Sprite	innerBackground;
	public Sprite	logTab;
	public Sprite	peopleTab;
	private Sprite	storageTab;

	public GameScreen(Game game) {
		super(game);

		Gameplay.start();
	}

	@Override
	public void show() {
		super.show();

		outerBackground = new Sprite(textures.findRegion("red"));
		innerBackground = new Sprite(textures.findRegion("yellow"));
		peopleTab = textures.createSprite("people");
		logTab = textures.createSprite("log");
		storageTab = textures.createSprite("log");

		outerBackground.setBounds(0, 0, width, height);
		innerBackground.setBounds(20, 20, width - 40, height - 70);
		peopleTab.setPosition(50, height - 50);
		logTab.setPosition(200, height - 50);
		storageTab.setPosition(350, height - 50);

		logTimer = 0;
		monthTimer = 0;
		Gdx.input.setInputProcessor(new GameInput(game, cam, this)); // I guess this has to be set in the show method
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		logTimer += delta;
		batch.begin();

		updateGame();
		updateDisplay(batch);
		// update other, I don't know

		batch.end();
	}

	private void updateDisplay(SpriteBatch batch) {
// if (peopleTabShown) {
//
// } else {
//
// }
		outerBackground.draw(batch);
		innerBackground.draw(batch);

		peopleTab.draw(batch);
		logTab.draw(batch);
		storageTab.draw(batch);
	}

	private void updateGame() {
		if (logTimer >= 3) {
			logTimer = 0;
			int logsToCreateThisSecond = (int) (Gameplay.round.people.size() / 8 + Math.random() * Gameplay.round.people.size() / 8);

			for (int i = 0; i < logsToCreateThisSecond; i++) {
				Gameplay.round.logs.add(new Log(Gameplay.round.people));
			}

			System.out.println(Gameplay.round.logs.size());
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public static enum Tab {
		PEOPLE(), LOG(), STORAGE();

		Tab() {
		}
	}
}
