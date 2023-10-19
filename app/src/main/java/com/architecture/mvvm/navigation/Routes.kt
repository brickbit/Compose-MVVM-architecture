package com.architecture.mvvm.navigation

sealed class Routes(val name: String) {
    object List: Routes("list")
    object Detail: Routes("detail")
}
