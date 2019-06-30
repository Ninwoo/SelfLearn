package top.ninwoo.selfLearn.service;

import top.ninwoo.selfLearn.assist.BusiWorkTime;
import top.ninwoo.selfLearn.pojo.QuestionInDB;
import top.ninwoo.selfLearn.pojo.SrcDocValue;

import java.util.Random;

/**
 * 题目处理类
 */
public class QuestionService {
    /**
     * 创建题目，包括：解析文本，下载图片等
     * @param question 题目信息
     * @return 题目解析后的文本
     */
    public String makeQuestion(QuestionInDB question) {
        // 这里采用休眠来模拟解析过程
        Random random = new Random();
        BusiWorkTime.work(500 + random.nextInt(100));
        return "CompleteQuetion[id=" + question.getId() + "content=:" + question.getDetail() + "]";
    }
}
