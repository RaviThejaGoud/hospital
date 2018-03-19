<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Manage K-Bank
					</h2>
				</div>
				<div class="block_content kBankNavLinks" id="sideMenu">
					<ul>
						<li class="active">
							<s:url id="urlAboutKBankDetailsLink" action="ajaxAdminKBank"
								includeParams="all" escapeAmp="false" namespace="/admin">
							</s:url>
							<sj:a href="%{urlAboutKBankDetailsLink}" targets="kBankContent"
								indicator="indicator">About Knowledge Bank</sj:a>
						</li>
						<!--<li>
							<a href="<c:url value='/common/ajaxKhanVideoPlaylists.do?'/>"
								target="_new"> Getting KVideos </a>
						</li>
					--></ul>
					<s:if
						test="%{knowledgeBankTypeList != null && !knowledgeBankTypeList.isEmpty()}">
						<ul style="padding-left: 0px;">
							<s:iterator value="knowledgeBankTypeList">
								<li id="kbankNav<s:property value='id'/>">
									<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param value="id" name="kBankTypeId" />
										<s:param value="typeName" name="kBankTypeName" />
									</s:url>
									<sj:a href="%{urlKBankDetailsLink}" targets="kBankContent"
										indicator="indicator">
										<s:property value="typeName" />
									</sj:a>
								</li>
							</s:iterator>
							<li>
								<s:url id="urlMyFavouriteLink" action="ajaxGetKBankFavourites" namespace="/admin"/>
								<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
									indicator="indicator">My Favourite</sj:a>
							</li>
							<li>
								<s:url id="urlMyFavouriteLink" action="ajaxGetKhanPlayList" namespace="/admin"/>
								<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
									indicator="indicator">Knowledge Videos</sj:a>
							</li> 
						</ul>
					</s:if>
				</div>
			</div>
			<div class="grid_14 omega">
				<div id="kBankContent">
					<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewKBank.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("KBank Details");
$('#KBank').addClass('current');
</script>