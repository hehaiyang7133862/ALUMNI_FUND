package com.laungee.proj.common.core;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


public class FilterOpenView extends org.springframework.orm.hibernate3.support.OpenSessionInViewFilter
{

	public FilterOpenView()
    {
    }

    protected Session getSession(SessionFactory sessionFactory)
        throws DataAccessResourceFailureException
    {
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.COMMIT);
        return session;
    }

    protected void closeSession(Session session, SessionFactory factory)
    {
        session.flush();
        try
        {
            if(!session.connection().isClosed())
            {
               // session.connection().commit();
               session.connection().setAutoCommit(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.gc();
        }
        super.closeSession(session, factory);
    }
}