<%@page import="blockchainevoting.GettingWInner"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Database.DatabaseConnection" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Map"%>
<%
	if(session.getAttribute("username")== null)
	{
%>
	<script type="text/javascript">
		alert("Session Expire Please Login");
		location="index.jsp";
	</script>
<%} %>
<!DOCTYPE HTML>
<html>
<head>
<title>EVoting System</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shoppy Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!--js-->
<script src="js/jquery-2.1.1.min.js"></script> 
<!--icons-css-->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!--Google Fonts-->
<link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
<!--static chart-->
<script src="js/Chart.min.js"></script>
<!--//charts-->
<!-- geo chart -->
    <script src="//cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <script>window.modernizr || document.write('<script src="lib/modernizr/modernizr-custom.js"><\/script>')</script>
    <!--<script src="lib/html5shiv/html5shiv.js"></script>-->
     <!-- Chartinator  -->
    <script src="js/chartinator.js" ></script>
    <!-- <script type="text/javascript">
        jQuery(function ($) {

            var chart3 = $('#geoChart').chartinator({
                tableSel: '.geoChart',

                columns: [{role: 'tooltip', type: 'string'}],
         
                colIndexes: [2],
             
                rows: [
                    ['China - 2015'],
                    ['Colombia - 2015'],
                    ['France - 2015'],
                    ['Italy - 2015'],
                    ['Japan - 2015'],
                    ['Kazakhstan - 2015'],
                    ['Mexico - 2015'],
                    ['Poland - 2015'],
                    ['Russia - 2015'],
                    ['Spain - 2015'],
                    ['Tanzania - 2015'],
                    ['Turkey - 2015']],
              
                ignoreCol: [2],
              
                chartType: 'GeoChart',
              
                chartAspectRatio: 1.5,
             
                chartZoom: 1.75,
             
                chartOffset: [-12,0],
             
                chartOptions: {
                  
                    width: null,
                 
                    backgroundColor: '#fff',
                 
                    datalessRegionColor: '#F5F5F5',
               
                    region: 'world',
                  
                    resolution: 'countries',
                 
                    legend: 'none',

                    colorAxis: {
                       
                        colors: ['#679CCA', '#337AB7']
                    },
                    tooltip: {
                     
                        trigger: 'focus',

                        isHtml: true
                    }
                }

               
            });                       
        });
    </script> -->
<!--geo chart-->

<!--skycons-icons-->
<script src="js/skycons.js"></script>
<!--//skycons-icons-->
</head>
<body>	
<div class="page-container">	
   <div class="left-content">
	   <div class="mother-grid-inner">
            <!--header start here-->
				<div class="header-main">
					<div class="header-left">
							<div class="logo-name">
									 <a href="adminHome.html"> <h1>Admin</h1> 
									<!--<img id="logo" src="" alt="Logo"/>--> 
								  </a> 								
							</div>

							<div class="clearfix"> </div>
						 </div>
						 <div class="header-right">
							
							<!--notification menu end -->
							<div class="profile_details">		
								<ul>
									<li class="dropdown profile_details_drop">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<div class="profile_img">	
												<div class="user-name">
													<p><%=session.getAttribute("username") %></p>
													<span>Administrator</span>
													
												</div>
												<i class="fa fa-angle-down lnr"></i>
												<i class="fa fa-angle-up lnr"></i>
												<div class="clearfix"></div>	
											</div>	
										</a>
										<ul class="dropdown-menu drp-mnu">
											<li> <a href="logout.jsp"><i class="fa fa-sign-out"></i> Logout</a> </li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="clearfix"> </div>				
						</div>
				     <div class="clearfix"> </div>	
				</div>
<!--heder end here-->
<!-- script-for sticky-nav -->
		<script>
		$(document).ready(function() {
			 var navoffeset=$(".header-main").offset().top;
			 $(window).scroll(function(){
				var scrollpos=$(window).scrollTop(); 
				if(scrollpos >=navoffeset){
					$(".header-main").addClass("fixed");
				}else{
					$(".header-main").removeClass("fixed");
				}
			 });
			 
		});
		</script>
		<!-- /script-for sticky-nav -->
<!--inner block start here-->
<div class="inner-block">

<%
int i=1; double total=0,vote=0;
double avg=0;
int avg1 = 0;
DatabaseConnection db = new DatabaseConnection();
db.dbconnection();
String totaluser = "select count(islogin) as total, sum(islogin) as vote from userentry";
ResultSet rs1 = db.getResultSet(totaluser);
while(rs1.next())
{
	total = rs1.getDouble("total");
	vote = rs1.getDouble("vote");
	avg = (vote/total)*100;
	avg1 = (int)avg;
}
System.out.println(avg1);
 
