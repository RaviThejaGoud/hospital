<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Library
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<li>
						<s:url id="urlSchoolWideBooksLink" action="ajaxDoStudentBooksHistory" namespace="/library" />
						<sj:a href="%{urlSchoolWideBooksLink}" targets="studentLibraryContent" indicator="indicator" data-toggle="tab">Booked History </sj:a>
					</li>
						 <li class="active">
							<s:url id="sLibrary"  action="ajaxGetStudentLibrayHome" namespace="/library">
							</s:url>
							<sj:a  href="%{sLibrary}" targets="mainContentDiv" data-toggle="tab">View Books</sj:a> 	
						 </li>
					</ul>
					<div id="studentLibraryContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
						page="/WEB-INF/pages/library/student/ajaxStudentLibraryDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 