package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.chat.ChatMessage;
import com.slfl.portfolio_project.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    /**
     * Trova l'id della chat e associa questo messaggio alla chatRoom adeguata.
     * Memorizza il messaggio ricevuto nel database.
     * @param chatMessage messaggio da salvare
     * @return ChatMessage
     */
    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
