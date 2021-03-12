package pl.lodz.p.zzpj.gitignore.webapi;

import pl.lodz.p.zzpj.gitignore.webapi.model.TechnologyObject;

import java.util.HashMap;

public interface GetDataInterface {
    HashMap<String, TechnologyObject> getTechnologyMap();
}
