package com.ilisi.mstxfleetlocation.config;

import com.ilisi.mstxfleetlocation.entity.LocationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  private final KafkaTemplate<String, LocationMessage> kafkaTemplate;


  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").setAllowedOrigins("*");
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(inboundChannelInterceptor());
  }

  @Bean
  public ChannelInterceptor inboundChannelInterceptor() {
    return new ChannelInterceptor() {

      @Override
      public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
          String userId = accessor.getFirstNativeHeader("userId");
          accessor.getSessionAttributes().put("userId", userId);
          log.info("User connected: " + userId);
          log.info("Session id: " + accessor.getSessionId());
        }

        if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
          String userId = (String) accessor.getSessionAttributes().get("userId");
          accessor.getSessionAttributes().remove("userId");
          log.info("User disconnected: " + userId);

          //make the user offline
          kafkaTemplate.send("location", LocationMessage.builder()
                  .userId(userId)
                  .isOnline(false)
                  .build());
        }

        return message;
      }
    };
  }
}