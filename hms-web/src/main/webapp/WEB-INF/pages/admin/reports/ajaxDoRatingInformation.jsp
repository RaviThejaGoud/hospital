<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-title">
						Staff Feedback Information</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<s:if test='%{customer != null}'>
						<s:form name="myformIdCards" theme="simple" namespace="/admin"
							method="post" cssClass="form-horizontal" id="ratingForStaff"
							action="ajaxDoRatingInformationForPdf" 
							onsubmit="javascript: return getClassIdsAndAdmissionNumber();">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="selectedId" id="classNameIds" />
							<div class="form-body">
								<%@ include file="/common/messages.jsp"%>
								<div class="form-group" id="staffRoles">
									<div class="col-md-6">
										<p>
											<span class="label label-danger"> NOTE : </span> &nbsp;
											Please select at least one staff member.
										</p>
									</div>
								</div>

								<div class="form-group" id="staffRoles">
									<div class="col-md-6">
										<label class="col-md-3 control-label"> Select Staff :
										</label>
										<div class="col-md-7">
											<s:select list="objectList" id="staffIdValeus"
												name="classSectionId" listKey="staffId"
												listValue="fullNameRoleName" theme="simple"
												cssClass="form-control" headerKey="0"
												headerValue="All Staff">
											</s:select>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-3 col-md-9">
											<s:submit value="Genarate Report" cssClass="btn blue long" 
												id="ratingForStaff">
											</s:submit>
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function getClassIdsAndAdmissionNumber() {	  
	var selectedClassIds = [];
	$('select[name="classSectionId"]').each(function(){
       if(this.options[this.selectedIndex].value==0){
         // Loop through the option elements inside the select element
        $(this).find('option').each(function(){
           selectedClassIds.push($(this).attr('value'));
        });
        }else{
         selectedClassIds.push(this.options[this.selectedIndex].value);
        }
       $("#classNameIds").val("(" + selectedClassIds + ")");
    }); 
}	
</script>