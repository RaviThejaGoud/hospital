package com.urt.persistence.impl.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringUtil;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.util.common.RayGunException;

public class UniversalHibernateDao extends HibernateDaoSupport implements UniversalDao
{
	private static final Log log = LogFactory.getLog(UniversalHibernateDao.class);
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	public Session getSession()
	{
		//sessionFactory.openSession();
		return  sessionFactory.getCurrentSession(); //getSessionFactory().getCurrentSession();
	}
	
	public void save(Object object) throws DataAccessException{
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			/*//Session session=sessionFactory.getCurrentSession();
			getSession().merge(object);
			getSession().flush();
			getSession().clear();
			getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			getHibernateTemplate().merge(object);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();*/
			
			   
		        session.persist(object);
		        session.flush();tx.commit();tx.commit();
		        
		        
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new URTDataAccessException(e.getMessage());
		}
		finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}	
	
	public Object get(Class clazz, Serializable id) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			/*Object obj=  session.get(clazz, id);//getHibernateTemplate().getSessionFactory().getCurrentSession().get(clazz, id);
			if (!ObjectFunctions.isNullOrEmpty(obj)) {
				return obj;
			}
			session.flush();tx.commit();*/

			return session.get(clazz, id);
			
		
			/*List objs= (List) session.createQuery("from " + clazz.getName() + " where id=" + id).list();
			if (!ObjectFunctions.isNullOrEmpty(objs)) {
				return objs.get(0);
			} */
			//session.flush();tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			//getSession().flush();
			//getSession().clear();
			//session.flush();
			//tx.commit();
			if(session != null) {
			session.close(); // extra work 
			} 
		}
		//return null;
	}
	
	public Object get(Class clazz,  String clause) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			//List objs= getHibernateTemplate().find("from " + clazz.getName() + " where " + clause);
			log.debug("Query:"+"from " + clazz.getName() + " where " + clause);
			List objs= (List) session.createQuery("from " + clazz.getName() + " where " + clause).list();
			if (!ObjectFunctions.isNullOrEmpty(objs)) {
				return objs.get(0);
			} 
			/*else {
				return null;
			}*/
			//getHibernateTemplate().flush();
			//getHibernateTemplate().clear();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
			//session.flush();
			//tx.commit();
			session.close(); // extra work 
			} 
		}
		return null;
	}
	
	public Object get(Class clazz, Serializable id, String columnName) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			//List objs= getHibernateTemplate().find("from " + clazz.getName() + " where " + columnName + "= "+id);
			log.debug("Query:"+"from " + clazz.getName() + " where " + columnName + "= "+id);
			
			List objs= (List) session.createQuery("from " + clazz.getName() + " where " + columnName + "= "+id).list();
			if (!ObjectFunctions.isNullOrEmpty(objs)) {
				return objs.get(0);
			}
			//getHibernateTemplate().flush();
			//getHibernateTemplate().clear();
			
			/*else {
				return null;
			}*/
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
				//session.flush();
				//tx.commit();
				session.close(); // extra work 
			} 
		}
		return null;
	}
	@Transactional
	public List getAll(Class clazz) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			/*Criteria criteria = session.createCriteria(clazz);
			List list =criteria.list();
			return list;*/
			return getHibernateTemplate().loadAll(clazz);
			 
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
				//session.flush();
				//tx.commit();
			session.close(); // extra work 
			} 
		}
	}
	
	public void remove(Class clazz, Serializable id) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			session.delete(get(clazz, id));
			session.flush();tx.commit();
			/*getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			getHibernateTemplate().delete(get(clazz, id));
			getHibernateTemplate().flush();*/
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}	
		finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	@Transactional
	public Object saveObject(Object o) throws DataAccessException {
		//Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try { 
			//Transaction tx=session.beginTransaction();
			//session.merge(o);
			/*session.saveOrUpdate(o);
			session.flush();tx.commit();*/
			getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			//o = getHibernateTemplate().merge(o);
			//getHibernateTemplate().flush();
			//getHibernateTemplate().clear();
			getHibernateTemplate().saveOrUpdate(o);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
		} catch (Exception e) { 
			/*if(tx != null) {
				tx.rollback();
				} */
			e.printStackTrace();
			throw new URTDataAccessException(e.getMessage());
		}
		/*finally {
			if(session != null) {
				session.close(); // extra work 
			} 
		}*/
		return o;
	}
	@Transactional
	public Object mergeObject(Object o) throws DataAccessException {
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		Object newObj =null;
		try { 
			//Transaction tx=session.beginTransaction();
			newObj = session.merge(o);
			session.flush();tx.commit();
			/*getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			newObj = getHibernateTemplate().merge(o);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();*/
		} catch (Exception e) {
			/*if(tx != null) {
				tx.rollback();
				} */
			e.printStackTrace();
			throw new URTDataAccessException(e.getMessage());
		}
		finally {
			if(session != null) {
				session.close(); // extra work 
			} 
		}
		return newObj;
	}
	
	public AcademicYear getCurrentAcademicYear(String status,long custId){
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
			List academicYear=(List) session.createQuery("from AcademicYear where status = '"+ status +"' and custId="+custId).list();
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				return (AcademicYear)academicYear.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		finally {
			if(session != null) {
				//session.flush();tx.commit();
			session.close(); // extra work 
			} 
		}
		return null;
	}
	
	public Role getRoleByName(String rolename) {
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
        List roles =(List) session.createQuery("from Role where name= '"+ rolename +"'").list();
        if (!roles.isEmpty()) {
        	return (Role) roles.get(0);
        } /*else {
            
        }*/
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
        finally {
			if(session != null) {
				 //session.flush();tx.commit();
				 session.close(); // extra work 
			} 
		}
		return null;
	}
	
	public List getAll(Class clazz,  String clause) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();	
		try {
		     //   Transaction tx = session.beginTransaction();
			log.debug("Query:"+"from " + clazz.getName() + " where " + clause);
			
		        List objList = session.createQuery("from " + clazz.getName() + " where " + clause).list();
		        if(!ObjectFunctions.isNullOrEmpty(objList)){
		        	return objList;
		        }
		        else if(ObjectFunctions.isNullOrEmpty(objList)){
					return new ArrayList();
				}
		        
		        return null;
				//return getHibernateTemplate().find("from " + clazz.getName() + " where " + clause);
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				if(session != null) {
					//session.flush();
					//tx.commit();
					if(session.isOpen())
				session.close();	// extra work 
				} 
			}
	}
	
	public int remove(String clazz, String clause) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			log.debug("Query:"+"delete from "+clazz+" where "+clause);
			
			Query qry = session.createSQLQuery("delete from "+clazz+" where "+clause);
			
			//return qry.executeUpdate();
			int res = qry.executeUpdate();
			session.flush();tx.commit();
			return res;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	public int getCount(String clazz, String clause)  throws DataAccessException{
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from "+clazz+" where "+clause);
				log.debug("Query:"+queryBuff.toString());
				Number count = (Number)session.createSQLQuery(queryBuff.toString()).uniqueResult();
				//session.flush();tx.commit();
				if(!ObjectFunctions.isNullOrEmpty(count)){
					return count.intValue();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		  finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
			return 0;
	}
	
	public List getAll(String sqlQuery) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			log.debug("Query:"+sqlQuery);
			List objList = session.createSQLQuery(sqlQuery).list();
			if(!ObjectFunctions.isNullOrEmpty(objList)){
	        	return objList;
	        }
	        else if(ObjectFunctions.isNullOrEmpty(objList)){
				return new ArrayList();
			}
	        
	        return null;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
				//session.flush();tx.commit();
				if(session.isOpen())
			session.close(); // extra work 
			} 
		}
	}
	
	public Object[] get(String sqlQuery) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			log.debug("Query:"+sqlQuery);
			List dataList=session.createSQLQuery(sqlQuery).list();
			 if(ObjectFunctions.isNullOrEmpty(dataList))
				 return null;
			 else
				 return (Object[]) dataList.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(session != null) {
				 //session.flush();tx.commit();
			session.close(); // extra work 
			} 
		}
	}
	
	public List getAllHqlQuery(String hqlQuery) throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			log.debug("Query:"+hqlQuery);
			List objList  = session.createQuery(hqlQuery).list();
			if(!ObjectFunctions.isNullOrEmpty(objList)){
				return objList;
			}
			//return null;
			 else if(ObjectFunctions.isNullOrEmpty(objList)){
					return new ArrayList();
				}
		} catch (HibernateException e) {
			e.printStackTrace();
			//throw e;
			return null;
		}
		finally {
			if(session != null) {
				if(session.isOpen())
				//session.flush();tx.commit();
			session.close(); // extra work 
			} 
		}
		return null;
	}
	public int getCountForGroupByClause(String clazz, String clause)  throws DataAccessException{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();  
		try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from "+clazz+" where "+clause);
				log.debug("Query:"+queryBuff.toString());
				List resultList=session.createSQLQuery(queryBuff.toString()).list();
				if(ObjectFunctions.isNotNullOrEmpty(resultList)){
					return resultList.size();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		  finally {
				if(session != null) {
					//session.flush();tx.commit();
				session.close(); // extra work 
				} 
			}
			return 0;
	}
	
	/*public List<SchoolHolidays> getHolidaysListByMonthIdForAttendance(int monthId, long academicYearId, String holidayYear, Date date,long customerId, long classId, String staffOrStudent) {
		
		try {
				StringBuffer sb = new StringBuffer();
				sb.append("select description from schoolHolidays where monthId=" + monthId);
				sb.append(" and academicYearId = " + academicYearId);
				sb.append(" and custId = " + customerId);
				sb.append(" and yearId = '" + holidayYear+"'");
				// include class and sectionId
				if (monthId > 0)
					sb.append(" and monthId = " + monthId);
	
				if("ST".equalsIgnoreCase(staffOrStudent))
					sb.append(" and classId = " + classId);
				
				if (!ObjectFunctions.isNullOrEmpty(date)) {
					sb.append(" and ( holidayDate = '");
					sb.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, date));
					sb.append("') ");
				}
				 return getSession().createSQLQuery(sb.toString()).addScalar("description").setResultTransformer( Transformers.aliasToBean(SchoolHolidays.class)).list();
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return  null;
	}*/
	
	public User getUserEmailByUserName(String email){

		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
	        StringBuffer queryString = new StringBuffer();
	        queryString.append("from User where ");
	        queryString.append(" username ='");
	        queryString.append(email);
	        queryString.append("'");
	    	log.debug("Query:"+queryString.toString());
	        List usersList =  (List) session.createQuery(queryString.toString()).list();
	        //session.flush();tx.commit();
	        if(!ObjectFunctions.isNullOrEmpty(usersList))
	        {
	            return (User)usersList.get(0);
	        }
	        return null;
	    } catch (RuntimeException re) {
	        throw re;
	    }
	    finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	@Transactional 
	public Object saveOrUpdateObject(Object object) {
		//Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
			getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			getHibernateTemplate().saveOrUpdate(object);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
			
			
		        //session.merge(object);
		      /* session.saveOrUpdate(object);
		        session.flush();
		        tx.commit();*/
			 return object;
		}
		catch (HibernateException ex) {
			/*if(tx != null) {
				tx.rollback();
				} */
			ex.printStackTrace();
			throw new URTDataAccessException(ex.getMessage());
		}
		finally {
			/*if(session != null) {
			session.close(); // extra work 
			} */
		}
	}
	
	
	public User getUserByUserName(String username) 
	{
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
		log.debug("Query:"+"from User where username= '"+ username +"' OR secondaryUsername='"+username+"' ");
		List users = (List) session.createQuery("from User where username= '"+ username +"' OR secondaryUsername='"+username+"' ").list();
		/*session.flush();tx.commit();
		if (ObjectFunctions.isNullOrEmpty(users)) {
			 return null;
		} else {
			User user = (User) users.get(0);
			users=null;
			return user;
		}*/
		
		if (!ObjectFunctions.isNullOrEmpty(users)) {
			User user = (User) users.get(0);
			users=null;
			return user;
		}//session.flush();tx.commit();
		}
		catch (HibernateException ex) {
			ex.printStackTrace();
			throw new URTDataAccessException(ex.getMessage());
		}
		finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
		return null;
	}
	
	public CommonType getCommonType(long custId,String type,String mediumId){
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
			log.debug("Query:"+"from CommonType where custId="+custId+" and type='"+type+"' and skillTypeName='"+mediumId+"'");
			List commonTypeList = (List) session.createQuery("from CommonType where custId="+custId+" and type='"+type+"' and skillTypeName='"+mediumId+"'").list(); 
			//session.flush();tx.commit();
			 if (!ObjectFunctions.isNullOrEmpty(commonTypeList)) {
					return (CommonType) commonTypeList.get(0);
				} 
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		 return null; 
			
	 }
	
	public CastSettings getCastNames(String castName,long custId){
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction(); 
		try{
			log.debug("Query:"+"from CastSettings where castName='"+castName+"' and custId="+custId);
			List castList = (List) session.createQuery("from CastSettings where castName='"+castName+"' and custId="+custId).list();
			 if (!ObjectFunctions.isNullOrEmpty(castList)) {
					return (CastSettings) castList.get(0);
				} 
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 finally {
				if(session != null) {
					//session.flush();
					//tx.commit();
				session.close(); // extra work 
				} 
			}
		 return null;
	 }
	 public SubCastSettings getSubCast(long custId,String subCastName,long castId){
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		 try{
			 log.debug("Query:"+"from SubCastSettings where subCastName='"+subCastName+"' and custId="+custId+" and castId="+castId);
			 List subCastList =(List) session.createQuery("from SubCastSettings where subCastName='"+subCastName+"' and custId="+custId+" and castId="+castId).list();
			 if (!ObjectFunctions.isNullOrEmpty(subCastList)) {
					return (SubCastSettings) subCastList.get(0);
				} 
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 finally {
				if(session != null) {
					 //session.flush();
					// tx.commit();
				session.close(); // extra work 
				} 
			}
		 return null;
	 }
	 
	 public List getAllByCustId(String clazz,long custId,long academicYearId) {
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
			try {
				List aList=null;
				if(academicYearId>0){
					//return (List) session.createQuery("from "+clazz+" where  custId="+custId+" and academicYearId="+academicYearId).list();
					aList = (List) session.createQuery("from "+clazz+" where  custId="+custId+" and academicYearId="+academicYearId).list();
				}
				else{
					//return (List) session.createQuery("from "+clazz+" where  custId="+custId).list();
					aList = (List) session.createQuery("from "+clazz+" where  custId="+custId).list();
				}
				if (!ObjectFunctions.isNullOrEmpty(aList)) {
					return aList;
				}

			} catch(Exception ex){
				ex.printStackTrace();			
			}
			finally {
				if(session != null) {
					//session.flush();tx.commit();
				session.close(); // extra work 
				} 
			}
			return null;
	}
	 public int getAllottedSmsCount(long custId,long academicYearId){
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		 try{
			 StringBuffer queryString = new StringBuffer();
	         queryString.append("select count(*) from student where custId="+custId);
	         queryString.append(" and academicYearId="+ academicYearId+" and description is null");
	         BigInteger count = (BigInteger)session.createSQLQuery(queryString.toString()).uniqueResult();
	         //session.flush();tx.commit();
	         if(!ObjectFunctions.isNullOrEmpty(count) && count.intValue() > 0){
		         log.debug(count);
		         int smsCount = count.intValue()*30;
		         log.debug(smsCount);
					if(!ObjectFunctions.isNullOrEmpty(smsCount) && smsCount > 0){
						return smsCount;
					}
				 log.debug(smsCount);
	         }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		 finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		return 0;
	 }
	 
	 public List<Student> getStudentList (StringBuffer studentQuery){
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		 try{
		 if (!ObjectFunctions.isNullOrEmpty(studentQuery)){
			 log.debug("Query:"+studentQuery.toString());
			 List<Student> studentsList=(List<Student>) session.createQuery(studentQuery.toString()).list();
			 
			 return studentsList;
			// return (List<Student>) getSession().createSQLQuery(studentQuery.toString()).addScalar("custId",Hibernate.LONG).addScalar("academicYear",Hibernate.entity(AcademicYear.class)).addScalar("account",Hibernate.entity(User.class)).setResultTransformer(Transformers.aliasToBean(Student.class)).list();
		 	}
		 }
		 catch(Exception ex){
				ex.printStackTrace();			
			}
			 finally {
					if(session != null) {
						//session.flush();tx.commit();
					session.close(); // extra work 
					} 
				}
		 return null;
	 }
	 
	 public List<Staff> getStaffList (StringBuffer staffQuery){
		 if (!ObjectFunctions.isNullOrEmpty(staffQuery)){
			 log.debug("Query:"+staffQuery.toString());
			 List<String> staffIds = this.getAll(staffQuery.toString());
			 if(!ObjectFunctions.isNullOrEmpty(staffIds)){
				 List<Staff> staffList = this.getAll(Staff.class, "id in ("+StringUtil.convertListToString(staffIds)+")");
				 if(!ObjectFunctions.isNullOrEmpty(staffList)){
					 return staffList;
				 } 
			 }
		 	//return (List<Staff>) getSession().createSQLQuery(staffQuery.toString()).addScalar("custId",StandardBasicTypes.LONG).addEntity("academicYear",AcademicYear.class).addEntity("account",User.class).setResultTransformer(Transformers.aliasToBean(Staff.class)).list();
		 	}
		 return null;
	 }
	 public int getTotalSmsCount(long custId,long academicYearId){
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		 try{
			 StringBuffer queryString = new StringBuffer();
	         queryString.append("select sum(m.messagesCount) from Messages m where m.messageType='SMS'");
	         queryString.append(" and m.customer="+ custId);
	         queryString.append(" and m.academicYear="+ academicYearId);
	         log.debug("Query:"+queryString.toString());
			 List totalSmsCountList =  (List) session.createQuery(queryString.toString()).list();
			 //session.flush();tx.commit();
	         if(ObjectFunctions.isNullOrEmpty(totalSmsCountList)){
	        	   return 0;
	         } else {
	        	 if(!ObjectFunctions.isNullOrEmpty(totalSmsCountList.get(0)))
	        		 return Integer.valueOf(totalSmsCountList.get(0).toString());
	        	 else
	        		 return 0;
	         }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		 finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		return 0;
	 }

	public long inserMessagesBySql(Messages messages) {
		try {
			Query qry = getSession().createSQLQuery("CALL sp_insertMessage(:createdById,:custId,:status,:purposeType,:messageDescription,:senderName,:sentSms,:messageType,:invalidMobileNos,:messagesCount,:academicYearId,:smsProviders,:guid)")
			.setParameter("createdById", messages.getCreatedById())
			.setParameter("custId", messages.getCustomer().getId())
			.setParameter("status",messages.getStatus())
			.setParameter("purposeType",messages.getPurposeType())
			.setParameter("messageDescription",messages.getMessageDescription())
			.setParameter("senderName",messages.getSenderName())
			.setParameter("sentSms",messages.getSentSms())
			.setParameter("messageType",messages.getMessageType())
			.setParameter("invalidMobileNos",messages.getInvalidMobileNos())
			.setParameter("messagesCount",messages.getMessagesCount())
			.setParameter("academicYearId",messages.getAcademicYear().getId())
			.setParameter("smsProviders",messages.getSmsProviders().getId())
			.setParameter("channel",messages.getChannel())
			.setParameter("guid",messages.getGuid());
			List resultList = qry.list();
			return ((BigInteger)resultList.get(0)).longValue();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return 0;
	}
	public void insertMessageDetailsTracking(Set<MessageDetailsTracking> messageDetailsTrackings,long messageId,long userId){
		try {
			log.debug("Started for the custId of messageDetailsTrackings insertion.....");
			for(MessageDetailsTracking detailsTracking : messageDetailsTrackings){
				Query query = getSession().createSQLQuery("INSERT INTO messageDetailsTracking(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,academicYearId,custId,deliveryStatus,messageId,mobileNumber,accountId) VALUES (:createdById,:createdDate,:lastAccessDate,:lastUpdatedById,:lastUpdatedDate,:version,:academicYearId,:custId,:deliveryStatus,:messageId,:mobileNumber,:accountId)");
				query.setParameter("createdById",userId);
				query.setParameter("createdDate",new Date());
				query.setParameter("lastAccessDate",new Date());
				query.setParameter("lastUpdatedDate",new Date());
				query.setParameter("lastUpdatedById",userId);
				query.setParameter("version",0);
				query.setParameter("academicYearId",detailsTracking.getAcademicYearId());
				query.setParameter("custId",detailsTracking.getCustId());
				query.setParameter("deliveryStatus",detailsTracking.getDeliveryStatus());
				query.setParameter("messageId",messageId);
				query.setParameter("mobileNumber",detailsTracking.getMobileNumber());
				if(!ObjectFunctions.isNullOrEmpty(detailsTracking.getAccount()))
					query.setParameter("accountId",detailsTracking.getAccount().getId());
				else
					query.setParameter("accountId",null);
				query.executeUpdate();
  	    	}
			log.debug("Completed for the messageDetailsTrackings insertion.....");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	
	public long getInvoiceNumberByCustId(String table,long custId,long academicYearId) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			if("challanaPayment".equalsIgnoreCase(table))
				queryBuff.append("select IFNULL(max(challanaNumber),0) ");
			else
				queryBuff.append("select max(invoiceNumber) ");
			if(!"studentPayment".equalsIgnoreCase(table)){
				queryBuff.append(" from "+table+" where custId=");
				queryBuff.append(custId);
				if(academicYearId != 0){
					queryBuff.append(" and academicYearId=").append(academicYearId);
				}
			}
			else{
				queryBuff = new StringBuffer();
				if(academicYearId != 0){
					queryBuff.append("SELECT MAX(_maxId) newInvoiceNo FROM (select IFNULL(MAX(invoiceNumber),0) _maxId from studentPayment SP where custId="+custId+"  and academicyearId="+academicYearId+" ");
					queryBuff.append("UNION select IFNULL(MAX(invoiceNumber),0) _maxId from studentFeeRefund where custId="+custId+" and academicyearId="+academicYearId+")v;");
				}
				else{
					queryBuff.append("SELECT MAX(_maxId) newInvoiceNo FROM (select IFNULL(MAX(invoiceNumber),0) _maxId from studentPayment SP where custId="+custId+" ");
					queryBuff.append("UNION select IFNULL(MAX(invoiceNumber),0) _maxId from studentFeeRefund where custId="+custId+")v;");
				}
			}
			List resultList = getSession().createSQLQuery(queryBuff.toString()).list();
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				if (!ObjectFunctions.isNullOrEmpty(resultList.get(0))) {
					BigInteger var = ((BigInteger) resultList.get(0));
					return var.longValue();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return 0;
	}
	public void updateStudentFeePaidStatus(long studentId,String status) {
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update student set feePaidStatus='"+status+"' where id=" + studentId);
			Query qry = session.createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
			 session.flush();tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	@Transactional
	public Object saveSmsObject(Object o) throws DataAccessException {
		//Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			/*getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			o = getHibernateTemplate().merge(o);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();*/
			//getHibernateTemplate().flush();
			 /*session.saveOrUpdate(o);
		     session.flush();tx.commit();*/
			getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
			//getHibernateTemplate().merge(o);
			getHibernateTemplate().saveOrUpdate(o);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
		     return o;
		}catch (HibernateException ex) {
			/*if(tx != null) {
				tx.rollback();
				} */
			ex.printStackTrace();
			throw new URTDataAccessException(ex.getMessage());
		}
		finally {
			/*if(session != null) {
			session.close(); // extra work 
			} */
		}
	}
	public int updateQuery(String query)
	{
		Query qry = getSession().createSQLQuery(query);
		return qry.executeUpdate();
	}

	public User getUserBySecondaryUserName(String secondaryUsername) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			List users = (List) session.createQuery("from User where secondaryUsername= '" + secondaryUsername+ "' and accountEnabled='Y' ").list();
			if (!ObjectFunctions.isNullOrEmpty(users)) {
				User user = (User) users.get(0);
				users = null;
				return user;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new URTDataAccessException(ex.getMessage());
		} finally {
			if (session != null) {
				session.close(); // extra work
			}
		}
		return null;
	}
	public void removeAllAndroidMobileUsers(String notRegisteredKey) {
		try{
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from androidMobileUsers where registrationKey='"+notRegisteredKey+"'" );
			int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
			if(row > 0)
            {
               log.debug("The no of nonRegisteredKey rows deleted:"+row);
            }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	@Transactional
	public void persist(Object object) throws DataAccessException{
		try {
			getHibernateTemplate().persist(object);
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new URTDataAccessException(ex.getMessage());
		}
	}
	
	public int getSum(String clause)  throws DataAccessException{
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append(clause);
				Number count = (Number)session.createSQLQuery(queryBuff.toString()).uniqueResult();
				//session.flush();tx.commit();
				if(!ObjectFunctions.isNullOrEmpty(count)){
					return count.intValue();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		  finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
			return 0;
	}
	/**
	 * Get the feature Academic Year details
	 */
	public AcademicYear getFeatureAcademicYear(long custId , long currentAcademicYearId){
		Session session = this.sessionFactory.openSession();
		try{
			List academicYear=(List) session.createQuery("from AcademicYear where id > "+ currentAcademicYearId +" and custId="+custId).list();
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				return (AcademicYear)academicYear.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		finally {
			if(session != null) {
			session.close(); 
			} 
		}
		return null;
	}
}