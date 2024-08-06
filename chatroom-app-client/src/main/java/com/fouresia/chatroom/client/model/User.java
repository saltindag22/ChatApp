package com.fouresia.chatroom.client.model;

public class User {
   

        private String id;
    
        private String username;
    
        private String password;
    
        private String color;
    
        private String displayname;
    
        private UserType type;
    
        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        public String getColor() {
            return color;
        }
    
        public void setColor(String color) {
            this.color = color;
        }
    
        public String getDisplayname() {
            return displayname;
        }
    
        public void setDisplayname(String displayname) {
            this.displayname = displayname;
        }
    
        public UserType getType() {
            return type;
        }
    
        public void setType(UserType type) {
            this.type = type;
        }
    
}
