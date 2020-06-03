package com.example.lolresults

import android.app.Application
import com.example.lolresults.di.apiModule
import com.example.lolresults.di.dataSourceModule
import com.example.lolresults.di.repositoryModule
import com.example.lolresults.di.viewModelModule
import com.example.lolresults.utils.SvgDecoderExample
import com.facebook.drawee.backends.pipeline.DraweeConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.decoder.ImageDecoderConfig
import com.facebook.imageformat.ImageFormatChecker
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupDependencyInjection()
        setupFresco()
    }

    private fun setupDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(listOf(apiModule, dataSourceModule, viewModelModule, repositoryModule))
        }
    }

    private fun setupFresco() {
        val imageDecoderConfig = ImageDecoderConfig.newBuilder()
            .addDecodingCapability(
                SvgDecoderExample.SVG_FORMAT,
                SvgDecoderExample.SvgFormatChecker(),
                SvgDecoderExample.SvgDecoder()
            )
            .build()


        val config = OkHttpImagePipelineConfigFactory
            .newBuilder(this, get())
            .setImageDecoderConfig(imageDecoderConfig)
            .build()

        val draweeConfig = DraweeConfig.newBuilder()
            .addCustomDrawableFactory(SvgDecoderExample.SvgDrawableFactory())
            .build()

        Fresco.initialize(this, config, draweeConfig)
    }
}