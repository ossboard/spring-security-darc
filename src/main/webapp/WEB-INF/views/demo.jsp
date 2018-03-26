<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./includes/taglib.jsp" %>

DEMO

<br>

<sec:authorize access="hasRole('ROLE_USER')">ROLE_USER<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_archive_download_request_proxy')">auth_prime_archive_download_request_proxy<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_archive_download_proxy')">auth_prime_archive_download_proxy<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_archive_download_request_proxy_noprime')">auth_prime_archive_download_request_proxy_noprime<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_archive_download_picture')">auth_prime_archive_download_picture<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_archive_download_paper')">auth_prime_archive_download_paper<br/></sec:authorize>
<sec:authorize access="hasAuthority('auth_prime_workspace')">auth_prime_workspace<br/></sec:authorize>
