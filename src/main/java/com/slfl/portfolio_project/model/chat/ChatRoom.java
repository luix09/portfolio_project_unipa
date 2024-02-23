package com.slfl.portfolio_project.model.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


/**
 * Entity used to store all messages between a sender and a receiver.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="chat_room")
@EnableAutoConfiguration
public class ChatRoom {
    @Id
    @UuidGenerator
    @Column
    private String id;

    @Column
    private String chatId;

    @Column
    private String senderId;

    @Column
    private String recipientId;
}