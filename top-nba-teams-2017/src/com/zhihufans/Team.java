package com.zhihufans;

import java.util.List;

public class Team implements Comparable<Team> {
	
	String name;
	
	List<Player> players;
	
	int rank;

	@Override
	public int compareTo(Team o) {
		if (this.getNumber() == o.getNumber()) {
			return this.getRankSum() - o.getRankSum();
		} else {
			return o.getNumber() - this.getNumber();
		}
	}
	
	public int getNumber() {
		return players.size();
	}
	
	public int getRankSum() {
		int sum = 0;
		for (Player p: players) {
			sum += p.rank;
		}
		return sum;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rank+". "+name+": ");
		for (Player p: players) {
			sb.append(p.name+"("+p.rank+") ");
		}
		return sb.toString();
	}

}
