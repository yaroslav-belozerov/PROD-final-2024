package com.yaabelozerov.lifestylehub.weather.domain.mapper

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(obj: T): DomainModel
}