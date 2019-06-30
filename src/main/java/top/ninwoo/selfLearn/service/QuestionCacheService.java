package top.ninwoo.selfLearn.service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * QuestionService的增强版本，加入缓存，如果发现已经生成过该题目，
 * 则使用缓存
 */
public class QuestionCacheService {
    // 用于缓存已经生成的题目
    private static final ConcurrentHashMap<Integer, String> questionCache
            = new ConcurrentHashMap<>();
    private QuestionService questionService = new QuestionService();

    public String makeQuestion(Integer questionId, String questionSrc) {
        if(questionCache.containsKey(questionId)) {
            System.out.println("命中缓存！");
            // TODO： 题目更新未处理
            return questionCache.get(questionId);
        } else {
            String result = questionService.makeQuestion(questionId, questionSrc);
            questionCache.put(questionId, questionSrc);
            return result;
        }
    }
}
