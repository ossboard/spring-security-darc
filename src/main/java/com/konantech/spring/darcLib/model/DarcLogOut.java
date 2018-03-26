package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcLogOut {

    private String logout;

    private Error error;

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
<?xml version="1.0" encoding="utf-8"?><kscc_response><logout>success</logout></kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/