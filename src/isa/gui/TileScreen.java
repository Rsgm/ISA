package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TileScreen extends IsaScreen {

	private BitmapFont	font;

	private String		text			= "";
	private String		info			= "On October 26, 2001. 48 hours after it was drafted,\nthe act has passed and is now being enforced."
												+ "\nThe majority of it has been kept secret for national security." + "~";
	private String		info1			= "Nothing in this game is real, nor has any correlation to any outside events."
												+ "\nIf you see any resemblance to anything, let me know so I can get that fixed."
												+ "\nThe last thing I want is for someone to get offended over a simple game.";
	private float		alpha			= 0;

	private float		time			= 0f;
	private int			counter			= 0;

	private int			random;
	private float		randomTime;

	private int			introProgress	= 0;

	TextureAtlas		actTextures		= new TextureAtlas("isa/gui/resources/acts.txt");

	private Sprite		act;
	private Sprite		background;
	private Sprite		textBox;

	public TileScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
// game.setScreen(new GameScreen(game));// uncomment to skip title

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("isa/gui/resources/fonts/whitrabt.ttf"));
		font = generator.generateFont(17);
		generator.dispose();

		font.setColor(0f, 0f, 0f, 1f);

		act = new Sprite(actTextures.findRegion("act hidden"));
		act.setScale(0.85f, 0.85f);
		act.setPosition(-50, 0);

		background = new Sprite(textures.findRegion("cork background"));
		background.setSize(width, height);
		background.setPosition(0, 0);

		textBox = new Sprite(textures.findRegion("white"));
		textBox.setBounds(40, 57, 700, 48);
		textBox.setColor(1f, 1f, 1f, .75f);

// Gdx.input.setInputProcessor(new TitleInput(game));

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		time += delta;

		batch.begin();

		login(batch, delta);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
		}
	}

	private void login(SpriteBatch batch, float delta) {
		switch (introProgress) { // basically a timeline of the title screen
			case 0:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, info1, 40, 320);

				break;
			case 1:
				if (time % 1 > Math.random() * 0.3 + .02) {
					time = 0f;
					text += info.charAt(counter);
					counter++;
				}
				background.draw(batch);
				act.translate(.02f, -.38f);
				act.draw(batch);
				textBox.draw(batch);
				font.drawMultiLine(batch, text, 70f, 100f);

				if (info.charAt(counter) == '~') {
					introProgress++;
					counter++;
					time = 0f;
				}
				break;
			case 2:
				background.draw(batch);
				act.translate(.02f, -.38f);
				act.draw(batch);
				textBox.draw(batch);
				font.drawMultiLine(batch, text, 70f, 100f);

				if (time >= 3.5f) {
					introProgress++;
					time = 0f;
				}
				break;
			case 3:
				background.draw(batch);
				act.draw(batch);
				textBox.draw(batch);
				font.drawMultiLine(batch, text, 70f, 100f);

				if (time > 5) {
					game.setScreen(new GameScreen(game));
				}
				break;
// case 4:
// font.setColor(0.4f, 0.7f, 0.4f, 0.4f);
//
// if (logTimer > 0.8) {
// introProgress++;
// }
// break;
// case 5:
//
// if (logTimer > 2 + randomTime) { // case 4 logTimer + case 5 logTimer
// game.setScreen(new MenuScreen(game));
// }
// break;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		actTextures.dispose();
	}
}
