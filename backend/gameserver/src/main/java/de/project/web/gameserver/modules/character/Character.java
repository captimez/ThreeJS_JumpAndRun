package de.project.web.gameserver.modules.character;

import de.project.web.gameserver.modules.player.Player;

public class Character {
    Player player;
    String name;
    //TODO Abilities

    Character() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}
