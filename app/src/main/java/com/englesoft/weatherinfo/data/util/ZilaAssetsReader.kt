package com.englesoft.weatherinfo.data.util

import android.content.Context
import com.englesoft.weatherinfo.data.model.Zila
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class ZilaAssetsReader @Inject constructor(
    private val context: Context
) {

    fun readZilaListFromAssets(): List<Zila> {
        val json = readAssetFile("zila_list.json")
        return parseCityListJson(json)
    }

    private fun readAssetFile(fileName: String): String {
        return try {
            val stream = context.assets.open(fileName)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            String(buffer)
        } catch (exception: Exception) {
            throw IOException("Error reading asset file: $fileName", exception)
        }
    }

    private fun parseCityListJson(json: String): List<Zila> {
        return try {
            val type = object : TypeToken<List<Zila>>() {}.type
            val gson = GsonBuilder().create()
            gson.fromJson(json, type)
        } catch (exception: Exception) {
            throw JsonParseException("Error parsing zila list JSON", exception)
        }
    }
}
