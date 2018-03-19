<%@ include file="/common/taglibs.jsp"%>
    <head>
        <title>URT Apps | All Events</title>
        <style type="text/css">
        .grid_31{
				      width: 647px;
					border: 0px solid #CCCCCC;
					padding: 10px;
					
			}
        </style>
    </head>
    <body />
            <div class="wrapper container_16">
                <!-- wrapper begins -->
                <div class="block grid_4">
                    <div class="block_head">
                        <h2>
                            Left Nav
                        </h2>
                    </div>
                    <div class="block_content" id="sideMenu"
                        style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
                        <ul style="padding-left: 0px;">
                            <li>
                                <a href="#">Item1</a>
                            </li>
                            <li>
                                <a href="#">Item2</a>
                            </li>
                            <li>
                                <a href="#">Item3</a>
                            </li>
                            <li>
                                <a href="#">Item4</a>
                            </li>
                            <li>
                                <a href="#">Item5</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="block grid_12">

                    <div class="block_head">
                        <h2>
                            All Events
                        </h2>
                    </div>
                    <!-- .block_head ends -->

                        <div class="block_content">
           
                           
                       
                                           
                        <s:iterator value="eventDetailsList">
                        <div class="grid_11">
                                <div class="grid_31">
                                 <strong>Title:</strong><br />
                                    <p><s:property value="title"/></p>
                                    <strong>Start Date:</strong><br/>
                                    <p><s:property value="startDate"/></p>
                                    <strong>Start Time</strong><br/>
                                    <p><s:property value="startTime"/></p>
                                    <strong>End Time</strong><br/>
                                    <p><s:property value="endTime"/></p>
                                    <strong>Description</strong><br/>
                                    <p><s:property value="message" /></p>
                                    
                                </div>   
                        </div>
                    </s:iterator>
               
                <s:else>There are no Pages.
                    </s:else>
            </div>
                </div>
                <!-- .leftcol ends -->
            </div>

