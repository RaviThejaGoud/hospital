function URTSubForm() {
			this.host = 'eazyschool.in',
			this.ssl = 'unset',
			this.autoResize = true,
			this.height = 'auto',
			this.width = '100%',
			this.disabled = false,
			this.formHash ='',
			this.frameUrl = '',
			this.product = '',
			this.productCId = '',
			this.product_url = {
				'signup' : 'ajaxDoLoginForm.do',
			},
			this.defaultValues = '',
			this.frameUrl = '',
			this.resizeDone = '',
			this.initialize = function(params) {
				for (key in params) {
					this[key] = params[key];
				}
				this.formHash +=Math.floor(Math.random()*1000000);
				this.generateFrameUrl();
			},
			this.generateFrameUrl = function() {
				var url = '';
				url += this.determineSecurity();
				url += this.host + '/' + this.product + '/'
						+ this.product_url[this.product];
				url += '?id=' + this.custId;
				if (this.productCId)
					url += '&campaignId=' + this.productCId;
				this.frameUrl = url;
				if (this.type)
					url += '&type=' + this.type;
				this.frameUrl = url;
			},
			this.isFunction = function(object) {
				return typeof object === "function";
			},
			this.determineSecurity = function() {
				if (this.ssl == true)
					return 'https://';
				else
					return 'http://';
			},
			this.generateFrameMarkup = function() {
				var scroll = 'no';
				var src = '<iframe id="urtForm'
						+ this.formHash
						+ '" height="'
						+ this.height
						+ '" allowTransparency="true" frameborder="0" scrolling="'
						+ scroll + '" style="width:' + this.width
						+ ';border:none"' + ' src="' + this.frameUrl + '">';
				return src;
			},
			this.display = function() {
				document.write(this.generateFrameMarkup());
			},
			this.addEvent = function(obj, type, fn) {
				if (obj.attachEvent) {
					obj["e" + type + fn] = fn;
					obj[type + fn] = function() {
						obj["e" + type + fn](window.event)
					};
					obj.attachEvent("on" + type, obj[type + fn]);
				} else {
					obj.addEventListener(type, fn, false);
				}
			}
}