package com.dashfleet.selinaLibrary.data.database

class DAOUtils<T>(entity: Class<T>) {

    private var tableNameEntity: String = entity.name
    private var entityClass: Class<T> = entity

    fun <E> executeUpdateById(columnName: String, value: E, id: Int) {
        HibernateSession.initHibernateSession()
        val queryString = "UPDATE $tableNameEntity SET $columnName =:value WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("value", value)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun executeSelectAll(tableName: String): List<T> {
        HibernateSession.initHibernateSession()
        val queryString = "FROM :table"
        val query = HibernateSession.session.createQuery(queryString, entityClass)
        query.setParameter("table", tableName)
        return query.resultList
    }

    fun executeSelectById(id: Int): T {
        HibernateSession.initHibernateSession()
        val queryResult = HibernateSession.session.get(entityClass, id)
        HibernateSession.closeHibernateSession()
        return queryResult
    }

    fun executeInsert(entityToStore: T) {
        HibernateSession.initHibernateSession()
        HibernateSession.session.save(entityToStore)
        HibernateSession.closeHibernateSession()
    }

    fun executeInsertAll(entitiesToStore: List<T>) {
        HibernateSession.initHibernateSession()
        for (entity in entitiesToStore) {
            HibernateSession.session.save(entity)
        }
        HibernateSession.closeHibernateSession()
    }
}