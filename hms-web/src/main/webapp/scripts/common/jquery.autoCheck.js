/*
 * autoCheck
 * Copyright 2010-2011 Sreeramulu Jadapolu
 *
 * Version 1.0  -   Updated: Aug. 5, 2010
 *
 * This Plug-In will auto-check as you type. This should be used only to notify
 * user whether key word given user is already exist or not.
 * You can add multiple selections and remove them on
 * the fly. It supports keybord navigation (UP + DOWN + RETURN), as well
 * as multiple autoCheck fields on the same page.
 *
 * Inspied by the AutoSuggest plugin by: Drew Wilson
 */

(function($) {
	$.fn.autoCheck = function(data, options) {
		var defaults = {
			asHtmlID : false,
			emptyText : "Available",
			queryParam : "keyWord",
			extraParams : "",
			minChars : 1,
			keyDelay : 1,
			resultsHighlight : true,
			neverSubmit : false,
			selectionLimit : false,
			showResultList : true,
			start : function() {
			},
			selectionClick : function(elem) {
			},
			selectionAdded : function(elem) {
			},
			selectionRemoved : function(elem) {
				elem.remove();
			},
			formatList : false, // callback function
			beforeRetrieve : function(string) {
				return string;
			},
			retrieveComplete : function(data) {
				return data;
			},
			resultClick : function(data) {
			},
			resultsComplete : function() {
			}
		};
		var opts = $.extend(defaults, options);

		var d_type = "object";
		var d_count = 0;
		if (typeof data == "string") {
			d_type = "string";
			var req_string = data;
		} else {
			var org_data = data;
			for (k in data)
				if (data.hasOwnProperty(k))
					d_count++;
		}
		if ((d_type == "object" && d_count > 0) || d_type == "string") {
			return this.each(function(x) {
				if (!opts.asHtmlID) {
					x = x + "" + Math.floor(Math.random() * 100);
					// this ensures there will be unique IDs on the page if
					// autoCheck() is called multiple times
					var x_id = "as-input-" + x;
				} else {
					x = opts.asHtmlID;
					var x_id = x;
				}
				opts.start.call(this);
				var input = $(this);
				input.attr("autocomplete", "off").addClass("as-input").attr(
						"id", x_id);
				var input_focus = false;

				// Setup basic elements and render them to the DOM
				input
						.wrap(
								'<div class="as-selections" id="as-selections-' + x + '"></div>')
						.wrap(
								'<div class="as-original" id="as-original-' + x + '"></div>');
				var selections_holder = $("#as-selections-" + x);
				var org_li = $("#as-original-" + x);
				var results_holder = $(
						'<div class="as-results" id="as-results-' + x + '"></div>')
						.hide();
				var values_input = $('<input type="hidden" class="as-values" name="as_values_'
						+ x + '" id="as-values-' + x + '" />');

				input.after(values_input);
				selections_holder.click(function() {
					input_focus = true;
					input.focus();
				}).mousedown(function() {
					input_focus = false;
				}).after(results_holder);

				var timeout = null;
				var prev = "";
				var totalSelections = 0;
				var tab_press = false;

				// Handle input field events
				input.focus(
						function() {
							if (values_input.val() == "") {
								$(this).val();
							} else if (input_focus) {
								if ($(this).val() != "") {
									results_holder.css("width",
											selections_holder.outerWidth());
									results_holder.show();
								}
							}
							input_focus = true;
							return true;
						}).blur(function() {
					/*if (input_focus) {
						results_holder.hide();
					}*/
				}).keydown(function(e) {
					// track last key pressed
						lastKeyPressCode = e.keyCode;
						first_focus = false;
						switch (e.keyCode) {
						case 38: // up
							e.preventDefault();
							break;
						case 40: // down
							e.preventDefault();
							break;
						case 8: // delete
							if (input.val() == "") {
								var last = values_input.val().split(",");
								last = last[last.length - 2];
								selections_holder.children().not(org_li.prev())
										.removeClass("selected");
								if (org_li.prev().hasClass("selected")) {
									values_input.val(values_input.val()
											.replace("," + last + ",", ","));
									opts.selectionRemoved.call(this, org_li
											.prev());
								} else {
									opts.selectionClick.call(this, org_li
											.prev());
									org_li.prev().addClass("selected");
								}
							}
							if (input.val().length == 1) {
								results_holder.hide();
								prev = "";
							}
							if ($(":visible", results_holder).length > 0) {
								if (timeout) {
									clearTimeout(timeout);
								}
								timeout = setTimeout(function() {
									keyChange();
								}, opts.keyDelay);
							}
							//using for BackSpace
							var string = input.val().slice(0, -1);//removing last character by pressing backSpace 
							if (string == prev)
								return;
							prev = string;
							if (string.length >= opts.minChars) {
								selections_holder.addClass("loading");
								if (d_type == "string") {
									if (opts.beforeRetrieve) {
										string = opts.beforeRetrieve.call(this, string);
									}
									$.getJSON(req_string + "?" + opts.queryParam + "=" + encodeURIComponent(string),
										function(data) {
											d_count = 0;
											var new_data = opts.retrieveComplete.call(this, data);
											processData(new_data, string);
										});
								} else {
									if (opts.beforeRetrieve) {
										string = opts.beforeRetrieve.call(this, string);
									}
									processData(org_data, string);
								}
							} else {
								selections_holder.removeClass("loading");
								results_holder.hide();
							}
							break;
						case 9:
						case 188: // tab or comma
							tab_press = true;
							break;
						case 13: // return
							tab_press = false;
							var active = $("li.active:first", results_holder);
							if (active.length > 0) {
								active.click();
								results_holder.hide();
							}
							if (opts.neverSubmit || active.length > 0) {
								e.preventDefault();
							}
							break;
						default:
							if (opts.showResultList) {
								if (timeout) {
									clearTimeout(timeout);
								}
								timeout = setTimeout(function() {
									keyChange();
								}, opts.keyDelay);
							}
							break;
						}
					});

				function keyChange() {
					// ignore if the following keys are pressed: [del] [shift]
					// [capslock]
					if (lastKeyPressCode == 46
							|| (lastKeyPressCode > 8 && lastKeyPressCode < 32)) {
						return results_holder.hide();
					}
					var string = input.val();  // .replace(/[\\]+|[\/]+/g, "");
					if (string == prev)
						return;
					prev = string;
					if (string.length >= opts.minChars) {
						selections_holder.addClass("loading");
						if (d_type == "string") {
							if (opts.beforeRetrieve) {
								string = opts.beforeRetrieve.call(this, string);
							}
							$.getJSON(req_string + "?" + opts.queryParam + "="
									+ encodeURIComponent(string),
									function(data) {
										d_count = 0;
										var new_data = opts.retrieveComplete
												.call(this, data);
										processData(new_data, string);
									});
						} else {
							if (opts.beforeRetrieve) {
								string = opts.beforeRetrieve.call(this, string);
							}
							processData(org_data, string);
						}
					} else {
						selections_holder.removeClass("loading");
						results_holder.hide();
					}
				}
				var num_count = 0;
				function processData(data, query) {

					var matchCount = 0;
					results_holder.html("").hide();
					if (data) {
						// var autoCheckJson = data.jsonResult;
						var responseStatus = data.autoCheck;
					    if (responseStatus == '1')
							matchCount = 1;
						if(responseStatus == '2')
							matchCount = 2;
					}
					selections_holder.removeClass("loading");
					if (matchCount <= 0) {
						results_holder
								.html('<p class="word-available">' + opts.emptyText + '</p>');
						$('.submit').attr("disabled", false);
					}else if (matchCount == 2){
						results_holder.html('<p class="word-taken">Current Password not matched.</p>');
						$('.submit').attr("disabled", true);
					}else {
						results_holder
								.html('<p class="word-taken">Already taken!!!</p>');
						$('.submit').attr("disabled", true);
					}
					results_holder.css("width", selections_holder.outerWidth());
					results_holder.show();
					opts.resultsComplete.call(this);
				}
			});
		}
	}
})(jQuery);