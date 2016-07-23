package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Dan on 7/22/16.
 */
@Controller
public class VSChat1Controller {
    static SimpMessagingTemplate messenger;
    @Autowired
    public VSChat1Controller(SimpMessagingTemplate messenger) {
        this.messenger = messenger;
    }

    @MessageMapping("/topic/chat")
    @SendTo("/chat")
    //@SendTo("/topic/chat")
    public Message sendMessage(Message msg) {
        System.out.println(new String((byte[]) msg.getPayload()));
        return msg;
    }
}
