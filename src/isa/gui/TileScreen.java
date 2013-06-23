package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
	private String		info1			= "Nothing in this game is real," + "\nnor does this have any correlation to any outside events."
												+ "\nIf you see any resemblance to anything, let me know so I can get that fixed."
												+ "\nThe last thing I want is for someone to get offended over a simple game."
												+ "\n\n\n\n(Click to skip, but stay for the controls)";
	private String		controls0		= "You are an agent in the ISA looking through data for anything suspicious."
												+ "\nReport any one who may look suspicious by clicking suspect."
												+ "\n\nWhat happens to them after that does not matter to you.";
	private String		controls1		= "Navigate the game by clicking the different tabs,"
												+ "\nwhich are labeled \"People\", \"Logs\", and \"Storage\"."
												+ "\n\nYou can scroll through the people and logs with the mouse wheel.";
	private String		controls2		= "At this facility we have a limited amount of disk space," + "\nwe limit it to 1TB per person."
												+ "\n\nAs more people start using the Internet and creating more data,"
												+ "\nyou will need to buy more storage.";
	private String		controls3		= "Each month you will be have a quota of people to suspect,"
												+ "\nwe make money by suspecting people, thus increasing public fear,"
												+ "\nbut we also lose money by not suspecting people."
												+ "\n\nYou will make money each month based on how many people you suspect.";
	public String		controls4		= "There is a percent foreign under every person,"
												+ "\nthis means how confident we are that they are foreign, it is 51% accurate."
												+ "\n\nRemember most of these are good working citizens,"
												+ "\ncan you live with yourself if you get a good-guy taken away and probably killed, or worse?";

	private float		alpha			= 0;

	private float		time			= 0f;
	private int			counter			= 0;

	private int			introProgress	= 0;

	TextureAtlas		actTextures		= new TextureAtlas("isa/gui/resources/acts.txt");
	Texture				eagle			= new Texture(Gdx.files.internal("isa/gui/resources/graphics/eagle.png"));

	private Sprite		act;
	private Sprite		background;
	private Sprite		textBox;
	private Sprite		eagleSprite;

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
		act.setScale(0.85f);
		act.setPosition(-50, 0);

		background = new Sprite(textures.findRegion("cork background"));
		background.setSize(width, height);
		background.setPosition(0, 0);

		textBox = new Sprite(textures.findRegion("white"));
		textBox.setBounds(40, 57, 700, 48);
		textBox.setColor(1f, 1f, 1f, .75f);

		eagle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		eagleSprite = new Sprite(eagle);
		eagleSprite.setPosition(-120, -205);
		eagleSprite.setScale(0.5f);
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
					alpha = 0;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, info1, 20, 320);

				break;
			case 1:
				if (alpha < 1 && time < 4) { // this is the bug that causes the flickering, I like it, it will stay
					alpha += 0.005f;
				} else if (alpha > 0) {
					alpha -= 0.005f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					alpha = 0;
					break;
				}

				eagleSprite.setColor(1f, 1f, 1f, alpha);
				eagleSprite.draw(batch);
				break;
			case 2:
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
			case 3:
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
			case 4:
				background.draw(batch);
				act.draw(batch);
				textBox.draw(batch);
				font.drawMultiLine(batch, text, 70f, 100f);

				if (time > 2.5) {
					introProgress++;
					time = 0f;
				}
				break;
			case 5:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					alpha = 0;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, controls0, 20, 320);
				break;
			case 6:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					alpha = 0;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, controls1, 20, 320);
				break;
			case 7:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					alpha = 0;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, controls2, 20, 320);
				break;
			case 8:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					font.setColor(0f, 0f, 0f, 1f);
					introProgress++;
					time = 0f;
					alpha = 0;
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, controls3, 20, 320);
				break;
			case 9:
				if (alpha < 1 && time < 10) {
					alpha += 0.002f;
				} else if (alpha > 0) {
					alpha -= 0.002f;
				} else {
					game.setScreen(new GameScreen(game));
					break;
				}
				font.setColor(1f, 1f, 1f, alpha);
				font.drawMultiLine(batch, controls4, 20, 320);
				break;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		actTextures.dispose();
		eagle.dispose();
	}
}
