<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:form id="saveUserSocailStatusId" action="ajaxSaveUserSocailStatus" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee" enctype="multipart/form-data">
<div class="row">
<div class="col-md-9">
	<div class="tabbable tabbable-custom tabbable-full-width">
		<ul class="nav nav-tabs">
			<li class="active"> <a data-toggle="tab" id="statusDIv" href="#tab_2_2">Status</a></li>
			<li><a data-toggle="tab" id="photosDiv">Photos</a></li>
			<li><a data-toggle="tab" id="eventsDiv" href="#tab_1_4">Events</a></li>
		</ul>
	<div class="tab-content" style="padding:10px 7px 0px 7px">
		<div id="photosContentDiv" style="display:none;">
			<div class="form-group" id="messageContDiv">
				<label class="control-label col-md-2">Photo Upload:</label>
				<div class="col-md-10">
					<input type="file" name="fileUpload" value="" tabindex="1" id="uploadDocs" class="btn default required" multiple="multiple">
				</div>
			</div>
		</div>
		<div id="eventsContentDiv" style="display:none;">
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>Event Name :</label>
				<div class="col-md-4">
				<sj:textfield name="shareUserActivitiesVO.eventName" id="title"
					 tabindex="2" cssClass="form-control required"
					 maxlength="40"></sj:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-5">
					<div class="input-group date form_meridian_datetime">                                       
						<input type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.startDateTime"  value="">
						<span class="input-group-btn">
						<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
						</span>
						<span class="input-group-btn">
						<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>End Date :</label>
				<div class="col-md-5">
					 <div class="input-group date form_meridian_datetime">                                       
						<input type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.endDateTime" value="">
						<span class="input-group-btn">
						<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
						</span>
						<span class="input-group-btn">
						<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"> <span class="required">*</span>Location:</label>
				<div class="col-md-4">
					<sj:textfield name="shareUserActivitiesVO.eventLocation" id="eventLocation" 
								cssClass="form-control"></sj:textfield>
				</div>
			</div>
	  </div>
		<div id="statusContentDIv" class="tab-pane active">
			<div class="form-group" id="messageContDiv">
				<label class="control-label col-md-2"><span class="required">*</span>Description:</label>
				<div class="col-md-10">
					<sj:textarea name="shareUserActivitiesVO.description" id="description"
						rows="2" cssClass="wysihtml5 form-control messagesArea1 required"
						cols="20"></sj:textarea>
				</div>
			</div>
		</div>
		<div class="form-actions fluid form-body" style="margin: 0 -7px;padding: 2px;">
				<div class="col-md-offset-2 col-md-5">
				<div class="col-md-6" style="padding-left: 0px;">
					<s:select list="#{'P':'Public','O':'Only Me'}" cssClass="form-control input-small"
						required="true" name="shareUserActivitiesVO.visibleType">
					</s:select>
				</div>
				<sj:submit value="Share" cssClass="submitBt btn blue btn-sm" onBeforeTopics="SaveUserSocailStatus" 
					formIds="saveUserSocailStatusId" targets="mainContentDiv" indicator="indicator" validate="true" resetForm="true"/>
			</div>
	    </div>
	</div>
</div>	
<div id="viewAllSocialFriendStautsDivId">
	<jsp:include page="/WEB-INF/pages/alumnee/ajaxViewAllSocialFriendsStatus.jsp"></jsp:include>
</div>
</div>	
	<div class="col-md-3 blog-sidebar">
			<h3>Latest Photos</h3>
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<ul class="list-inline blog-images">
			 		<s:iterator value="tempList">
	 					 <li>
							<a href="javascript:;">
								<img src="${pageContext.request.contextPath}/<s:property value="imagePath"/><s:property value="imageName"/>" alt="" >
							</a>
						</li>
					</s:iterator>
				 </ul>
			</s:if>
			<s:else><div class="alert alert-info">No Photos added.</div></s:else>
		<div class="spaceDiv"> </div>
	</div>
	</div>
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>

<script type="text/javascript">
$(document).ready( function() {
	$('#description').val('');
	FormComponents.init();
	changePageTitle("View Posts");
}); 
$('.messagesArea1').wysihtml5({
	"font-styles" : false, //Font styling, e.g. h1, h2, etc. Default true
	"emphasis" : true, //Italics, bold, etc. Default true
	"lists" : true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
	"html" : true, //Button which allows you to edit the generated HTML. Default false
	"link" : true, //Button to insert a link. Default true
	"image" : false, //Button to insert an image. Default true,
	"color" : false
//Button to change color of font  
});
$('a#photosDiv').click(function(){
	$('div#photosContentDiv').show();
	$('div#eventsContentDiv').hide();
});
$('a#eventsDiv').click(function(){
	$('div#eventsContentDiv').show();
	$('div#photosContentDiv').hide();
});
$('a#statusDIv').click(function(){
	$('div#photosContentDiv').hide();
	$('div#eventsContentDiv').hide();
});

$.subscribe('SaveUserSocailStatus', function(event, data) {
	if ($('#saveUserSocailStatusId').valid()){
		//document.getElementById("saveUserSocailStatusId").reset();
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
</script>