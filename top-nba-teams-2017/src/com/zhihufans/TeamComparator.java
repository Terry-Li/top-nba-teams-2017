package com.zhihufans;

import java.util.Comparator;


/**
 * @author Terry Li
 * 
 * Sort by the average rank of five team members
 *
 */
public class TeamComparator implements Comparator<Team> {

	@Override
	public int compare(Team t1, Team t2) {
		int rankSum1 = 0;
		int rankSum2 = 0;
		for (int i=0; i<t1.players.size(); i++) {
			rankSum1 += t1.players.get(i).rank;			
		}
		for (int i=0; i<t2.players.size(); i++) {
			rankSum2 += t2.players.get(i).rank;			
		}
		
		//assume that all players not making top 100 rank 101
		rankSum1 += 101 * (5 - t1.players.size());
		rankSum2 += 101 * (5 - t2.players.size());
		
		return rankSum1 - rankSum2;
	}

}
