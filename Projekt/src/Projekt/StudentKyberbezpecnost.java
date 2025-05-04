package Projekt;

class StudentKyberbezpecnost extends Student {
    public StudentKyberbezpecnost(String jmeno, String prijmeni, int rokNarozeni) {
        super(jmeno, prijmeni, rokNarozeni);
    }

    @Override
    public void spustDovednost() {
        System.out.println("SHA-256 hash jména: " + hashuj(getJmeno() + " " + getPrijmeni()));
    }

    private String hashuj(String text) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            return "Chyba při hashování.";
        }
    }

    @Override
    public String getObor() {
        return "Kyberbezpečnost";
    }
}
