<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Search Profile Info
				</div>
			</div>
			<div class="portlet-body">
				<div id="staffProfileContectDiv" class="tab-content">
					<jsp:include
					page="/WEB-INF/pages/admin/principal/ajaxPrincipalDetails.jsp" />
				</div>
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Staff Leaves
				</div>
			</div>
			<div class="portlet-body">
				<div id="staffLeaveDiv" class="tab-content">
					<jsp:include page="/WEB-INF/pages/admin/principal/alertsBoard.jsp" />
				</div>
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upcoming Exam Schedules Info
				</div>
			</div>
			<div class="portlet-body">
				<div id="upcomingExamSchedulesDiv" class="tab-content">
					<jsp:include page="/WEB-INF/pages/exam/ajaxManageUpcomingExamSchedules.jsp" />
				</div>
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Financial/Payment 
				</div>
			</div>
			<div class="portlet-body">
				<div id="stafffinancialDiv" class="tab-content">
					<jsp:include
					page="/WEB-INF/pages/admin/principal/financialAndExamSchedules.jsp" />
				</div>
			</div>
		</div>

		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Attendance Summary
				</div>
			</div>
			<div class="portlet-body">
				<div id="upcomingExamSchedulesDiv" class="tab-content">
					<jsp:include page="/WEB-INF/pages/admin/principal/ajaxStudentAndTeachersAttendance.jsp" />
				</div>
			</div>
		</div>
		
	</div>
</div> 
<script LANGUAGE="JavaScript" type="text/javascript">
  function getMySchoolClasses(){
        $("#schoolClsses").show();
       }
/*  function getAllClassesAttendance(classId){
     
        var classNameAccountId=classId;
        var monthName=document.getElementById("examTypeId").value;
        if(monthName==''){
         alert("Please Select Month");
         return true;
        }
        var str="";
        var url = jQuery.url.getChatURL("/admin/ajaxGetAllClassSectionsAttendance.do?classId="+classNameAccountId+"&month="+monthName);
		$.ajax( {
			url : url,
			cache : false,
			data : 'json',
			success : function(response) {
					var str=response.sectionId;
					if(str!=null){
					var chartChildren1 = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chartChildrenId", "280", "185", "0", "0"); 
			        chartChildren1.setDataXML(str);
		            chartChildren1.render("classSectionsAttendancediv");
	               }
	          }
       });
 }*/
 
  $(document).ready(function() {
	  	/*var str="";
        var url = jQuery.url.getChatURL("/admin/ajaxGetAttendanceForClasses.do");
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
	  				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'classSectionsAttendancediv'
					},
					title: {
						text: response.title,
						x: -20
					},
					 subtitle: {
				         text: 'Student Absentees by Month',
				         x: -20
				      },
					yAxis: {
				        startOnTick: false,
				        minPadding: 0.8,
				        title: { text: 'No. of Absentees'}
				    },
					xAxis: {
						categories: response.categories
					},
					tooltip: {
				         formatter: function() {
				                   return '<b>'+ this.series.name +'</b><br/>'+
				               this.x +': '+ this.y +' Absentees';
				         }
				      },
					series: response.series
				   });
				}
         });*/
     
       /* var url = jQuery.url.getChatURL("/admin/ajaxViewStudentPendingFeeList.do");
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
			        alert(JSON.stringify(response));
	  				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'pendingPayments'
					},
					title: {
						text: response.title,
						x: -20
					},
					 subtitle: {
				         text: 'Classes',
				         x: -20
				      },
					yAxis: {
				        startOnTick: false,
				        minPadding: 0.8,
				        title: { text: 'Paid And Not Paid Amount'}
				    },
					xAxis: {
						categories: response.categories
					},
					tooltip: {
				         formatter: function() {
				                   return '<b>'+ this.series.paid +'</b><br/>'+
				               this.x +': '+ this.y +' Total Amount';
				         }
				      },
					series: response.series
				   });
				}
         });*/
         
  });
         
        function getStaffPerfomance(perfomanceName){
     
        var perfomanceName=perfomanceName;
        if(perfomanceName==''){
         alert("Please Select Perfomance type");
         return true;
        }
       
 	}
</script>