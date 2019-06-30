package top.ninwoo.selfLearn;

import top.ninwoo.selfLearn.assist.QuestionBank;
import top.ninwoo.selfLearn.pojo.SrcDocValue;
import top.ninwoo.selfLearn.service.DocService;

import java.util.concurrent.*;

/**
 * 添加缓存机制，防止重复生成相同题目
 */
public class MultiThreadCacheDemo {
    // 生成Doc的线程池
    private static final ExecutorService generateDocPool
            = Executors.newFixedThreadPool(4);
    // 上传Doc的线程池
    private static final ExecutorService uploadDocPool
            = Executors.newFixedThreadPool(4);

    private static final CompletionService<String> docCompletionService
            = new ExecutorCompletionService<>(generateDocPool);
    private static final CompletionService<String> uploadCompletionService
            = new ExecutorCompletionService<>(uploadDocPool);


    private static class GenerateDocTask implements Callable<String> {
        // 文档名称
        private String docName;
        // 题目数量
        private int num;

        public GenerateDocTask(String docName, int num) {
            this.docName = docName;
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            System.out.println("Thread-" + Thread.currentThread().getId() + ":开始生成题目");
            SrcDocValue srcDocValue = QuestionBank.generateQuestions(docName, 60);
            System.out.println("Thread-" + Thread.currentThread().getId() + ":生成题目成功");
            return DocService.generateDoc(srcDocValue);
        }
    }

    private static class UploadDocTask implements Callable<String> {
        private String srcDoc;

        public UploadDocTask(String srcDoc) {
            this.srcDoc = srcDoc;
        }

        @Override
        public String call() throws Exception {
            return DocService.uploadDoc(srcDoc);
        }
    }

    public static void main(String[] args) {
        int page = 8;

        // 初始化
        QuestionBank.initQuestions(800);

        long start = System.currentTimeMillis();

        for (int i = 0; i < page; i++) {
            GenerateDocTask generateDocTask = new GenerateDocTask("test" + page, 60);
            docCompletionService.submit(generateDocTask);
        }
        for (int i = 0; i < page; i++) {
            try {
                Future<String> docSrc = docCompletionService.take();
                uploadCompletionService.submit(new UploadDocTask(docSrc.get()));
                System.out.println("本地文档已完成：" +docSrc.get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < page; i++) {
            try {
                Future<String> take = uploadCompletionService.take();
                System.out.println("已上传：" + take.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(System.currentTimeMillis() - start + "ms 完成!");


        //关闭线程池
        uploadDocPool.shutdown();
        generateDocPool.shutdown();
    }
}
