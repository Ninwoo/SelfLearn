package top.ninwoo.selfLearn.assist;

import top.ninwoo.selfLearn.pojo.QuestionInDB;
import top.ninwoo.selfLearn.pojo.SrcDocValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 题目库
 */
public class QuestionBank {
    // 用于存储题目信息
    public static ConcurrentHashMap<Integer, QuestionInDB> questions
            = new ConcurrentHashMap<>();

    /**
     * 初始化题目
     * @param num：要生成的题目数量
     */

    public static void initQuestions(int num) {
        for (int i = 0; i < num; i++) {
            String questionDetail = createQuestionDetail(800);
            QuestionInDB questionInDB = new QuestionInDB(i, questionDetail, EncryptUtils.getSHA(questionDetail));
            questions.put(i, questionInDB);
        }
    }

    /**
     * 模拟创建题目详情
     * @param length 题目长度
     * @return
     */
    public static String createQuestionDetail(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }

    /**
     * 模拟更细题目
     * @param id 题目id
     * @param questionDetail 更新后的题目细节
     * @return
     */
    public static boolean updateQuestion(int id, String questionDetail) {
        QuestionInDB questionInDB = questions.get(id);
        int questionId = questionInDB.getId();
        questions.put(id, new QuestionInDB(questionId,
                questionDetail, EncryptUtils.getSHA(questionDetail)));
        //System.out.println("更新题目[id:" + id + ",content:"+questionDetail + "]");
        return true;
    }

    /**
     * 生成一个题目列表
     * @param docName 试题册名称
     * @param num 题目数量
     * @return
     */
    public static SrcDocValue generateQuestions(String docName, Integer num) {
        List<Integer> questionList = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            questionList.add(random.nextInt(questions.size()));
        }
        SrcDocValue srcDocValue = new SrcDocValue(docName, questionList);
        return srcDocValue;
    }
}
