<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Teacher | Class Exam Details</title>
</head>
<body />
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Knowledge Bank
					</h2>
				</div>
				<div class="block_content kBankNavLinks" id="sideMenu">
					<ul>
						<li class="active">
							<s:url id="urlAboutKBankDetailsLink" action="ajaxStudentKBank"
								includeParams="all" escapeAmp="false" namespace="/student">
							</s:url>
							<sj:a href="%{urlAboutKBankDetailsLink}" targets="kBankContent"
								indicator="indicator">About Knowledge Bank</sj:a>
						</li>
					</ul>
					<jsp:include page="/common/messages.jsp"></jsp:include>
					<s:if
						test="%{knowledgeBankTypeList != null && !knowledgeBankTypeList.isEmpty()}">
						<ul style="padding-left: 0px;">
							<s:iterator value="knowledgeBankTypeList">
								<li id="kbankNav<s:property value='id'/>">
									<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
										includeParams="all" escapeAmp="false" namespace="/student">
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
								<s:url id="urlMyFavouriteLink" action="ajaxGetKBankFavourites" namespace="/student"/>
								<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
									indicator="indicator">My Favourite</sj:a>
							</li>
							<s:if test='%{user.isSchoolStudent=="Y"}'>
								<li>
									<s:url id="urlMyQuizLink" action="ajaxGetQuiz" namespace="/student"/>
									<sj:a href="%{urlMyQuizLink}" targets="kBankContent"
										indicator="indicator">Quiz</sj:a>
								</li>
							</s:if>
							<li>
								<s:url id="urlMyFavouriteLink" action="ajaxGetKhanPlayList" namespace="/student"/>
								<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
									indicator="indicator">Knowledge Videos</sj:a>
							</li>
							<li class="" style="line-height: 0px">
								&nbsp
							</li>
						</ul>
					</s:if>
				</div>
			</div>
			<div  id="kBankContent">
				<jsp:include page="/WEB-INF/pages/student/kBank/ajaxViewKBank.jsp" />
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Manage Student KBank");
$('#KBank').addClass('current');
</script>