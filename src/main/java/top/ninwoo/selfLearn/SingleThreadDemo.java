package top.ninwoo.selfLearn;

import top.ninwoo.selfLearn.assist.QuestionBank;
import top.ninwoo.selfLearn.pojo.SrcDocValue;
import top.ninwoo.selfLearn.service.DocService;

/**
 * 单线程生成一套卷的时间需要40-50s
 */
public class SingleThreadDemo {
    public static void main(String[] args) {
        DocService docService = new DocService();
        long start = System.currentTimeMillis();
        // 初始化800道题目
        QuestionBank.initQuestions(800);
        // 选择一套题目，这里采用随机选取的方式
        SrcDocValue test01 = QuestionBank.generateQuestions("test01", 60);
        // 生成一套卷子
        String s = docService.generateDoc(test01);
        System.out.println("生成试题册：" + s);
        String s1 = docService.uploadDoc(s);
        System.out.println("试题上传地址 " + s1);
        System.out.println("用时：" + (System.currentTimeMillis() - start));
    }
}
