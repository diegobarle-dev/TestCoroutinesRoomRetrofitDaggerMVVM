package uk.co.diegobarle.core.di

import dagger.Module

/**
 * Groups all the core modules together
 */
@Module(includes = [
    DatabaseModule::class,
    NetworkModule::class,
    CoreDataModule::class])
class CoreModule