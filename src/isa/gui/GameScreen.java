package isa.gui;

import isa.Player;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends IsaScreen {

	public GameScreen(Game game, String name) {
		super(game);

		GamePlay.generateGame();
		Network n = NetworkController.addPublicNetwork(NetworkType.PLAYER);

		player = new Player(name, n, textures, this);
		room = new Room(player, this);

		Sprite sprite = player.getSprite();
		sprite.setSize(sprite.getWidth() / tileSize, sprite.getHeight() / tileSize);

		cam.setToOrtho(false, room.getFloor().getWidth(), room.getFloor().getHeight());
		cam.update();

		renderer.setView(cam);

		cam.position.x = room.getFloor().getWidth() / 2;
		cam.position.y = 0;
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(new GameInput(game, cam, player, this)); // I guess this has to be set in the show method
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		SpriteBatch rBatch = renderer.getSpriteBatch();

		renderer.setView(cam);
		renderer.render();

		rBatch.begin();
		if (OPEN_WINDOW == null) {
			updateMovement();
			checkPosition(rBatch);
		}

		// update display();
		// update game
		// update other, I don't know

		player.getSprite().draw(rBatch);
		rBatch.end();

		if (OPEN_WINDOW != null) {
			OPEN_WINDOW.render(cam, batch, delta);
		}

		if (MAP_OPEN) {
			map.render(cam, batch, delta);
			System.out.println("map");
		}
	}

	private void checkPosition(SpriteBatch batch) {
		int x = player.getIsoX();
		int y = player.getIsoY();

		Device d = room.getDeviceAtTile(x - 1, y);

		if (d == null) {
			d = room.getDeviceAtTile(x, y - 1);
		}

		if (d != null) {
			Sprite s = new Sprite(textures.findRegion("spaceBarIcon"));
			s.setPosition(player.getSprite().getX(), player.getSprite().getY() + 32 / tileSize);
			s.setSize(16 / tileSize, 16 / tileSize);

			s.draw(batch);

			if (Gdx.input.isKeyPressed(Keys.SPACE) && OPEN_WINDOW == null) {

				OPEN_WINDOW = d.getTerminal();
				OPEN_WINDOW.open(textures, this);
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
