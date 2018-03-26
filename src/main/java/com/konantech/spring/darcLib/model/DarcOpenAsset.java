package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcOpenAsset {

    private OpenAsset openasset;
    private Error error;

    @Data
    public static class OpenAsset {
        private Metaset metaset;
        private int assetcount;
    }

    @Data
    public static class Metaset {
        private List<MetaField> metafield;
        private boolean isdelete;
        private int virtualpkvalue;
        private String virtualpkname;
        private int pkvalue;
        private String pkname;
    }

    @Data
    public static class MetaField {
        private String fieldname;
        private String fieldvalue;
        private String fieldtype;
    }

}

/*
 -- success --
<kscc_response>
   <openasset>
      <metaset>
         <metafield>
            <fieldname>systemconfigid</fieldname>
            <fieldvalue>1</fieldvalue>
            <fieldtype>int</fieldtype>
         </metafield>
         <metafield>
            <fieldname>trfuserid</fieldname>
            ....
         </metafield>
         <isdelete>false</isdelete>
         <virtualpkvalue />
         <virtualpkname />
         <pkvalue>1</pkvalue>
         <pkname>systemconfigid</pkname>
      </metaset>
      <assetcount>1</assetcount>
   </openasset>
</kscc_response>


 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/