package com.anonymous.roomchat;

public class Web_msg {

    String web_message_id;
    String web_message;
    String web_message_name;
    String web_user_email;

    public Web_msg(){

    }

    public Web_msg(String web_message_id, String web_message, String web_message_name, String web_message_email) {
        this.web_message_id = web_message_id;
        this.web_message = web_message;
        this.web_message_name = web_message_name;
        this.web_user_email = web_message_email;
    }

    public String getWeb_message_id() {
        return web_message_id;
    }

    public String getWeb_message() {
        return web_message;
    }

    public String getWeb_message_name() {
        return web_message_name;
    }

    public  String getWeb_user_email(){
        return web_user_email;
    }
}
