package com.tensor.api.org.service.spark.statistics;

import com.tensor.api.org.enpity.News;
import com.tensor.api.org.service.spark.base.BaseSparkServiceImpl;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.data.annotation.Transient;
import scala.Tuple2;

import java.util.*;

/**
 * 统计功能实现类
 *
 * @author LightDance
 * @date 2019/4/3
 */
public class StatisticServiceImpl extends BaseSparkServiceImpl implements StatisticsService {
    /**
     * 词频统计
     * TODO 未使用分词工具，目前仅统计单词
     *
     * @param article 文章
     * @return 文章中word列表
     */
    @Override
    public JavaPairRDD<String, Integer> getWordFrequency(News article) {
//        String content = article.getText();
        JavaRDD<String> content = sc.parallelize(Arrays.asList(article.getText()));
        JavaPairRDD<String , Integer> result = content
                .flatMap((FlatMapFunction<String, String>) s -> {
            //以换行、空格、逗号句号分开
            String[] words = s.split("[ .\n,\\s]");
            return Arrays.asList(words).iterator();
        })
                .mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s , 1))
                .reduceByKey((Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2);
//        List<Tuple2<String, Integer>> output = result.collect();
//        debug
//        for (Tuple2<String , Integer> r:output) {
//            System.out.println(r._1 + " -- " + r._2);
//        }
        return result;
    }
}
