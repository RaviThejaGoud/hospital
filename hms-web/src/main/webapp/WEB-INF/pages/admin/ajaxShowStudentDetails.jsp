<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="button"></i>Search Results</div>
				</div>
				<div class="portlet-body">
					<div class="form-horizontal">
						<s:if test="%{tempList != null && !tempList.isEmpty()}">
							<jsp:include
								page="/WEB-INF/pages/admin/ajaxViewAllStudentPersonalDetails.jsp" />
								<div id="viewMyPerformance"></div>
						</s:if>
						<s:else>
							<div class="alert alert-info span11">Oops! system couldn't
								find any match with keyword. Try by correcting the word.</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Admin Search Results");
	});
</script>
