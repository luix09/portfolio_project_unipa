package com.slfl.portfolio_project.repository;

import com.slfl.portfolio_project.model.chat.ChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
}
