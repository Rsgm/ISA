package isa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import isa.gui.Assets;
import isa.gui.TitleScreen;

public class Isa extends Game {
	private int		width;
	private int		height;

	private boolean	fullscreen;
	private boolean	vsync;

	@Override
	public void create() {
        Assets.init();

		width = 800;
		height = 600;
		fullscreen = false;
		vsync = true;

		Gdx.graphics.setDisplayMode(width, height, fullscreen);
		Gdx.graphics.setVSync(vsync); // because no one needs to render 4000 frames per second, but then again it keeps the room warm

		setScreen(new TitleScreen(this));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public boolean isVsync() {
		return vsync;
	}

	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}
}
