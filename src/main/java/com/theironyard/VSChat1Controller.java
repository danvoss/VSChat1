package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Dan on 7/22/16.
 */
@Controller
public class VSChat1Controller {

    @Autowired
    UserRepository users;

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

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findByUsername(username);
        if (user == null) {
            user = new User(username, password);
            users.save(user);
        }
        else if (!password.equals(user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }
}
