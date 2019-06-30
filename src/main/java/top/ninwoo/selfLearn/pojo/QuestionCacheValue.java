package top.ninwoo.selfLearn.pojo;

/**
 * 缓存中只保存sha和生成的文本地址，不保存detail字段，防止内存不足
 */
public class QuestionCacheValue {
    private final int id;
    private final String sha;

    private final String srcDoc;

    public QuestionCacheValue(int id, String sha, String srcDoc) {
        this.id = id;
        this.sha = sha;
        this.srcDoc = srcDoc;
    }

    public int getId() {
        return id;
    }

    public String getSha() {
        return sha;
    }

    public String getSrcDoc() {
        return srcDoc;
    }
}
