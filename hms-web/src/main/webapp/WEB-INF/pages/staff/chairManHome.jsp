<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
	<div class="col-md-6" style="padding-left: 0px;">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Staff & Student Info
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
				   <div data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<li>
								<div class="col1" style="width: 84%">
									<div class="cont">
										<div class="cont-col1">
											<div class="label label-sm label-success">
												<i class="fa fa-bolt"></i>
											</div>
										</div>
										<div class="cont-col2">
											<div class="desc">
												Total
												<b>staffs</b>
											</div>
										</div>
									</div>
								</div>
								<div class="col2">
									<div class="leaves">
										<ul class="tooltipDiv" style="float: right;">
											<li>
												<span> <b><s:property value="tempId"/></b></span>
												
											</li>
										</ul>
									</div>
								</div>
							</li>
							<li>
								<div class="col1" style="width: 84%">
									<div class="cont">
										<div class="cont-col1">
											<div class="label label-sm label-success">
												<i class="fa fa-bolt"></i>
											</div>
										</div>
										<div class="cont-col2">
											<div class="desc">
												Total
												<b>students</b> 
											</div>
										</div>
									</div>
								</div>
								<div class="col2">
									<div class="leaves">
										<ul class="tooltipDiv" style="float: right;">
											<li>
												<span>
												<b><s:property value="tempId1"/></b> </span>
											</li>
										</ul>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="col-md-6" style="padding-right: 0px;">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Admission Summary for Current Year and Previous Year
					</div>
				</div>
				<div class="portlet-body">
					<div class="form-group">
						<label class="col-md-2 control-label">
							<span class="required">*</span>Select Year :
						</label>
						<div class="col-md-6">
							<s:select id="academicYearId" list="academicYearList"
								cssClass="required form-control input-medium" listKey="id"
								listValue="academicYear" headerKey="0" 
								name="tempId2"
								onchange="javascript:academicApplicationDetails();" />
						</div>
					</div>
					<div class="spaceDiv">&nbsp;</div>
				<div id="admissionsPendingContentAppli" style="display: none;">
					<jsp:include
						page="/WEB-INF/pages/staff/ajaxGetAdmissionDetailsForChairMan.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6" style="padding-left: 0px;">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Fee Summary
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
				   <div data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<li>
								<div class="col1" style="width: 84%">
									<div class="cont">
										<div class="cont-col1">
											<div class="label label-sm label-success">
												<i class="fa fa-bolt"></i>
											</div>
										</div>
										<div class="cont-col2">
											<div class="desc">
												Total
												<b>Amount</b>
											</div>
										</div>
									</div>
								</div>
								<div class="col2">
									<div class="leaves">
										<ul class="tooltipDiv" style="float: right;">
											<li>
												<span> <b><s:property value="totalAmount"/></b></span>
												
											</li>
										</ul>
									</div>
								</div>
							</li>
							<li>
								<div class="col1" style="width: 84%">
									<div class="cont">
										<div class="cont-col1">
											<div class="label label-sm label-success">
												<i class="fa fa-bolt"></i>
											</div>
										</div>
										<div class="cont-col2">
											<div class="desc">
												Total
												<b>PaidAmount</b> 
											</div>
										</div>
									</div>
								</div>
								<div class="col2">
									<div class="leaves">
										<ul class="tooltipDiv" style="float: right;">
											<li>
												<span>
												<b><s:property value="paymentAmount"/></b> </span>
											</li>
										</ul>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
		<%-- <div class="col-md-6" style="padding-left: 0px;">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-globe"></i>
					Exam Results
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required">*</span>Select Year :
					</label>
					<div class="col-md-6">
						<s:select id="academicYearIds" list="objectList"
							cssClass="required form-control input-medium" listKey="id"
							listValue="academicYear" headerKey="0" 
							name="eventId"
							onchange="javascript:getClassWiseExamGraphDetails();" />
					</div>
				</div>
				<div class="spaceDiv">&nbsp;</div>
			   <div id="classWisePassAndFailGraphDiv">
			   </div>
			</div>
		</div>
		</div> --%>
		<div class="col-md-6" style="padding-left: 0px;">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Expenses 
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
				   <div data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<li>
								<div class="col1" style="width: 84%">
									<div class="cont">
										<div class="cont-col1">
											<div class="label label-sm label-success">
												<i class="fa fa-bolt"></i>
											</div>
										</div>
										<div class="cont-col2">
											<div class="desc">
												Total 
												<b>Amount</b>
											</div>
										</div>
									</div>
								</div>
								<div class="col2">
									<div class="leaves">
										<ul class="tooltipDiv" style="float: right;">
											<li>
												<span> <b><s:property value="payLeaves"/></b></span>
												
											</li>
										</ul>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Teacher Dashboard");
	academicApplicationDetails();
	getClassWiseExamGraphDetails();
});
function academicApplicationDetails() {
	var academicYearId = $('#academicYearId option:selected').val(); 
	if(isNonEmpty(academicYearId) && academicYearId != 0 && academicYearId !="- Select -"){
		var pars = "academicYearId=" + academicYearId;
		$("#admissionsPendingContentAppli").html(
				'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
		var url = jQuery.url.getChatURL("/staff/ajaxGetApplicationsDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#admissionsPendingContentAppli").html(html);
				$("#admissionsPendingContentAppli").show();
			}
		});	
	}else{
		$("#admissionsPendingContentAppli").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#admissionsPendingContentAppli").show();
	}
}
function getClassWiseExamGraphDetails(){
	 var academicYearId = $('#academicYearIds option:selected').val(); 
	 if(isNonEmpty(academicYearId) && academicYearId != 0 && academicYearId !="- Select -"){
		var pars = "academicYearId=" + academicYearId;
		var url = jQuery.url.getChatURL("/exam/ajaxGetPassAndFailPersentByClassIdGraphs.do?academicYearId="+academicYearId);
	 	$('#classWisePassAndFailGraphDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
			if(isNonEmpty(response)){
				 chart = new Highcharts.Chart({
				      chart: {
				         renderTo: 'classWisePassAndFailGraphDiv',
				         defaultSeriesType: 'column'
				      },
				      title: {
				    	  text: ''
				      },
				      xAxis: {
				         categories: response.categories,
				         labels: {
				            align: 'right',
				            style: {
				                font: 'normal 13px Verdana, sans-serif'
				            }
				         }
				      },
				      yAxis: {
				         min: 0,
				         max: response.max,
				         title: {
				            text: 'Persentage'
				         },
				         stackLabels: {
				            enabled: true,
				            style: {
				               fontWeight: 'bold',
				               color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				            }
				         }
				      },
				      tooltip: {
					         formatter: function() {
					            return '<b>'+ this.x +'</b><br/>'+
					                this.series.name +': '+ this.y +'<br/>';
					         }
					      }, 
				       series: response.series
				   });
			  }else{
			  	$('#classWisePassAndFailGraphDiv').html('<div class="alert alert-info">'+'There are no latest marks.'+'</div>');
			  }
	        }
		});
	 }else{
			$("#classWisePassAndFailGraphDiv").html('<div class="alert alert-info">Please select academic year.</div>');
		}
}
</script>