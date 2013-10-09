package isa.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTFrame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.jna.Native;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.io.*;

public class TitleScreen extends IsaScreen {
    private BitmapFont font = Assets.font;
    private Skin skin = Assets.skin;

    private Stage stage;
    private Table canvas;

    private String text;

    private float time = 0f;
    private int counter = 0;

    private int introProgress = 0;

    private Image eagle;
    private Image redLine;

    private boolean vlcAvailable;
    private LwjglAWTFrame ls;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private long initialTime = 0;
    private File file;


    public TitleScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        // game.setScreen(new GameScreen(game));// uncomment to skip title

        font.setColor(0f, 0f, 0f, 1f);
        stage = new Stage();
        canvas = new Table(skin);
        stage.addActor(canvas);
        canvas.setSize(width, height);

        Label l = new Label("Nothing in this game is real." + "\nNor does this have any correlation to outside events.", skin);
        l.setAlignment(Align.left);
        Label l2 = new Label("(click to skip)", skin);
        l2.setAlignment(Align.center);
        canvas.add(l);
        canvas.row();
        canvas.add(l2).height(80);

        eagle = new Image(new TextureRegionDrawable(Assets.smoothTextures.findRegion("eagle")));
        eagle.scale(.001f);
        eagle.setColor(Color.CLEAR);
        redLine = new Image((new TextureRegionDrawable(Assets.smoothTextures.findRegion("redLine"))));
        redLine.setSize(800, 50);
        redLine.setPosition(0, -redLine.getHeight());

        try {
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            vlcAvailable = true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            vlcAvailable = false;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.act(delta);
        stage.draw();

        titleSequence(batch, delta);

        if (Gdx.input.isTouched()) {
            dispose();
//                    game.setScreen(new GameScreen(game));
        }
    }

    private void titleSequence(SpriteBatch batch, float delta) {
        switch (introProgress) { // basically a timeline of the title screen
            case 0:
                time += delta;
                if (time >= 1) {
                    introProgress++;
                    time = 0f;
                    counter = 0;
                    canvas.clearChildren();

                    if (!vlcAvailable) {
                        canvas.add(eagle).center();
                        canvas.addActor(redLine);
                        introProgress++;
                    } else {
                        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
                        ls = new LwjglAWTFrame(Gdx.app.getApplicationListener(), "Video by Cody B. of inkblock.net", 600, 800, Gdx.graphics.isGL20Available());

                        ls.setContentPane(mediaPlayerComponent);

                        ls.setLocation(100, 100);
                        ls.setSize(800, 600);
                        ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ls.setResizable(false);
                        ls.setVisible(true);

                        ls.addMouseListener(new MouseInputListener() {
                            @Override
                            public void mouseClicked(MouseEvent mouseEvent) {
                                dispose();
//                    game.setScreen(new GameScreen(game));
                            }

                            @Override
                            public void mousePressed(MouseEvent mouseEvent) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent mouseEvent) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent mouseEvent) {

                            }

                            @Override
                            public void mouseExited(MouseEvent mouseEvent) {

                            }

                            @Override
                            public void mouseDragged(MouseEvent mouseEvent) {

                            }

                            @Override
                            public void mouseMoved(MouseEvent mouseEvent) {

                            }
                        });

                        InputStream inputStream = null;
                        OutputStream outputStream = null;

                        // from http://www.mkyong.com/java/how-to-convert-inputstream-to-file-in-java/
                        try {
                            // read this file into InputStream
                            inputStream = TitleScreen.class.getResourceAsStream("resources/USA_vs_NSA_Animation.mp4");

                            file = File.createTempFile("USA_vs_NSA_Animation", ".mp4");

                            // write the inputStream to a FileOutputStream
                            outputStream = new FileOutputStream(file);

                            int read;
                            byte[] bytes = new byte[1024];

                            while ((read = inputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                            }

                            System.out.println("Done!");

                            if (mediaPlayerComponent.getMediaPlayer().prepareMedia(file.getAbsolutePath())) {
                                mediaPlayerComponent.getMediaPlayer().play();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (outputStream != null) {
                                try {
                                    // outputStream.flush();
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }

                break;
            case 1:
                if (initialTime == 0) {
                    initialTime = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - initialTime > 22000) {
                    dispose();
//                    game.setScreen(new GameScreen(game));
                    break;
                }
                break;
            case 2:
                time += delta;
                counter++;

                if (time < 2) {
                    eagle.setColor(1, 1, 1, time / 2);
                }

                // eagle alpha
                if ((time > 2.5 && time < 3.5) && counter % 5 > 0) {
                    eagle.setColor(Color.CLEAR);
                } else if ((time > 2.5 && time < 3.5) && counter % 5 == 0) {
                    eagle.setColor(Color.WHITE);
                } else if (time > 3.5) {
                    eagle.setColor(Color.CLEAR);
                }

                // red line movement
                if (time > 4 && time < 6) {
                    redLine.translate(0, 6);
                } else if (time > 6 && time < 8) {
                    redLine.translate(0, -6);
                }

                if (time > 8.5) {
                    dispose();
//                    game.setScreen(new GameScreen(game));
                }
                break;
        }

    }

    @Override
    public void dispose() {
        super.dispose();

        if (mediaPlayerComponent != null) {
            mediaPlayerComponent.getMediaPlayer().stop();
            mediaPlayerComponent.getMediaPlayer().release();
            mediaPlayerComponent.release();
            ls.dispose();
            file.delete();
        }
    }
}
