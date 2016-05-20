public enum EmailProvider {
    QQ("smtp.qq.com", 587),
    NetEase_163("smtp.163.com", 25);

    protected final String host;
    protected final int port;

    EmailProvider(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