%>

<!--market updates updates-->
	 <div class="market-updates">
			<div class="col-md-4 market-update-gd">
				<div class="market-update-block clr-block-1">
					<div class="col-md-8 market-update-left">
						<h3><%=total %></h3>
						<h4>Total Voter</h4>
					<!-- 	<p>one vote one person</p> -->
					</div>
					<div class="col-md-4 market-update-right">
						<i class="fa fa-file-text-o"> </i>
					</div>
				  <div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-4 market-update-gd">
				<div class="market-update-block clr-block-2">
				 <div class="col-md-8 market-update-left">
					<h3><%=vote %></h3>
					<h4>voted people</h4>
					<!-- <p>one vote one person</p> -->
				  </div>
					<div class="col-md-4 market-update-right">
						<i class="fa fa-eye"> </i>
					</div>
				  <div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-4 market-update-gd">
				<div class="market-update-block clr-block-3">
					<div class="col-md-8 market-update-left">
						<h3><%=avg1 %>%</h3>
						<h4>Votes in percent</h4>
						<!-- <p>one vote one person</p> -->
					</div>
					<div class="col-md-4 market-update-right">
						<i class="fa fa-envelope-o"> </i>
					</div>
				  <div class="clearfix"> </div>
				</div>
			</div>
			
		   <div class="clearfix"> </div>
		</div>
<!--market updates end here-->
<!--mainpage chit-chating-->


<div class="chit-chat-layer1">
	<div style="margin-right:19px">
               <div class="work-progres">
                            <div class="chit-chat-heading">
                                  Party votes
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover">
                                  <thead>
                                    <tr>
                                      <th>Party name</th>
                                      <th>Vote</th>
                                     
                                      
                                  </tr>
                              </thead>
                              <tbody>
             <% 
             int bsp_votes=0, shivsena_votes=0, congress_votes=0,bjp_votes=0;
             Map<String, Integer> hm1 = GettingWInner.returnResult();
				for (Map.Entry<String, Integer> en : hm1.entrySet()) { 
					
					String partysplit=en.getKey();//.split(".");
					int value=en.getValue();
					
		            System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue()); 
		            
		            if(en.getKey().equals("bsp.jpg")){bsp_votes = en.getValue();}
		            if(en.getKey().equals("shivsena.jpg")){shivsena_votes = en.getValue();}
		            if(en.getKey().equals("congress.jpg")){congress_votes = en.getValue();}
		            if(en.getKey().equals("bjp.jpg")){bjp_votes = en.getValue();} 
		            
             %>  
                              <tr>
                                  <td><%=partysplit %></td>
                                  <td><%=value %></td>
				<%} %> 
                          </tbody>
                      </table>
                  </div>
             </div>
      </div>
      
     <div class="clearfix"> </div>
</div>


<div class="chit-chat-layer1">
	<div style="margin-right:19px">
               <div class="work-progres">
                            <div class="chit-chat-heading">
                                  Voter List
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover">
                                  <thead>
                                    <tr>
                                      <th>#</th>
                                      <th>username</th>
                                      <th>password</th>                                   
                                      <th>Email</th>
                                      <th>mobile</th>
                                      <th>uid</th>
                                      <th>vote</th>
                                      
                                  </tr>
                              </thead>
                              <tbody>
             <% 
             
             String query = "select * from userentry";
             //System.out.println(query);
             ResultSet rs = db.getResultSet(query);
             while(rs.next())
             {
             %>
                              
                              <tr>
                                  <td><%=i %></td>
                                  <td><%=rs.getString("username") %></td>
                                  <td><%=rs.getString("encpassword") %></td>                                 
								  <td><%=rs.getString("mail") %></td>    
								  <td><%=rs.getString("mobile") %></td>                                 
								  <td><%=rs.getString("uid") %></td> 
				<%if(rs.getInt("islogin") == 0){ %>
                                  <td><span class="label label-danger"><%=rs.getInt("islogin") %></span></td>
				<%}else{ %>
								  <td><span class="label label-success"><%=rs.getInt("islogin") %></span></td>
				<%} %>
                              </tr>
			<%i++;}%>  
                          </tbody>
                      </table>
                  </div>
             </div>
      </div>
      
     <div class="clearfix"> </div>
</div>
<!-- ===================== -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div class="chit-chat-layer1">
<div class="chit-chat-heading">
	Pie Chart
