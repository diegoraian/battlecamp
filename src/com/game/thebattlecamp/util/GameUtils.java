package com.game.thebattlecamp.util;

import java.util.Random;

public class GameUtils {

	private GameUtils() {
	}

	public static int randomizeStartPlayerPosition() {
		return new Random().nextInt(Constantes.PLAYER_POSITIONS_X.length);
	}
	
}
