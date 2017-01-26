package com.stratio.poc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stratio.poc.common.dto.User;

@RestController
@RequestMapping(value = "/score/v1")
public class ScoreApi {

    @Value("${infoprofile}")
    private String profileName;

    @Autowired
    private RestTemplate restTemplate;

    Map<String, List<String>> cache = new HashMap<>();

    @HystrixCommand(fallbackMethod = "hyxtrixMethod")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Score get(@PathVariable("userId") String userId) {
        System.out.println("ENV: " + profileName);

        User user = getUser(userId);

        Score ret = new Score(user, profileName);

        return ret;
    }
    
    @HystrixCommand(fallbackMethod = "hyxtrixMethod")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Score delete(@PathVariable("userId") String userId) {
        System.out.println("ENV: " + profileName);
        
        cache.remove(userId);
        
        User user = getUser(userId);

        Score ret = new Score(user, profileName);

        return ret;
    }
    

    public User getUser(String userId) {
        User user = restTemplate.getForObject("http://user-service/user/v1/" + userId, User.class);
        return user;
    }
    
    
    public Score hyxtrixMethod(String userId) throws Exception {
        throw new Exception("Houston, tenemos un problema!");
    }
    
    public void hyxtrixMethod2(String userId) throws Exception {
        throw new Exception("Houston, tenemos un problema!");
    }
    
    class Score {
        private String node;
        private User user;
        private String score;
        private List<String> history = new ArrayList<>();

        public Score(User user, String node) {
            this.node = node;
            this.user = user;
            this.score = "" + new Random().nextInt(100);

            if (!cache.containsKey(user.getId())) {
                cache.put(user.getId(), new ArrayList<>());
            }
            cache.get(user.getId()).add(this.score);

            this.history.addAll(cache.get(user.getId()));
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public List<String> getHistory() {
            return history;
        }

        public void setHistory(List<String> history) {
            this.history = history;
        }

    }
}
