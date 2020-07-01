<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%@ page import="Database.DatabaseConnection" %>
<%@ page import="java.sql.ResultSet"%>
    
    <div class="inner-block">
    <div class="portlet-grid-page">  
    	<h2>Details</h2>	
    	 	
<%
	DatabaseConnection db = new DatabaseConnection();
	db.dbconnection();
	String totaluser = "select * from candidate_details";
	ResultSet rs1 = db.getResultSet(totaluser);
	while(rs1.next())
	{%>
		
		<div class="portlet-grid panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">Name : <%=rs1.getString("name")%></h3>
			</div>
			<div class="panel-body">
				<img src="partyimage/<%=rs1.getString("partyimage")%>" alt="party image" height="50" width="50"><br>
				Party Name : <%=rs1.getString("partyname")%><br>
				Manifesto : <%=rs1.getString("manifesto")%>
			</div>
		</div>
		
<%}%>
		

		<div class="clearfix"> </div>
  </div>
</div>

<%@ include file="fooer.jsp" %>