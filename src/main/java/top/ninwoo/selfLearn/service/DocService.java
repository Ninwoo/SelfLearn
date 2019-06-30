package top.ninwoo.selfLearn.service;

import top.ninwoo.selfLearn.assist.BusiWorkTime;
import top.ninwoo.selfLearn.assist.QuestionBank;
import top.ninwoo.selfLearn.pojo.SrcDocValue;

import java.util.List;
import java.util.Random;

/**
 * 关于文档的相关服务
 */
public class DocService {
    /**
     * 生成试题集
     * @param srcDocValue 题目列表
     * @return 返回题目的位置
     */
    private static QuestionService questionService = new QuestionService();

    public static String generateDoc(SrcDocValue srcDocValue) {
        StringBuffer sb = new StringBuffer();
        List<Integer> questionList = srcDocValue.getQuestionList();
        for (Integer questionId : questionList) {
            String s = questionService.makeQuestion(questionId,
                    QuestionBank.questions.get(questionId).getDetail());
            sb.append(s);
        }
        // 保持题目
        String result = sb.toString();
        return "file/doc/" + srcDocValue.getDocName() + "_" + System.currentTimeMillis() + ".pdf";
    }

    /**
     * 上传文档
     * @param srcDoc 本地试题位置
     * @return 返回题目的网页链接
     */
    public static String uploadDoc(String srcDoc) {
        // 模拟上传时间
        Random r = new Random();
        BusiWorkTime.work(9000 + r.nextInt(400));
        return "http://www.ninwoo.top/file/upload/" + srcDoc;
    }
}
