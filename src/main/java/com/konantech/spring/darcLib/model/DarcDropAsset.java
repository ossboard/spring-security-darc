package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcDropAsset {

    private String dropasset;

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
<?xml version="1.0" encoding="utf-8"?><kscc_response><dropasset>success</dropasset></kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/