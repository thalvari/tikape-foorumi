package tikape.foorumi;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.foorumi.database.AiheDao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.KetjuDao;
import tikape.foorumi.database.ViestiDao;
import tikape.foorumi.domain.Aihe;
import tikape.foorumi.domain.Ketju;
import tikape.foorumi.domain.Viesti;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:foorumi.db");
//        database.setDebugMode(true);
        database.init();

        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        get("/:offset", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Aihe> aiheet = aiheDao.findAll(req.params(":offset"));
            map.put("aiheet", aiheet);
            map.put("offset", req.params(":offset"));
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/aihe/:id/:offset", (req, res) -> {
            HashMap map = new HashMap<>();
            System.out.println(req.params(":id"));
            System.out.println(req.params(":offset"));
            Aihe aihe = aiheDao.findOne(req.params(":id"));
            List<Ketju> ketjut = ketjuDao.findBy(aihe, req.params(":offset"));
            map.put("aihe", aihe);
            map.put("ketjut", ketjut);
            map.put("offset", req.params(":offset"));
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        get("/ketju/:id/:offset", (req, res) -> {
            HashMap map = new HashMap<>();
            Ketju ketju = ketjuDao.findOne(req.params(":id"));
            Aihe aihe = ketju.getKetjuAihe();
            List<Viesti> viestit = viestiDao.findBy(ketju,
                    req.params(":offset"));
            map.put("aihe", aihe);
            map.put("ketju", ketju);
            map.put("viestit", viestit);
            map.put("offset", req.params(":offset"));
            return new ModelAndView(map, "ketju");
        }, new ThymeleafTemplateEngine());

        get("/", (req, res) -> {
            res.redirect("/0");
            return "";
        });

        get("/aihe/:id", (req, res) -> {
            res.redirect("/aihe/" + req.params(":id") + "/0");
            return "";
        });

        get("/ketju/:id", (req, res) -> {
            res.redirect("/ketju/" + req.params(":id") + "/0");
            return "";
        });

        post("/uusiAihe", (req, res) -> {
            String nimi = req.queryParams("nimi").trim();
            if (!nimi.isEmpty()) {
                aiheDao.save(new Aihe(nimi));
            }
            res.redirect("/");
            return "";
        });

        post("/uusiKetju/:id", (req, res) -> {
            Aihe aihe = aiheDao.findOne(req.params(":id"));
            String otsikko = req.queryParams("otsikko").trim();
            String nimimerkki = req.queryParams("nimimerkki").trim();
            String sisalto = req.queryParams("sisalto").trim();
            if (!otsikko.isEmpty() && !nimimerkki.isEmpty()
                    && !sisalto.isEmpty()) {
                ketjuDao.save(new Ketju(aihe, otsikko));
                Ketju ketju = ketjuDao.findBy(aihe, "" + 0).get(0);
                viestiDao.save(new Viesti(ketju, nimimerkki, sisalto));
            }
            res.redirect("/aihe/" + req.params(":id"));
            return "";
        });

        post("/uusiViesti/:id", (req, res) -> {
            Ketju ketju = ketjuDao.findOne(req.params(":id"));
            String nimimerkki = req.queryParams("nimimerkki").trim();
            String sisalto = req.queryParams("sisalto").trim();
            if (!nimimerkki.isEmpty() && !sisalto.isEmpty()) {
                viestiDao.save(new Viesti(ketju, nimimerkki, sisalto));
            }
            res.redirect("/ketju/" + req.params(":id"));
            return "";
        });
    }
}
