<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Staff & Student Attendance Info
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
				   <div data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
						<div class="form-body">
							<div class="form-group" id="classWiseStuDetailsPdfDetails">
								<label class="conLable col-md-1 control-label">
									<strong>Select : </strong>
								</label>
								<div class="radio-list">
									<label class="radio-inline">
										<input type="radio" name="SelectType"
											value="classSectionWise"
											onclick="getSelectedValue(this.value);" checked="checked">
										School Start Time
									</label>
									<label class="radio-inline">
										<input type="radio" name="SelectType" value="roleWise"
											onclick="getSelectedValue(this.value);">
										After School Time
									</label>
								</div>
							</div>
							<div id="classAndSection">
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
													<b>Strength</b>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="leaves">
											<ul class="tooltipDiv" style="float: right;">
												<li>
													<span> <b><s:property value="tempId2"/></b></span>
													
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="form-group" id="staffRoles">
						<div class="row">
							<div class="col-md-6">
								<label class="control-label col-md-2">Select Time :</label>
								<div class="col-md-3">
									<div class="input-icon">
										<i class="fa fa-clock-o"></i>
										<input type="text" class="form-control timepicker timepicker-seconds-default"  name="anyTitle" id='startTime_<s:property value="id" />' onclick="javascript:getStudentCount(this.value);">
									</div>
								</div>
							</div>
						</div>
						<div id="timeContentId"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	 $("input:checkbox, input:radio").uniform();
	 	changePageTitle("Student And Staff Attendance Count");	
		var selected = $('input[name=SelectType]:radio:checked').val();	
		getSelectedValue(selected);	
		FormComponents.init();
	});
	function getSelectedValue(value) {			    
		if (isNonEmpty(value)) {				
			if (value == 'classSectionWise' ) {
				$("#staffRoles").hide();
				$("#classAndSection").show();
			} else {
				$('#classAndSection').hide();
				$("#staffRoles").show();
			}
		}
	}
	function getStudentCount(selectBox) {
		var url = jQuery.url.getChatURL("/staff/ajaxGetStudentAndStaffCountBasedOnTime.do");
		if (selectBox == 0) {
			alert("!Oops select Time.");
		} else {
			$("#timeContentId") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars ="PlTitle="+selectBox;
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#timeContentId").html(html);
				}
			});
		}
	}
	
</script>