package com.moodifyx;

import java.util.*;

public class MoodRepository {
    private final Map<String,List<Song>> moodSongs = new HashMap<>();
    
    public MoodRepository(){
        initializeMoodSongs();
    }

        private void initializeMoodSongs(){
            Map<String, String[]> moodFiles = Map.of(
                "happy", new String[]{"happygirl.wav","happygirlie.wav"},
                "angry", new String[]{"happygirl.wav","happygirlie.wav"},
                "chill", new String[]{"happygirl.wav","happygirlie.wav"},
                "moody", new String[]{"happygirl.wav","happygirlie.wav"},
                "mysterious", new String[]{"happygirl.wav","happygirlie.wav"},
                "peacefull", new String[]{"happygirl.wav","happygirlie.wav"},
                "positive", new String[]{"happygirl.wav","happygirlie.wav"},
                "practice", new String[]{"happygirl.wav","happygirlie.wav"},
                "romantic", new String[]{"happygirl.wav","happygirlie.wav"}    
            );
            for(Map.Entry<String, String[]> entry: moodFiles.entrySet()){
                addMood(entry.getKey(), entry.getValue());
            }   
        }
        private String formatTitle(String fileName){
            String base = fileName.replace(".wav","");
            if (base.endsWith("girl"))
                 base = base.replace("girl","");
            else if (base.endsWith("girlie"))
                base = base.replace("girlie","");
                base = base.trim();
        return base.substring(0,1).toUpperCase() + base.substring(1)  + "Vibe";
    }


    private void addMood(String mood, String[] files){
        List<Song> songs = new ArrayList<>();
       for (String file : files) {
             songs.add(new Song(formatTitle(file), "./music/" + file));
        } 

        moodSongs.put(mood, songs); 

    }

    public List<Song> getSongsForMood(String mood){
        return moodSongs. getOrDefault(mood, List.of(new Song("Default Vibe", "music/happygirl.wav")));
    
   }

   public Set<String> getAllMoods(){
   return moodSongs.keySet();
   }
}
    
