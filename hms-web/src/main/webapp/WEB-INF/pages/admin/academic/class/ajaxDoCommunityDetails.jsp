
<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commonFormTabs">
	<s:form id="viewCommunity" action="ajaxViewCommunityDetails" method="post" theme="css_xhtml">
		<fieldset>
			<div class="grid_6">
				<label>
					<span class="required">*</span>Select Class:
				</label>
				<div class="grid_6">
					<s:select list="classNameList" listKey="id" listValue="className"
						cssClass="required textfield" theme="css_xhtml" required="true"
						name="classId" headerKey="" headerValue="- Select Class Name-"
						requiredposition="first"
						onchange="javascript:getAllIssuedBooks(this);">
					</s:select>
				</div>
				<div class=" grid_6">
					<s:select id="castName" list="castSettingList" label="Community Name"
						cssClass="required" listKey="castName" cssStyle="width:200px;"
						listValue="castName" headerKey="" headerValue="- Select -"
						name="onlineApplicationDetails.castName" theme="css_xhtml"/>
				</div>
				<div class="grid_6">
					<s:select id="gender" label="Gender" headerKey="" tabindex="11"
						headerValue="- Select -" name="person.bloodGroup" cssClass="required" 
						list="#{'M':'Male','F':'Female','A':'All'}" />
				</div>
				<div class="grid_4">
					<sj:submit   targets="stepClassSections" value="Submit"
						cssClass="submit small"   indicator="indicator" validate="true"/>
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
