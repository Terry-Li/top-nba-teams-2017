package com.zhihufans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TopNBATeams {
	
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.si.com/nba/2016/09/12/nba-top-100-player-rankings").get();
		Elements players = doc.select("h1.heading-content");
		Pattern pattern = Pattern.compile("(.*?)\\.(.*?),(.*?),(.*)");
		Map<String, Team> teams = new HashMap<>();
		//read from the end of the list (get the higher ranked first); skip the first element since it's something else
		for (int i=players.size()-1; i>0; i--) {
			String line = players.get(i).text();			
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				Player player  = new Player();
				player.rank = Integer.parseInt(matcher.group(1));
				player.name = matcher.group(2).trim();
				player.position = matcher.group(3).trim();
				player.team = matcher.group(4).trim();
				//System.out.println(player);
				if (teams.containsKey(player.team)) {
					teams.get(player.team).players.add(player);
				} else {
					Team newTeam = new Team();
					newTeam.name = player.team;
					newTeam.players = new ArrayList<>();
					newTeam.players.add(player);
					teams.put(newTeam.name, newTeam);
				}
			}
		}
		
		//rank the teams by number of top 100 players, and by rank sum of all players in case of a tie
		List<Team> teamList = new ArrayList<Team>(teams.values());
		Collections.sort(teamList);
		for (int i=0; i<teamList.size(); i++) {
			Team team = teamList.get(i);
			team.rank = i+1;
			System.out.println(team);
		}
	}

}
