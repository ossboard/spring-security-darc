package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcSpecialUserAuthorityList {

    private SpecialUserAuthorityList getspecialuserauthoritylist;
    private Error error;

    @Data
    public static class SpecialUserAuthorityList {
        private List<Authority> authority;
    }

    @Data
    public static class Authority {
        private int checkstatus;
        private String authorityname;
        private String caption;
        private String specialflag;
        private int seq;
    }

    @Data
    public static class Error {
        private int errorcode;
        private String errormsg;
        private String trname;
    }

}

/*
 -- success --
<kscc_response>
    <getspecialuserauthoritylist>
        <authority>
            <checkstatus>0</checkstatus>
            <authorityname>auth_prime_product_download_picture</authorityname>
            <caption>사진다운로드</caption>
            <specialflag>oun</specialflag>
            <seq>13</seq>
        </authority>
        <authority>
            <checkstatus>0</checkstatus>
            ...

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/