</div>
	<div style="margin-right:19px" align="center">
					<div id="piechart"></div>
					<script type="text/javascript">
						// Load google charts
						google.charts.load('current', {'packages':['corechart']});
						google.charts.setOnLoadCallback(drawChart);
						
						// Draw the chart and set the chart values
						function drawChart() {
						  var data = google.visualization.arrayToDataTable([
						  ['Task', 'Hours per Day'],
						  ['BSP', <%=bsp_votes %>],
						  ['Shivsena', <%=shivsena_votes %>],
						  ['Congress', <%=congress_votes %>],
						  ['BJP', <%=bjp_votes %>],
						]);
						
						  // Optional; add a title and set the width and height of the chart
						  var options = {'title':'Total Votes', 'width':750, 'height':400};
						
						  // Display the chart inside the <div> element with id="piechart"
						  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
						  chart.draw(data, options);
					}
					</script>
					
	            
		
      </div>
     <div class="clearfix"> </div>
</div>

<!-- ======================= -->


</div>
<div class="copyrights">
	 <p>E-voting using Blockchain</p>
</div>	
<!--COPY rights end here-->
</div>
</div>
<!--slider menu-->
    <div class="sidebar-menu">
		  	<div class="logo"> <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a href="#"> <span id="logo" ></span> 
			      <!--<img id="logo" src="" alt="Logo"/>--> 
			  </a> </div>		  
		    <div class="menu">
		      <ul id="menu" >
		        <li id="menu-home" ><a href="adminHome.jsp"><i class="fa fa-tachometer"></i><span>Dashboard</span></a></li>
		        <li><a href="#"><i class="fa fa-cog"></i><span>System</span><span class="fa fa-angle-right" style="float: right"></span></a>
		        	 <ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="AddCandidate.jsp">Add Candidate</a></li>
			            <li id="menu-academico-boletim" ><a href="CandidateDetails.jsp">Candidate Details</a></li>
		             </ul>
		        </li>
		        <!-- <li><a href="#"><i class="fa fa-cogs"></i><span>Components</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul>
		            <li><a href="grids.html">Grids</a></li>
		            <li><a href="portlet.html">Portlets</a></li>		            
		          </ul>
		        </li>
		        <li id="menu-comunicacao" ><a href="#"><i class="fa fa-book nav_icon"></i><span>Element</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-comunicacao-sub" >
		            <li id="menu-mensagens" style="width: 120px" ><a href="buttons.html">Buttons</a>		              
		            </li>
		            <li id="menu-arquivos" ><a href="typography.html">Typography</a></li>
		            <li id="menu-arquivos" ><a href="icons.html">Icons</a></li>
		          </ul>
		        </li>
		          <li><a href="maps.html"><i class="fa fa-map-marker"></i><span>Maps</span></a></li>
		        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text"></i><span>Pages</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-academico-sub" >
		          	 <li id="menu-academico-boletim" ><a href="login.html">Login</a></li>
		            <li id="menu-academico-avaliacoes" ><a href="signup.html">Sign Up</a></li>		           
		          </ul>
		        </li>
		        
		        <li><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span></a></li>
		        <li><a href="#"><i class="fa fa-envelope"></i><span>Mailbox</span><span class="fa fa-angle-right" style="float: right"></span></a>
		        	 <ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="inbox.html">Inbox</a></li>
			            <li id="menu-academico-boletim" ><a href="inbox-details.html">Compose email</a></li>
		             </ul>
		        </li>
		         <li><a href="#"><i class="fa fa-cog"></i><span>System</span><span class="fa fa-angle-right" style="float: right"></span></a>
		         	 <ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="404.html">404</a></li>
			            <li id="menu-academico-boletim" ><a href="blank.html">Blank</a></li>
		             </ul>
		         </li>
		         <li><a href="#"><i class="fa fa-shopping-cart"></i><span>E-Commerce</span><span class="fa fa-angle-right" style="float: right"></span></a>
		         	<ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="product.html">Product</a></li>
			            <li id="menu-academico-boletim" ><a href="price.html">Price</a></li>
		             </ul>
		         </li> -->
		      </ul>
		    </div>
	 </div>
	<div class="clearfix"> </div>
</div>
<!--slide bar menu end here-->
<script>
var toggle = true;
            
$(".sidebar-icon").click(function() {                
  if (toggle)
  {
    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
    $("#menu span").css({"position":"absolute"});
  }
  else
  {
    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
    setTimeout(function() {
      $("#menu span").css({"position":"relative"});
    }, 400);
  }               
                toggle = !toggle;
            });
</script>
<!--scrolling js-->
		<script src="js/jquery.nicescroll.js"></script>
		<script src="js/scripts.js"></script>
		<!--//scrolling js-->
<script src="js/bootstrap.js"> </script>
<!-- mother grid end here-->
</body>
</html>                     