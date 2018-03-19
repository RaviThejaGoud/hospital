var URTPRD;
if (URTPRD == undefined) {
	URTPRD = {};
}

if (!URTPRD.initialized) {

	URTPRD.gId = function(id) {
		return document.getElementById(id);
	};

	URTPRD.hasClassName = function(element, className) {
		var elementClassName = element.className;

		return (elementClassName.length > 0 && (elementClassName == className || new RegExp(
				"(^|\\s)" + className + "(\\s|$)").test(elementClassName)));
	};

	URTPRD.addClassName = function(element, className) {
		if (!URTPRD.hasClassName(element, className))
			element.className += (element.className ? ' ' : '') + className;
		return element;
	};

	URTPRD.removeClassName = function(element, className) {
		var newClass = URTPRD.strip(element.className.replace(new RegExp(
				"(^|\\s+)" + className + "(\\s+|$)"), ' '));
		element.className = newClass;
		return element;
	};

	URTPRD.strip = function(string) {
		return string.replace(/^\s+/, '').replace(/\s+$/, '');
	};

	URTPRD.add_css = function(css_content) {
		var head = document.getElementsByTagName('head')[0];
		var style = document.createElement('style');
		style.type = 'text/css';

		if (style.styleSheet) {
			style.styleSheet.cssText = css_content;
		} else {
			rules = document.createTextNode(css_content);
			style.appendChild(rules);
		}
		head.appendChild(style);
	}

	URTPRD.initialized = true;
}

URTPRD.urt_product_widget = function(options) {
	this.widget_disabled = false;

	this.options = options;
	this.options.display = "overlay";
	this.is_ssl = ("https:" == document.location.protocol);

	if (this.is_ssl) {
		this.urt_product_base_url = this.local_ssl_base_url;
		this.asset_base_url = this.s3_ssl_base_url;
	} else {
		this.urt_product_base_url = this.local_base_url;
		this.asset_base_url = this.s3_base_url;
	}

	if (this.options.local_assets == true) {
		this.asset_base_url = this.urt_product_base_url;
	}

	this.query = [];

	if (this.options.custId) {
		this.query.push("custId=" + encodeURIComponent(this.options.custId));
	}

	if (this.options.campaignId) {
		this.query.push("campaignId="
				+ encodeURIComponent(this.options.campaignId));
	}
	
	if (this.options.productId) {
		this.query.push("productId="
				+ encodeURIComponent(this.options.productId));
	}

	if (this.options.style) {
		this.query.push("style=" + encodeURIComponent(this.options.style));
	}

	if (this.options.custom_css) {
		this.query.push("custom_css="
				+ encodeURIComponent(this.options.custom_css));
	}

	this.query_string = "?" + this.query.join("&");

	this.urt_product_url = this.urt_product_base_url
			+ "/campaign/ajaxSubscribeCampaigns.do" + this.query_string;
	
	this.options = options ? options : {};

	this.options.color = this.options.color ? this.options.color : '#222';

	if (this.options.display == 'overlay') {
		this.initial_iframe_url = this.empty_url();
		if (!this.options.width) {
			this.options.width = "658px";
		}
		if (!this.options.height) {
			this.options.height = "100%";
		}
	} else {
		this.initial_iframe_url = this.urt_product_url;
		if (!this.options.width) {
			this.options.width = "100%";
		}
		if (!this.options.height) {
			this.options.height = "400px";
		}
	}
	if (this.widget_disabled) {
		this.iframe_html = '<div id="urtprd_iframe" style="position:relative; top: 20px; margin:20px;background:orange;color:purple;font-size:72px; padding: 20px;">' + 'WIDGETS ARE UNAVAILABLE LOL' + '</div>';
	}

	else {
		this.iframe_html = '<iframe id="urtprd_iframe" allowTransparency="true" scrolling="no" frameborder="0" class="loading"'
				+ ' src="'
				+ this.initial_iframe_url
				+ '"'
				+ ' width="'
				+ this.options.width
				+ '"'
				+ ' height="'
				+ this.options.height
				+ '"'
				+ ' style="width: '
				+ this.options.width
				+ '; height: '
				+ this.options.height + ';"></iframe>';
	}
	var image_source = this.urt_product_base_url + '/images/'
			+ this.options.campignImage;
	this.tab_html = '<a href="#"   id="urtprd_tab" class="urtprd_tab_'
			+ this.options.placement + '" style="background-color:'
			+ this.options.color + '"><img src="' + image_source + '"/></a>';
	this.overlay_html = '<div id="urtprd_overlay" style="display:none">'
			+ '<div id="urtprd_container">'
			+ '<a href="#" id="urtprd_close" style="margin:0px;padding:0px;"></a>'
			+ this.iframe_html + '</div>' + '<div id="urtprd_screen"></div>'
			+ '</div>';
	if (this.options.display == 'overlay') {
		raw_css = "#urtprd_overlay {\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  z-index: 1000000;\n  position: fixed; }\n\n#urtprd_screen {\n  top: 0;\n  left: 0;\n  z-index: 1;\n  width: 100%;\n  position: fixed;\n  background-color: #000;\n  opacity: 0.45;\n  -moz-opacity: 0.45;\n  filter: alpha(opacity=45); }\n\n#urtprd_container {\n  width: 420px;\n  height: 320px;\n  margin: 0 auto;\n  z-index: 2;\n  position: relative; \n  background-color: #FFFFFF; }\n  #urtprd_container iframe {\n    width: 658px;\n    height: 100%;\n    margin: 20px;\n    background: transparent; }\n  #urtprd_container iframe.loading {\n    background: transparent url(https://ems.groupsinteractive.com/images/indicator.gif) no-repeat; }\n\na.urtprd_tab_right {\n  right: 0 !important;\n  left: auto !important;\n  margin-right: 0 !important;\n  margin-left: auto !important;\n  width: 35px !important; }\n  a.urtprd_tab_right:hover {\n    width: 38px !important;\n    margin-right: 0 !important;\n    margin-left: auto !important; }\n\na.urtprd_tab_bottom {\n  top: auto!important;\n  bottom: 0 !important;\n  left: 20% !important;\n  height: 38px !important;\n  width: 102px !important;\n  background-position: 0 -102px !important;\n  margin-bottom: -7px !important;\n  margin-left: auto !important; }\n  a.urtprd_tab_bottom:hover {\n    margin-bottom: -4px !important;\n    margin-left: auto !important; }\n\na.urtprd_tab_hidden {\n  display: none !important; }\n\na#urtprd_close {\n padding:0px;\n position: absolute;\n  cursor: pointer;\n  outline: none;\n  top: -14px;\n  right: -14px;\n  z-index: 4;\n  width: 28px;\n  height: 28px;\n  overflow: hidden;\n  background-image: url(http://ems.groupsinteractive.com/images/fancybox.png);\n  background-position: -42px 0px;\n  _background: none;\n  _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='https://s3.amazonaws.com/getsatisfaction.com/images/urt_product-close.png', sizingMethod='crop'); }\n  a#urtprd_close:hover {\n padding:0px; \n background-image: url(http://ems.groupsinteractive.com/images/fancybox.png);\n border:none;\n background-position: -42px 0; }\n\n.urt_product_tab_on embed, .urt_product_tab_on select, .urt_product_tab_on object {\n  visibility: hidden; }\n";
		replacer_regex = new RegExp(this.s3_ssl_base_url, "g");
		translated_css = raw_css.replace(replacer_regex, this.asset_base_url);
		URTPRD.add_css(translated_css);

		if (this.options.container) {
			var container_el = URTPRD.gId(this.options.container);
			container_el.innerHTML = this.tab_html + this.overlay_html;
		} else {
			document.write(this.tab_html);
			document.write(this.overlay_html);
		}

		var urt_product_obj = this;
		URTPRD.gId('urtprd_tab').onclick = function() {
			urt_product_obj.show();
			return false;
		}
		URTPRD.gId('urtprd_close').onclick = function() {
			urt_product_obj.hide();
			return false;
		}
		URTPRD.gId('urtprd_iframe').setAttribute("src", this.empty_url());

	} else {
		if (this.options.container) {
			var container_el = URTPRD.gId(this.options.container);
			container_el.innerHTML = this.iframe_html;
		} else {
			document.write(this.iframe_html);
		}
	}

};

