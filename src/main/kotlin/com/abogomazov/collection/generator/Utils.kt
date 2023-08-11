package com.abogomazov.collection.generator

import java.util.logging.Logger

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger(this.javaClass.name) }
}
