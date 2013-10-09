package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import isa.Gameplay;

public class IsaScreen implements Screen {
    public static int width = Gdx.graphics.getWidth();
    public static int height = Gdx.graphics.getHeight();

    public Game game;

    public OrthographicCamera cam;
    public SpriteBatch batch = new SpriteBatch();

    public IsaScreen(Game game) {
        this.game = game;
        Gameplay.screen = this;

        cam = new OrthographicCamera();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// update camera
        cam.update();
        cam.apply(Gdx.gl10);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}
