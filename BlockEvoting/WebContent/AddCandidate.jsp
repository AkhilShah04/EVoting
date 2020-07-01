<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<!--inner block start here-->

<div class="inner-block">
	    <div class="inbox">
	    	  <h2>Add Candidate Details</h2>
	    	 	<div class="col-md-8 compose-right">
						<div class="inbox-details-default">
							<div class="inbox-details-heading">
								Party Details
							</div>
							<div class="inbox-details-body">
								<div class="alert alert-info">
									Please fill details
								</div>
								<form class="com-mail" action="AddCandidate" method="post" enctype="multipart/form-data">
									<input type="text"  placeholder="Candidate Name" name="name">
									<input type="text"  placeholder="Party Name" name="partyname">
									<input type="text"  placeholder="Manifesto" name="manifesto">
									<!-- <input type="file"  placeholder="Manifesto" name="manifesto"> -->
									
									<div class="form-group">
										<div class="btn btn-default btn-file">
											<i class="fa fa-paperclip"> </i> Party Image
											<input type="file" name="partyimage">
										</div>
									</div>
									<input type="submit" value="Add Candidate"> 
								</form>
							</div>
						</div>
					</div>
	          <div class="clearfix"> </div>     
	   </div>
</div>

<!--inner block end here-->

<%@ include file="fooer.jsp" %>
     