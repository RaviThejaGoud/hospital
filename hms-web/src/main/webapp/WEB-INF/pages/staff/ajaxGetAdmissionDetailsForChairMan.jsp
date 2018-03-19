<%@ include file="/common/taglibs.jsp"%>
<div class="table-responsive">
   <div data-always-visible="1" data-rail-visible="0">
		<ul class="feeds">
			<li>
				<div class="col1" style="width: 84%">
					<div class="cont">
						<div class="cont-col1">
							<div class="label label-sm label-success">
								<i class="fa fa-bolt"></i>
							</div>
						</div>
						<div class="cont-col2">
							<div class="desc">
								Total
								<b>Applications</b>
							</div>
						</div>
					</div>
				</div>
				<div class="col2">
					<div class="leaves">
						<ul class="tooltipDiv" style="float: right;">
							<li>
								<span><b> <s:property value="bankId"/></b></span>
								
							</li>
						</ul>
					</div>
				</div>
			</li>
			<li>
				<div class="col1" style="width: 84%">
					<div class="cont">
						<div class="cont-col1">
							<div class="label label-sm label-success">
								<i class="fa fa-bolt"></i>
							</div>
						</div>
						<div class="cont-col2">
							<div class="desc">
								Total
								<b>Approved Applications</b> 
							</div>
						</div>
					</div>
				</div>
				<div class="col2">
					<div class="leaves">
						<ul class="tooltipDiv" style="float: right;">
							<li>
								<span>
								<b> <s:property value="quizId"/></b> </span>
							</li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>