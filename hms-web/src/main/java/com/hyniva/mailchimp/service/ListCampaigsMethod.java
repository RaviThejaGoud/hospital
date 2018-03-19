package com.hyniva.mailchimp.service;

import com.ecwid.mailchimp.MailChimpAPIVersion;
import com.ecwid.mailchimp.MailChimpMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaingRelatedMethod;
import com.ecwid.mailchimp.method.v1_3.list.ListsMethodFilters;
import com.ecwid.mailchimp.method.v1_3.list.ListsResult;


/**
 * See:
 * <a href="http://apidocs.mailchimp.com/api/1.3/campaigncreate.func.php">
 *      http://apidocs.mailchimp.com/api/1.3/campaigncreate.func.php
 * </a>
 *
 * @author Massimo Lusetti <mlusetti@gmail.com>
 *
 */
@MailChimpMethod.Method(name = "campaigns", version = MailChimpAPIVersion.v1_3)
public class ListCampaigsMethod extends CampaingRelatedMethod<ListsResult> {
	
	@Field
	  public ListsMethodFilters filters = null;

	  @Field
	  public Integer start = null;

	  @Field
	  public Integer limit = null;

	  @Field
	  public String sort_field = null;

	  @Field
	  public String sort_dir = null;
	
}
