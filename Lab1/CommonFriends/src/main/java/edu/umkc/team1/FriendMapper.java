package edu.umkc.team1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FriendMapper extends Mapper <Text, Text, Text, Text> {

    private final Text outKey = new Text();
    private final Text outValue = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String name = key.toString();
        ArrayList<String> friends = new ArrayList<>(Arrays.asList(value.toString().split(" ")));

        for (int i = 0; i < friends.size(); i++) {
            ArrayList<String> others = (ArrayList<String>) friends.clone();
            String friend = others.remove(i);
            if (name.compareTo(friend) <= 0) {
                outKey.set(name + friend);
            } else {
                outKey.set(friend + name);
            }
            for (String other: others) {
                outValue.set(other);
                context.write(outKey, outValue);
            }
        }
    }
}
