package isa;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import isa.gui.GameScreen;

public class Person {
    public boolean citizen;            // in percent
    public String name;
    public boolean suspected = false;

    public Sprite suspect;
    public Sprite background;

	/* Since you are reading through the code, I will let you in on a little secret, there are no terrorists.
     * I could have made a system to pick a few random people to be terrorists that you have to find, but that isn't very critical. ;) */

    public Person() {
        citizen = Math.random() >= .7;

        name = Names.GenerateName();

        background = Gameplay.screen.textures.createSprite("white");
        background.setColor(145f / 255f, 1f, 145f / 255f, 1f);

        suspect = Gameplay.screen.textures.createSprite("suspect");
    }

    public void render(SpriteBatch batch, int offset) {
        background.setBounds(50, offset, GameScreen.WIDTH - 93, 100);
        background.draw(batch);

        suspect.setPosition(60, offset + 5);
        suspect.draw(batch);

        GameScreen.text1.draw(batch, "Name: " + name, 64, offset + 95);
        GameScreen.text2.draw(batch, "Foreignness: " + citizen + "%", 64, offset + 75);
    }

    int getRandomNumber() {
        return 4; // chosen by fair dice roll
        // guaranteed to be random
    }

    public void suspect() {
        Gameplay.suspectedQuota--;
        Gameplay.people.remove(this);
    }
}
