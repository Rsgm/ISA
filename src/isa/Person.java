package isa;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Person {
    public boolean citizen;
    public String name;
    public boolean suspected = false;

    public Sprite suspect;
    public Sprite background;

	/*
     * Since you are reading through the code, I will let you in on a little secret, there are no terrorists.
     * I could have made a system to pick a few random people to be terrorists that you have to find, but that isn't as realistic. ;)
     */

    public Person() {
        citizen = Math.random() <= .7;
        name = Names.GenerateName();
    }

    int getRandomNumber() {
        return 4; // chosen by fair dice roll
        // guaranteed to be random
    }

    public void suspect() {
        Gameplay.suspecteQuota--;
        Gameplay.people.remove(this); // pretty dark
    }
}
