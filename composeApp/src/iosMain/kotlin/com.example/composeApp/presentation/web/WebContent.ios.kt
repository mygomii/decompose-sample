package com.example.composeApp.presentation.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import com.example.composeApp.presentation.web.WebViewObserver.KeyPath.Companion.keyPaths
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import observer.ObserverProtocol
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSNumber
import platform.Foundation.NSURL
import platform.Foundation.addObserver
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.darwin.NSObject

@Composable
actual fun CustomWebView(backHandler: () -> Unit) {
    MyWebView(backHandler)
}

@OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)
@Composable
fun MyWebView(backHandler: () -> Unit) {
    val webView = remember { WKWebView() }
    val state = remember { WebViewState() }
    val observer = remember {
        WebViewObserver(
            webViewState = state
        )
    }

    val delegate = remember { WKNavigationDelegate() }
    val req = NSMutableURLRequest.requestWithURL(URL = NSURL(string = "https://mygomii.tistory.com/"))

    webView.loadRequest(req)
    webView.navigationDelegate = delegate
    webView.addProgressObservers(
        observer = observer
    )


    Column {
        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(40.dp),
            onClick = {
                if (state.canGoBack) {
                    webView.goBack()
                } else {
                    backHandler()
                }
            }
        ) {
            Icon(Icons.Outlined.Call, contentDescription = "Back")
        }

        UIKitView(
            factory = {
                webView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

class WebViewState {
    var canGoBack: Boolean by mutableStateOf(false)
        internal set
}

class WKNavigationDelegate : NSObject(), WKNavigationDelegateProtocol {
    override fun webView(webView: WKWebView, didStartProvisionalNavigation: WKNavigation?) {
        println("####### didStartProvisionalNavigation")
    }
}

@OptIn(ExperimentalForeignApi::class)
class WebViewObserver(
    private val webViewState: WebViewState,
) : NSObject(), ObserverProtocol {
    override fun observeValueForKeyPath(keyPath: String?, ofObject: Any?, change: Map<Any?, *>?, context: COpaquePointer?) {
        if (keyPath.isNullOrEmpty()) {
            return
        }

        when (KeyPath.getKey(keyPath)) {
            KeyPath.EstimatedProgress -> {

            }

            KeyPath.Title -> {

            }

            KeyPath.Url -> {

            }

            KeyPath.CanGoBack -> {
                val canGoBack = change?.get("new") as? NSNumber
                println("canGoBack : ${canGoBack?.boolValue}")
                webViewState.canGoBack = canGoBack?.boolValue ?: false
            }

            KeyPath.CanGoForward -> {

            }

            null -> {

            }
        }
    }


    enum class KeyPath(val value: String) {
        EstimatedProgress("estimatedProgress"),
        Title("title"),
        Url("URL"),
        CanGoBack("canGoBack"),
        CanGoForward("canGoForward");

        companion object {
            fun getKey(value: String): KeyPath? {
                return entries.firstOrNull { it.value == value }
            }

            var keyPaths = entries.map {
                it.value
            }
        }
    }

}

fun WKWebView.addProgressObservers(observer: NSObject) {
    this.addObservers(
        observer = observer,
        properties = keyPaths,
    )

}

@OptIn(ExperimentalForeignApi::class)
fun WKWebView.addObservers(observer: NSObject, properties: List<String>) {
    properties.forEach {
        this.addObserver(
            observer,
            forKeyPath = it,
            options = platform.Foundation.NSKeyValueObservingOptionNew,
            context = null,
        )
    }
}
