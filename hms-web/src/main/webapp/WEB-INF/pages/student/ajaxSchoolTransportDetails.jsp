<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div>
<s:if test="%{viewStaffVehicleTripList != null && !viewStaffVehicleTripList.isEmpty()}">
	<s:iterator value="viewStaffVehicleTripList">	
	<div class="grid_3">
		&nbsp;
	</div>
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; margin-bottom: 10px;">
			<h3>
				Route Details
			</h3>
		</div>
	</div>
	
	<div class="grid_8">
		<div class="grid_3" >
			Route No :
		</div>
		<div class="grid_4">
			<s:property value="routeNumber"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Starting Point :
		</div>
		<div class="grid_4">
			<s:property value="startPoint"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Starting Time :
		</div>
		<div class="grid_4">
			<s:property value="startTime"/>
		</div>
	</div>
	
	<s:if test='%{AM != amPm8 }'>
	<div class="grid_8">
		<div class="grid_3" >
			Boarding Point :
		</div>
		<div class="grid_4">
			<s:property value="endPoint"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Boarding Time :
		</div>
		<div class="grid_4">
			<s:property value="endTime"/>
		</div>
	</div>
	</s:if>
	<s:else>
	<div class="grid_8">
		<div class="grid_3" >
			Drop Point :
		</div>
		<div class="grid_4">
			<s:property value="endPoint"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Drop Time :
		</div>
		<div class="grid_4">
			<s:property value="endTime"/>
		</div>
	</div>
	</s:else>
	<div class="grid_3">
		&nbsp;
	</div>
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; margin-bottom: 15px;">
			<h3>
				Person Details
			</h3>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Driver Name :
		</div>
		<div class="grid_4">
			<s:property value="diverPersonFullName"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Driver PhoneNumber :
		</div>
		<div class="grid_4">
			<s:property value="driverMobileNumber"/>
		</div>
	</div>
	<div class="grid_8">
		<div class="grid_3" >
			Helper :
		</div>
		<div class="grid_4">
			<s:property value="helperPersonFullName"/>
		</div>
	</div>
	<div class="grid_8 border "><b>&nbsp;</b></div>
	</s:iterator>
	</s:if>
</div>