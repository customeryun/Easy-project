package com.android.easymanager.ui.fragment;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.easymanager.R;
import com.android.easymanager.contract.HomeContract;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class HomeFragment extends BaseFragment<HomeContract.View,HomeContract.Presenter> implements HomeContract.View{

    @BindView(R.id.web_view)
    WebView web_view;

    public static HomeFragment getInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void init() {
        initWebView();
    }

    public void initWebView(){
        onBackPressed();
        web_view.loadUrl("http://www.baidu.com");
        web_view.setWebChromeClient(webChromeClient);
        web_view.setWebViewClient(webViewClient);

        WebSettings webSettings=web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

    }

    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            //progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.equals("http://www.baidu.com/")){
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    private WebChromeClient webChromeClient=new WebChromeClient(){
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定",null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen","网页标题:"+title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //progressBar.setProgress(newProgress);
        }
    };

    public void onBackPressed(){
        web_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK){
                    Toast.makeText(getActivity(), "按了返回键", Toast.LENGTH_SHORT).show();
                    if(web_view.canGoBack()){
                        web_view.goBack();
                    }else{
                        mActivity.finish();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_layout;
    }

    @Override
    public HomeContract.View crateView() {
        return null;
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return null;
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        web_view.destroy();
        web_view=null;
    }
}
