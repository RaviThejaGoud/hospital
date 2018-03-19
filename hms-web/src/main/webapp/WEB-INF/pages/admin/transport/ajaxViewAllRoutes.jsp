<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<div class="grid_14">
	<div class="grid_8">
		<div class="grid_2">
			<sj:textfield name="destination" id="destination" cssStyle="width:125px;padding:4px;"
				value="Enter Place" onchange="clearTextField(this);"
				cssClass="textfield large required defaultValue" required="true"></sj:textfield>
		</div>
		<div class="grid_4">
			<a class="cancelButton" id="searchPlace"
				onclick="javascript:searchRouteMap();">Search</a>
		</div>
	</div>
	<div class="grid_4">
		&nbsp;
	</div>
<div id="viewRoutes" class="grid_13" style="height: 450px;"></div>
	<div id="warnings_panel" style="width: 100%; height: 10%; text-align: center"></div>
</div>
<div class="grid_14">
	&nbsp;
</div>

<div id="commonStep13">
<fieldset>
	<s:if test="%{vehicleList!=null && !vehicleList.isEmpty() }">
	<div class="grid_13" align="right"  data-target="mapPaginationCont">
		<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
	</div>
					<div id="results" class="grid_13 th">
						<div class="grid_2">
							Route Name
						</div>
						<div class="grid_2">
							Available Seats
						</div>
						<div class="grid_3">
							Destination Point
						</div>
						<div class="grid_3">
							Arrival Point
						</div>
						<div class="grid_2">
							Driver Name
						</div>
						<div class="grid_1">
							Contact Information
						</div>
					</div>
					<div id="mapPaginationCont">
					<s:iterator value="vehicleList">
						<div class="grid_13 row">
							<div class="grid_2">
								<s:property value="routeName" />
							</div>
							<div class="grid_2">
								<s:property value="availableNoOfSeats" />
							</div>
							<div class="grid_3">
								<s:property value="routePointName" />
							</div>
							<div class="grid_3">
								<s:property value="routeEndName" />
							</div>
							<div class="grid_2">
								<s:property value="helperPersonFullName" />
							</div>
							<div class="grid_1">
								<s:property value="driverMobileNumber" />
							</div>
						</div>
					</s:iterator>
				</div>
	</s:if>
	<s:else>Currently there are no routes assigned to the vehicle.</s:else>
</fieldset>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
  $("#mapPaginationCont").pagination();
			loadAllRoutes();
			/*$('#destination').keyup(function(e) {
			if(e.keyCode == 13) {
				searchRouteMap();
			 }
		  });*/
		});
		
  function loadAllRoutes(){
   map = new GMap2(document.getElementById("viewRoutes"));
   map.setCenter(new GLatLng(13.0412298,80.1814062), 12);
   map.addControl(new GSmallMapControl());
   var mapURL = jQuery.url.getChatURL("/admin/ajaxLoadAllRotes.do");
   var directions=null;
   var waypoints=null;
	$.ajax( {
		url : mapURL,
		cache : false,
		dataType : 'json',
		success : function(response) {
		if(isNonEmpty(response)){
			var routesList = response.routeList;
			var boardingPointsList='';
			directions=new Array(routesList.length);
			for(var i=0;i<routesList.length;i++){
				waypoints=new  Array();
				var k=0;
				waypoints[k]=routesList[i].routePointName+",Chennai, Tamilnadu, India";;
				boardingPointsList=routesList[i].routeBoardingPointList;
				k++;
				if(isNonEmpty(boardingPointsList)){
					for(var j=0;j<boardingPointsList.length;j++){
						waypoints[k]=boardingPointsList[j].bordingPointName+",Chennai, Tamilnadu, India";
						k++;
					}
				}
				waypoints[k]=routesList[i].routeNumber+",Chennai, Tamilnadu, India";
				directions[i]= new GDirections(map);	
				directions[i].loadFromWaypoints(waypoints);
			}
		}
			}
		});
  }
	function searchRouteMap(){
	  var place = $('#destination').val();
	  if (place == null || place.trim() == '' || place == 'Enter Place') {
		alert("Please enter place.");
		} else {
	 	  loadAllRoutes();
	 	  var directions=null;
		  var waypoints=null;
		  waypoints=new Array();
		  waypoints[i]=waypoints[k];
		 // waypoints[1]=place+",chennai, Tamil Nadu,India";
	 	  directions = new GDirections(map);
	 	  GEvent.addListener(directions,'load',function()
	 	  {
           directions.getPolyline().setStrokeStyle({
                 color: "#800517",
                 opacity: '1',
                 weight: '4'
           });
		 	/*  setTimeout(function() {
	          for (var i=0; i < directions.getNumRoutes()+1; i++) {
	             changeImage(i,directions,waypoints);
	           }
	          },0);*/
	  	 });
      	 directions.loadFromWaypoints(waypoints);
	   }
	  }
	  function changeImage(k,directions,waypoints) {        
	  	 var marker = directions.getMarker(k);
		 var html =  waypoints[k];
         GEvent.addListener(marker, "click", function() {
          marker.openInfoWindowHtml(html);
           });
       }
</script>
