package isa.gui;

import isa.Gameplay;
import isa.Log;
import isa.gui.input.GameInput;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameScreen extends IsaScreen {
	private BitmapFont	font;

	public float		logTimer;
	public float		monthTimer;
	public int			countdown;			// round timer

	public Sprite		outerBackground;
	public Sprite		innerBackground;
	public Sprite		logTab;
	public Sprite		peopleTab;
	public Sprite		storageTab;

	public Tab			tab	= Tab.PEOPLE;

	public Sprite		storageBuy10;
	public Sprite		storageBuy100;
	public Sprite		storageBuy1000;
	public Sprite		storageBuy10000;
	public Sprite		storageBuy100000;

	public GameScreen(Game game) {
		super(game);

		Gameplay.start();
	}

	@Override
	public void show() {
		super.show();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("isa/gui/resources/fonts/whitrabt.ttf"));
		font = generator.generateFont(17);
		generator.dispose();

		font.setColor(150f / 255f, 1f, 1f, 1f);

		outerBackground = new Sprite(textures.findRegion("red"));
		innerBackground = new Sprite(textures.findRegion("yellow"));
		outerBackground.setBounds(0, 0, width, height);
		innerBackground.setBounds(20, 20, width - 40, height - 70);

		peopleTab = textures.createSprite("people");
		logTab = textures.createSprite("log");
		storageTab = textures.createSprite("storage");

		peopleTab.setPosition(50, height - 50);
		logTab.setPosition(200, height - 50);
		storageTab.setPosition(350, height - 50);

		storageBuy10 = textures.createSprite("10tb");
		storageBuy100 = textures.createSprite("100tb");
		storageBuy1000 = textures.createSprite("1000tb");
		storageBuy10000 = textures.createSprite("10000tb");
		storageBuy100000 = textures.createSprite("100000tb");

		storageBuy10.setPosition(150, 200);
// storageBuy100;
// storageBuy1000;
// storageBuy10000;
// storageBuy100000;

		logTimer = 0;
		monthTimer = 0;
		Gdx.input.setInputProcessor(new GameInput(this));
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
		switch (tab) {
			case PEOPLE:
				break;
			case LOG:
				break;
			case STORAGE:
				storageBuy10.draw(batch);
// storageBuy100;
// storageBuy1000;
// storageBuy10000;
// storageBuy100000;
				break;
		}

		outerBackground.draw(batch);
		innerBackground.draw(batch);

		peopleTab.draw(batch, 1);
		logTab.draw(batch, 1);
		storageTab.draw(batch, 1);

		font.drawMultiLine(batch,
				"Terrorists left:" + Gameplay.suspectedQuota + "\nMoney:" + Gameplay.money + "\nStorage:"
						+ (Gameplay.round.logs.size() + Gameplay.round.people.size()) + "/" + Gameplay.storage, width - 200, height - 5);

	}

	private void updateGame() {
		if (logTimer >= 3) {
			logTimer = 0;
			int logsToCreateThisCycle = (int) (Gameplay.round.people.size() / 8 + Math.random() * Gameplay.round.people.size() / 8);

			System.out.println(logsToCreateThisCycle);

			if (Gameplay.round.logs.size() + Gameplay.round.people.size() + logsToCreateThisCycle > Gameplay.storage) {
				logsToCreateThisCycle = Gameplay.storage - Gameplay.round.logs.size() - Gameplay.round.people.size();
			}

			System.out.println(logsToCreateThisCycle);

			for (int i = 0; i < logsToCreateThisCycle; i++) {
				Gameplay.round.logs.add(new Log(Gameplay.round.people));

			}

			System.out.println(Gameplay.round.logs.size());
		}

		if (countdown <= 0) {
			Gameplay.nextMonth();
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
