package com.moodifyx;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class MoodSelectorUI extends JFrame {
    private  final MoodRepository repository = new MoodRepository();

    public MoodSelectorUI(){
        setTitle("ModifyX");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupIU();
        setVisible(true);
    }


    private void setupIU(){
        Set<String> moods = repository.getAllMoods();
        JComboBox<String> moodDropdown = new JComboBox<>(moods.toArray(new String[0]));
        JButton detectMoodBtn = new JButton("Recommend Music");

        detectMoodBtn.addActionListener(e ->{
            String mood = (String) moodDropdown.getSelectedItem();

            while(true){
                List<Song> songs = repository.getSongsForMood(mood);
                Song selected = songs.get(ThreadLocalRandom.current().nextInt(songs.size()));
                SongPlayer.play(selected.getFilePath());

                Object[] Options = {" Another Song", "Change Mood", "Stop Music and ext"};
                int choice = JOptionPane.showOptionDialog(
                    this,
                    "Now playing: " + selected.getTitle() + "\nMood : " + mood, "your Mood Music",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Options,
                    Options[0]
                );
                SongPlayer.stop();

                if (choice == JOptionPane.YES_NO_CANCEL_OPTION) {
                    continue;
                } else if (choice == JOptionPane.NO_OPTION) {
                    break;
                } else{
                    System.out.println(0);
   
                }  
            }
        });
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Your Mood: "));
        panel.add(moodDropdown);
        panel.add(detectMoodBtn);
        add(panel);


    }
    
}
