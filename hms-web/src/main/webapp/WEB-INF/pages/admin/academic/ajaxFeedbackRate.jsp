<%@ include file="/common/taglibs.jsp"%>
<div>
<table>
	<tr>
		<td>
			<a href="#" title="add blogComments">Rate this post...</a>
		</td>
		<s:if test="%{blogRate.average ==1}">
			<td style="padding: 0px 41px 0px 0px;"> 
				<table>
					<tr>
						<td>
							<div id="afterrateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,1,<s:property value="account.id" />,'ehh...')"
									id="1" title="ehh..."></a>
							</div>
						</td>
						<td>
							<table>
								<tr>
									<td>
										<div id="rateMe" title="Rate Me...">
											<a
												onclick="rateIt(<s:property value="blogRate.blogPost.id" />,2,<s:property value="account.id" />,'Not Bad')"
												id="2" title="Not Bad"></a>
											<a
												onclick="rateIt(<s:property value="blogRate.blogPost.id" />,3,<s:property value="account.id" />,'Pretty Good')"
												id="3" title="Pretty Good"></a>
											<a
												onclick="rateIt(<s:property value="blogRate.blogPost.id" />,4,<s:property value="account.id" />,'Out Standing')"
												id="4" title="Out Standing"></a>
											<a
												onclick="rateIt(<s:property value="blogRate.blogPost.id" />,5,<s:property value="account.id" />,'Freakin Awesome!')"
												id="5" title="Freakin Awesome!"></a>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</s:if>
		<s:elseif test="%{blogRate.average ==2}">
			<td>
				<table>
					<tr>
						<td>
							<div id="afterrateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,1,<s:property value="account.id" />,'ehh...')"
									id="1" title="ehh..."></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,2,<s:property value="account.id" />,'Not Bad')"
									id="2" title="Not Bad"></a>
							</div>
						</td>
						<td>
							<div id="rateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,3,<s:property value="account.id" />,'Pretty Good')"
									id="3" title="Pretty Good"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,4,<s:property value="account.id" />,'Out Standing')"
									id="4" title="Out Standing"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,5,<s:property value="account.id" />,'Freakin Awesome!')"
									id="5" title="Freakin Awesome!"></a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</s:elseif>
		<s:elseif test="%{blogRate.average ==3}">
			<td>
				<table>
					<tr>
						<td>
							<div id="afterrateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,1,<s:property value="account.id" />,'ehh...')"
									id="1" title="ehh..."></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,2,<s:property value="account.id" />,'Not Bad')"
									id="2" title="Not Bad"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,3,<s:property value="account.id" />,'Pretty Good')"
									id="3" title="Pretty Good"></a>
							</div>
						</td>
						<td>
							<div id="rateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,4,<s:property value="account.id" />,'Out Standing')"
									id="4" title="Out Standing"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,5,<s:property value="account.id" />,'Freakin Awesome!')"
									id="5" title="Freakin Awesome!"></a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</s:elseif>
		<s:elseif test="%{blogRate.average ==4}">
			<td>
				<table>
					<tr>
						<td>
							<div id="afterrateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,1,<s:property value="account.id" />,'ehh...')"
									id="1" title="ehh..."></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,2,<s:property value="account.id" />,'Not Bad')"
									id="2" title="Not Bad"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,3,<s:property value="account.id" />,'Pretty Good')"
									id="3" title="Pretty Good"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,4,<s:property value="account.id" />,'Out Standing')"
									id="4" title="Out Standing"></a>
							</div>
						</td>
						<td>
							<div id="rateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,5,<s:property value="account.id" />,'Freakin Awesome!')"
									id="5" title="Freakin Awesome!"></a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</s:elseif>
		<s:elseif test="%{blogRate.average ==5}">
			<td>
				<table>
					<tr>
						<td>
							<div id="afterrateMe" title="Rate Me...">
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,1,<s:property value="account.id" />,'ehh...')"
									id="1" title="ehh..."></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,2,<s:property value="account.id" />,'Not Bad')"
									id="2" title="Not Bad"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,3,<s:property value="account.id" />,'Pretty Good')"
									id="3" title="Pretty Good"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,4,<s:property value="account.id" />,'Out Standing')"
									id="4" title="Out Standing"></a>
								<a
									onclick="rateIt(<s:property value="blogRate.blogPost.id" />,5,<s:property value="account.id" />,'Freakin Awesome!')"
									id="5" title="Freakin Awesome!"></a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</s:elseif>
	</tr>
