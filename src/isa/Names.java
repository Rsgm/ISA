package isa;

import de.beimax.janag.NameGenerator;

import java.util.Locale;

public class Names {
    public static String[] languages = new String[]{"Pseudo Old German", "Greek", "Spanish", "Italian",
            "Irish", "French w Muslim", "French wo Muslim", "Polish", "Hebrew", "Belgian-All",
            "Belgian-Wallon", "Belgian-Flams", "Netherlands", "Estonian", "Latvian",
            "Lithuanian", "German with Moslem/Turks", "German wo Moslem/Turks", "Danish", "Finnish",
            "Swedish", "Norwegian", "Iceland", "Turkmenistan", "Uzbekistan", "Kirghizstan", "Kazakhstan",
            "Ukrainian", "Moldavian", "Georgian", "Caucasian", "Armenian", "Belarussian", "Russian",
            "Romanian", "Albanian", "Bulgarian", "Egyptian", "Marrocan", "Algerian", "Iranian", "Turkey",
            "Afghanian", "Pakistani", "Indian (Hindi)", "Schwarzes Auge RPG"};

    public static String GenerateName(String language, String gender) {
        NameGenerator n = new NameGenerator("/lib/JaNaG_GUI.jar!/de/beimax/janag/languages.txt", "/lib/JaNaG_GUI.jar!/de/beimax/janag/semantics.txt");

        if (gender == null) {
            String[] g = n.getGenders(language);
            gender = g[(int) (Math.random() * g.length)];
        }

        return n.getRandomName(language, gender, 1, Locale.getDefault().getLanguage())[0];
    }

    public static String GenerateName() {
        String s = "";
        do {
            String language;
            if (Math.random() * 100 >= 70) {
                language = "U.S. Human (1990)";
            } else {
                language = Names.languages[(int) (Math.random() * Names.languages.length)];
            }

            try {

                s = Names.GenerateName(language, null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } while (s.isEmpty());
        return s;
    }

}
