<%@ include file="/common/taglibs.jsp"%>
 
<div class="block grid_12" id="groupList">
	<div class="block_head">
		<h2>
			Embed Codes
		</h2>
	</div>
	<!-- .block_head ends -->

	<div class="block_content" id="editGroupList">
		<p><b style="margin-left:10px">Place the following code in the link on your website:</b></p>
			<div style="margin-left:10px;" id="neighborhood">
				&lt;script type="text/javascript" charset="utf-8" &gt;
					<br />
						<div style="margin-left:20px">
							var host = (("https:" == document.location.protocol) ? "https://secure.": "http://");<br />
							document.write(unescape("%3Cscript src='<s:property value="hostUrl"/>/scripts/common/form.js' type='text/javascript'%3E%3C/script%3E"));<br />
						</div>
					&lt;/script&gt;
					<br />
				&lt;script type="text/javascript" charset="utf-8"&gt;
				<br /><div style="margin-left:20px">
					var urtGILite = new URTSubForm();<br />
					urtGILite.initialize({<br />
					'product':'signup',<br />
					'custId' : '3',
					'scrolling':'yes',<br />
					'height':'1450'});<br />
					urtGILite.display();<br />
					</div>
				&lt;/script&gt;
			</div>
	</div>
</div>
<script type="text/javascript">
 document.title='Eazy School | Embed Codes';
</script>