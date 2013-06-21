package isa;

import isa.gui.GameScreen;
import isa.gui.IsaScreen;

public class Gameplay {
	// The game is on a turn per month schedule. You play until the end of each month and stuff happens in between.
	// You make you have a quota of terrorists to "find". You get a few strikes before you are forced to resign and sign an NDA.
	// Score will be calculated on how much of your quota you miss, as well as the rare and random terrorist attacks that add to your score

	// You will have a log of phone calls and internet messages
	// it will have location(international vs citizens), duration, names, and, huh, there was one other thing that I forgot, oh well.

	public static Round		round;

	public static int		month			= 0;

	public static int		suspectedQuota	= 0;
	public static int		totalSuspected	= 0;
	public static int		score			= 0;

	public static int		storage			= 20;	// in people + logs
	public static int		money;					// in millions

	public static IsaScreen	screen;

	public static void start() {
		nextMonth();
	}

	public static void nextMonth() {
		month++;
		round = new Round(month);

		totalSuspected += suspectedQuota;
		suspectedQuota = 0;

		((GameScreen) screen).logTimer = 0;
		((GameScreen) screen).countdown = 30;
	}

}
