package isa;


public class Gameplay {
	// The game is on a turn per month schedule. You play until the end of each month and stuff happens in between.
	// You make you have a quota of terrorists to "find". You get a few strikes before you are forced to resign and sign an NDA.
	// Score will be calculated on how much of your quota you miss, as well as the rare and random terrorist attacks that add to your score

	// You will have a log of phone calls and internet messages. it will have location(international vs citizens), duration, names, and

	public static Round	round;

	public static void start() {
		round = new Round(0);
	}

}
