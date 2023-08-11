package com.abogomazov.avatar.generator

import java.util.logging.Logger

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger(this.javaClass.name) }
}
