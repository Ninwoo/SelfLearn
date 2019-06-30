package top.ninwoo.selfLearn.pojo;

import java.util.List;

/**
 * 用于存储Doc中包含的题目
 */
public class SrcDocValue {
    // 文档名称
    private final String docName;
    // 题目列表
    private final List<Integer> questionList;

    public SrcDocValue(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
