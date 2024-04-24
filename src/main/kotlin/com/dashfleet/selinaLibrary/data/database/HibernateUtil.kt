package com.dashfleet.selinaLibrary.data.database

import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry

class HibernateUtil {

    companion object {

        private lateinit var sessionFactory: SessionFactory
        private lateinit var session: Session

        /** create sessions factory **/
        fun buildSessionFactory() {

            val configuration: Configuration = Configuration()
            configuration.configure("hibernate.cfg.xml")

            //registering entity classes to map
            configuration.addAnnotatedClass(ConfigurationEntity::class.java)

            val serviceRegistry: ServiceRegistry = StandardServiceRegistryBuilder()
                .applySettings(configuration.properties).build()

            sessionFactory = configuration.buildSessionFactory(serviceRegistry)

        }

        /** opens new session **/
        fun openSession() {
            session = sessionFactory.openSession()
        }

        /** get the current session **/
        fun getCurrentSession(): Session {
            if (session == null || !session.isOpen) {
                openSession()
            }
            return session
        }

        /** Closes hibernate **/
        fun closeHibernate() {

            if (session != null) {
                session.close()
            }

            if (sessionFactory != null) {
                sessionFactory.close()
            }
        }
    }
}