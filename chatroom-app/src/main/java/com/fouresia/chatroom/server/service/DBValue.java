package com.fouresia.chatroom.server.service;

public class DBValue {

    private String value;

    private DBValueType type = DBValueType.STR;

    public DBValue() {
    }

    public DBValue(String value, DBValueType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DBValueType getType() {
        return type;
    }

    public void setType(DBValueType type) {
        this.type = type;
    }

}
