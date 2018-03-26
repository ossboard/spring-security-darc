package com.konantech.spring.darcLib.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "kscc_response")
public class DarcGetListSchemaSearchView {

    private ListSchemaSearchView getlistschemasearchview;
    private Error error;

    @Data
    public static class ListSchemaSearchView {
        private List<SchemaSearch> schemasearch;
    }

    @Data
    public static class SchemaSearch {
        private String searchviewname;
        private int searchviewid;
        private String tablename;
        private String primarykeyname;
        private String caption;
        private String scenarioname;
        private List<SearchFieldset> searchfieldset;
    }

    @Data
    public static class SearchFieldset {
        private List<SearchField> searchfield;
    }

    @Data
    public static class SearchField {
        private String dbfieldname;
        private String kqlfieldname;
        private String fieldtype;
        private String ref;
        private String caption;
        private String property;
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
 <?xml version="1.0" encoding="UTF-8"?>
<kscc_response>
   <getlistschemasearchview>
      <schemasearch>
         <searchviewname>sch_dmc_archive_audio</searchviewname>
         <searchviewid>13</searchviewid>
         <tablename>kmu_audio_view</tablename>
         <primarykeyname>audioid</primarykeyname>
         <caption>sch_br_audio</caption>
         <scenarioname>scn_audio</scenarioname>
         <searchfieldset>
            <searchfield>
               <dbfieldname>audioid</dbfieldname>
               <kqlfieldname>audioid</kqlfieldname>
               <fieldtype>int</fieldtype>
               <ref />
               <caption>오디오ID</caption>
               <property />
            </searchfield>
            <searchfield>
               <dbfieldname>audioid</dbfieldname>
                ...
        </searchfieldset>
        <fetchfieldset>
            <fetchfield>
               <dbfieldname>audioid</dbfieldname>
               <kqlfieldname />
               <fieldtype>int</fieldtype>
               <ref />
               <caption>오디오ID</caption>
               <property />
            </fetchfield>
            <fetchfield>
               <dbfieldname>contgroupid</dbfieldname>
               ...
            </fetchfield>
         </fetchfieldset>
      </schemasearch>
   </getlistschemasearchview>
</kscc_response>

 -- error --
<kscc_response><error><errorcode>100005</errorcode><errormsg>system error.</errormsg><trname>logout</trname></error></kscc_response>
*/