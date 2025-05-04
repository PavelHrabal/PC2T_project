package Projekt;

import java.util.Map;

class StudentTelekomunikace extends Student {
    public StudentTelekomunikace(String jmeno, String prijmeni, int rokNarozeni) {
        super(jmeno, prijmeni, rokNarozeni);
    }

    @Override
    public void spustDovednost() {
        System.out.println("Morseův kód: " + naMorse(getJmeno() + " " + getPrijmeni()));
    }

    private String naMorse(String text) {
        Map<Character, String> morse = Map.ofEntries(
            Map.entry('A', ".-"), Map.entry('B', "-..."), Map.entry('C', "-.-."),
            Map.entry('D', "-.."), Map.entry('E', "."), Map.entry('F', "..-."),
            Map.entry('G', "--."), Map.entry('H', "...."), Map.entry('I', ".."),
            Map.entry('J', ".---"), Map.entry('K', "-.-"), Map.entry('L', ".-.."),
            Map.entry('M', "--"), Map.entry('N', "-."),
            Map.entry('O', "---"), Map.entry('P', ".--."), Map.entry('Q', "--.-"),
            Map.entry('R', ".-."),
            Map.entry('S', "..."), Map.entry('T', "-"), Map.entry('U', "..-"),
            Map.entry('V', "...-"), Map.entry('W', ".--"), Map.entry('X', "-..-"),
            Map.entry('Y', "-.--"), Map.entry('Z', "--.."),
            Map.entry('0', "-----"), Map.entry('1', ".----"), Map.entry('2', "..---"),
            Map.entry('3', "...--"), Map.entry('4', "....-"), Map.entry('5', "....."),
            Map.entry('6', "-...."), Map.entry('7', "--..."), Map.entry('8', "---.."),
            Map.entry('9', "----."),
            Map.entry(' ', "/")
        );
        StringBuilder sb = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            sb.append(morse.getOrDefault(c, " ")).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String getObor() {
        return "Telekomunikace";
    }
}
