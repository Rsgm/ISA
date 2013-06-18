package isa;

import java.util.List;

public class Log {
	public Type		type;
	public Person	person1;
	public Person	person2;	// person1 and person 2 can be the same, I would say that is suspicious.
	public int		duration;	// in minutes
	public int		scaryWords; // i.e. words the filter caught

	public Log(List<Person> people) {
		type = Type.values()[(int) Math.random() * type.values().length];

		person1 = people.get((int) (Math.random() * people.size()));
		person2 = people.get((int) (Math.random() * people.size()));
		duration = (int) (Math.random() * 180);
		scaryWords = (int) Math.random() * 1000;
	}

	public static enum Type {
		INTERNET_MESSAGE(0), PHONE_CALL(1), VIDEO_CHAT(2);

		int	value;

		Type(int value) {
			this.value = value;
		}

	}
}
