<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Manage Events
		</h2>
		<ul>
		    <li>
		         <s:url id="urlallEventsLink" action="ajaxGetUserEventDetails"
							includeParams="all" />
				 <sj:a  id="eventsLink2" href="%{urlallEventsLink}" targets="categoryContent"
							indicator="indicator">Events By Category</sj:a>
		    </li>
		   <li>Export
			    <a href='${pageContext.request.contextPath}/calendar/ajaxICal.do?custid=<s:property value="userCustId"/>' target="_new"> ICal </a>
				|
				<a href='${pageContext.request.contextPath}/calendar/ajaxxmlres.do?custid=<s:property value="userCustId"/>' target="_new"> Xml </a>
				|
				<a href="#"> Json </a>		
			</li>
			<li>
					<a href="${pageContext.request.contextPath}/calendar/adminCalendar.do" id="categories">Calendar View</a>
			</li>
		</ul>
	
	</div>
	<!-- .block_head ends -->
	<!--<div class="block_content" id="categorieEventsList">
		<jsp:include page="viewEvents.jsp" />
	</div>
	
--></div>

<script language="JavaScript" type="text/javascript">

$.subscribe('categoryFormValidation', function(event, data) {
         if ($('#createCategory').valid())
         	 return true;
          else
          	 return false;
   });
function searchCategory(){
        var pars ="scrhTxt=" +document.getElementById("searchTxtId").value;
   		$.ajax( {
			url : "ajaxSearchByCategoryName.do",
			cache : false,
			data: pars,
			success : function(response) {
			     $('#categorieEventsList').html(response);
			}
		});	
   	}	

</script>

