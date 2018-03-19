package com.hyniva.mailchimp.service;

import com.ecwid.mailchimp.MailChimpAPIVersion;
import com.ecwid.mailchimp.MailChimpMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaingRelatedMethod;
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
@MailChimpMethod.Method(name = "campaignStats", version = MailChimpAPIVersion.v1_3)
public class CampaignReportMethod extends CampaingRelatedMethod<ListsResult> {
	
}
