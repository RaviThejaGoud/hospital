<li class="start active"><a href="javascript:;" id="LR"> <i
		class="fa fa-book"></i> <span class="title">Library</span> <span
		class="selected"></span> <span class="arrow"></span>
</a>
	<ul class="sub-menu">
		<li id="viewBookResults" class="active"><s:url
				id="viewBookResults" action="ajaxLibraryHome" namespace="/library" />
			<sj:a href="%{viewBookResults}" id="stockMaitenance"
				targets="mainContentDiv" cssClass='ajaxify LSM'>
				Stock Maintenance
			</sj:a></li>
		<li><s:url id="issuedBookDetail" action="ajaxDoIssuedBookDetail"
				namespace="/library" /> <sj:a href="%{issuedBookDetail}"
				targets="mainContentDiv" cssClass='ajaxify LRB'>
				Issue / Return Books
			</sj:a></li>
		<li><s:url id="issuedAndRequestBooksList"
				action="ajaxDoIssuedAndRequestBooks" namespace="/library"
				includeParams="all" escapeAmp="false">
				<s:param name="anyId">RR</s:param>
			</s:url> <sj:a href="%{issuedAndRequestBooksList}" targets="mainContentDiv"
				cssClass='ajaxify LPR'>
				Student / Staff Requests
			</sj:a></li>
		<li><s:url id="librarySettings" action="ajaxViewLibrarySettings"
				namespace="/library" /> <sj:a href="%{librarySettings}"
				targets="mainContentDiv" cssClass='ajaxify LLS'>
				Library Settings
			</sj:a></li>
		<li><s:url id="manageRacks" action="ajaxDoBlockDetails"
				namespace="/library" /> <sj:a href="%{manageRacks}"
				targets="mainContentDiv" cssClass='ajaxify LMB'>
				Manage Blocks & Racks
			</sj:a></li>
		<li id="viewStudLibIdCards"><s:url id="viewStudLibIdCards"
				action="ajaxViewStudentLibraryIdCasrds" namespace="/library" /> <sj:a
				href="%{viewStudLibIdCards}" id="StudLibIdCards"
				targets="mainContentDiv" cssClass='ajaxify SLIG'>
					Id Card Generation
			</sj:a></li>
	</ul></li>
<li><a href="javascript:;" id="MPER"> <i class="fa fa-gift"></i>
		<span class="title">Manage Permissions</span> <span class="selected">
	</span> <span class="arrow"> </span>
</a>
	<ul class="sub-menu">
		<li><s:url id="StaffRequest" action="ajaxViewStaffRequest"
				namespace="/staff" /> <sj:a href="%{StaffRequest}"
				targets="mainContentDiv" cssClass="ajaxify MPER">Permissions</sj:a>
		</li>
	</ul></li>
<li><a
	href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=tManageLeaves"
	id="tManageLeaves"> <i class="fa fa-asterisk"></i> <span
		class="title">Leaves Management</span> <span class="selected"></span>
</a></li>
<li><a href="javascript:;" id="HASE"> <i class="fa fa-envelope"></i>
		<span class="title">Inbox</span> <span class="selected"></span> <span
		class="arrow"></span>
</a>
	<ul class="sub-menu">
		<li><s:url id="urlInboxesDetails"
				action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
			</s:url> <sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
				cssClass='ajaxify'>
				My Inbox
			</sj:a></li>
		<li><s:url id="urlDashboardDetails"
				action="ajaxDoGetSchoolWideAlertsList" namespace="/common"></s:url>
			<sj:a href="%{urlDashboardDetails}" targets="mainContentDiv"
				cssClass='ajaxify'>
				Dashboard Messages
			</sj:a></li>
		<li><s:url id="urlCircularMessagesAlerts"
				action="ajaxDoGetCircularMessagesList" namespace="/common" /> <sj:a
				href="%{urlCircularMessagesAlerts}" targets="mainContentDiv"
				cssClass='ajaxify ACMIL'>Circular Messages</sj:a></li>
	</ul></li>
<li id="manageEvents"><a href="javascript:;" id="EVNTS"><i
		class="fa fa-music"></i><span class="title"> Manage Events</span><span
		class="selected"></span><span class="arrow "></span> </a>
	<ul class="sub-menu">
		<li id="eventsInfo"><s:url id="urlEventsLink"
				action="ajaxStaffEvents" namespace="/staff" /> <sj:a
				href="%{urlEventsLink}" targets="mainContentDiv"
				cssClass="ajaxify EVNT">
				<i class="fa fa-music"></i>
					Events
			</sj:a></li>
	</ul></li>
<li id="addVideosDiv"><a href="javascript:;" id="HelpDoc"> <i
		class="fa fa-file-text"></i> <span class="title">Videos</span> <span
		class="selected"></span> <span class="arrow"></span>
</a>
	<ul class="sub-menu">
		<li><s:url id="viewVideosurl" action="ajaxDoViewVideos"
				namespace="/common"></s:url> <sj:a href="%{viewVideosurl}"
				targets="mainContentDiv" cssClass='ajaxify VVEO'> View Videos</sj:a>
		</li>
	</ul></li>