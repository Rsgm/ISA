package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Ogg;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import isa.Gameplay;
import isa.Log;

public class GameScreen extends IsaScreen {
    Ogg.Music music;
    public BitmapFont font;
    public BitmapFont error;

    // people/log stuff
    public static Sprite boxBackground;
    public static BitmapFont text1;
    public static BitmapFont text2;
    public static BitmapFont text3;
    public static BitmapFont text4;

    public float logTimer;
    public float monthTimer;
    public float countdown;                                        // round timer
    public float errorTextCountdownTimerFloatingPointVariable;

    public String errorText = "Test string, please ignore";    // test comment, please ignore

    public Sprite outerBackground;
    public Sprite innerBackground;
    public Sprite logTab;
    public Sprite peopleTab;
    public Sprite storageTab;

    public Tab tab = Tab.PEOPLE;

    public Sprite storageBuy10;
    public Sprite storageBuy100;
    public Sprite storageBuy1000;
    public Sprite storageBuy10000;
    public Sprite storageBuy100000;

    public Sprite errorBox;

    public boolean paused;

    public GameScreen(Game game) {
        super(game);

        Gameplay.nextMonth();
    }

    @Override
    public void show() {
        super.show();


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

        errorText(batch);

        batch.end();

        if (music == null || !music.isPlaying()) {
            System.out.println("new song");
            music = (Ogg.Music) Gdx.audio.newMusic(Gdx.files.internal("isa/gui/resources/audio/" + (int) (Math.random() * 16 + 1) + ".ogg"));
            music.setLooping(false);
            music.play();
        }
    }

    private void errorText(SpriteBatch batch) {
        if (errorTextCountdownTimerFloatingPointVariable > 0) {
            errorBox.draw(batch);
            error.draw(batch, errorText, 60, 68);
        }
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
        music.dispose();
    }

    public static enum Tab {
        PEOPLE(), LOG(), STORAGE();

        Tab() {
        }
    }
}
