package isa;

public class Person {
	public double	foreignness;	// in percent
	public String	name	= "";

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

	int getRandomNumber() {
		return 4; // chosen by fair dice roll
					// guaranteed to be random
	}
}
