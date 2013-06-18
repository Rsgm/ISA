package isa;

import java.util.ArrayList;
import java.util.List;

public class Round { // acts as the game round
	public final static int	monthLength	= 30;						// seconds

	public List<Person>		people		= new ArrayList<Person>();
	public List<Log>		logs		= new ArrayList<Log>();

	public Round(int month) {
		int peopleAmount = (int) Math.round(Math.pow(2f, month / 16f) + Math.random() * 5 + 5);
		int logAmount = (int) Math.round(Math.pow(2f, month / 20f) + Math.random() * 5 + 5);
		// yes there were only 5 people on the internet in 2001

		for (int i = 0; i < peopleAmount; i++) {
			people.add(new Person());
			System.out.println(i);
		}
		for (int i = 0; i < logAmount; i++) {
			logs.add(new Log(people));
			System.out.println(i);
		}

	}
}
