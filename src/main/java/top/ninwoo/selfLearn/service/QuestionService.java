package top.ninwoo.selfLearn.service;

import top.ninwoo.selfLearn.assist.BusiWorkTime;
import top.ninwoo.selfLearn.pojo.SrcDocValue;

import java.util.Random;

/**
 * 题目处理类
 */
public class QuestionService {
    /**
     * 创建题目，包括：解析文本，下载图片等
     * @param questionId 题目id
     * @param questionSrc 题目的html
     * @return 题目解析后的文本
     */
    public String makeQuestion(Integer questionId, String questionSrc) {
        // 这里采用休眠来模拟解析过程
        Random random = new Random();
        BusiWorkTime.work(500 + random.nextInt(100));
        return "CompleteQuetion[id=" + questionId + "content=:" + questionSrc + "]";
    }
}
