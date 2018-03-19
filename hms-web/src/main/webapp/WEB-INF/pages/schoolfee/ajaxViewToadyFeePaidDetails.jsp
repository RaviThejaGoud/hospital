<%@ include file="/common/taglibs.jsp"%>
<div class="row"  >
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Day Wise Fee Collection
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="feeContentDiv" class="tab-content">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">
										<span class="required">*</span> Select Date :
									</label>
									<div class="col-md-5">
										<div data-date-start-date="" data-date-end-date="+0d"
											data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="startDate" readonly="readonly" name="startDate"  value='<s:property value="attendanceDate"/>'
												class="required form-control input-medium" />
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
							</div>
							</div>
							<div id="feePaidContentDiv" class="tab-content">	
									<jsp:include page="/WEB-INF/pages/schoolfee/ajaxViewTodayFeeCollections.jsp"></jsp:include>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("Day Wise Fee Collections");
	});
	$('.date-picker').datepicker().on('changeDate', function(ev){
		getstudentFeeDetailsByDate();
		$('.datepicker').hide();
	});
	function getstudentFeeDetailsByDate(){
		var startDate = $('#startDate').val(); 
		//alert(startDate+"=="+startDate);
		if(isNonEmpty(startDate)){
			var pars = "startDate=" + startDate;
			$("#feePaidContentDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : jQuery.url
						.getChatURL("/schoolfee/ajaxFeeCollectionByDate.do"),
				cache : false,
				data : pars,
				success : function(response) {
					$('#feePaidContentDiv').html(response);
				}
			});
		}/* else{
			if (isNonEmpty(attendanceDate)) 
				$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select Class & Section.</p>");
			else
				$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select attendance date.</p>");
		} */
	}
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
