package com.konantech.spring.darcLib.model;

import lombok.Data;

@Data
public class AuthSessionInfo {

    private int sessionId;

    public AuthSessionInfo(int sessionId) {
        this.sessionId = sessionId;
    }
}
