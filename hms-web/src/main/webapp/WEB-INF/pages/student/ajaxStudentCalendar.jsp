<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">
	<div class="portlet box blue calendar">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-reorder"></i>Calendar
			</div>
		</div>
		<div class="portlet-body light-grey">
			<div class="row">
				<div class="col-md-3 col-sm-12">
					<h3 class="event-form-title">Draggable Events</h3>
					<div id="external-events">
						<form class="inline-form">
							<input type="text" value="" class="form-control" placeholder="Event Title..." id="event_title"/><br/>
							<a href="javascript:;" id="event_add" class="btn green">Add Event</a>
						</form>
						<hr/>
						<div id="event_box">
						</div>
						<label for="drop-remove">
						<input type="checkbox" id="drop-remove"/>remove after drop </label>
						<hr class="visible-xs"/>
					</div>
				</div>
				<div class="col-md-9 col-sm-9">
					<div id="calendar" class="has-toolbar">
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function() {       
	   Calendar.init();
	   changePageTitle("Calendar");
	});
</script>