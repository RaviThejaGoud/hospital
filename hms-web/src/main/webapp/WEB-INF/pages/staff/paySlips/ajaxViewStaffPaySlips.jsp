<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Pay Slips
				</div>
			</div>
			<div class="portlet-body">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<s:form id="viewPaySlips" action="ajaxDownloadStaffPaySlipsInPdfFormat" method="post"
						theme="simple" cssClass="form-horizontal" namespace="/staff">
						<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-2">
									Select Month :
								</label>
								<div class="col-md-3">
									<s:select id="pstaffId" list="objectList" listKey="accountIdAndClassSectionId" headerKey="" 
									listValue="monthName" 
									name="anyTitle" theme="simple" cssClass="form-control required " />
							</div>
							</div>
							<div class="col-md-10" style="float: right;">
							<sj:submit value="Download" cssClass="submitBt btn blue"
								onBeforeTopics="addVideosForm" formIds="viewPaySlips"
								 indicator="indicator" validate="true" />
							</div>
							</br>
						</div>
					</s:form> 
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no payslips generated.
					</div>
				</s:else>
				</div>
		</div>
	</div>
</div>		
