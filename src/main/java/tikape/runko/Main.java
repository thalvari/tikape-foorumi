package tikape.runko;

import java.util.HashMap;
import java.util.List;
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
        Database database = new Database("org.sqlite.JDBC", "jdbc:sqlite:foorumi.db");
        database.init();

        AiheDao aiheDao = new AiheDao(database);
        KetjuDao ketjuDao = new KetjuDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Aihe> aiheet = aiheDao.findAll();
            for (Aihe aihe : aiheet) {
                Viesti viesti = viestiDao.findViimeisinAihe(aihe.getId());
                if (viesti != null) {
                    aihe.setViimeisin(viesti.getAika());
                }
            }
            map.put("aiheet", aiheet);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/aihe/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Ketju> ketjut = ketjuDao.findAll(Integer.parseInt(
                    req.params("id")));
            for (Ketju ketju : ketjut) {
                Viesti viesti = viestiDao.findViimeisinKetju(ketju.getId());
                if (viesti != null) {
                    ketju.setViimeisin(viesti.getAika());
                }
            }
            map.put("ketjut", ketjut);
            map.put("aihe", aiheDao.findOne(Integer.parseInt(
                    req.params("id"))));
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

//        OpiskelijaDao opiskelijaDao = new OpiskelijaDao(database);
//
//        get("/", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("viesti", "tervehdys");
//
//            return new ModelAndView(map, "index");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelijat", opiskelijaDao.findAll());
//
//            return new ModelAndView(map, "opiskelijat");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(
//                    Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());
    }
}
