package de.project.web.gameserver.modules.lobby;

import java.util.ArrayList;
import java.util.List;

import de.project.web.gameserver.modules.player.Player;

public class Lobby {
    private String id; //Lobby ID
    private String hostId;  // Spieler-ID des Hosts
    private List<Player> Players = new ArrayList<>(); // Liste der Spieler in der Lobby
    private boolean gameStarted = false; // wurde die lobby gestarte oder nicht

    public Lobby(String id, String hostId){
        this.id = id;
        this.hostId = hostId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(List<Player> players) {
        Players = players;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean inGameStarted) {
        this.gameStarted = inGameStarted;
    }
    
}
