package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcOpenTreeChildNode {

    private OpenTreeChildNode opentreechildnode;
    private Error error;

    @Data
    public static class OpenTreeChildNode {
        private List<TreeNode> treenode;
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

}

/*
 -- success --
<kscc_response>
   <opentreechildnode>
      <treenode>
         <nodename>t_projectgenre_6b2cab403cc30caeb0fe</nodename>
         <parentnodename>node_1</parentnodename>
         <order>2</order>
         <exintvalue>0</exintvalue>
         <exstrvalue>A</exstrvalue>
         <caption>화면조정</caption>
         <specialflag>0</specialflag>
         <dbvalue>4294967299</dbvalue>
         <childcount>0</childcount>
      </treenode>
      <treenode>
         <nodename>t_projectgenre_a49e14f7b81ea07287dd</nodename>
         ....
      </treenode>
   </opentreechildnode>
</kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/