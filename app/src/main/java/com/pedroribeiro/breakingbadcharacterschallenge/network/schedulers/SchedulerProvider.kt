package com.pedroribeiro.breakingbadcharacterschallenge.network.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
