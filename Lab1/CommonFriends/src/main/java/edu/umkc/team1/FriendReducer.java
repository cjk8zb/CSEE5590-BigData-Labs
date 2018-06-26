package edu.umkc.team1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendReducer extends Reducer<Text, Text, Text, Text> {

    private final Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        // Create storage
        Set<String> foundFriends = new HashSet<>();
        List<String> commonList = new ArrayList<>();

        // Iterate through each friend
        values.forEach(text -> {
            String friend = text.toString();

            // If the friend has already been found, add it to the common list
            if (foundFriends.contains(friend)) {
                commonList.add(friend);
            } else {
                foundFriends.add(friend);
            }
        });

        // Sort the list
        commonList.sort(String::compareToIgnoreCase);

        // Concatenate each friend to a single string.
        String friends = commonList.stream().reduce(String::concat).get();
        outValue.set(friends);

        // Write the key value pair
        context.write(key, outValue);
    }
}