URTPRD.urt_product_widget.prototype = {
	local_base_url : "http://ems.groupsinteractive.com",
	local_ssl_base_url : "https://ems.groupsinteractive.com",
	s3_base_url : "http://ems.groupsinteractive.com",
	s3_ssl_base_url : "https://ems.groupsinteractive.com",

	asset_url : function(asset) {
		return this.asset_base_url + asset;
	},

	empty_url : function() {
		return this.asset_url("/images/transparent.gif");
	},

	set_position : function() {
		this.scroll_top = document.documentElement.scrollTop
				|| document.body.scrollTop;
		this.scroll_height = document.documentElement.scrollHeight;
		this.client_height = window.innerHeight
				|| document.documentElement.clientHeight;

		URTPRD.gId('urtprd_screen').style.height = this.scroll_height + "px";
		URTPRD.gId('urtprd_container').style.top = this.scroll_top
				+ (this.client_height * 0.1) + "px";
	},

	show : function() {
		URTPRD.gId('urtprd_iframe').setAttribute("src", this.urt_product_url);
		if (URTPRD.gId('urtprd_iframe').addEventListener) {
			URTPRD.gId('urtprd_iframe').addEventListener("load", this.loaded,
					false);
		} else if (URTPRD.gId('urtprd_iframe').attachEvent) {
			URTPRD.gId('urtprd_iframe').attachEvent("onload", this.loaded);
		}
		this.set_position();

		URTPRD.addClassName(document.getElementsByTagName('html')[0],
				'urt_product_tab_on');
		URTPRD.gId('urtprd_overlay').style.display = "block";
	},

	hide : function() {
		if (URTPRD.gId('urtprd_iframe').addEventListener) {
			URTPRD.gId('urtprd_iframe').removeEventListener("load",
					this.loaded, false);
		} else if (URTPRD.gId('urtprd_iframe').attachEvent) {
			URTPRD.gId('urtprd_iframe').detachEvent("onload", this.loaded);
		}

		URTPRD.gId('urtprd_overlay').style.display = "none";
		URTPRD.gId('urtprd_iframe').setAttribute("src", this.empty_url());
		URTPRD.gId('urtprd_iframe').className = "loading";

		URTPRD.removeClassName(document.getElementsByTagName('html')[0],
				'urt_product_tab_on');
	},

	loaded : function() {
		URTPRD.gId('urtprd_iframe').className = "loaded";
	},

	get_fastpass_url : function(options) {
		if (options.fastpass) {
			return options.fastpass;
		}
		var script_tags = document.getElementsByTagName('script');
		for ( var i = 0; i < script_tags.length; i++) {
			var tag = script_tags[i];
			if (tag.src.match(/\/fastpass/ && tag.src.match(/oauth/))) {
				return tag.src;
			}
		}
	}
}