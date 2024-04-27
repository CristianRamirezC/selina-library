package com.dashfleet.selinaLibrary.data.database.util

import org.hibernate.Session
import org.hibernate.Transaction

class HibernateSession {
    companion object {
        lateinit var session: Session
        private lateinit var transaction: Transaction

        fun initHibernateSession() {
            HibernateUtil.buildSessionFactory()
            HibernateUtil.openSession()
            session = HibernateUtil.getCurrentSession()
            transaction = session.beginTransaction()
        }

        fun closeHibernateSession() {
            transaction.commit()
            session.close()
        }
    }
}