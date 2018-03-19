<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9 omega block">
	<div class="block_head">
		<h2>
			My Profile
		</h2>
	</div>
	<div class="block_content" id="profileContent">
		<div class="grid_6 alpha">
			<b>My Contact Details</b>
		</div>
		<br/>
		<div class="grid_2 alpha">
			First Name:
		</div>
		<div class="grid_4 omega">
			<s:property value="user.person.firstName" />
		</div>
		<br/>
		<div class="grid_2 alpha">
			User Name:
		</div>
		<div class="grid_4 omega">
			<s:property value="user.username" />
		</div>
	</div>
	</div>