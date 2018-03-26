package com.konantech.spring.darcLib.service;

import _1._0._0._127.darc_wsdl.DarcPortType;
import com.konantech.spring.darcLib.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class DarcPort {

    private DarcPortType darcPortType;

    public DarcPort(DarcPortType darcPortType) {
        this.darcPortType = darcPortType;
    }

    public DarcLogIn logIn(int clientType, String userName, String password) throws Exception {
        String response = darcPortType.logIn(clientType, userName, password);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcLogIn.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcLogIn) unmarshaller.unmarshal(new StringReader(response));
    }

    public String ssoLogIn(int clientType, String loginxml) {
        return darcPortType.ssoLogIn(clientType, loginxml);
    }

    public String logInClientIP(int clientType, String userName, String password, String clientIP) {
        return darcPortType.logInClientIP(clientType, userName, password, clientIP);
    }

    public String logInEncrypt(int clientType, String userName, String password) {
        return darcPortType.logInEncrypt(clientType, userName, password);
    }

    public String ssoLogInClientIP(int clientType, String loginxml, String clientIP) {
        return darcPortType.ssoLogInClientIP(clientType, loginxml, clientIP);
    }

    public String logInEncryptClientIP(int clientType, String userName, String password, String clientIP) {
        return darcPortType.logInEncryptClientIP(clientType, userName, password, clientIP);
    }


    public String getLogInfo(int sessionid, String username) {
        return darcPortType.getLogInfo(sessionid, username);
    }

    public String getMyInfo(int sessionid) {
        return darcPortType.getMyInfo(sessionid);
    }

    public DarcLogOut logOut(int sessionid) throws JAXBException {
        String response = darcPortType.logOut(sessionid);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcLogOut.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcLogOut) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcSpecialUserAuthorityList getSpecialUserAuthorityList(int sessionid, String specialflag) throws JAXBException {
        String response = darcPortType.getSpecialUserAuthorityList(sessionid, specialflag);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcSpecialUserAuthorityList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcSpecialUserAuthorityList) unmarshaller.unmarshal(new StringReader(response));
    }


    public DarcUserAuthorityList getUserAuthorityList(int sessionid) throws JAXBException {
        String response = darcPortType.getUserAuthorityList(sessionid);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcUserAuthorityList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcUserAuthorityList) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcSearch search(int sessionid, String requestxml)  throws JAXBException {
        String response = darcPortType.search(sessionid, requestxml);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcSearch.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcSearch) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcGetListSchemaSearchView getListSchemaSearchView(int sessionid) throws JAXBException {
        String response = darcPortType.getListSchemaSearchView(sessionid);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcGetListSchemaSearchView.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcGetListSchemaSearchView) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcOpenAsset openAsset(int sessionid, String assetviewname, String pkname, String pkvalue, boolean includedelasset)  throws JAXBException {
        String response = darcPortType.openAsset(sessionid, assetviewname, pkname, pkvalue, includedelasset);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcOpenAsset.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcOpenAsset) unmarshaller.unmarshal(new StringReader(response));

    }

    public String openAssetList(int sessionid, String assetviewname, String pkname, String pkvaluelist, boolean includedelasset) {
        return darcPortType.openAssetList(sessionid, assetviewname, pkname, pkvaluelist, includedelasset);
    }

    public DarcOpenWithFK openAssetWithFK(int sessionid, String assetviewname, String fkname, int fkvalue, boolean includedelasset)  throws JAXBException {
        String response = darcPortType.openAssetWithFK(sessionid, assetviewname, fkname, fkvalue, includedelasset);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcOpenWithFK.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcOpenWithFK) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcOpenTreeRootNode openTreeRootNode(int sessionid, String treename) throws JAXBException {
        String response = darcPortType.openTreeRootNode(sessionid, treename);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcOpenTreeRootNode.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcOpenTreeRootNode) unmarshaller.unmarshal(new StringReader(response));
    }

    public DarcOpenTreeChildNode openTreeChildNode(int sessionid, String treename, String nodename)  throws JAXBException {
        String response = darcPortType.openTreeChildNode(sessionid, treename, nodename);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcOpenTreeChildNode.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcOpenTreeChildNode) unmarshaller.unmarshal(new StringReader(response));
    }

    public String createAssetWithFK(int sessionid, String assetviewname, String fkname, int fkvalue, String metasetxml) throws JAXBException {
        return darcPortType.createAssetWithFK(sessionid, assetviewname, fkname, fkvalue, metasetxml);
    }

    public String createAsset(int sessionid, String assetviewname, String metasetxml) throws JAXBException {
        return darcPortType.createAsset(sessionid, assetviewname, metasetxml);
    }

    public String darcExCall(int sessionid, String trname, String requestxml, int poolindex, int timeout) {
        return darcPortType.darcExCall(sessionid, trname, requestxml, poolindex, timeout);
    }

    public DarcDropAsset dropAsset(int sessionid, String assetviewname, String pkname, String pkvalue, boolean harddrop) throws JAXBException {
        String response = darcPortType.dropAsset(sessionid, assetviewname, pkname, pkvalue, harddrop);
        JAXBContext jaxbContext = JAXBContext.newInstance(DarcDropAsset.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (DarcDropAsset) unmarshaller.unmarshal(new StringReader(response));
    }

    public String dropAssetWithFK(int sessionid, String assetviewname, String fkname, String fkvalue, boolean harddrop) {
        return darcPortType.dropAssetWithFK(sessionid, assetviewname, fkname, fkvalue, harddrop);
    }


    public String runWorkflowAbstract(int sessionid, String abstractworkflowname, String assetviewname, String pkvalue, String usermsg) {
        return darcPortType.runWorkflowAbstract(sessionid, abstractworkflowname, assetviewname, pkvalue, usermsg);
    }

    public String forceUpdateAsset(int sessionid, String assetviewname, String pkname, String pkvalue, String metasetxml) {
        return darcPortType.forceUpdateAsset(sessionid, assetviewname, pkname, pkvalue, metasetxml);
    }

    public String openUser(int sessionid, String username) {
        return darcPortType.openUser(sessionid, username);
    }

    public String commitUser(int sessionid, String username, String userxml, String rolexml, String groupxml, String metasetxml) {
        return darcPortType.commitUser(sessionid, username, userxml, rolexml, groupxml, metasetxml);
    }

    public String checkPassword(int sessionid, String username, String pssword) {
        return darcPortType.checkPassword(sessionid, username, pssword);
    }

    public String isValidSession(int sessionid) {
        return darcPortType.isValidSession(sessionid);
    }













    public String addRelUserGroup(int sessionid, String username, String groupname, int exintvalue, String exstrvalue, boolean isheadgroup) {
        return darcPortType.addRelUserGroup(sessionid, username, groupname, exintvalue, exstrvalue, isheadgroup);
    }

    public String linkAssetByUserNode(int sessionid, String baseassetviewname, int basepkvalue, String targetusernodename, int subtype, boolean linktable) {
        return darcPortType.linkAssetByUserNode(sessionid, baseassetviewname, basepkvalue, targetusernodename, subtype, linktable);
    }

    public String rollBackAsset(int sessionid, String assetviewname, String pkname, String pkvalue) {
        return darcPortType.rollBackAsset(sessionid, assetviewname, pkname, pkvalue);
    }

    public String unLinkAssetByUserNode(int sessionid, String baseassetviewname, int basepkvalue, String targetusernodename, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByUserNode(sessionid, baseassetviewname, basepkvalue, targetusernodename, subtype, linktable);
    }

    public String unLinkAssetByAsset(int sessionid, String baseassetviewname, int basepkvalue, String targetassetviewname, int targetpkvalue, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByAsset(sessionid, baseassetviewname, basepkvalue, targetassetviewname, targetpkvalue, subtype, linktable);
    }


    public String getSpecialGroupAuthorityList(int sessionid, String specialflag) {
        return darcPortType.getSpecialGroupAuthorityList(sessionid, specialflag);
    }


    public String getListRole(int sessionid) {
        return darcPortType.getListRole(sessionid);
    }

    public String openTreeNode(int sessionid, String treename, String nodename) {
        return darcPortType.openTreeNode(sessionid, treename, nodename);
    }

    public String isRunningWorkflow(int sessionid, String workflowname, String assetviewname, String pkname, String pkvalue) {
        return darcPortType.isRunningWorkflow(sessionid, workflowname, assetviewname, pkname, pkvalue);
    }

    public String openTree(int sessionid, String treename) {
        return darcPortType.openTree(sessionid, treename);
    }

    public String checkOutRole(int sessionid, String rolename) {
        return darcPortType.checkOutRole(sessionid, rolename);
    }

    public String delRelUserGroup(int sessionid, String username, String groupname) {
        return darcPortType.delRelUserGroup(sessionid, username, groupname);
    }

    public String commitRole(int sessionid, String rolename, String caption, String authorityxml) {
        return darcPortType.commitRole(sessionid, rolename, caption, authorityxml);
    }

    public String openTreeChildNodeLinkedByUser(int sessionid, String treename, String nodename, String username, int subtype) {
        return darcPortType.openTreeChildNodeLinkedByUser(sessionid, treename, nodename, username, subtype);
    }

    public String isValidAsset(int sessionid, String assetviewname, String pkname, String pkvalue, boolean includedelasset) {
        return darcPortType.isValidAsset(sessionid, assetviewname, pkname, pkvalue, includedelasset);
    }





    public String checkOutGroupAll(int sessionid) {
        return darcPortType.checkOutGroupAll(sessionid);
    }




    public String createRole(int sessionid, String rolename, String caption, String authorityxml) {
        return darcPortType.createRole(sessionid, rolename, caption, authorityxml);
    }


    public String openRootAuthority(int sessionid) {
        return darcPortType.openRootAuthority(sessionid);
    }


    public String linkAssetByAsset(int sessionid, String baseassetviewname, int basepkvalue, String targetassetviewname, int targetpkvalue, int subtype, boolean linktable) {
        return darcPortType.linkAssetByAsset(sessionid, baseassetviewname, basepkvalue, targetassetviewname, targetpkvalue, subtype, linktable);
    }


    public String commitHierarchyAsset(int sessionid, String hassetviewname, String pkvalue, String hmetasetxml) {
        return darcPortType.commitHierarchyAsset(sessionid, hassetviewname, pkvalue, hmetasetxml);
    }


    public String unLinkAssetByGroup(int sessionid, String baseassetviewname, int basepkvalue, String targetgroupname, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByGroup(sessionid, baseassetviewname, basepkvalue, targetgroupname, subtype, linktable);
    }


    public String hasAuthorityOfRole(int sessionid, String rolename, String authorityname) {
        return darcPortType.hasAuthorityOfRole(sessionid, rolename, authorityname);
    }


    public String disconnect(int sessionid) {
        return darcPortType.disconnect(sessionid);
    }


    public String commitAuthority(int sessionid, String authorityname, String caption, String specialflag) {
        return darcPortType.commitAuthority(sessionid, authorityname, caption, specialflag);
    }


    public String linkAssetByAuthority(int sessionid, String baseassetviewname, int basepkvalue, String targetathorityname, int subtype, boolean linktable) {
        return darcPortType.linkAssetByAuthority(sessionid, baseassetviewname, basepkvalue, targetathorityname, subtype, linktable);
    }


    public String rollbackRole(int sessionid, String rolename) {
        return darcPortType.rollbackRole(sessionid, rolename);
    }


    public String openCode(int sessionid, String codename) {
        return darcPortType.openCode(sessionid, codename);
    }


    public String getListTree(int sessionid) {
        return darcPortType.getListTree(sessionid);
    }


    public String linkAssetByTreeNode(int sessionid, String baseassetviewname, int basepkvalue, String targettreenodename, int subtype, boolean linktable) {
        return darcPortType.linkAssetByTreeNode(sessionid, baseassetviewname, basepkvalue, targettreenodename, subtype, linktable);
    }


    public String getTreeNodePath(int sessionid, String treename, String nodename, String delimiter) {
        return darcPortType.getTreeNodePath(sessionid, treename, nodename, delimiter);
    }


    public String openRole(int sessionid, String rolename) {
        return darcPortType.openRole(sessionid, rolename);
    }


    public String unlockGroup(int sessionid) {
        return darcPortType.unlockGroup(sessionid);
    }


    public String moveGroup(int sessionid, String groupname, String parentgroupname) {
        return darcPortType.moveGroup(sessionid, groupname, parentgroupname);
    }


    public String unlockAuthority(int sessionid) {
        return darcPortType.unlockAuthority(sessionid);
    }






    public String delRelGroupRole(int sessionid, String groupname, String rolename) {
        return darcPortType.delRelGroupRole(sessionid, groupname, rolename);
    }


    public String linkAssetByGroup(int sessionid, String baseassetviewname, int basepkvalue, String targetgroupname, int subtype, boolean linktable) {
        return darcPortType.linkAssetByGroup(sessionid, baseassetviewname, basepkvalue, targetgroupname, subtype, linktable);
    }


    public String rollbackUser(int sessionid, String username) {
        return darcPortType.rollbackUser(sessionid, username);
    }


    public String unlockTree(int sessionid, String treename) {
        return darcPortType.unlockTree(sessionid, treename);
    }


    public String addRelRoleAuthority(int sessionid, String rolename, String authorityname, int exintvalue, String exstrvalue) {
        return darcPortType.addRelRoleAuthority(sessionid, rolename, authorityname, exintvalue, exstrvalue);
    }


    public String createAuthority(int sessionid, String authorityname, String parentauthorityname, String caption, String specialflag) {
        return darcPortType.createAuthority(sessionid, authorityname, parentauthorityname, caption, specialflag);
    }

    public String getListRunningWorkflow(int sessionid) {
        return darcPortType.getListRunningWorkflow(sessionid);
    }


    public String addRelGroupRole(int sessionid, String groupname, String rolename, int exintvalue, String exstrvalue, boolean isheadrole) {
        return darcPortType.addRelGroupRole(sessionid, groupname, rolename, exintvalue, exstrvalue, isheadrole);
    }


    public String addRelUserRole(int sessionid, String username, String rolename, int exintvalue, String exstrvalue, boolean isheadrole) {
        return darcPortType.addRelUserRole(sessionid, username, rolename, exintvalue, exstrvalue, isheadrole);
    }


    public String checkOutGroup(int sessionid, String groupname) {
        return darcPortType.checkOutGroup(sessionid, groupname);
    }


    public String openChildGroup(int sessionid, String groupname) {
        return darcPortType.openChildGroup(sessionid, groupname);
    }


    public String openAuthority(int sessionid, String authorityname) {
        return darcPortType.openAuthority(sessionid, authorityname);
    }


    public String dropTreeNode(int sessionid, String treename, String nodename, boolean forcedrop, String defaultnodename) {
        return darcPortType.dropTreeNode(sessionid, treename, nodename, forcedrop, defaultnodename);
    }


    public String getListUser(int sessionid) {
        return darcPortType.getListUser(sessionid);
    }




    public String openHierarchyAsset(int sessionid, String hassetviewname, String pkvalue) {
        return darcPortType.openHierarchyAsset(sessionid, hassetviewname, pkvalue);
    }








    public String commitCode(int sessionid, String codename, String codexml) {
        return darcPortType.commitCode(sessionid, codename, codexml);
    }


    public String getLinkAssetByAsset(int sessionid, String baseassetviewname, int basepkvalue, String targetassetviewname, int subtype, boolean linktable) {
        return darcPortType.getLinkAssetByAsset(sessionid, baseassetviewname, basepkvalue, targetassetviewname, subtype, linktable);
    }


    public String getWorkflowStatus(int sessionid, int workflowid) {
        return darcPortType.getWorkflowStatus(sessionid, workflowid);
    }




    public String rollBackGroup(int sessionid, String groupname) {
        return darcPortType.rollBackGroup(sessionid, groupname);
    }


    public String checkOutHierarchyAssetForce(int sessionid, String hassetviewname, String pkvalue, boolean forcecheckout) {
        return darcPortType.checkOutHierarchyAssetForce(sessionid, hassetviewname, pkvalue, forcecheckout);
    }



    public String hasAuthorityOfGroup(int sessionid, String groupname, String authorityname) {
        return darcPortType.hasAuthorityOfGroup(sessionid, groupname, authorityname);
    }


    public String getListUserOfGroup(int sessionid, String groupname, boolean includechildgroup) {
        return darcPortType.getListUserOfGroup(sessionid, groupname, includechildgroup);
    }


    public String checkOutTreeNode(int sessionid, String treename, String nodename) {
        return darcPortType.checkOutTreeNode(sessionid, treename, nodename);
    }


    public String openGroup(int sessionid, String groupname) {
        return darcPortType.openGroup(sessionid, groupname);
    }


    public String checkOutCode(int sessionid, String codename) {
        return darcPortType.checkOutCode(sessionid, codename);
    }




    public String existUser(int sessionid, String username) {
        return darcPortType.existUser(sessionid, username);
    }


    public String getListCode(int sessionid) {
        return darcPortType.getListCode(sessionid);
    }


    public String delRelRoleAuthority(int sessionid, String rolename, String authorityname) {
        return darcPortType.delRelRoleAuthority(sessionid, rolename, authorityname);
    }




    public String hasAuthorityOfUser(int sessionid, String username, String authorityname) {
        return darcPortType.hasAuthorityOfUser(sessionid, username, authorityname);
    }


    public String commitGroupWithParent(int sessionid, String groupname, String caption, String rolexml, String parentgroupname, String metasetxml) {
        return darcPortType.commitGroupWithParent(sessionid, groupname, caption, rolexml, parentgroupname, metasetxml);
    }


    public String dropAuthority(int sessionid, String authorityname, boolean forcedrop, String defaultauthorityname) {
        return darcPortType.dropAuthority(sessionid, authorityname, forcedrop, defaultauthorityname);
    }


    public String dropUser(int sessionid, String username, boolean forcedrop, String defaultusername) {
        return darcPortType.dropUser(sessionid, username, forcedrop, defaultusername);
    }


    public String createGroup(int sessionid, String groupname, String parentgroupname, String caption, String rolexml, String metasetxml) {
        return darcPortType.createGroup(sessionid, groupname, parentgroupname, caption, rolexml, metasetxml);
    }


    public String checkOutAsset(int sessionid, String assetviewname, String pkname, String pkvalue) {
        return darcPortType.checkOutAsset(sessionid, assetviewname, pkname, pkvalue);
    }


    public String createUser(int sessionid, String username, String userxml, String rolexml, String groupxml, String metasetxml) {
        return darcPortType.createUser(sessionid, username, userxml, rolexml, groupxml, metasetxml);
    }


    public String moveAuthority(int sessionid, String authorityname, String parentauthorityname) {
        return darcPortType.moveAuthority(sessionid, authorityname, parentauthorityname);
    }


    public String runWorkflow(int sessionid, String workflowname, int subtype, int priority, String assetviewname, String pkname, String pkvalue, String usermsg, String notifyurl) {
        return darcPortType.runWorkflow(sessionid, workflowname, subtype, priority, assetviewname, pkname, pkvalue, usermsg, notifyurl);
    }






    public String getListSchemaAssetView(int sessionid) {
        return darcPortType.getListSchemaAssetView(sessionid);
    }

    public String commitGroup(int sessionid, String groupname, String caption, String rolexml, String metasetxml) {
        return darcPortType.commitGroup(sessionid, groupname, caption, rolexml, metasetxml);
    }


    public String rollBackHierarchyAsset(int sessionid, String hassetviewname, String pkvalue) {
        return darcPortType.rollBackHierarchyAsset(sessionid, hassetviewname, pkvalue);
    }


    public String existGroup(int sessionid, String groupname) {
        return darcPortType.existGroup(sessionid, groupname);
    }


    public String getListSchemaCompserver(int sessionid) {
        return darcPortType.getListSchemaCompserver(sessionid);
    }


    public String createFileID(int sessionid, String assetviewname, String pkname, String pkvalue, String fieldname) {
        return darcPortType.createFileID(sessionid, assetviewname, pkname, pkvalue, fieldname);
    }


    public String unLinkAssetByUser(int sessionid, String baseassetviewname, int basepkvalue, String targetusername, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByUser(sessionid, baseassetviewname, basepkvalue, targetusername, subtype, linktable);
    }


    public String existAuthority(int sessionid, String authorityname) {
        return darcPortType.existAuthority(sessionid, authorityname);
    }



    public String checkPasswordEncrypt(int sessionid, String username, String pssword) {
        return darcPortType.checkPasswordEncrypt(sessionid, username, pssword);
    }


    public String lockAuthority(int sessionid) {
        return darcPortType.lockAuthority(sessionid);
    }


    public String openChildAuthority(int sessionid, String authorityname) {
        return darcPortType.openChildAuthority(sessionid, authorityname);
    }


    public String linkAssetByUser(int sessionid, String baseassetviewname, int basepkvalue, String targetusername, int subtype, boolean linktable) {
        return darcPortType.linkAssetByUser(sessionid, baseassetviewname, basepkvalue, targetusername, subtype, linktable);
    }


    public String checkOutAuthority(int sessionid, String authorityname) {
        return darcPortType.checkOutAuthority(sessionid, authorityname);
    }


    public String getUniKey(int sessionid, String unikeytype) {
        return darcPortType.getUniKey(sessionid, unikeytype);
    }


    public String lockGroup(int sessionid) {
        return darcPortType.lockGroup(sessionid);
    }


    public String getListAttFile(int sessionid, String fileids) {
        return darcPortType.getListAttFile(sessionid, fileids);
    }


    public String commitTreeNode(int sessionid, String treename, String nodename, String caption, int spectialflag, int exintvalue, String exstrvalue, String metasetxml) {
        return darcPortType.commitTreeNode(sessionid, treename, nodename, caption, spectialflag, exintvalue, exstrvalue, metasetxml);
    }



    public String getListSchemaClientType(int sessionid) {
        return darcPortType.getListSchemaClientType(sessionid);
    }


    public String cancelWorkflow(int sessionid, int workflowid) {
        return darcPortType.cancelWorkflow(sessionid, workflowid);
    }


    public String countSubAsset(int sessionid, String assetviewname, String fkname, int fkvalue, boolean includedelasset) {
        return darcPortType.countSubAsset(sessionid, assetviewname, fkname, fkvalue, includedelasset);
    }


    public String moveTreeNode(int sessionid, String treename, String nodename, String parentnodename) {
        return darcPortType.moveTreeNode(sessionid, treename, nodename, parentnodename);
    }


    public String getSchemaSearchView(int sessionid, String searchviewname) {
        return darcPortType.getSchemaSearchView(sessionid, searchviewname);
    }


    public String commitAsset(int sessionid, String assetviewname, String pkname, String pkvalue, String metasetxml) {
        return darcPortType.commitAsset(sessionid, assetviewname, pkname, pkvalue, metasetxml);
    }


    public String unLinkAssetByAuthority(int sessionid, String baseassetviewname, int basepkvalue, String targetathorityname, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByAuthority(sessionid, baseassetviewname, basepkvalue, targetathorityname, subtype, linktable);
    }


    public String dropRole(int sessionid, String rolename, boolean forcedrop, String defaultrolename) {
        return darcPortType.dropRole(sessionid, rolename, forcedrop, defaultrolename);
    }


    public String checkOutHierarchyAsset(int sessionid, String hassetviewname, String pkvalue) {
        return darcPortType.checkOutHierarchyAsset(sessionid, hassetviewname, pkvalue);
    }


    public String rollbackTreeNode(int sessionid, String treename, String nodename) {
        return darcPortType.rollbackTreeNode(sessionid, treename, nodename);
    }


    public String delRelUserRole(int sessionid, String username, String rolename) {
        return darcPortType.delRelUserRole(sessionid, username, rolename);
    }


    public String dropGroup(int sessionid, String groupname, boolean forcedrop, String defaultgroupname) {
        return darcPortType.dropGroup(sessionid, groupname, forcedrop, defaultgroupname);
    }


    public String unLinkAssetByTreeNode(int sessionid, String baseassetviewname, int basepkvalue, String targettreenodename, int subtype, boolean linktable) {
        return darcPortType.unLinkAssetByTreeNode(sessionid, baseassetviewname, basepkvalue, targettreenodename, subtype, linktable);
    }


    public String reRunWorkflow(int sessionid, int workflowid, int orderindex) {
        return darcPortType.reRunWorkflow(sessionid, workflowid, orderindex);
    }


    public String checkOutAssetForce(int sessionid, String assetviewname, String pkname, String pkvalue, boolean forcecheckout) {
        return darcPortType.checkOutAssetForce(sessionid, assetviewname, pkname, pkvalue, forcecheckout);
    }


    public String getSchemaAssetView(int sessionid, String assetviewname) {
        return darcPortType.getSchemaAssetView(sessionid, assetviewname);
    }


    public String connect(int clienttype, String username, String password) {
        return darcPortType.connect(clienttype, username, password);
    }




    public String lockTree(int sessionid, String treename) {
        return darcPortType.lockTree(sessionid, treename);
    }


    public String createTreeNode(int sessionid, String treename, String nodename, String parentnodename, String caption, int spectialflag, int exintvalue, String exstrvalue, String metasetxml) {
        return darcPortType.createTreeNode(sessionid, treename, nodename, parentnodename, caption, spectialflag, exintvalue, exstrvalue, metasetxml);
    }


    public String getListAuthority(int sessionid) {
        return darcPortType.getListAuthority(sessionid);
    }


    public String registCallbackURL(int sessionid, boolean opennotify, String callbackurl) {
        return darcPortType.registCallbackURL(sessionid, opennotify, callbackurl);
    }


    public String getListSchemaWorkflow(int sessionid) {
        return darcPortType.getListSchemaWorkflow(sessionid);
    }


    public String reserveWorkflow(int sessionid, String workflowname, int subtype, int priority, String assetviewname, String pkname, String pkvalue, String usermsg, String notifyurl, long reservetime) {
        return darcPortType.reserveWorkflow(sessionid, workflowname, subtype, priority, assetviewname, pkname, pkvalue, usermsg, notifyurl, reservetime);
    }


    public String rollBackCode(int sessionid, String codename) {
        return darcPortType.rollBackCode(sessionid, codename);
    }


    public String rollBackAuthority(int sessionid, String authorityname) {
        return darcPortType.rollBackAuthority(sessionid, authorityname);
    }


    public Integer pingTest() {
        return darcPortType.pingTest();
    }




    public String commitGroupAll(int sessionid) {
        return darcPortType.commitGroupAll(sessionid);
    }


    public String openRootGroup(int sessionid) {
        return darcPortType.openRootGroup(sessionid);
    }





    public String getListGroup(int sessionid) {
        return darcPortType.getListGroup(sessionid);
    }





    public String checkOutUser(int sessionid, String username) {
        return darcPortType.checkOutUser(sessionid, username);
    }


}
