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
import tikape.runko.domain.Viesti;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:foorumi.db");
        database.setDebugMode(true);
        database.init();
//        alustaTestiSisalto(database);

        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Aihe> aiheet = aiheDao.findAll();
            map.put("aiheet", aiheet);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/aihe/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Aihe aihe = aiheDao.findOne(req.params(":id"));
            List<Ketju> ketjut = ketjuDao.findBy(aihe);
            map.put("aihe", aihe);
            map.put("ketjut", ketjut);
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        get("/ketju/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Ketju ketju = ketjuDao.findOne(req.params(":id"));
            Aihe aihe = ketju.getKetjuAihe();
            List<Viesti> viestit = viestiDao.findBy(ketju);
            map.put("aihe", aihe);
            map.put("ketju", ketju);
            map.put("viestit", viestit);
            return new ModelAndView(map, "ketju");
        }, new ThymeleafTemplateEngine());
    }

    private static void alustaTestiSisalto(Database database)
            throws Exception {
        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        ArrayList<Aihe> aiheet = new ArrayList<>();
        List<Ketju> ketjut = new ArrayList<>();
        List<Viesti> viestit = new ArrayList<>();

        Aihe aihe1 = new Aihe(1, "Aihe 1", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe1);
        odota();
        Aihe aihe2 = new Aihe(2, "Aihe 2", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe2);
        odota();
        Aihe aihe3 = new Aihe(3, "Aihe 3", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe3);
        odota();
        Aihe aihe4 = new Aihe(4, "Aihe 4", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe4);
        odota();
        Aihe aihe5 = new Aihe(5, "Aihe 5", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe5);
        odota();
        Aihe aihe6 = new Aihe(6, "Aihe 6", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe6);
        odota();
        Aihe aihe7 = new Aihe(7, "Aihe 7", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe7);
        odota();
        Aihe aihe8 = new Aihe(8, "Aihe 8", new Timestamp(System.currentTimeMillis()));
        aiheet.add(aihe8);
        odota();

        Ketju ketju1 = new Ketju(1, aihe1, new Timestamp(System.currentTimeMillis()), "Ketju 1");
        ketjut.add(ketju1);
        odota();
        Ketju ketju2 = new Ketju(2, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 2");
        ketjut.add(ketju2);
        odota();
        Ketju ketju3 = new Ketju(3, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 3");
        ketjut.add(ketju3);
        odota();
        Ketju ketju4 = new Ketju(4, aihe4, new Timestamp(System.currentTimeMillis()), "Ketju 4");
        ketjut.add(ketju4);
        odota();
        Ketju ketju5 = new Ketju(5, aihe6, new Timestamp(System.currentTimeMillis()), "Ketju 5");
        ketjut.add(ketju5);
        odota();
        Ketju ketju6 = new Ketju(6, aihe6, new Timestamp(System.currentTimeMillis()), "Ketju 6");
        ketjut.add(ketju6);
        odota();
        Ketju ketju7 = new Ketju(7, aihe7, new Timestamp(System.currentTimeMillis()), "Ketju 7");
        ketjut.add(ketju7);
        odota();
        Ketju ketju8 = new Ketju(8, aihe8, new Timestamp(System.currentTimeMillis()), "Ketju 8");
        ketjut.add(ketju8);
        odota();

        Viesti viesti1 = new Viesti(1, ketju1, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti1");
        viestit.add(viesti1);
        odota();
        Viesti viesti2 = new Viesti(2, ketju1, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti2");
        viestit.add(viesti2);
        odota();
        Viesti viesti3 = new Viesti(3, ketju1, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti3");
        viestit.add(viesti3);
        odota();
        Viesti viesti4 = new Viesti(4, ketju1, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti4");
        viestit.add(viesti4);
        odota();
        Viesti viesti5 = new Viesti(5, ketju5, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti5");
        viestit.add(viesti5);
        odota();
        Viesti viesti6 = new Viesti(6, ketju5, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti6");
        viestit.add(viesti6);
        odota();
        Viesti viesti7 = new Viesti(7, ketju6, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti7");
        viestit.add(viesti7);
        odota();
        Viesti viesti8 = new Viesti(8, ketju7, new Timestamp(System.currentTimeMillis()), "Käyttäjä", "teksti8");
        viestit.add(viesti8);

        for (Aihe aihe : aiheet) {
            aiheDao.save(aihe);
        }
        for (Ketju ketju : ketjut) {
            ketjuDao.save(ketju);
        }
        for (Viesti viesti : viestit) {
            viestiDao.save(viesti);
        }
    }

    private static void odota() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (Exception e) {
        }
    }
}
