package edu.umkc.team1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendMapper extends Mapper<Text, Text, Text, Text> {

    private final Text outKey = new Text();
    private final Text outValue = new Text();

    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
        String name = key.toString();
        // Split string into individual friends
        String[] friends = value.toString().split(" ");

        // Iterate though the friends
        for (int i = 0; i < friends.length; i++) {
            // Create a copy of the array
            ArrayList<String> others = new ArrayList<>(Arrays.asList(friends));

            // Remove a friend from the array.
            String friend = others.remove(i);

            // Create the (user, user) pair alphabetically
            if (name.compareTo(friend) <= 0) {
                outKey.set(name + friend);
            } else {
                outKey.set(friend + name);
            }

            // Write out each remaining friend.
            for (String other : others) {
                outValue.set(other);
                context.write(outKey, outValue);
            }
        }
    }
}
