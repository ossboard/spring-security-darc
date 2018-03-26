package com.konantech.spring.service;

import _1._0._0._127.darc_wsdl.DarcPortType;
import com.konantech.spring.CommonTests;
import com.konantech.spring.config.MamProfiles;
import com.konantech.spring.darcLib.SchemaViewManager;
import com.konantech.spring.darcLib.builder.RequestBuilder;
import com.konantech.spring.darcLib.model.*;
import com.konantech.spring.darcLib.query.Query;
import com.konantech.spring.darcLib.query.SearchControls;
import com.konantech.spring.darcLib.service.DarcPort;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Properties;

public class DarcSoapTest extends CommonTests{

    @Autowired
    private MamProfiles mamProfiles;

    @Autowired
    private DarcPort darcPort;

    @Autowired
    private DarcPortType darcPortType;

    private int sessionid = 822;
    private String specialflag = "oun";

    private String userName = "konan";
    private String password = "konan1";

    @Value("${darc.clientType}")
    private int clientType;

    @Test
    public void login1() throws Exception {
        String response = darcPortType.logIn(clientType, userName, password );
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcLogIn.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        DarcLogIn logIn = (DarcLogIn) unmarshaller.unmarshal(new StringReader(response));
        printObj(logIn);
    }

    @Test
    public void login2() throws Exception {
        printObj(darcPort.logIn(clientType, userName, password));
        printObj(darcPort.logIn(clientType, userName, "fail"));
    }

    @Test
    public void logout() throws Exception {
        DarcLogOut darcLogOut = darcPort.logOut(12345);
        printObj(darcLogOut);
    }

    @Test
    public void list() {
        String result = darcPort.getListGroup(sessionid);
        printObj(result);
    }

    /* specialflag 는 앞으로 사용금지 */
    @Test
    public void role1() throws Exception {
        DarcSpecialUserAuthorityList specialUserAuthorityList = darcPort.getSpecialUserAuthorityList(sessionid, specialflag);
        printList(specialUserAuthorityList.getGetspecialuserauthoritylist().getAuthority());
        System.out.println(darcPortType.getSpecialUserAuthorityList(00000, specialflag));
        System.out.println(specialUserAuthorityList.getGetspecialuserauthoritylist().getAuthority().size());
    }

    @Test
    public void role2() throws Exception {
        DarcUserAuthorityList userAuthorityList = darcPort.getUserAuthorityList(sessionid);
        printList(userAuthorityList.getGetuserauthoritylist().getAuthority());
        System.out.println(darcPortType.getUserAuthorityList(00000));
        System.out.println(userAuthorityList.getGetuserauthoritylist().getAuthority().size());
    }

    @Test
    public void getListSchemaSearchView() throws Exception {
        DarcGetListSchemaSearchView darcGetListSchemaSearchView = darcPort.getListSchemaSearchView(sessionid);
        printList(darcGetListSchemaSearchView.getGetlistschemasearchview().getSchemasearch());
    }

    @Test
    public void search() throws Exception {

        // aliveonly : delflag가 0인것
        // droponly : delflag가 1인것
        // all : 전체, 디폴트(all 이외 모든 문자)
        Query query = new Query();
        query.setMediaType("weekly");
        query.setSelectMode("aliveonly");

        int offset = 0;
        int limit = 20;
        SearchControls controls = new SearchControls(offset, limit, SearchControls.SUBTREE_SCOPE);
        String requestxml = RequestBuilder.search(query, controls);
        DarcSearch darcSearch = darcPort.search(sessionid, requestxml);
        System.out.println(darcSearch);
    }

    @Test
    public void openAsset() throws Exception {
        String assetViewName = SchemaViewManager.getAssetViewName("sysconfig");
        DarcOpenAsset darcOpenAsset = darcPort.openAsset(sessionid, assetViewName, "", "1", false);
        System.out.println(darcOpenAsset);
    }


    @Test /* subAsset */
    public void openAssetWithFK() throws Exception {
        int parentId = 1;
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        DarcOpenWithFK darcOpenWithFK = darcPort.openAssetWithFK(sessionid, assetViewName, "treenodeid", parentId, false);
        System.out.println(darcOpenWithFK);
    }


