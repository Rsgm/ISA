package isa;

import isa.gui.GameScreen;
import isa.gui.IsaScreen;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {
    // The game is on a turn per month schedule. You play until the end of each month and stuff happens in between.
    // You make you have a quota of terrorists to "find". You get a few strikes before you are forced to resign and sign an NDA.
    // Score will be calculated on how much of your quota you miss, as well as the rare and random terrorist attacks that add to your score

    // You will have a log of phone calls and internet messages
    // it will have location(international vs citizens), duration, names, and, huh, there was one other thing that I forgot, oh well.

    public static List<Person> people = new ArrayList<Person>();
    public static List<Log> logs = new ArrayList<Log>();

    public static int month = 0;

    public static int suspecteQuota = 0;
    public static int totalSuspected = 0;
    public static int score = 0;

    public static int storage = 20;                        // in logs
    public static int money = 1;                        // in millions

    public static IsaScreen screen;

    public static void nextMonth() {
        month++;

        if (suspecteQuota <= 0) {
            money -= 2 * suspecteQuota;
        } else {
            money -= suspecteQuota;
        }

        totalSuspected += suspecteQuota;

        ((GameScreen) screen).logTimer = 0;
        ((GameScreen) screen).countdown = 60;

        int peopleAmount = (int) Math.round(Math.pow(2, month / 4) + Math.random() * 5 + 8);
        // yes there were only 8-13 people on the internet in 2001

        if (Gameplay.logs.size() + Gameplay.people.size() + peopleAmount > Gameplay.storage) {
            peopleAmount = Gameplay.storage - Gameplay.logs.size() - Gameplay.people.size();
        }

        for (int i = 0; i < peopleAmount; i++) {
            people.add(new Person());
        }

        suspecteQuota = (int) (people.size() * (Math.random() * 0.3 + 0.3));
    }

}
