package tikape.runko;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import spark.ModelAndView;
import static spark.Spark.get;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AiheDao;
import tikape.runko.database.Database;
import tikape.runko.database.KetjuDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Ketju;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("org.sqlite.JDBC", "jdbc:sqlite:foorumi.db");
        database.setDebugMode(true);
        database.init();

        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        alustaTestausLauseet(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Aihe> aiheet = aiheDao.findAll();
            map.put("aiheet", aiheet);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/aihe/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Aihe aihe = aiheDao.findOne(":id");
            List<Ketju> ketjut = ketjuDao.findBy(aihe);
            map.put("aihe", aihe);
            map.put("ketjut", ketjut);
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());
    }

    private static void alustaTestausLauseet(Database database)
            throws Exception {
        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        ArrayList<Aihe> aiheet = new ArrayList<>();
        List<Ketju> ketjut = new ArrayList<>();

        Aihe aihe1 = new Aihe(0, "Aihe 1", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe1);
        odota();
        Aihe aihe2 = new Aihe(0, "Aihe 2", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe2);
        odota();
        Aihe aihe3 = new Aihe(0, "Aihe 3", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe3);
        odota();
        Aihe aihe4 = new Aihe(0, "Aihe 4", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe4);
        odota();
        Aihe aihe5 = new Aihe(0, "Aihe 5", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe5);
        odota();
        Aihe aihe6 = new Aihe(0, "Aihe 6", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe6);
        odota();
        Aihe aihe7 = new Aihe(0, "Aihe 7", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe7);
        odota();
        Aihe aihe8 = new Aihe(0, "Aihe 8", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe8);

        Ketju ketju1 = new Ketju(0, aihe1, new Timestamp(System.currentTimeMillis()), "Ketju 1");
        ketjut.add(ketju1);
        odota();
        Ketju ketju2 = new Ketju(0, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 2");
        ketjut.add(ketju2);
        odota();
        Ketju ketju3 = new Ketju(0, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 3");
        ketjut.add(ketju3);
        odota();
        Ketju ketju4 = new Ketju(0, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 4");
        ketjut.add(ketju4);
        odota();
        Ketju ketju5 = new Ketju(0, aihe6, new Timestamp(System.currentTimeMillis()), "Ketju 5");
        ketjut.add(ketju5);
        odota();
        Ketju ketju6 = new Ketju(0, aihe6, new Timestamp(System.currentTimeMillis()), "Ketju 6");
        ketjut.add(ketju6);
        odota();
        Ketju ketju7 = new Ketju(0, aihe7, new Timestamp(System.currentTimeMillis()), "Ketju 7");
        ketjut.add(ketju7);
        odota();
        Ketju ketju8 = new Ketju(0, aihe8, new Timestamp(System.currentTimeMillis()), "Ketju 8");
        ketjut.add(ketju8);

        for (Aihe aihe : aiheet) {
            aiheDao.save(aihe);
        }
        for (Ketju ketju : ketjut) {
            ketjuDao.save(ketju);
        }
    }

    private static void odota() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (Exception e) {
        }
    }

//    private static List<String> testausLauseet() {
//        ArrayList<String> lista = new ArrayList<>();
//
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('A');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('B');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('C');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('D');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('E');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('F');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('G');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('H');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('I');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('J');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('K');");
//        lista.add("INSERT INTO Aihe (nimi) VALUES ('L');");
//
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (1, 'a');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (1, 'b');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (1, 'c');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (1, 'd');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (1, 'e');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (6, 'f');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (6, 'g');");
//        lista.add("INSERT INTO Ketju (aihe, otsikko) VALUES (8, 'h');");
//
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (1, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 1, 'käyttäjä', 'tekstiä', 0)");
//
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (1, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 2, 'käyttäjä', 'tekstiä', 0)");
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (Exception e) {
//        }
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (3, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 3, 'käyttäjä', 'tekstiä', 0)");
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (Exception e) {
//        }
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (3, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 4, 'käyttäjä', 'tekstiä', 0)");
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (Exception e) {
//        }
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (6, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 5, 'käyttäjä', 'tekstiä', 0)");
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (Exception e) {
//        }
//        lista.add("INSERT INTO Viesti (ketju, aika, id, nimimerkki, sisalto, "
//                + "vastausId) VALUES (8, '"
//                + new Timestamp(System.currentTimeMillis()).toString()
//                + "', 6, 'käyttäjä', 'tekstiä', 0)");
//
//        return lista;
//    }
}