    @Test
    public void subAsset() throws Exception {
        int parentId = 1;
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        DarcOpenWithFK darcOpenWithFK = darcPort.openAssetWithFK(sessionid, assetViewName, "treenodeid", parentId, false);
        System.out.println(darcOpenWithFK);
    }

    @Test
    public void openTreeRootNode() throws Exception {
        String treeName = "t_projectgenre";
        DarcOpenTreeRootNode darcOpenTreeRootNode = darcPort.openTreeRootNode(sessionid, treeName);
        System.out.println(darcOpenTreeRootNode);
    }

    @Test
    public void openTreeChildNode() throws Exception {
        String treeName = "t_projectgenre";
        String nodeName = "node_1";
        DarcOpenTreeChildNode darcOpenTreeChildNode = darcPort.openTreeChildNode(sessionid, treeName, nodeName);
        System.out.println(darcOpenTreeChildNode);
    }


    @Test
    public void createAssetWithFK() throws Exception {
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String fkname = "";
        int fkvalue = 0;
        String metasetxml = "";
        System.out.println(darcPort.createAssetWithFK(sessionid, assetViewName, fkname, fkvalue, metasetxml ));
    }

    @Test
    public void createAsset() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("aa","bb");
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String metasetxml = RequestBuilder.asset(properties);
        System.out.println(darcPort.createAsset(sessionid, assetViewName, metasetxml ));
    }

    @Test
    public void updateAsset() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("aa","bb");

        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String pkname = "";
        String pkvalue = "";
        String metasetxml = RequestBuilder.asset(properties);
        System.out.println(darcPort.forceUpdateAsset(sessionid, assetViewName, pkname, pkvalue, metasetxml ));
    }

    @Test
    public void darcExCall() throws Exception {
        String trname = "darcexcall";
        String requestxml = "";
        int poolindex = 0;
        int timeout = 60;
        System.out.println(darcPort.darcExCall(sessionid, trname, requestxml, poolindex, timeout ));
    }

    @Test
    public void dropAsset() throws Exception {
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String pkname = "";
        String pkvalue = "";
        boolean harddrop = false;
        DarcDropAsset darcDropAsset = darcPort.dropAsset(sessionid, assetViewName, pkname, pkvalue, harddrop);
        System.out.println(darcDropAsset);
    }

    @Test
    public void dropAssetWithFK() throws Exception {
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String fkname = "";
        String fkvalue = "";
        boolean harddrop = false;
        System.out.println(darcPort.dropAssetWithFK(sessionid, assetViewName, fkname, fkvalue, harddrop));
    }

    @Test /* startWorkflow */
    public void runWorkflowAbstract() throws Exception {
        String abstractworkflowname = "";
        String assetViewName = SchemaViewManager.getAssetViewName("treenodeex");
        String pkvalue = "";
        String usermsg = "";
        System.out.println(darcPort.runWorkflowAbstract(sessionid, abstractworkflowname, assetViewName, pkvalue, usermsg));
    }

    @Test
    public void openUser() throws Exception {
        String username = "konan";
        System.out.println(darcPort.openUser(sessionid, username));
    }


    @Test
    public void commitUser() throws Exception {
        String username = "konan";
        String userxml = "";
        String rolexml = "";
        String groupxml = "";
        String metasetxml = "";
        System.out.println(darcPort.commitUser(sessionid, username, userxml, rolexml, groupxml, metasetxml));
    }

    @Test
    public void checkPassword() throws Exception {
        String username = "konan";
        String password = "konan1";
        System.out.println(darcPort.checkPassword(sessionid, username, password));
    }

    @Test
    public void isValidSession() throws Exception {
        System.out.println(darcPort.isValidSession(sessionid));
    }

    @Test
    public void test() throws Exception {
        String requestxml = MessageFormat.format(mamProfiles.getMamex().getSsologin(), "a","b","c");
        System.out.println(requestxml);
    }



}