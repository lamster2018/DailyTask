package com.example.lahm.dailytask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lahm.dailytask.R;

public class ReplaceActivity extends AppCompatActivity {

    TextView tv;
    String htmlLinkText = "电话：2371023  " +
            "网址：http://weibo.com/u/2414714651/home?wvr=5&c=spr_web_sq_firefox_weibo_t001 " +
            "家乐福https://zhidao.baidu.com/question/305613911327094804.html " +
            "房价大幅 https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_3_dg&wd=%E6%8F%90%E5%8F%96textView%E4%B8%AD%E7%9A%84url&oq=textview%E4%B8%AD%E7%9A%84url%E8%B6%85%E9%93%BE%E6%8E%A5%E6%9B%BF%E6%8D%A2%E6%88%90%E8%87%AA%E5%AE%9A%E4%B9%89%E6%A0%B7%E5%BC%8F&rsv_pq=f868eff000002dd3&rsv_t=754dozB5LN%2Bqi7SPefBRa%2Fd2Fo3a7PQrAKzquNPKzAWQ4HEcytQzAfEnAAHpM1QMkP4r&rqlang=cn&rsv_enter=0&inputT=80124&rsv_sug3=499&rsv_sug1=225&rsv_sug7=101&rsv_sug2=0&rsv_sug4=83873";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(htmlLinkText);
        interceptHyperLink(tv);
    }

    /**
     * 拦截超链接
     * http://www.jianshu.com/p/9027921d4b66
     * http://souly.cn/技术博文/2016/01/29/TextView超链接实现方式总结/
     *
     * @param tv
     */
    private void interceptHyperLink(TextView tv) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) tv.getText();
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) return;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            //循环遍历所有链接
            for (URLSpan uri : urlSpans) {
                String url = uri.getURL();
//                if (url.indexOf("http://") == 0) {//拦截所有http://开头的链接
                CustomUrlSpan customUrlSpan = new CustomUrlSpan(url);
                String toBeReplaced = "网址链接";
                spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(uri),
                        spannableStringBuilder.getSpanEnd(uri),
                        toBeReplaced);
                spannableStringBuilder.setSpan(customUrlSpan,
                        spannableStringBuilder.getSpanStart(uri),
                        spannableStringBuilder.getSpanEnd(uri),
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//                }
            }
            tv.setText(spannableStringBuilder);
        }
        tv.setText("replace failed");
    }

    public class CustomUrlSpan extends ClickableSpan {
        private String url;

        public CustomUrlSpan(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View v) {
//            在这里可以做任何自己想要的处理
            Log.i("Xxxxxxx", "onClick: " + url);
        }
    }
}
