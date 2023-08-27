package com.nna.moodify.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AuthenticatedOkHttpClient

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class NonAuthenticatedOkHttpClient
