package com.pilgrimspath.data.shuttle;

public class ShuttleFactory {
	public static Shuttle CreateShuttle(String type, int count) {
		if (type.equals(Diamondhead.NAME)) {
			return new Diamondhead(count);
		} else {
			return new Shuttle(count);
		}
	}
}
