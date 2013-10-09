package isa;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import isa.gui.Assets;
import isa.gui.GameScreen;

import java.util.List;

public class Log {
    public Type type;
    public Person person1;
    public Person person2; // person1 and person 2 can be the same, I would say that is suspicious.
    public int duration; // in minutes
    public int scaryWords; // i.e. words the filter caught
    public Place collectedFrom;

    private Table table;
    public Button p1;
    public Button p2;
    public Button close;

    public Log(List<Person> people) {
        type = Type.valueOf("T" + (int) (Math.random() * Type.values().length));
        collectedFrom = Place.valueOf("P" + (int) (Math.random() * Place.values().length));

        person1 = people.get((int) (Math.random() * people.size()));
        do {
            person2 = people.get((int) (Math.random() * people.size()));
        } while (person1 != person2);

        duration = (int) (Math.random() * 180);
        scaryWords = (int) (Math.random() * 1000);

        createActors();
    }

    private void createActors() {
        Skin skin = Assets.skin;
        table = new Table(skin);
        p1 = new Button(skin);
        p2 = new Button(skin);
        close = new Button(skin);


        p1.add("View " + person1 + "'s profile.");
        p1.add("View " + person2 + "'s profile.");


    }

    public static enum Type {
        T0("Internet message"), T1("Phone call"), T2("Video chat"), T3("Photos"), T4("Video"), T5("VoIP"), T6("Video Conference"), T7(
                "Social Network Site"), T8("Encrypted, just guess at what it says");

        String name;

        Type(String name) {
            this.name = name;
        }
    }

    public enum Place {
        P0("Goo-Goo"), P1("MSSS"), P2("AT-AT"), P3("VIZOR"), P4("Your isp"), P5("The drone flying above you"), P6("ColdMail"), P7("Hooya"), P8(
                "The BookFace"), P9("FriendTalk"), P10("Pear"), P11("OAL"), P12("The person right next to you"), P13("Your closest friend"), P14(
                "JMail");

        String name;

        Place(String name) {
            this.name = name;
        }
    }

    public void render(SpriteBatch batch, int offset) {
        GameScreen.text1.draw(batch, "Type of Communication: " + type.name, 64, offset + 95);
        GameScreen.text2.draw(batch, "Between: " + person1.name + " and " + person2.name, 64, offset + 75);
        GameScreen.text2.draw(batch, "Number of suspicious words used: " + scaryWords, 64, offset + 55);
        GameScreen.text3.draw(batch, "Collected voluntarily from: " + collectedFrom.name, 64, offset + 35);
    }
}
