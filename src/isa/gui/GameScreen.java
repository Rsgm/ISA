package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import isa.Gameplay;
import isa.Log;
import isa.Person;

import java.util.List;

public class GameScreen extends IsaScreen {
	public BitmapFont			font;
	public BitmapFont			error;

	// people/log stuff
	public static Sprite		boxBackground;
	public static BitmapFont	text1;
	public static BitmapFont	text2;
	public static BitmapFont	text3;
	public static BitmapFont	text4;

	public float				logTimer;
	public float				monthTimer;
	public float				countdown;										// round timer
	public float				errorTextCountdownTimerFloatingPointVariable;

	public String				errorText	= "Test string, please ignore";	// test comment, please ignore

	public Sprite				outerBackground;
	public Sprite				innerBackground;
	public Sprite				logTab;
	public Sprite				peopleTab;
	public Sprite				storageTab;

	public Tab					tab			= Tab.PEOPLE;

	public Sprite				storageBuy10;
	public Sprite				storageBuy100;
	public Sprite				storageBuy1000;
	public Sprite				storageBuy10000;
	public Sprite				storageBuy100000;

	public Sprite				errorBox;

	public boolean				paused;

	public GameScreen(Game game) {
		super(game);

		Gameplay.start();
	}

	@Override
	public void show() {
		super.show();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/whitrabt.ttf"));
		font = generator.generateFont(17);
		error = generator.generateFont(17);

		text1 = generator.generateFont(19);
		text2 = generator.generateFont(16);
		text3 = generator.generateFont(16);
		text4 = generator.generateFont(19);
		generator.dispose();

		font.setColor(.5f, .5f, 1f, 1f);
		error.setColor(Color.RED);

		text1.setColor(.9f, 0f, 0f, 1f);
		text2.setColor(Color.BLACK);
		text3.setColor(Color.GRAY);
		text4.setColor(Color.RED);

		outerBackground = new Sprite(textures.findRegion("red"));
		innerBackground = new Sprite(textures.findRegion("cork background"));
		outerBackground.setBounds(0, 0, width, height);
		innerBackground.setBounds(20, 20, width - 40, height - 70);

		peopleTab = textures.createSprite("people");
		logTab = textures.createSprite("log");
		storageTab = textures.createSprite("storage");

		peopleTab.setPosition(25, height - 50);
		logTab.setPosition(175, height - 50);
		storageTab.setPosition(325, height - 50);
		peopleTab.setColor(.7f, .7f, .7f, 1f);

		storageBuy10 = textures.createSprite("10tb");
		storageBuy100 = textures.createSprite("100tb");
		storageBuy1000 = textures.createSprite("1000tb");
		storageBuy10000 = textures.createSprite("10000tb");
		storageBuy100000 = textures.createSprite("100000tb");

		storageBuy10.setPosition(width / 2 - 250, 300);
		storageBuy100.setPosition(width / 2 - 50, 300);
		storageBuy1000.setPosition(width / 2 + 150, 300);
		storageBuy10000.setPosition(width / 2 - 150, 150);
		storageBuy100000.setPosition(width / 2 + 50, 150);

		errorBox = textures.createSprite("white");
		errorBox.setColor(1f, 1f, 1f, .5f);
		errorBox.setBounds(50, 50, width - 100, 25);

		boxBackground = textures.createSprite("white");

		logTimer = 0;
		monthTimer = 0;
		Gdx.input.setInputProcessor(new GameInput(this));
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (!paused) {
			logTimer += delta;
			countdown -= delta;
			errorTextCountdownTimerFloatingPointVariable -= delta;
		}

		batch.begin();

		updateGame();
		updateDisplay(batch);
		errorText(batch);

		batch.end();
	}

	private void errorText(SpriteBatch batch) {
		if (errorTextCountdownTimerFloatingPointVariable > 0) {
			errorBox.draw(batch);
			error.draw(batch, errorText, 60, 68);
		}
	}

	private void updateDisplay(SpriteBatch batch) {
		outerBackground.draw(batch);
		innerBackground.draw(batch);

		switch (tab) {
			case PEOPLE:
				List<Person> people = Gameplay.people;
				for (int i = 0; i < people.size(); i++) {
					people.get(i).render(batch, GameInput.scroll + i * 120);
				}
				break;
			case LOG:
				List<Log> logs = Gameplay.logs;
				for (int i = 0; i < logs.size(); i++) {
					logs.get(i).render(batch, GameInput.scroll + i * 120);
				}
				break;
			case STORAGE:
				storageBuy10.draw(batch);
				storageBuy100.draw(batch);
				storageBuy1000.draw(batch);
				storageBuy10000.draw(batch);
				storageBuy100000.draw(batch);
				break;
		}
		peopleTab.draw(batch, 1);
		logTab.draw(batch, 1);
		storageTab.draw(batch, 1);

		font.drawMultiLine(batch,
				"Terrorists left: " + Gameplay.suspecteQuota + "\nMoney: $" + Gameplay.money + "M\nStorage: "
						+ (Gameplay.logs.size() + Gameplay.people.size()) + "/" + Gameplay.storage + "TB", width - 200, height - 5);

		text4.drawMultiLine(batch, "Time left this month: " + (int) (countdown), width - 320, 17);
	}

	private void updateGame() {
		if (logTimer >= 3) {
			logTimer = 0;
			int logsToCreateThisCycle = (int) (Gameplay.people.size() / 5 + Math.random() * Gameplay.people.size() / 8);

			if (Gameplay.logs.size() + Gameplay.people.size() + logsToCreateThisCycle > Gameplay.storage) {
				logsToCreateThisCycle = Gameplay.storage - Gameplay.logs.size() - Gameplay.people.size();
			}

			for (int i = 0; i < logsToCreateThisCycle; i++) {
				Gameplay.logs.add(new Log(Gameplay.people));
			}
		}

		if (countdown <= 0) {
			Gameplay.nextMonth();
			System.out.println("New month");
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
