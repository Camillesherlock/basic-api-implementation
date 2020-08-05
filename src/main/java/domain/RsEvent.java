package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RsEvent<eventName> {
    private String eventName;
    private String keyWord;
    private Users users;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public void setUser(Users users) { this.users = users; }
    public String getEventName() {
        return eventName;
    }


    public RsEvent(String eventName, String keyWord, Users users){
        this.eventName = eventName;
        this.keyWord = keyWord;
        this.users = users;
    }
    public String getKeyWord() {
        return keyWord;
    }
    @JsonIgnore
    public Users getUsers() {
        return users;
    }



}