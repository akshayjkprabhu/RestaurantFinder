package com.ajp.dinein.core.util

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

object JsonFileReader {
	fun readFromAsset(filename : String, context : Context) : String {
		try {
			context.assets.open(filename).use { stream ->
				val size = stream.available()
				val bytes = ByteArray(size)
				stream.read(bytes)
				return String(bytes, Charset.forName("UTF-8"))
			}
		} catch (ioe : IOException) {
			throw JsonReaderException("Error Reading from the asset file $filename")
		}
	}
	
	class JsonReaderException(override val message : String) : Exception(message)
}