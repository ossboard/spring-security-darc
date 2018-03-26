package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcOpenTreeRootNode {

    private OpenTreeRootNode opentreerootnode;
    private Error error;

    @Data
    public static class OpenTreeRootNode {
        private TreeNode treenode;
        private Metaset metaset;
        private int assetcount;
    }

    @Data
    public static class TreeNode {
        private String nodename;
        private String parentnodename;
        private String order;
        private String exstrvalue;
        private String caption;
        private String specialflag;
        private String dbvalue;
        private String childcount;
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
   <opentreerootnode>
      <treenode>
         <nodename>node_1</nodename>
         <parentnodename />
         <order>1</order>
         <exintvalue>0</exintvalue>
         <exstrvalue />
         <caption>전체</caption>
         <specialflag>0</specialflag>
         <dbvalue>4294967297</dbvalue>
         <childcount>24</childcount>
      </treenode>
      <metaset>
         <metafield>
            <fieldname>seqid</fieldname>
            <fieldvalue>1</fieldvalue>
            <fieldtype>int</fieldtype>
         </metafield>
         <metafield>
            ....
         </metafield>
         <isdelete>false</isdelete>
         <virtualpkvalue />
         <virtualpkname />
         <pkvalue>1</pkvalue>
         <pkname>seqid</pkname>
      </metaset>
      <assetcount>1</assetcount>
   </opentreerootnode>
</kscc_response>


 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/