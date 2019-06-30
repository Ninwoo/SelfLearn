package top.ninwoo.selfLearn.pojo;

/**
 * 用于模拟在DB中的数据条目
 */
public class QuestionInDB {
    // 题目id
    private final int id;
    // 题目详情，长度要求：平均700字节
    private final String detail;

    // 题目摘要
    private final String sha;

    public QuestionInDB(int id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }
}
