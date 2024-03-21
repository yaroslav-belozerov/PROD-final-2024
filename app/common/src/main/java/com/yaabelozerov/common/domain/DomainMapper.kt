package com.yaabelozerov.common.domain

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(obj: T): DomainModel
}