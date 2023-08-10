package com.abogomazov.collection.generator

import com.abogomazov.collection.generator.app.App
import com.abogomazov.collection.generator.app.readConfig


fun main(args: Array<String>) {
    val config = readConfig(path = args.first()).validated()

    App.instantiate(config).run()
}
