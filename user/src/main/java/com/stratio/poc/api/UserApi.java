package com.stratio.poc.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stratio.poc.common.dto.User;

@RestController
@RequestMapping(value = "/user/v1")
public class UserApi {

    @Value("${infoprofile}")
    private String profileName;
    
    @Autowired
    private  Map<String, User> cacheUsers;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User get(@PathVariable("userId") String userId) {
        
        try {
            //pruebas para timeout de hystrix. node3 dara timeout en esta peticion
            if ("node3".equals(profileName)) {
                System.out.println(LocalDateTime.now() + "- NODE3: Timeout");
                Thread.sleep(20000); 
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        return cacheUsers.get(userId);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> get() {
        
        try {
            //pruebas para timeout de hystrix. node3 dara timeout en esta peticion
            if ("node3".equals(profileName)) {
                System.out.println(LocalDateTime.now() + "- NODE3: Timeout");
                Thread.sleep(20000); 
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        return new ArrayList<User>(cacheUsers.values());
    }

}
