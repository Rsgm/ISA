package isa;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Person {
	public double	foreignness;			// in percent
	public String	name		= "";
	public boolean	suspected	= false;

	public Sprite	suspect;

	/* Since you are reading through the code, I will let you in on a little secret, there are no terrorists.
	 * I could have made a system to pick a few random people to be terrorists that you have to find, but that isn't very critical. ;) */

	public Person() {
		foreignness = Math.random();

		int length = (int) (Math.random() * 10 + 20);
		int seperator = (int) (Math.random() * 5 + length / 2);

		for (int i = 0; i < length; i++) {
			if (i == 0 || i == seperator + 1) {
				char c = (char) ((int) (Math.random() * 26) + 97);
				name += Character.toString(c).toUpperCase();
			} else if (i == seperator) {
				name += " ";
			} else {
				name += (char) ((int) (Math.random() * 26) + 97);
			}
		}
	}

	public void render() {

	}

	int getRandomNumber() {
		return 4; // chosen by fair dice roll
					// guaranteed to be random
	}

	public void suspect() {
		Gameplay.suspectedQuota--;
		Gameplay.round.people.remove(this);
	}
}
