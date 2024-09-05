package com.example.composeApp.presentation.web

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
actual fun CustomWebView(backHandler: () -> Unit) {
    MyWebView(backHandler)
}

@Composable
fun MyWebView(backHandler: () -> Unit) {
    var webView: WebView? = null

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            backHandler()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                webView = WebView(context).apply {
                    scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
//                    setBackgroundColor(Color.Transparent)
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    // Enable horizontal scrolling
                    settings.apply {
                        loadWithOverviewMode = true
                        useWideViewPort = true
                        layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                    }

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                            isLoading(true)
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
//                            view?.scrollTo(view.contentHeight, 0)
//                            isLoadingFinished = true
//                            isLoading(false)
                        }
                    }
                }

                webView!!
            },
            update = { webView ->
                webView.loadUrl("https://mygomii.tistory.com/")
            }
        )
    }
}

