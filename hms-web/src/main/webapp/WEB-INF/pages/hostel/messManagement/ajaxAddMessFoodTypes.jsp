<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body" id="studRegister">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxAddMessFoodTypes" theme="simple"
					cssClass="form-horizontal" id="addMessFoodTypeFormId" method="post"
					namespace="/hostel">
					<s:hidden id="messFoodTypeId" name="messFoodType.id" />
					<div class="form-body">
						<h4 class="bold pageTitle">Add Mess Food Types</h4>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Mess Name :
									</label>
									<div class="col-md-5">
										<s:select id="messName" list="messList" listKey="id"
											listValue="messName" tabindex="1" headerKey=""
											headerValue="- Select -" name="messFoodType.messId"
											cssClass="required form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Food Type Name :
									</label>
									<div class="col-md-6">
										<sj:textfield name="messFoodType.foodTypeName"
											id="foodTypeName" tabindex="2"
											cssClass="required form-control input-medium as-input"
											maxlength="50"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-6">
								<div class="col-md-offset-4 col-md-12">
									<sj:submit cssClass="btn blue" value="Submit"
										indicator="indicator" formIds="addMessFoodTypeFormId"
										targets="messSettingContent" validate="true" />
								</div>
							</div>
						</div>

					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>


