package top.ninwoo.selfLearn.service;

import top.ninwoo.selfLearn.assist.QuestionBank;
import top.ninwoo.selfLearn.pojo.QuestionCacheValue;
import top.ninwoo.selfLearn.pojo.QuestionInDB;

import java.util.concurrent.ConcurrentHashMap;

/**
 * QuestionService的增强版本，加入缓存，如果发现已经生成过该题目，
 * 则使用缓存
 *
 * 新版本解决题目更新缓存失效的问题
 */
public class QuestionCacheService {
    // 用于缓存已经生成的题目
    private static final ConcurrentHashMap<Integer, QuestionCacheValue> questionCache
            = new ConcurrentHashMap<>();
    private QuestionService questionService = new QuestionService();

    public String makeQuestion(QuestionInDB question) {
        int questionId = question.getId();
        String detail = question.getDetail();
        if(questionCache.containsKey(questionId)) {
            System.out.println("命中缓存！");
            // 校验摘要，看题目是否更新
            QuestionCacheValue cacheValue = questionCache.get(questionId);
            if(cacheValue.getSha() == question.getSha()) {
                return cacheValue.getSrcDoc();
            }
            // 缓存更新
            System.out.println("缓存已经失效");
        }
        String result = questionService.makeQuestion(question);
        QuestionCacheValue newCacheValue = new QuestionCacheValue(questionId, question.getSha(),result);
        questionCache.put(questionId, newCacheValue);
        return result;
    }
}
