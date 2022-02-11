package com.web.devvy.infrastructure.persistence.entity

interface ModelMapper<D, P> {
    fun mapToDomainEntity(model: P): D
    fun mapToJdbcEntity(model: D): P

}