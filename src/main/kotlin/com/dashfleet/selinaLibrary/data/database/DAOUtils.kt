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
        val queryString = "FROM $tableName"
        val query = HibernateSession.session.createQuery(queryString, entityClass)
        val queryResult = query.resultList
        return queryResult
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

    /** Execute a custom SELECT query by sending the query as String **/
    fun executeCustomSelectQuery(customQuery: String): List<T> {
        HibernateSession.initHibernateSession()
        val query = HibernateSession.session.createQuery(customQuery, entityClass)
        val queryResult = query.resultList
        HibernateSession.closeHibernateSession()
        return queryResult
    }

    /** Execute query DELETE by ID **/
    fun executeDeleteById(id: Int) {
        HibernateSession.initHibernateSession()
        val entityToDelete = HibernateSession.session.load(entityClass, id)
        HibernateSession.session.delete(entityToDelete)
        HibernateSession.closeHibernateSession()
    }

    /** Custom INSERT, UPDATE or DELETE queries **/
    fun executeCustomModifyingQuery(customQuery: String) {
        HibernateSession.initHibernateSession()
        val sqlQuery = HibernateSession.session.createNativeQuery(customQuery)
        sqlQuery.executeUpdate()
        HibernateSession.closeHibernateSession()
    }
}