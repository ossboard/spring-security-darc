package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcSearch {

    private Search search;

    @Data
    public static class Search {
        private int resultcount;
        private int pageindex;
        private List<MetaSet> metaset;
    }

    @Data
    public static class MetaSet {
        private int pkvalue;
        private List<MetaField> metafield;
    }

    @Data
    public static class MetaField {
        private String fieldname;
        private String fieldtype;
        private String fieldvalue;
    }

}

/*
 -- success --

<kscc_response>
   <search>
      <resultcount>3496</resultcount>
      <pageindex>0</pageindex>
      <metaset>
         <pkvalue>17511</pkvalue>
         <metafield>
            <fieldname>subjectcode</fieldname>
            <fieldtype>string</fieldtype>
            <fieldvalue><![CDATA[ZA5678]]></fieldvalue>
         </metafield>
         <metafield>
         ....
      </metaset>
      <metaset>
         <pkvalue>17551</pkvalue>
         ....
         <score>0</score>
      </metaset>
   </search>
</kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/