package org.addepar.investmenttracker.core.data.utils

sealed class Either<out A, out B> {
    data class Right<out B>(val value: B) : Either<Nothing, B>()
    data class Left<out A>(val value: A) : Either<A, Nothing>()
}