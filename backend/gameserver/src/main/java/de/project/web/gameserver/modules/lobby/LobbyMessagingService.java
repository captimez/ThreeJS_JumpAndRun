package de.project.web.gameserver.modules.lobby;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LobbyMessagingService {
    private static final Logger logger = LoggerFactory.getLogger(LobbyMessagingService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendPlayerList(String lobbyId, Object message) {
        logger.info("Sende Spieler-Liste an /topic/lobby/{}", lobbyId);
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, message);
    }
}