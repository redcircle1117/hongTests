package com.example.hong.asdsd;

import java.util.List;

/**
 * Created by Hong on 2015/12/14.
 */
public class GsonTools {

    /**
     * reason : 查询成功
     * result : [{"title":"普京露面破外媒失踪谣言","content":"<em>普京<\/em>自本月5日会见来访的意大利总理伦齐后,便未再公开露面。11日,俄发布俄罗斯、哈萨克斯坦、白俄罗斯三国领导人会晤推迟举行的消息,西方媒体借此猜测会晤推迟原因是\"<em>普京<\/em>生病\"。俄方予以否认,坚称<em>普京<\/em>身体状况\"绝对健康\"。13日,俄总统新闻秘书佩斯科夫否认了关于<em>普京<\/em>\"新添...","img_width":"","full_title":"普京露面破外媒失踪谣言","pdate":"39分钟前","src":"光明网","img_length":"","img":"","url":"http://world.gmw.cn/newspaper/2015-03/17/content_105205337.htm","pdate_src":"2015-03-17 10:55:00"},{"title":"看!媒体这样玩普京\"失踪\"","content":"再没有出现在公开场合。一些欧美媒体此后对<em>普京<\/em>行踪进行了诸多猜测性报道,路透社、法新社等西方通讯社也援引多个消息源不清的传言,以\"<em>普京<\/em>在哪里\"\"<em>普京<\/em>不在场\"等标题新闻予以报道。 <em>普京<\/em>16日会见阿坦巴耶夫,持续10天的传言得以停歇。这些天来,<em>普京<\/em>如何\"被<em>失踪<\/em>\"?西方媒体的...","img_width":"","full_title":"看!媒体这样玩普京\"失踪\"","pdate":"1小时前","src":"环球网","img_length":"","img":"","url":"http://world.huanqiu.com/hot/2015-03/5929476.html","pdate_src":"2015-03-17 10:24:00"},{"title":"普京\"失踪\"多日来首露面 会见吉尔吉斯斯坦总统","content":"当地时间2015年3月16日,据外媒报道,俄罗斯总统<em>普京<\/em>16日在圣彼得堡与吉尔吉斯斯坦总统阿坦巴耶夫会晤。此次会晤是两位领导人2015年的第二次会晤,会晤也破除了此前外界流传的<em>普京<\/em>身体\"抱恙\"的传言。图片来源:东方ic 版权作品 请勿转载 (责任编辑 :史博超)","img_width":"671","full_title":"普京\"失踪\"多日来首露面 会见吉尔吉斯斯坦总统","pdate":"1小时前","src":"中国经济网","img_length":"940","img":"http://p1.qhimg.com/t0149ac168ffc124d73.jpg","url":"http://intl.ce.cn/intlpic/gd/201503/17/t20150317_4842351.shtml","pdate_src":"2015-03-17 10:08:00"},{"title":"普京失踪11天后","content":"<em>普京<\/em>自本月5日会见来访的意大利总理伦齐后,便未再公开露面。11日,俄总统官方网站发布俄罗斯、哈萨克斯坦、白俄罗斯三国领导人会晤推迟举行的消息,西方媒体借此猜测会晤推迟原因是\"<em>普京<\/em>生病\"。俄方予以否认,坚称<em>普京<\/em>身体状况\"绝对健康\"。■据新华社","img_width":"","full_title":"普京失踪11天后","pdate":"3小时前","src":"凤凰网","img_length":"","img":"","url":"http://finance.ifeng.com/a/20150317/13558603_0.shtml","pdate_src":"2015-03-17 08:19:00"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * title : 普京露面破外媒失踪谣言
     * content : <em>普京</em>自本月5日会见来访的意大利总理伦齐后,便未再公开露面。11日,俄发布俄罗斯、哈萨克斯坦、白俄罗斯三国领导人会晤推迟举行的消息,西方媒体借此猜测会晤推迟原因是"<em>普京</em>生病"。俄方予以否认,坚称<em>普京</em>身体状况"绝对健康"。13日,俄总统新闻秘书佩斯科夫否认了关于<em>普京</em>"新添...
     * img_width :
     * full_title : 普京露面破外媒失踪谣言
     * pdate : 39分钟前
     * src : 光明网
     * img_length :
     * img :
     * url : http://world.gmw.cn/newspaper/2015-03/17/content_105205337.htm
     * pdate_src : 2015-03-17 10:55:00
     */

    private List<ResultEntity> result;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getImg_width() {
            return img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public String getSrc() {
            return src;
        }

        public String getImg_length() {
            return img_length;
        }

        public String getImg() {
            return img;
        }

        public String getUrl() {
            return url;
        }

        public String getPdate_src() {
            return pdate_src;
        }
    }
}
