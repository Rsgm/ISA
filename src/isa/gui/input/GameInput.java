package isa.gui.input;

import isa.Gameplay;
import isa.Person;
import isa.gui.GameScreen;
import isa.gui.GameScreen.Tab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class GameInput implements InputProcessor {

	private int			scroll;
	private GameScreen	screen;

	public GameInput(GameScreen screen) {
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screen.peopleTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
			screen.tab = Tab.PEOPLE;

			System.out.println("people");
			return true;
		} else if (screen.logTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
			screen.tab = Tab.LOG;

			System.out.println("logs");
			return true;
		} else if (screen.storageTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
			screen.tab = Tab.STORAGE;

			System.out.println("storage");
			return true;
		}

		switch (screen.tab) {
			default:
				for (Person p : Gameplay.round.people) {
					if (p.suspect.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
						p.suspect();
						return true;
					}
				}
				break;
			case STORAGE:
				if (screen.storageBuy10.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 10) {
					Gameplay.storage += 10;
					Gameplay.money -= 10;
					return true;
				}
				if (screen.storageBuy100.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 100) {
					Gameplay.storage += 100;
					Gameplay.money -= 100;
					return true;
				}
				if (screen.storageBuy1000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 1000) {
					Gameplay.storage += 1000;
					Gameplay.money -= 1000;
					return true;
				}
				if (screen.storageBuy10000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 10000) {
					Gameplay.storage += 10000;
					Gameplay.money -= 10000;
					return true;
				}
				if (screen.storageBuy100000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 100000) {
					Gameplay.storage += 100000;
					Gameplay.money -= 100000;
					return true;
				}
				break;
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		int height = Gdx.graphics.getHeight();

		if ((scroll >= height / 2 || amount == -1) && (scroll <= 0 /*what are the limits?*/|| amount == 1)) {
			scroll += amount;
		}
		return true;
	}

}
