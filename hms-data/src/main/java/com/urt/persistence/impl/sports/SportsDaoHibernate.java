package com.urt.persistence.impl.sports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.sports.SportsDao;

public class SportsDaoHibernate extends UniversalHibernateDao implements SportsDao {

	private static final Log log = LogFactory.getLog(SportsDaoHibernate.class);

}
