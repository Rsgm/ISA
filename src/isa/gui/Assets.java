package isa.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static TextureAtlas roughTextures;
    public static TextureAtlas smoothTextures;
    public static Skin skin;
    public static BitmapFont font;

    public static void init() {
        roughTextures = new TextureAtlas(Gdx.files.internal("isa/gui/resources/roughTextures.txt"));
        smoothTextures = new TextureAtlas(Gdx.files.internal("isa/gui/resources/smoothTextures.txt"));
        roughTextures.findRegion("white").getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        skin = new Skin(Gdx.files.internal("isa/gui/resources/skin/uiskin.json"));

        font = new BitmapFont(Gdx.files.internal("isa/gui/resources/skin/droid_sans_mono.fnt"));

    }
}
