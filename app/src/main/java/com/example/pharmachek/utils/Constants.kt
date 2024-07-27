package com.example.pharmachek.utils

object Constants {

    //Database
    const val DATABASE_NAME = "parmacheck_database"
    const val PARMACHECK_ENTITY = "search_medication_info_entity"

    //Network
    const val BASE_URL = "http://54.160.153.22:8082/"
    const val SEARCH_ENDPOINT = "fuzzy_search"
    const val CHECK_ENDPOINT = "check_nom"
    const val SAVE_ENDPOINT = "save"
    const val SEARCH_DRUG_STATE_SUCCESS = "Ma'lumot topildi"
    const val SEARCH_DRUG_STATE_ERROR = "Ma'lumot topilmadi"
    const val SAVE_DRUG_STATE_SUCCESS = "Ma'lumot saqlandi"
    const val SAVE_DRUG_STATE_ERROR = "Ma'lumot saqlanmadi"

    //SharedPrefHelper
    const val SHARED_PREFERENCE = "shared_preference"
    const val ON_BOARDING_SCREEN_OPENED_KEY = "onboarding_screen_opened_key"
}