</table>
</div>

<script type="text/javascript" src="<c:url value='/scripts/starrate/js/ui.core.min.js' />"></script>
<!-- Star Rating widget stuff here... -->
<script type="text/javascript" src="<c:url value='/scripts/starrate/js/ui.stars.js' />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/scripts/starrate/css/ui.stars.css' />"/>
<script type="text/javascript">
$(document).ready(function() {		
			$(".blogsList tr:nth-child(even)").css("background", "#f3f3f3");	
			$("a.blogs").fancybox({ 
					 'width' : '70%',
					 'height' : '75%',
					 'autoScale' : true,
			         'transitionIn' : 'none',
			         'transitionOut' : 'none',
			         'autoDimensions' : true,
			         'showCloseButton' : true,
			         'titleShow' : true,
			         'autoDimensions' : false,
			         
			         'titleFormat' : 'formatTitle'
			         });
			if($("#rat1"))
			{ 	
				$("#rat1").children().not(":radio").hide();
				$("#rat1").stars({
					starWidth: 18, // only needed in "split" mode
					split: 2,
					oneVoteOnly: true,
					callback: function(ui, type, value)
					{
						// Hide Stars while AJAX connection is active
						var blogPostId=$("#rat1 .blogPostIdValue").html();
						$("#rat1").hide();
						$("#loader1").show();
						$.ajax({
							url:"../ajax/ajacvbxBlogRating.do", 
							data:"&starValue=" + value + "&blogPostId=" + blogPostId,
							success : function(receiveRequest) {
										var response = receiveRequest.evalJSON(true);
										ui.select(Math.round(response.newRating));
										$("#loader1").hide();
										$("#rat1").show();
							}
						});
					}
				});
			}
			if($("#rat2"))
			{
				$("#rat2").stars({
					starWidth: 18, // only needed in "split" mode
					split: 2,
					oneVoteOnly: true,
					callback: function(ui, type, value)
					{
						// Hide Stars while AJAX connection is active
						var blogPostId=$("#rat2 .blogPostIdValue").html();
						$("#rat2").hide();
						$("#loader2").show();
						$.ajax({
							url:"../ajax/ajaxBlogRating.do", 
							data:"&starValue=" + value + "&blogPostId=" + blogPostId,
							success : function(receiveRequest) {
								var response = receiveRequest.evalJSON(true);
								ui.select(Math.round(response.newRating));
								$("#loader2").hide();
								$("#rat2").show();
							}
						});
					}
				});
			}
	  	}); 
</script>
	<style type="text/css">
#rateStatus {
	float: left;
	clear: both;
	width: 100%;
	height: 20px;
}

#rateMe {
	float: left;
	clear: both;
	width: 100%;
	height: auto;
	padding: 0px;
	margin: 0px;
}

#rateMe li {
	float: left;
	list-style: none;
}

#rateMe a:hover,#rateMe .on {
	background: url(../images/home/star_on.gif) no-repeat;
}

#rateMe a {
	float: left;
	background: url(../images/home/star_off.gif) no-repeat;
	width: 12px;
	height: 12px;
}

#ratingSaved {
	display: none;
}

.saved {
	color: red;
}

#rateStatus {
	float: right;
	clear: both;
	width: 100%;
	height: 20px;
}

#afterrateMe {
	float: right;
	clear: both;
	width: 100%;
	height: auto;
	padding: 0px;
	margin: 0px -4px 0px 0px;
}

#afterrateMe li {
	float: right;
	list-style: none;
}

#afterrateMe  a:hover,#afterrateMe .on {
	background: url(../images/home/star_off.gif) no-repeat;
}

#afterrateMe a {
	float: right;
	background: url(../images/home/star_on.gif) no-repeat;
	width: 12px;
	height: 12px;
}

#ratingSaved {
	display: none;
}

.saved {
	color: red;
}
</style>