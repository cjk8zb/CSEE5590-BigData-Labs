package edu.umkc.team1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

public class FriendReducer extends Reducer<Text, Text, Text, Text> {

    private final Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        super.reduce(key, values, context);
//        Set<String> set = new HashSet<>();
//        List<String> list = new ArrayList<>();
//
//        values.forEach(s -> {
//            String string = s.toString();
//            if (set.contains(string)) {
//                list.add(string);
//            } else {
//                set.add(string);
//            }
//        });
//
//        list.sort(String::compareToIgnoreCase);
//
//        outValue.set(list.stream().reduce(String::concat).get());
//
////        context.write(key, outValue);
    }
}
