package isa;

public class Log {
	public Type		type;
	public Person	person1;
	public Person	person2;	// person1 and person 2 can be the same, I would say that is suspicious.
	public int		duration;	// in minutes
	public int		scaryWords; // i.e. words the filter caught

	public Log() {
		type = Type.valueOf(Math.random() * 2 + "");
		person1 = Gameplay.people.get((int) (Math.random() * Gameplay.people.size()));
		person2 = Gameplay.people.get((int) (Math.random() * Gameplay.people.size()));
		duration = (int) (Math.random() * 180);
		scaryWords = (int) Math.random() * 1000;
	}

	public static enum Type {
		INTERNET_MESSAGE(0), PHONE_CALL(1);

		int	value;

		Type(int Value) {
			this.value = Value;
		}
	}
}
