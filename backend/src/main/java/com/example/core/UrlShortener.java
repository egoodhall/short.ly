package com.example.core;

import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UrlShortener {
    static HashMap<String, String> map = new HashMap<>();

    static String[] dict = new String[]{
            "Able", "About", "Above", "Accept", "Across", "Action", "Admit", "Adult", "Affect", "After",
            "Again", "Agency", "Agent", "Agree", "Ahead", "Allow", "Almost", "Alone", "Along", "Also",
            "Always", "Among", "Amount", "Animal", "Answer", "Anyone", "Appear", "Apply", "Area", "Argue",
            "Around", "Arrive", "Artist", "Assume", "Attack", "Author", "Avoid", "Away", "Baby", "Back",
            "Ball", "Bank", "Base", "Beat", "Become", "Before", "Begin", "Behind", "Best", "Better",
            "Beyond", "Bill", "Black", "Blood", "Blue", "Board", "Body", "Book", "Born", "Both",
            "Break", "Bring", "Budget", "Build", "Call", "Camera", "Cancer", "Card", "Care", "Career",
            "Carry", "Case", "Catch", "Cause", "Cell", "Center", "Chair", "Chance", "Change", "Charge",
            "Check", "Child", "Choice", "Choose", "Church", "City", "Civil", "Claim", "Class", "Clear",
            "Close", "Coach", "Cold", "Color", "Come", "Common", "Cost", "Could", "Couple", "Course",
            "Court", "Cover", "Create", "Crime", "Dark", "Data", "Deal", "Death", "Debate", "Decade",
            "Decide", "Deep", "Degree", "Design", "Detail", "Dinner", "Doctor", "Door", "Down", "Draw",
            "Dream", "Drive", "Drop", "Drug", "During", "Each", "Early", "East", "Easy", "Edge",
            "Effect", "Effort", "Eight", "Either", "Else", "Energy", "Enjoy", "Enough", "Enter", "Entire",
            "Even", "Event", "Ever", "Every", "Exist", "Expect", "Expert", "Face", "Fact", "Factor",
            "Fail", "Fall", "Family", "Fast", "Father", "Fear", "Feel", "Field", "Fight", "Figure",
            "Fill", "Film", "Final", "Find", "Fine", "Finger", "Finish", "Fire", "Firm", "First",
            "Fish", "Five", "Floor", "Focus", "Follow", "Food", "Foot", "Force", "Forget", "Form",
            "Former", "Four", "Free", "Friend", "From", "Front", "Full", "Fund", "Future", "Game",
            "Garden", "Girl", "Give", "Glass", "Goal", "Good", "Great", "Green", "Ground", "Group",
            "Grow", "Growth", "Guess", "Hair", "Half", "Hand", "Hang", "Happen", "Happy", "Hard",
            "Have", "Head", "Health", "Hear", "Heart", "Heat", "Heavy", "Help", "Here", "High",
            "Hold", "Home", "Hope", "Hotel", "Hour", "House", "Huge", "Human", "Idea", "Image",
            "Impact", "Indeed", "Inside", "Into", "Issue", "Item", "Itself", "Join", "Just", "Keep",
            "Kind", "Know", "Land", "Large", "Last", "Late", "Later", "Laugh", "Lawyer", "Lead",
            "Leader", "Learn", "Least", "Leave", "Left", "Legal", "Less", "Letter", "Level", "Life",
            "Light", "Like", "Likely", "Line", "List", "Listen", "Little", "Live", "Local", "Long",
            "Look", "Lose", "Loss", "Love", "Main", "Major", "Make", "Manage", "Many", "Market",
            "Matter", "Maybe", "Mean", "Media", "Meet", "Member", "Memory", "Method", "Middle", "Might",
            "Mind", "Minute", "Miss", "Model", "Modern", "Moment", "Money", "Month", "More", "Most",
            "Mother", "Mouth", "Move", "Movie", "Much", "Music", "Must", "Myself", "Name", "Nation",
            "Nature", "Near", "Nearly", "Need", "Never", "News", "Next", "Nice", "Night", "None",
            "North", "Note", "Notice", "Number", "Occur", "Offer", "Office", "Often", "Once", "Only",
            "Onto", "Open", "Option", "Order", "Other", "Others", "Over", "Owner", "Page", "Pain",
            "Paper", "Parent", "Part", "Party", "Pass", "Past", "Peace", "People", "Period", "Person",
            "Phone", "Pick", "Piece", "Place", "Plan", "Plant", "Play", "Player", "Point", "Police",
            "Policy", "Poor", "Power", "Pretty", "Price", "Prove", "Public", "Pull", "Push", "Quite",
            "Race", "Radio", "Raise", "Range", "Rate", "Rather", "Reach", "Read", "Ready", "Real",
            "Really", "Reason", "Recent", "Record", "Reduce", "Region", "Relate", "Remain", "Remove", "Report",
            "Rest", "Result", "Return", "Reveal", "Rich", "Right", "Rise", "Risk", "Road", "Rock",
            "Role", "Room", "Rule", "Safe", "Same", "Save", "Scene", "School", "Score", "Season",
            "Seat", "Second", "Seek", "Seem", "Sell", "Send", "Senior", "Sense", "Series", "Serve",
            "Seven", "Shake", "Share", "Short", "Should", "Show", "Side", "Sign", "Simple", "Simply",
            "Since", "Sing", "Single", "Sister", "Site", "Size", "Skill", "Skin", "Small", "Smile",
            "Social", "Some", "Song", "Soon", "Sort", "Sound", "Source", "South", "Space", "Speak",
            "Speech", "Spend", "Sport", "Spring", "Staff", "Stage", "Stand", "Star", "Start", "State",
            "Stay", "Step", "Still", "Stock", "Stop", "Store", "Story", "Street", "Strong", "Study",
            "Stuff", "Style", "Such", "Suffer", "Summer", "Sure", "System", "Table", "Take", "Talk",
            "Task", "Teach", "Team", "Tell", "Tend", "Term", "Test", "Than", "Thank", "That",
            "Their", "Them", "Then", "Theory", "There", "These", "They", "Thing", "Think", "Third",
            "This", "Those", "Though", "Threat", "Three", "Throw", "Thus", "Time", "Today", "Total",
            "Tough", "Toward", "Town", "Trade", "Travel", "Treat", "Tree", "Trial", "Trip", "True",
            "Truth", "Turn", "Type", "Under", "Unit", "Until", "Upon", "Value", "Very", "Victim",
            "View", "Visit", "Voice", "Vote", "Wait", "Walk", "Wall", "Want", "Watch", "Water",
            "Wear", "Week", "Weight", "Well", "West", "What", "When", "Where", "Which", "While",
            "White", "Whole", "Whom", "Whose", "Wide", "Wife", "Will", "Wind", "Window", "Wish",
            "With", "Within", "Woman", "Wonder", "Word", "Work", "Worker", "World", "Worry", "Would",
            "Write", "Writer", "Wrong", "Yard", "Yeah", "Year", "Young", "Your"
    };

    static int[] words = new int[]{0, dict.length/2, dict.length-1};

    private static String next() {
        words[2] = (words[2] - 1) % dict.length;
        words[1] = (words[1] + 3) % dict.length;
        words[0] = (words[0] + 9) % dict.length;

        String key = dict[words[0]] + dict[words[1]] + dict[words[2]];
        while (map.containsKey(key)) {
            words[2] = (words[2] - 9) % dict.length;
            words[1] = (words[1] + 6) % dict.length;
            words[0] = (words[0] + 3) % dict.length;
        }

        return dict[words[0]] + dict[words[1]] + dict[words[2]];
    }

    public static String shorten(String url) {
        String key = next();
        map.put(key, url);
        return key;
    }

    public static String lookup(String path) {
        return map.getOrDefault(path, null);
    }
}
