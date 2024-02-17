package com.slfl.portfolio_project.model.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="chat_message")
@EnableAutoConfiguration
public class ChatMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String chatId;

    @Column
    private String senderId;

    @Column
    private String recipientId;

    @Column
    private String content;

    @Column
    private Date timestamp;
}
