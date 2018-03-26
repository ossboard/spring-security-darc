package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcLogIn {

    private Login login;

    private Error error;

    @Data
    @XmlRootElement(name = "login")
    public static class Login {
        private String userid;
        private int sessionid;
    }

    @Data
    @XmlRootElement(name = "error")
    public static class Error {
        private int errorcode;
        private String errormsg;
        private String trname;
    }
}

/*
 -- success --
<?xml version="1.0" encoding="UTF-8"?><kscc_response><login><userid>12</userid><sessionid>234489</sessionid></login></kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/